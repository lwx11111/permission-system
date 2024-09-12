package org.example.service.management.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.domain.management.SysOrg;
import org.example.dao.management.SysOrgDao;
import org.example.dto.ListOrgsByCompanyParams;
import org.example.service.management.ISysOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.utils.TreeBuilder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.collect.Lists;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;

import java.io.InputStream;
import org.example.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公司部门表 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgDao, SysOrg> implements ISysOrgService {

    @Override
    public List<SysOrg> listOrgsByCompany(ListOrgsByCompanyParams params) {
        LambdaQueryWrapper<SysOrg> queryWrapper = new LambdaQueryWrapper<SysOrg>()
                .eq(SysOrg::getCompanyId, params.getCompanyId())
                .eq(SysOrg::getAppId, params.getAppId());

        return TreeBuilder.buildTreeOrg(this.list(queryWrapper));
    }

    @Override
    public void saveByParam(SysOrg obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysOrg obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysOrg> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysOrg> selectBy(Map<String, String> params) {
        QueryWrapper<SysOrg> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysOrg> selectPage(Map<String, String> params) {
        Page<SysOrg> page = PageUtils.pageHandler(params);
        QueryWrapper<SysOrg> query = getQuery(params);
        IPage<SysOrg> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysOrg> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysOrg> page = PageUtils.pageHandler(params);
        QueryWrapper<SysOrg> query = getQuery(params);
        IPage<SysOrg> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysOrg> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysOrg> page = PageUtils.pageHandler(params);
        IPage<SysOrg> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysOrg> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysOrg"), SysOrg.class, data);
        String fileName = String.format("SysOrg_%d.xls", System.currentTimeMillis());
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
        List<SysOrg> dataList = new ExcelImportService().importExcelByIs(inputStream, SysOrg.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysOrg> query = new QueryWrapper<>();
        List<SysOrg> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysOrg"),
        SysOrg.class, data);
        String fileName = String.format("SysOrg_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysOrg> getQuery(Map<String, String> params){
        QueryWrapper<SysOrg> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("orgCode".equals(entry.getKey())){
                query.eq("org_code",entry.getValue());
            }
            if("companyId".equals(entry.getKey())){
                query.eq("company_id",entry.getValue());
            }
            if("displayName".equals(entry.getKey())){
                query.eq("display_name",entry.getValue());
            }
            if("style".equals(entry.getKey())){
                query.eq("style",entry.getValue());
            }
            if("parentOrgCode".equals(entry.getKey())){
                query.eq("parent_org_code",entry.getValue());
            }
            if("orgLevel".equals(entry.getKey())){
                query.eq("org_level",entry.getValue());
            }
            if("leaf".equals(entry.getKey())){
                query.eq("leaf",entry.getValue());
            }
            if("functions".equals(entry.getKey())){
                query.eq("functions",entry.getValue());
            }
            if("supervisor".equals(entry.getKey())){
                query.eq("supervisor",entry.getValue());
            }
            if("orgManager".equals(entry.getKey())){
                query.eq("org_manager",entry.getValue());
            }
            if("viceManager".equals(entry.getKey())){
                query.eq("vice_manager",entry.getValue());
            }
            if("displayOrder".equals(entry.getKey())){
                query.eq("display_order",entry.getValue());
            }
            if("status".equals(entry.getKey())){
                query.eq("status",entry.getValue());
            }
            if("telephoneNumber".equals(entry.getKey())){
                query.eq("telephone_number",entry.getValue());
            }
            if("facsimileTelephoneNumber".equals(entry.getKey())){
                query.eq("facsimile_telephone_number",entry.getValue());
            }
            if("initials".equals(entry.getKey())){
                query.eq("initials",entry.getValue());
            }
            if("description".equals(entry.getKey())){
                query.eq("description",entry.getValue());
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
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
        }
        return  query;
    }
}
