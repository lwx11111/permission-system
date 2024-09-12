package org.example.service.management.impl;

import org.example.common.domain.management.SysDatasource;
import org.example.dao.management.SysDatasourceDao;
import org.example.service.management.ISysDatasourceService;
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
 *  服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysDatasourceServiceImpl extends ServiceImpl<SysDatasourceDao, SysDatasource> implements ISysDatasourceService {

    @Override
    public void saveByParam(SysDatasource obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysDatasource obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysDatasource> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysDatasource> selectBy(Map<String, String> params) {
        QueryWrapper<SysDatasource> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysDatasource> selectPage(Map<String, String> params) {
        Page<SysDatasource> page = PageUtils.pageHandler(params);
        QueryWrapper<SysDatasource> query = getQuery(params);
        IPage<SysDatasource> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysDatasource> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysDatasource> page = PageUtils.pageHandler(params);
        QueryWrapper<SysDatasource> query = getQuery(params);
        IPage<SysDatasource> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysDatasource> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysDatasource> page = PageUtils.pageHandler(params);
        IPage<SysDatasource> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysDatasource> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysDatasource"), SysDatasource.class, data);
        String fileName = String.format("SysDatasource_%d.xls", System.currentTimeMillis());
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
        List<SysDatasource> dataList = new ExcelImportService().importExcelByIs(inputStream, SysDatasource.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysDatasource> query = new QueryWrapper<>();
        List<SysDatasource> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysDatasource"),
        SysDatasource.class, data);
        String fileName = String.format("SysDatasource_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysDatasource> getQuery(Map<String, String> params){
        QueryWrapper<SysDatasource> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("dsId".equals(entry.getKey())){
                query.eq("ds_id",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("dsName".equals(entry.getKey())){
                query.eq("ds_name",entry.getValue());
            }
            if("dsUrl".equals(entry.getKey())){
                query.eq("ds_url",entry.getValue());
            }
            if("dsUsername".equals(entry.getKey())){
                query.eq("ds_username",entry.getValue());
            }
            if("dsPassword".equals(entry.getKey())){
                query.eq("ds_password",entry.getValue());
            }
            if("dsMinimum".equals(entry.getKey())){
                query.eq("ds_minimum",entry.getValue());
            }
            if("dsMaximum".equals(entry.getKey())){
                query.eq("ds_maximum",entry.getValue());
            }
            if("testQuery".equals(entry.getKey())){
                query.eq("test_query",entry.getValue());
            }
            if("driverClass".equals(entry.getKey())){
                query.eq("driver_class",entry.getValue());
            }
            if("dbType".equals(entry.getKey())){
                query.eq("db_type",entry.getValue());
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
        }
        return  query;
    }
}
