package org.example.service.management.account.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.common.domain.management.SysUser;
import org.example.common.domain.management.account.SysAccountFunctionRela;
import org.example.common.domain.monitor.LogAccountChangePassword;
import org.example.common.domain.monitor.LogAccountLock;
import org.example.common.domain.management.account.SysAccount;
import org.example.common.entity.AccountBo;
import org.example.common.enums.AccountStatusEnum;
import org.example.common.exception.AuthException;
import org.example.common.utils.AesUtil;
import org.example.dao.management.account.SysAccountDao;
import org.example.common.enums.Constants;
import org.example.dto.LoginParams;
import org.example.dto.ModifyPasswordParams;
import org.example.dto.SavePermParams;
import org.example.service.management.account.ISysAccountFunctionRelaService;
import org.example.service.management.account.ISysAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.utils.EncryptUtils;
import org.example.service.management.ISysSettingService;
import org.example.service.management.ISysUserService;
import org.example.service.management.group.impl.SysGroupMembersServiceImpl;
import org.example.service.mointor.impl.LogAccountChangePasswordServiceImpl;
import org.example.service.mointor.impl.LogAccountLockServiceImpl;
import org.example.utils.ValidateUtil;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.collect.Lists;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;

import java.io.InputStream;
import org.example.common.utils.PageUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户账号信息表 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
@Slf4j
public class SysAccountServiceImpl extends ServiceImpl<SysAccountDao, SysAccount> implements ISysAccountService {

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysSettingService settingService;

    @Resource
    private LogAccountLockServiceImpl accountLockService;

    @Resource
    private LogAccountChangePasswordServiceImpl accountChangePasswordService;

    @Resource
    private SysAccountRoleRelaServiceImpl accountRoleRelaService;

    @Resource
    private ISysAccountFunctionRelaService accountPrivateFunctionService;

    @Resource
    private SysGroupMembersServiceImpl groupMembersService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePerm(SavePermParams params) {
        String accountId = params.getAccountId();
        List<String> checkRoles = params.getCheckRoles();
        List<String> checkGroups = params.getCheckGroups();
        List<String> privateMenus = params.getPrivateMenus();


        accountRoleRelaService.saveAccountRoleRela(accountId, checkRoles);
        groupMembersService.saveGroupMembers(accountId, checkGroups);
        accountPrivateFunctionService.removeByAccountId(accountId);
        if (privateMenus != null) {
            List<SysAccountFunctionRela> relaList = privateMenus.stream().map(menuId -> {
                SysAccountFunctionRela rela = new SysAccountFunctionRela();
                rela.setAccountId(accountId);
                rela.setFunId(menuId);
                rela.setCreatedBy("admin");
                rela.setCreateTime(LocalDateTime.now());
                return rela;
            }).collect(Collectors.toList());
            accountPrivateFunctionService.saveBatch(relaList);
        }
    }

    @Override
    public void logicDeleteByUserId(String UserId) {
        LambdaUpdateWrapper<SysAccount> wrapper = new LambdaUpdateWrapper<SysAccount>()
                .eq(SysAccount::getUserId, UserId)
                .set(SysAccount::getStatus, "2");
        this.update(wrapper);
    }

    public SysAccount addSysAccountBySysUser(SysUser user) {
        SysAccount account = new SysAccount();
        account.setAppId(user.getAppId());
        account.setAccountName(user.getAccountName());
        account.setStatus(user.getStatus());
        account.setUserId(user.getUserId());
        account.setCreateTime(LocalDateTime.now());
        account.setCreatedBy(user.getUpdatedBy());
        // 默认密码
        final String passwordEncrypt = settingService.getPasswordEncryptType(account.getAppId());
        account.setLoginPass(EncryptUtils.passwordEncrypt(Constants.INITIAL_PASSWORD, passwordEncrypt, Constants.AES_KEY));
        // 初始密码标识
        account.setInitialPasswordFlag("1");
        account.setUpdatedBy(user.getUpdatedBy());
        account.setUpdatedTime(LocalDateTime.now());
        this.save(account);
        return account;
    }

    private String getAccountNameByUserName(String userName){
        LambdaQueryWrapper<SysAccount> query = new LambdaQueryWrapper<SysAccount>()
                .eq(SysAccount::getAccountName, userName);
        long num = this.count(query);
        return num > 0 ? getAccountNameByUserName(userName + "_" + num) : userName;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyPass(ModifyPasswordParams params) {
        //对传入的密码进行aes解密，再用MD5加密，然后与库中进行比对
        ValidateUtil.validate(params);

        String accountId = params.getAccountId();
        String oldPass = params.getOldPass();
        String newPass = params.getNewPass();

        try{
            SysAccount account = getById(accountId);
            String pass = account.getLoginPass();
            oldPass = AesUtil.decrypt(oldPass, Constants.AES_KEY);
            byte[] md5Str = DigestUtils.getMd5Digest().digest(oldPass.getBytes(StandardCharsets.UTF_8));
            oldPass =  new BigInteger(1, md5Str).toString(16);
            // 比较
            if (StringUtils.equals(oldPass, pass)) {
                newPass = AesUtil.decrypt(newPass, Constants.AES_KEY);
                byte[] md5Str1 = DigestUtils.getMd5Digest().digest(newPass.getBytes(StandardCharsets.UTF_8));
                newPass =  new BigInteger(1, md5Str1).toString(16);
                account.setLoginPass(newPass);
                updateById(account);
                LogAccountChangePassword accountChangePassword = new LogAccountChangePassword();
                accountChangePassword.setAccountId(accountId);
                accountChangePassword.setOldPass(oldPass);
                accountChangePassword.setCreateTime(LocalDateTime.now());
                accountChangePasswordService.save(accountChangePassword);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 密码比对
     * @param password 输入的密码
     * @param rightPass 正确的密码
     * @param appId
     * @return
     */
    private boolean isPasswordEqual(String password, String rightPass, String appId) {
        // 加密类型
        String passwordEncryptType = settingService.getPasswordEncryptType(appId);
        if (passwordEncryptType != null) {
            switch (passwordEncryptType) {
                case "sha256":
                    password = DigestUtils.sha256Hex(password);
                    break;
                case "md5":
                    break;
                case "aes":
                    try {
                        //对传入的密码进行aes解密，再用MD5加密，然后与库中进行比对
                        password = AesUtil.decrypt(password, Constants.AES_KEY);
                        byte[] md5Str = DigestUtils.getMd5Digest().digest(password.getBytes(StandardCharsets.UTF_8));
                        password = new BigInteger(1, md5Str).toString(16);
                        break;
                    } catch (Exception e) {
                        log.error("aes加密出错", e);
                    }
                    break;
                default: log.error("密码加密类型错误");
            }
        }
        return StringUtils.equals(rightPass, password);
    }
    @Override
    @Transactional
    public AccountBo login(LoginParams loginParams) {
        String appId = loginParams.getAppId();
        String loginName = loginParams.getUsername();
        String loginPass = loginParams.getPassword();
        Boolean singleLogin = loginParams.getSingleLogin();

        // 查询参数
        LambdaQueryWrapper<SysAccount> queryWrapper = new LambdaQueryWrapper<SysAccount>()
                .eq(SysAccount::getAccountName, loginName)
                //没有被逻辑删除
                .ne(SysAccount::getStatus, AccountStatusEnum.DELETED.getValue())
                .eq(SysAccount::getAppId, appId);
        List<SysAccount> accountList = list(queryWrapper);

        //从sys_setting表中获取登录失败最大次数
        int loginFailMaxTimes = Integer.parseInt(settingService.getVal(Constants.LOGIN_FAIL_MAX_TIME, appId));

        int autoUnlockAccountMins = Integer.parseInt(settingService.getVal(Constants.AUTO_UNLOCK_ACCOUNT_MINS, appId));

        if (accountList.size() > 0) {
            SysAccount account = accountList.get(0);

            // 密码是否正确
            boolean isSuccess = this.isPasswordEqual(loginPass, account.getLoginPass(), appId);

            // 登录失败次数
            int loginFailTimes = account.getLoginFailTimes() == null ? 0 : account.getLoginFailTimes();

            // 账号被锁定
            if (StringUtils.equals(AccountStatusEnum.LOCKED.getValue(), account.getInitialPasswordFlag())) {
                AccountBo accBo = new AccountBo();
                //如果密码比对相同或者是单点登录
                if (isSuccess || singleLogin) {
                    accBo.setAccount(account);
                    return accBo;
                } else {
                    account.setLoginFailTimes(-1);
                    accBo.setAccount(account);
                    return accBo;
                }
            // 正常状态
            } else {
                //如果密码比对相同或者是单点登录
                if ((loginFailMaxTimes == -1 || loginFailTimes < loginFailMaxTimes) && isSuccess
                    ||singleLogin)   {
                    AccountBo accountBo = new AccountBo();
                    SysUser user = userService.getById(account.getUserId());
                    accountBo.setAccount(account);
                    accountBo.setUser(user);
                    account.setLoginFailTimes(0);
                    account.setLastLoginTime(LocalDateTime.now());
                    updateById(account);
                    return accountBo;
                } else {
                    // 失败次数到达最大
                    if (loginFailMaxTimes > 0 && loginFailTimes >= loginFailMaxTimes) {
                        Calendar thisTimeStr = Calendar.getInstance();
                        Calendar lastTimeStr = Calendar.getInstance();
                        thisTimeStr.setTime(new Date());
                        lastTimeStr.setTime(Date.from(account.getLastLoginTime().atZone(ZoneId.systemDefault()).toInstant()));
                        Long timeOne = thisTimeStr.getTimeInMillis();
                        Long timeTwo = lastTimeStr.getTimeInMillis();
                        //转化minute
                        long minute = (timeOne - timeTwo) / (1000 * 60);
                        if (minute > autoUnlockAccountMins) {
                            account.setStatus("0");//解锁
                            updateById(account);
                            login(loginParams);//重新验证
                        } else {
                            throw new AuthException("登录失败达到了最大次数限制，账号被锁定，请在" + (autoUnlockAccountMins - minute) + "分钟后重试");
                        }
                    // 登录失败未到达最大次数
                    } else {
                        loginFailTimes = loginFailTimes + 1;
                        account.setLoginFailTimes(loginFailTimes);
                        account.setLastLoginTime(LocalDateTime.now());
                        //锁定
                        if (loginFailMaxTimes > 0 && loginFailTimes >= loginFailMaxTimes) {
                            account.setStatus("1");
                        }
                        if (loginFailMaxTimes == -1) {
                            account.setLoginFailTimes(0);
                        }
                        LambdaUpdateWrapper<SysAccount> wrapper = new LambdaUpdateWrapper<SysAccount>()
                                .set(SysAccount::getLoginFailTimes, account.getLoginFailTimes())
                                .set(SysAccount::getLastLoginTime, account.getLastLoginTime())
                                .set(SysAccount::getStatus, account.getStatus());
                        this.update(wrapper);

                        AccountBo accBo = new AccountBo();
                        SysAccount acc = new SysAccount();
                        acc.setLoginFailTimes(loginFailMaxTimes - loginFailTimes);
                        accBo.setAccount(acc);
                        return accBo;
                    }
                }
            }
        }
        return null;
    }


    @Override
    public void resetPassword(String accountId) {
        SysAccount account = this.getById(accountId);
//        final String passwordEncrypt = settingService.getPasswordEncryptType(account.getAppId());
        String passwordEncrypt = "md5";
        String password = EncryptUtils.passwordEncrypt(Constants.INITIAL_PASSWORD, passwordEncrypt, Constants.AES_KEY);

        LambdaUpdateWrapper<SysAccount> updateWrapper = new LambdaUpdateWrapper<SysAccount>()
                .eq(SysAccount::getAccountId, accountId)
                .set(SysAccount::getLoginPass, password)
                .set(SysAccount::getInitialPasswordFlag, "1");
        this.update(updateWrapper);

        LogAccountChangePassword accountChangePassword = new LogAccountChangePassword();
        accountChangePassword.setAccountId(accountId);
        accountChangePassword.setOldPass(account.getLoginPass());
        accountChangePassword.setCreateTime(LocalDateTime.now());
        accountChangePasswordService.save(accountChangePassword);
    }

    @Override
    @Transactional
    public void unlock(String accountId) {
        LambdaUpdateWrapper<SysAccount> updateWrapper = new LambdaUpdateWrapper<SysAccount>()
                .eq(SysAccount::getAccountId, accountId)
                .set(SysAccount::getStatus, "0");
        this.update(updateWrapper);

        LogAccountLock accountLock = new LogAccountLock();
        accountLock.setAccountId(accountId);
        accountLock.setLockType(1);
        accountLock.setCreateTime(LocalDateTime.now());
        accountLockService.save(accountLock);
    }

    @Override
    @Transactional
    public void lock(String accountId) {
        LambdaUpdateWrapper<SysAccount> updateWrapper = new LambdaUpdateWrapper<SysAccount>()
                .eq(SysAccount::getAccountId, accountId)
                .set(SysAccount::getStatus, "1");
        this.update(updateWrapper);

        LogAccountLock accountLock = new LogAccountLock();
        accountLock.setAccountId(accountId);
        accountLock.setLockType(0);
        accountLock.setCreateTime(LocalDateTime.now());
        accountLockService.save(accountLock);
    }


    @Transactional
    public void unlockByUserId(String userId) {
        LambdaUpdateWrapper<SysAccount> updateWrapper = new LambdaUpdateWrapper<SysAccount>()
                .eq(SysAccount::getUserId, userId)
                .set(SysAccount::getStatus, "0");
        this.update(updateWrapper);

        LogAccountLock accountLock = new LogAccountLock();
        accountLock.setAccountId("userId: " + userId);
        accountLock.setLockType(1);
        accountLock.setCreateTime(LocalDateTime.now());
        accountLockService.save(accountLock);
    }


    @Transactional
    public void lockByUserId(String userId) {
        LambdaUpdateWrapper<SysAccount> updateWrapper = new LambdaUpdateWrapper<SysAccount>()
                .eq(SysAccount::getUserId, userId)
                .set(SysAccount::getStatus, "1");
        this.update(updateWrapper);

        LogAccountLock accountLock = new LogAccountLock();
        accountLock.setAccountId("userId: " + userId);
        accountLock.setLockType(0);
        accountLock.setCreateTime(LocalDateTime.now());
        accountLockService.save(accountLock);
    }

    @Override
    public void saveByParam(SysAccount obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysAccount obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        LambdaUpdateWrapper<SysAccount> wrapper = new LambdaUpdateWrapper<SysAccount>()
                .eq(SysAccount::getUserId, params.get("userId"))
                .set(SysAccount::getStatus, "2");
        this.update(wrapper);
    }

    @Override
    public List<SysAccount> selectBy(Map<String, String> params) {
        QueryWrapper<SysAccount> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysAccount> selectPage(Map<String, String> params) {
        Page<SysAccount> page = PageUtils.pageHandler(params);
        QueryWrapper<SysAccount> query = getQuery(params);
        IPage<SysAccount> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysAccount> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysAccount> page = PageUtils.pageHandler(params);
        QueryWrapper<SysAccount> query = getQuery(params);
        IPage<SysAccount> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysAccount> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysAccount> page = PageUtils.pageHandler(params);
        IPage<SysAccount> result = this.baseMapper.selpageCustomSqlByMap(page, params);
        return result;
    }

    /**
     * 下载excel模板
     *
     * @param response HttpServletResponse
     * @param request  HttpServletRequest
     * @return: void
    */
    @Override
    public void downloadExcelTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception{
        List<SysAccount> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysAccount"), SysAccount.class, data);
        String fileName = String.format("SysAccount_%d.xls", System.currentTimeMillis());
        response.setHeader("Content-Disposition", "attachment;Filename="+ fileName);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * 导入数据
     * @param file
     * @throws Exception
     */
    @Override
    public void uploadExcel(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ImportParams params = new ImportParams();
        // bean 导入
        List<SysAccount> dataList = new ExcelImportService().importExcelByIs(inputStream, SysAccount.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysAccount> query = new QueryWrapper<>();
        List<SysAccount> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysAccount"),
        SysAccount.class, data);
        String fileName = String.format("SysAccount_%d.xls", System.currentTimeMillis());
        response.setHeader("Content-Disposition", "attachment;Filename="+ fileName);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * 定义数据查询条件
     * @param params
     * @return
     */
    private  QueryWrapper<SysAccount> getQuery(Map<String, String> params){
        QueryWrapper<SysAccount> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("accountId".equals(entry.getKey())){
                query.eq("account_id",entry.getValue());
            }
            if("userId".equals(entry.getKey())){
                query.eq("user_id",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("accountName".equals(entry.getKey())){
                query.eq("account_name",entry.getValue());
            }
            if("loginName".equals(entry.getKey())){
                query.eq("login_name",entry.getValue());
            }
            if("loginPass".equals(entry.getKey())){
                query.eq("login_pass",entry.getValue());
            }
            if("avatar".equals(entry.getKey())){
                query.eq("avatar",entry.getValue());
            }
            if("allowWeek".equals(entry.getKey())){
                query.eq("allow_week",entry.getValue());
            }
            if("allowBeginTime".equals(entry.getKey())){
                query.eq("allow_begin_time",entry.getValue());
            }
            if("allowEndTime".equals(entry.getKey())){
                query.eq("allow_end_time",entry.getValue());
            }
            if("status".equals(entry.getKey())){
                query.eq("status",entry.getValue());
            }
            if("description".equals(entry.getKey())){
                query.eq("description",entry.getValue());
            }
            if("startTime".equals(entry.getKey())){
                query.eq("start_time",entry.getValue());
            }
            if("endTime".equals(entry.getKey())){
                query.eq("end_time",entry.getValue());
            }
            if("createdBy".equals(entry.getKey())){
                query.eq("created_by",entry.getValue());
            }
            if("createTime".equals(entry.getKey())){
                query.eq("create_time",entry.getValue());
            }
            if("updatedBy".equals(entry.getKey())){
                query.eq("updated_by",entry.getValue());
            }
            if("updatedTime".equals(entry.getKey())){
                query.eq("updated_time",entry.getValue());
            }
            if("loginFailTimes".equals(entry.getKey())){
                query.eq("login_fail_times",entry.getValue());
            }
            if("lastLoginTime".equals(entry.getKey())){
                query.eq("last_login_time",entry.getValue());
            }
            if("initialPasswordFlag".equals(entry.getKey())){
                query.eq("initial_password_flag",entry.getValue());
            }
            if("phone".equals(entry.getKey())){
                query.eq("phone",entry.getValue());
            }
            if("customAccountId".equals(entry.getKey())){
                query.eq("custom_account_id",entry.getValue());
            }
        }
        return  query;
    }
}
