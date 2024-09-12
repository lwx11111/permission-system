package org.example.service.management.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.example.common.domain.management.SysUser;
import org.example.dao.management.SysUserDao;
import org.example.service.management.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.utils.AppIdUtil;
import org.example.service.management.account.impl.SysAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 账号信息表 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    private SysAccountServiceImpl accountService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void lock(String userId) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getUserId, userId)
                .set(SysUser::getStatus, "1");
        this.update(wrapper);
        // 一个用户有多个账号
        accountService.lockByUserId(userId);
    }

    @Override
    public void unlock(String userId) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getUserId, userId)
                .set(SysUser::getStatus, "0");
        this.update(wrapper);
        // 一个用户有多个账号
        accountService.unlockByUserId(userId);
    }

    @Override
    public void logicDeleteByUserId(String UserId) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getUserId, UserId)
                .set(SysUser::getStatus, "2");
        this.update(wrapper);
        accountService.logicDeleteByUserId(UserId);
    }

    @Override
//    @Transactional
    public void saveByParam(SysUser obj,Map<String, String> params) throws Exception{
        // 获取当前操作人和AppId
        String appId = AppIdUtil.getAppId(request);
        String userName = AppIdUtil.getUserName(request);
        LocalDateTime now = LocalDateTime.now();
        obj.setAppId(appId);
        obj.setCreatedBy(userName);
        obj.setCreateTime(now);
        obj.setUpdatedBy(userName);
        obj.setUpdatedTime(now);
        // 先创建user
        this.save(obj);
        // 再创建account
        accountService.addSysAccountBySysUser(obj);
    }

    @Override
    public void updateByParam(SysUser obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {

    }

    @Override
    public List<SysUser> selectBy(Map<String, String> params) {
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysUser> selectPage(Map<String, String> params) {
        Page<SysUser> page = PageUtils.pageHandler(params);
        QueryWrapper<SysUser> query = getQuery(params);
        IPage<SysUser> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysUser> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysUser> page = PageUtils.pageHandler(params);
        QueryWrapper<SysUser> query = getQuery(params);
        IPage<SysUser> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysUser> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysUser> page = PageUtils.pageHandler(params);
        IPage<SysUser> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysUser> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysUser"), SysUser.class, data);
        String fileName = String.format("SysUser_%d.xls", System.currentTimeMillis());
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
        List<SysUser> dataList = new ExcelImportService().importExcelByIs(inputStream, SysUser.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        List<SysUser> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysUser"),
        SysUser.class, data);
        String fileName = String.format("SysUser_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysUser> getQuery(Map<String, String> params){
        QueryWrapper<SysUser> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("userId".equals(entry.getKey())){
                query.eq("user_id",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("orgCode".equals(entry.getKey())){
                query.eq("org_code",entry.getValue());
            }
            if("companyId".equals(entry.getKey())){
                query.eq("company_id",entry.getValue());
            }
            if("cn".equals(entry.getKey())){
                query.eq("cn",entry.getValue());
            }
            if("sn".equals(entry.getKey())){
                query.eq("sn",entry.getValue());
            }
            if("description".equals(entry.getKey())){
                query.eq("description",entry.getValue());
            }
            if("email".equals(entry.getKey())){
                query.eq("email",entry.getValue());
            }
            if("nation".equals(entry.getKey())){
                query.eq("nation",entry.getValue());
            }
            if("gender".equals(entry.getKey())){
                query.eq("gender",entry.getValue());
            }
            if("birthday".equals(entry.getKey())){
                query.eq("birthday",entry.getValue());
            }
            if("c".equals(entry.getKey())){
                query.eq("c",entry.getValue());
            }
            if("religion".equals(entry.getKey())){
                query.eq("religion",entry.getValue());
            }
            if("telephoneNumber".equals(entry.getKey())){
                query.eq("telephone_number",entry.getValue());
            }
            if("mobile".equals(entry.getKey())){
                query.eq("mobile",entry.getValue());
            }
            if("facsimileTelephoneNumber".equals(entry.getKey())){
                query.eq("facsimile_telephone_number",entry.getValue());
            }
            if("startTime".equals(entry.getKey())){
                query.eq("start_time",entry.getValue());
            }
            if("endTime".equals(entry.getKey())){
                query.eq("end_time",entry.getValue());
            }
            if("status".equals(entry.getKey())){
                query.eq("status",entry.getValue());
            }
            if("passwordModifiedDate".equals(entry.getKey())){
                query.eq("password_modified_date",entry.getValue());
            }
            if("idCardNumber".equals(entry.getKey())){
                query.eq("id_card_number",entry.getValue());
            }
            if("employeeType".equals(entry.getKey())){
                query.eq("employee_type",entry.getValue());
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
            if("employeeNumber".equals(entry.getKey())){
                query.eq("employee_number",entry.getValue());
            }
            if("userLevel".equals(entry.getKey())){
                query.eq("user_level",entry.getValue());
            }
            if("levelName".equals(entry.getKey())){
                query.eq("level_name",entry.getValue());
            }
            if("category".equals(entry.getKey())){
                query.eq("category",entry.getValue());
            }
            if("functions".equals(entry.getKey())){
                query.eq("functions",entry.getValue());
            }
            if("displayOrder".equals(entry.getKey())){
                query.eq("display_order",entry.getValue());
            }
            if("duty".equals(entry.getKey())){
                query.eq("duty",entry.getValue());
            }
            if("positionLevel".equals(entry.getKey())){
                query.eq("position_level",entry.getValue());
            }
            if("supporterCorpName".equals(entry.getKey())){
                query.eq("supporter_corp_name",entry.getValue());
            }
            if("supporterDept".equals(entry.getKey())){
                query.eq("supporter_dept",entry.getValue());
            }
            if("supporterCorpContact".equals(entry.getKey())){
                query.eq("supporter_corp_contact",entry.getValue());
            }
            if("supervisor".equals(entry.getKey())){
                query.eq("supervisor",entry.getValue());
            }
            if("post".equals(entry.getKey())){
                query.eq("post",entry.getValue());
            }
            if("userNo".equals(entry.getKey())){
                query.eq("user_no",entry.getValue());
            }
            if("user".equals(entry.getKey())){
                query.eq("user",entry.getValue());
            }
            if("provinceId".equals(entry.getKey())){
                query.eq("province_id",entry.getValue());
            }
            if("cityId".equals(entry.getKey())){
                query.eq("city_id",entry.getValue());
            }
            if("cityName".equals(entry.getKey())){
                query.eq("city_name",entry.getValue());
            }
            if("companyName".equals(entry.getKey())){
                query.eq("company_name",entry.getValue());
            }
            if("orgName".equals(entry.getKey())){
                query.eq("org_name",entry.getValue());
            }
            if("countyId".equals(entry.getKey())){
                query.eq("county_id",entry.getValue());
            }
            if("countyName".equals(entry.getKey())){
                query.eq("county_name",entry.getValue());
            }
            if("accountName".equals(entry.getKey())){
                query.eq("account_name",entry.getValue());
            }
            if("userRoleId".equals(entry.getKey())){
                query.eq("user_role_id",entry.getValue());
            }
        }
        return  query;
    }
}
