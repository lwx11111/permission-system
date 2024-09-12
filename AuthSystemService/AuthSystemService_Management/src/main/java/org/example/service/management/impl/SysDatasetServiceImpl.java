package org.example.service.management.impl;

import org.example.common.domain.management.SysDataset;
import org.example.dao.management.SysDatasetDao;
import org.example.service.management.ISysDatasetService;
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
public class SysDatasetServiceImpl extends ServiceImpl<SysDatasetDao, SysDataset> implements ISysDatasetService {

    @Override
    public void saveByParam(SysDataset obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysDataset obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysDataset> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysDataset> selectBy(Map<String, String> params) {
        QueryWrapper<SysDataset> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysDataset> selectPage(Map<String, String> params) {
        Page<SysDataset> page = PageUtils.pageHandler(params);
        QueryWrapper<SysDataset> query = getQuery(params);
        IPage<SysDataset> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysDataset> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysDataset> page = PageUtils.pageHandler(params);
        QueryWrapper<SysDataset> query = getQuery(params);
        IPage<SysDataset> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysDataset> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysDataset> page = PageUtils.pageHandler(params);
        IPage<SysDataset> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysDataset> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysDataset"), SysDataset.class, data);
        String fileName = String.format("SysDataset_%d.xls", System.currentTimeMillis());
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
        List<SysDataset> dataList = new ExcelImportService().importExcelByIs(inputStream, SysDataset.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysDataset> query = new QueryWrapper<>();
        List<SysDataset> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysDataset"),
        SysDataset.class, data);
        String fileName = String.format("SysDataset_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysDataset> getQuery(Map<String, String> params){
        QueryWrapper<SysDataset> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("dsetId".equals(entry.getKey())){
                query.eq("dset_id",entry.getValue());
            }
            if("dsId".equals(entry.getKey())){
                query.eq("ds_id",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("dsetCode".equals(entry.getKey())){
                query.eq("dset_code",entry.getValue());
            }
            if("dsetName".equals(entry.getKey())){
                query.eq("dset_name",entry.getValue());
            }
            if("fetchDataContent".equals(entry.getKey())){
                query.eq("fetch_data_content",entry.getValue());
            }
            if("fetchDataType".equals(entry.getKey())){
                query.eq("fetch_data_type",entry.getValue());
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
            if("resTable".equals(entry.getKey())){
                query.eq("res_table",entry.getValue());
            }
            if("primarykeyCol".equals(entry.getKey())){
                query.eq("primarykey_col",entry.getValue());
            }
            if("resnameCol".equals(entry.getKey())){
                query.eq("resname_col",entry.getValue());
            }
            if("whereClause".equals(entry.getKey())){
                query.eq("where_clause",entry.getValue());
            }
            if("orderCol".equals(entry.getKey())){
                query.eq("order_col",entry.getValue());
            }
        }
        return  query;
    }
}
