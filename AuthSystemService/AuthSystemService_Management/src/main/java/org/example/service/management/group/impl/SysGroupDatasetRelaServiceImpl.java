package org.example.service.management.group.impl;

import org.example.common.domain.management.group.SysGroupDatasetRela;
import org.example.dao.management.group.SysGroupDatasetRelaDao;
import org.example.service.management.group.ISysGroupDatasetRelaService;
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
public class SysGroupDatasetRelaServiceImpl extends ServiceImpl<SysGroupDatasetRelaDao, SysGroupDatasetRela> implements ISysGroupDatasetRelaService {

    @Override
    public void saveByParam(SysGroupDatasetRela obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysGroupDatasetRela obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysGroupDatasetRela> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysGroupDatasetRela> selectBy(Map<String, String> params) {
        QueryWrapper<SysGroupDatasetRela> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysGroupDatasetRela> selectPage(Map<String, String> params) {
        Page<SysGroupDatasetRela> page = PageUtils.pageHandler(params);
        QueryWrapper<SysGroupDatasetRela> query = getQuery(params);
        IPage<SysGroupDatasetRela> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysGroupDatasetRela> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysGroupDatasetRela> page = PageUtils.pageHandler(params);
        QueryWrapper<SysGroupDatasetRela> query = getQuery(params);
        IPage<SysGroupDatasetRela> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysGroupDatasetRela> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysGroupDatasetRela> page = PageUtils.pageHandler(params);
        IPage<SysGroupDatasetRela> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysGroupDatasetRela> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysGroupDatasetRela"), SysGroupDatasetRela.class, data);
        String fileName = String.format("SysGroupDatasetRela_%d.xls", System.currentTimeMillis());
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
        List<SysGroupDatasetRela> dataList = new ExcelImportService().importExcelByIs(inputStream, SysGroupDatasetRela.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysGroupDatasetRela> query = new QueryWrapper<>();
        List<SysGroupDatasetRela> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysGroupDatasetRela"),
        SysGroupDatasetRela.class, data);
        String fileName = String.format("SysGroupDatasetRela_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysGroupDatasetRela> getQuery(Map<String, String> params){
        QueryWrapper<SysGroupDatasetRela> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("id".equals(entry.getKey())){
                query.eq("id",entry.getValue());
            }
            if("dsetId".equals(entry.getKey())){
                query.eq("dset_id",entry.getValue());
            }
            if("groupId".equals(entry.getKey())){
                query.eq("group_id",entry.getValue());
            }
            if("dataId".equals(entry.getKey())){
                query.eq("data_id",entry.getValue());
            }
            if("createdBy".equals(entry.getKey())){
                query.eq("created_by",entry.getValue());
            }
            if("createTime".equals(entry.getKey())){
                query.eq("create_time",entry.getValue());
            }
        }
        return  query;
    }
}
