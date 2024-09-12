package org.example.service.management.group.impl;

import org.example.common.domain.management.group.SysGroupFunctionRela;
import org.example.dao.management.group.SysGroupFunctionRelaDao;
import org.example.service.management.group.ISysGroupFunctionRelaService;
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
public class SysGroupFunctionRelaServiceImpl extends ServiceImpl<SysGroupFunctionRelaDao, SysGroupFunctionRela> implements ISysGroupFunctionRelaService {

    @Override
    public void saveByParam(SysGroupFunctionRela obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysGroupFunctionRela obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysGroupFunctionRela> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysGroupFunctionRela> selectBy(Map<String, String> params) {
        QueryWrapper<SysGroupFunctionRela> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysGroupFunctionRela> selectPage(Map<String, String> params) {
        Page<SysGroupFunctionRela> page = PageUtils.pageHandler(params);
        QueryWrapper<SysGroupFunctionRela> query = getQuery(params);
        IPage<SysGroupFunctionRela> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysGroupFunctionRela> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysGroupFunctionRela> page = PageUtils.pageHandler(params);
        QueryWrapper<SysGroupFunctionRela> query = getQuery(params);
        IPage<SysGroupFunctionRela> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysGroupFunctionRela> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysGroupFunctionRela> page = PageUtils.pageHandler(params);
        IPage<SysGroupFunctionRela> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysGroupFunctionRela> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysGroupFunctionRela"), SysGroupFunctionRela.class, data);
        String fileName = String.format("SysGroupFunctionRela_%d.xls", System.currentTimeMillis());
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
        List<SysGroupFunctionRela> dataList = new ExcelImportService().importExcelByIs(inputStream, SysGroupFunctionRela.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysGroupFunctionRela> query = new QueryWrapper<>();
        List<SysGroupFunctionRela> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysGroupFunctionRela"),
        SysGroupFunctionRela.class, data);
        String fileName = String.format("SysGroupFunctionRela_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysGroupFunctionRela> getQuery(Map<String, String> params){
        QueryWrapper<SysGroupFunctionRela> query  = new QueryWrapper<>();
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
            if("groupId".equals(entry.getKey())){
                query.eq("group_id",entry.getValue());
            }
            if("funId".equals(entry.getKey())){
                query.eq("fun_id",entry.getValue());
            }
            if("resTypeId".equals(entry.getKey())){
                query.eq("res_type_id",entry.getValue());
            }
            if("permit".equals(entry.getKey())){
                query.eq("permit",entry.getValue());
            }
            if("resList".equals(entry.getKey())){
                query.eq("res_list",entry.getValue());
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
