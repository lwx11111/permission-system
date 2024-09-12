package org.example.service.mointor.impl;

import org.example.common.domain.monitor.LogPlatformOper;
import org.example.dao.monitor.LogPlatformOperDao;
import org.example.service.mointor.ILogPlatformOperService;
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
public class LogPlatformOperServiceImpl extends ServiceImpl<LogPlatformOperDao, LogPlatformOper> implements ILogPlatformOperService {

    @Override
    public void saveByParam(LogPlatformOper obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(LogPlatformOper obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<LogPlatformOper> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<LogPlatformOper> selectBy(Map<String, String> params) {
        QueryWrapper<LogPlatformOper> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<LogPlatformOper> selectPage(Map<String, String> params) {
        Page<LogPlatformOper> page = PageUtils.pageHandler(params);
        QueryWrapper<LogPlatformOper> query = getQuery(params);
        IPage<LogPlatformOper> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<LogPlatformOper> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<LogPlatformOper> page = PageUtils.pageHandler(params);
        QueryWrapper<LogPlatformOper> query = getQuery(params);
        IPage<LogPlatformOper> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<LogPlatformOper> selpageCustomSqlByMap(Map<String, String> params) {
        Page<LogPlatformOper> page = PageUtils.pageHandler(params);
        IPage<LogPlatformOper> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<LogPlatformOper> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "LogPlatformOper"), LogPlatformOper.class, data);
        String fileName = String.format("LogPlatformOper_%d.xls", System.currentTimeMillis());
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
        List<LogPlatformOper> dataList = new ExcelImportService().importExcelByIs(inputStream, LogPlatformOper.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<LogPlatformOper> query = new QueryWrapper<>();
        List<LogPlatformOper> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "LogPlatformOper"),
        LogPlatformOper.class, data);
        String fileName = String.format("LogPlatformOper_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<LogPlatformOper> getQuery(Map<String, String> params){
        QueryWrapper<LogPlatformOper> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("operation".equals(entry.getKey())){
                query.like("operation",entry.getValue());
            }
            if("type".equals(entry.getKey())){
                query.eq("type",entry.getValue());
            }
            if("createTime".equals(entry.getKey())){
                query.ge("create_time",entry.getValue());
            }
            if("endTime".equals(entry.getKey())){
                query.le("end_time",entry.getValue());
            }
        }
        return  query;
    }
}
