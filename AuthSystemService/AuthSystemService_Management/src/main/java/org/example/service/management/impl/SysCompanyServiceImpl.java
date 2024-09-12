package org.example.service.management.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.domain.management.SysCompany;
import org.example.dao.management.SysCompanyDao;
import org.example.dto.ListCompanyParams;
import org.example.service.management.ISysCompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * 公司表 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysCompanyServiceImpl extends ServiceImpl<SysCompanyDao, SysCompany> implements ISysCompanyService {

    @Override
    public List<SysCompany> listCompany(ListCompanyParams params) {
        LambdaQueryWrapper<SysCompany> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(params.getCompanyName())) {
            queryWrapper.like(SysCompany::getCompanyName, "%" + params.getCompanyName() + "%");
        }
        if (StringUtils.isNotBlank(params.getStatus())) {
            queryWrapper.eq(SysCompany::getStatus, params.getStatus());
        }
        queryWrapper.eq(SysCompany::getAppId, params.getAppId());
        if (StringUtils.isNotBlank(params.getCompanyId())){
            queryWrapper.eq(SysCompany::getCompanyId,params.getCompanyId());
        }
        return list(queryWrapper);
    }

    @Override
    public void saveByParam(SysCompany obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysCompany obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysCompany> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysCompany> selectBy(Map<String, String> params) {
        QueryWrapper<SysCompany> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysCompany> selectPage(Map<String, String> params) {
        Page<SysCompany> page = PageUtils.pageHandler(params);
        QueryWrapper<SysCompany> query = getQuery(params);
        IPage<SysCompany> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysCompany> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysCompany> page = PageUtils.pageHandler(params);
        QueryWrapper<SysCompany> query = getQuery(params);
        IPage<SysCompany> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysCompany> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysCompany> page = PageUtils.pageHandler(params);
        IPage<SysCompany> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysCompany> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysCompany"), SysCompany.class, data);
        String fileName = String.format("SysCompany_%d.xls", System.currentTimeMillis());
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
        List<SysCompany> dataList = new ExcelImportService().importExcelByIs(inputStream, SysCompany.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysCompany> query = new QueryWrapper<>();
        List<SysCompany> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysCompany"),
        SysCompany.class, data);
        String fileName = String.format("SysCompany_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysCompany> getQuery(Map<String, String> params){
        QueryWrapper<SysCompany> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("companyId".equals(entry.getKey())){
                query.eq("company_id",entry.getValue());
            }
            if("address".equals(entry.getKey())){
                query.eq("address",entry.getValue());
            }
            if("companyName".equals(entry.getKey())){
                query.eq("company_name",entry.getValue());
            }
            if("abbre".equals(entry.getKey())){
                query.eq("abbre",entry.getValue());
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
            if("status".equals(entry.getKey())){
                query.eq("status",entry.getValue());
            }
            if("postalCode".equals(entry.getKey())){
                query.eq("postal_code",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
        }
        return  query;
    }
}
