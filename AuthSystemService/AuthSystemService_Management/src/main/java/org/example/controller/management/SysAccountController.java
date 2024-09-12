package org.example.controller.management;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.common.annotation.OperationLog;
import org.example.common.domain.*;
import org.example.common.domain.management.account.SysAccount;
import org.example.common.domain.management.group.SysGroup;
import org.example.common.domain.management.role.SysRole;
import org.example.common.domain.management.SysUser;
import org.example.common.entity.AccountBo;
import org.example.common.enums.Constants;
import org.example.common.jwt.JwtAccessToken;
import org.example.common.jwt.TokenProvider;
import org.example.common.jwt.TokenResponse;
import org.example.common.utils.AesUtil;
import org.example.dto.*;
import org.example.service.*;
import org.example.service.management.*;
import org.example.service.management.account.ISysAccountService;
import org.example.service.management.group.ISysGroupService;
import org.example.service.management.role.ISysRoleService;
import org.example.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户账号信息表 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@RestController
@Tag(name = "用户账号信息表服务")
@RequestMapping("/sysaccount")
@Slf4j
public class SysAccountController {

    @Resource
    private ISysUserService userService;

    @Autowired
    private ISysAccountService service;

    @Resource
    private ISysSettingService settingService;

    @Resource
    private TokenProvider tokenProvider;

    @Resource
    private ISysRoleService roleService;

    @Resource
    private ISysGroupService groupService;

    @Resource
    private ISysRegionService regionService;

    @Resource
    private ISysVerificationCodeCacheService verificationCodeCacheService;

    @Resource
    private ISysCompanyService companyService;

    @Resource
    private ISysOrgService orgService;

    @OperationLog(value = "保存账号权限", type = 1, sensitive = 4)
    @PostMapping("/savePerm")
    public SimpleResponse savePerm(@RequestBody SavePermParams params) {
//        //用户账号同一个界面
//        if (switchConfig.getAccountopen() == 1) {
//            account = accountService.findByUserId(account.getAccountId()).get(0);
//            log.info("account={},checkRoles={},checkGroups={},privateMenus={}", account, checkRoles, checkGroups, privateMenus);
//            accountService.savePerm(account, checkRoles, checkGroups, privateMenus);
//        } else {
//            log.info("account={},checkRoles={},checkGroups={},privateMenus={}", account, checkRoles, checkGroups, privateMenus);
//
//        }
        ValidateUtil.validate(params);
        service.savePerm(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @OperationLog("查询账号对应组信息")
    @PostMapping("/listGroupByAccountId")
    public SimpleResponse listGroupByAccountId(@RequestBody ListGroupByAccountIdParams params) {
        ValidateUtil.validate(params);
        String accountId = params.getAccountId();
        String appId = params.getAppId();

        LambdaQueryWrapper<SysGroup> queryWrapper = new LambdaQueryWrapper<SysGroup>()
                .eq(SysGroup::getAppId, appId);
        List<SysGroup> groups = groupService.list(queryWrapper);
        Map<String, Object> info = new HashMap<>();
        info.put("groups", groups);
        List<SysGroup> checkGroups = groupService.listGroupByAccountId(accountId);
        info.put("checkGroups", checkGroups);
        return new SimpleResponse.SimpleResponseBuilder().success(info).build();
    }

    @OperationLog("查询账号对应角色信息")
    @PostMapping("/getRoleByAccountId")
    public SimpleResponse getRoleByAccountId(@RequestBody GetRoleByAccountIdParams params) {
        ValidateUtil.validate(params);
        String accountId = params.getAccountId();
        String appId = params.getAppId();
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getAppId, appId);
        List<SysRole> roles = roleService.list(queryWrapper);
        Map<String, Object> info = new HashMap<>();
        info.put("roles", roles);
        List<SysRole> checkRoles = roleService.getRolesByAccountId(accountId, appId);
        info.put("checkRoles", checkRoles);
        return new SimpleResponse.SimpleResponseBuilder().success(info).build();
    }

    @OperationLog("查询账号对应的用户信息")
    @GetMapping("/getUserAndAccountByAccountId/{id}")
    public SimpleResponse getUserAndAccountByAccountId(@PathVariable String id) {
        if (StringUtils.isNotBlank(id)) {
            //用户账号同一个界面
//            if (switchConfig.getAccountopen() == 1) {
//                SysUser user = userService.getById(id);
//                List<SysAccount> accountList = accountService.findByUserId(user.getUserId());
//                if (accountList.size() != 0) {
//                    Map<String, Object> info = new HashMap<>();
//                    info.put("account", accountList.get(0));
//                    info.put("user", user);
//                    return createResponse().withData(info).build();
//                }
//            } else {
//
//
//            }
            SysAccount account = service.getById(id);
            if (account != null) {
                SysUser user = userService.getById(account.getUserId());
                Map<String, Object> info = new HashMap<>();
                info.put("account", account);
                info.put("user", user);
                return new SimpleResponse.SimpleResponseBuilder().success(info).build();
            }
            return new SimpleResponse.SimpleResponseBuilder().failure("无对应的帐号信息").build();
        } else {
            throw new IllegalArgumentException("{id} is blank");
        }
    }


    @OperationLog(value = "修改账号密码", type = 3, sensitive = 3)
    @PostMapping("/modify-password")
    public SimpleResponse modifyPassword(@RequestBody ModifyPasswordParams params) {
        service.modifyPass(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @OperationLog(value = "验证码账号登陆", type = 6, sensitive = 4)
    @PostMapping("/anon/loginWithCode")
    @ResponseBody
    @Operation(description = "验证码账号登陆")
    public SimpleResponse loginWithCode(@RequestBody LoginParams params) {
        // 参数校验
        ValidateUtil.validate(params);
        // 获取参数
        String uuid = params.getUuid();
        String s2 = params.getVerify().toLowerCase();
        // s1 系统生产的验证码
        String s1 = "";
        SysVerificationCodeCache verificationCodeCache = verificationCodeCacheService.getByUUID(uuid);
        if (verificationCodeCache != null) {
            s1 = verificationCodeCache.getCode().toLowerCase();
        }
        // 判断验证码
        if (verificationCodeCache == null || !StringUtils.equals(s1, s2)) {
            return new SimpleResponse.SimpleResponseBuilder().failure("验证码不正确！").build();
        }
        // 非单点登录
        params.setSingleLogin(false);
        return this.login(params);
    }

    @OperationLog(value = "账号登陆", type = 6, sensitive = 4)
    @PostMapping("/anon/login")
    public SimpleResponse login(@RequestBody LoginParams loginParams){
        //对前台加密的账号进行解密
        try {
            String username = AesUtil.decrypt(loginParams.getUsername(), Constants.AES_KEY);
            loginParams.setUsername(username);
            log.info("========================aes解密后的username:"+username);
        } catch (Exception e) {
            log.error("aes解密出错", e);
        }

        // 获取账号信息
        AccountBo accountBo = service.login(loginParams);
        if(null == accountBo){
            return new SimpleResponse.SimpleResponseBuilder().failure("账号信息不存在，请重新输入正确的账号").build();
        }

        if (StringUtils.isNotBlank(accountBo.getAccount().getAccountName())) {
            SysAccount account = accountBo.getAccount();
            if (!StringUtils.equals("1", account.getInitialPasswordFlag())) {
                if (StringUtils.equals("0", account.getStatus())) {
                    // 构建JWT令牌
                    JwtAccessToken jwtAccessToken = new JwtAccessToken();
                    jwtAccessToken.setIss("Lwx");
                    Map<String, Object> params = new HashMap<>();
                    params.put(Constants.LOGIN_USER_ID, account.getUserId());
                    params.put(Constants.LOGIN_ACCOUNT_ID, account.getAccountId());
                    params.put(Constants.LOGIN_USER_NAME, accountBo.getUser().getUserName());
                    params.put(Constants.LOGIN_LOGINNAME, account.getAccountName());
                    params.put(Constants.LOGIN_ACCOUNT_NAME, account.getAccountName());
                    params.put(Constants.LOGIN_APP_ID, accountBo.getAccount().getAppId());
                    Map<String, Object> data = new HashMap<>();
                    long expiredTime = Long.parseLong(settingService.getVal("token-expired-time", loginParams.getAppId()));
                    if (expiredTime == -1) {
                        expiredTime = Constants.DEFAULT_TOKEN_EXPIRED_TIME;
                    } else {
                        expiredTime = expiredTime * 60 * 1000;
                    }
                    TokenResponse tokenResponse = tokenProvider.createToken(jwtAccessToken, expiredTime, params);
                    accountBo.getAccount().setLoginPass("");
                    accountBo.setSuperAdmin(roleService.isSuperRole(account.getAccountId(), account.getAppId()));
                    SysUser user = accountBo.getUser();
                    accountBo.setUser(user);
                    data.put("token", tokenResponse);
                    data.put("info", accountBo);
//                    if (StringUtils.isNotBlank(user.getCityId())) {
//                        SysRegion obj = regionService.getSysRegionById(user.getCityId());
//                        if(null != obj && null != obj.getRegionName()) {
//                            String s = obj.getRegionName();
//                            if (StringUtils.isNotBlank(s)) {
//                                user.setCityName(s);
//                            }
//                        }
//                    }
//                    if (StringUtils.isNotBlank(user.getCountyId())) {
//                        SysRegion obj = regionService.getSysRegionById(user.getCountyId());
//                        if(null != obj && null !=obj.getRegionName()){
//                            String s = obj.getRegionName();
//                            if (StringUtils.isNotBlank(s)) {
//                                user.setCountyName(s);
//                            }
//                        }
//                    }
//                    if (StringUtils.isNotBlank(user.getProvinceId())) {
//                        String s = regionService.getSysRegionById(user.getProvinceId()).getRegionName();
//                        if (StringUtils.isNotBlank(s)) {
//                            user.setProvinceName(s);
//                        }
//                    }
//                    if (StringUtils.isNotBlank(user.getOrgCode())) {
//                        SysOrg obj =  orgService.getById(user.getOrgCode());
//                        if(null != obj && null!=obj.getDisplayName()){
//                            String s  = obj.getDisplayName();
//                            if (StringUtils.isNotBlank(s)) {
//                                user.setOrgName(s);
//                            }
//                        }
//                    }
//                    if (StringUtils.isNotBlank(user.getCompanyId())) {
//                        SysCompany obj = companyService.getById(user.getCompanyId());
//                        if(null != obj && null!= obj.getCompanyName()){
//                            String s = obj.getCompanyName();
//                            if (StringUtils.isNotBlank(s)) {
//                                user.setCompanyName(s);
//                            }
//                        }
//                    }

                    return new SimpleResponse.SimpleResponseBuilder().success(data).build();
                } else if (StringUtils.equals("1", account.getStatus())) {
                    return new SimpleResponse.SimpleResponseBuilder().failure("账号已经被锁定").build();
//                    simpleResponseBuilder.withCode(ErrCode.E50005).withMessage("账号已经被锁定");
                }
            } else if (account.getLoginFailTimes() < 0) {
                System.out.println("account.getLoginFailTimes() < 0");
                return new SimpleResponse.SimpleResponseBuilder().failure("用户名或者密码不正确").build();
//                simpleResponseBuilder.withCode(ErrCode.E50004).withMessage("用户名或者密码不正确");

            } else {
                return new SimpleResponse.SimpleResponseBuilder().failure("请修改初始密码").build();
//                simpleResponseBuilder.withCode(ErrCode.E50006).withMessage("请修改初始密码");
            }
        } else if (accountBo == null) {
            return new SimpleResponse.SimpleResponseBuilder().failure("账号信息不存在").build();
//            simpleResponseBuilder.withCode(ErrCode.E20001).withMessage("账号信息不存在");
        } else {
            Integer times = accountBo.getAccount().getLoginFailTimes();
            if (times < 0) {
                return new SimpleResponse.SimpleResponseBuilder().failure("用户名或者密码不正确").build();
//                simpleResponseBuilder.withCode(ErrCode.E50004).withMessage("用户名或者密码不正确");
            } else {
                return new SimpleResponse.SimpleResponseBuilder().failure("用户名或者密码不正确，登录剩余尝试次数 " + times).build();
//                simpleResponseBuilder.withCode(ErrCode.E50004).withMessage("用户名或者密码不正确，登录剩余尝试次数 " + times);
            }
        }
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }


    @GetMapping("/resetPassword/{accountId}")
    @ResponseBody
    @Operation(description = "创建用户账号信息表")
    public SimpleResponse resetPassword(@PathVariable String accountId){
        service.resetPassword(accountId);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/unlock/{accountId}")
    @ResponseBody
    @Operation(description = "创建用户账号信息表")
    public SimpleResponse unlock(@PathVariable String accountId){
        service.unlock(accountId);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/lock/{accountId}")
    @ResponseBody
    @Operation(description = "创建用户账号信息表")
    public SimpleResponse lock(@PathVariable String accountId){
        System.out.println("Enter account");
        service.lock(accountId);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PostMapping
    @ResponseBody
    @Operation(description = "创建用户账号信息表")
    public SimpleResponse save(@RequestBody SysAccount obj){
        service.saveByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PutMapping("/{accountid}")
    @ResponseBody
    @Operation(description = "更新用户账号信息表")
    public SimpleResponse update(@PathVariable(name = "accountid") String accountid,@RequestBody SysAccount obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{accountid}")
    @ResponseBody
    @Operation(description = "按ID删除用户账号信息表")
    public SimpleResponse remove(@PathVariable(name = "accountid") String accountid){
        service.removeById(accountid);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{accountid}")
    @Operation(description = "按ID查询用户账号信息表")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "accountid") String accountid) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(accountid)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个用户账号信息表")
    public SimpleResponse removes(@RequestBody List<String> accountids){
        service.removeByIds(accountids);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除用户账号信息表")
    public SimpleResponse deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PostMapping("/selby")
    @ResponseBody
    public SimpleResponse selectBy(@RequestBody(required = false) Map<String, String> params) {
        List<SysAccount> sysaccounts = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(sysaccounts).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询用户账号信息表")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<SysAccount> page = service.selectPage(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByWrapper")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByWrapper(@RequestBody Map<String, String> params) {
        try {
            IPage<SysAccount> page = service.selpageCustomSqlByWrapper(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map用户账号信息表")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        try {
            IPage<SysAccount> page = service.selpageCustomSqlByMap(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    /**
     * 下载excel模板
     * @param response
     * @param request
     * @throws Exception
     */
    @PostMapping("/downloadExcelTemplate")
    @ResponseBody
    public void downloadExcelTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {
        service.downloadExcelTemplate(response, request);
    }

    /**
     * excel导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/uploadExcel")
    @ResponseBody
    public void uploadExcel(@RequestParam MultipartFile file) throws Exception {
        service.uploadExcel(file);
    }

    @PostMapping("/excel")
    @ResponseBody
    public void excel(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, String> params) throws Exception {
        service.excel(response, request, params);
    }
}

