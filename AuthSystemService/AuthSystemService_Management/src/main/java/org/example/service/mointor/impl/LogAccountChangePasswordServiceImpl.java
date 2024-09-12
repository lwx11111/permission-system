package org.example.service.mointor.impl;

import org.example.common.domain.monitor.LogAccountChangePassword;
import org.example.dao.monitor.LogAccountChangePasswordDao;
import org.example.service.mointor.ILogAccountChangePasswordService;
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
public class LogAccountChangePasswordServiceImpl extends ServiceImpl<LogAccountChangePasswordDao, LogAccountChangePassword> implements ILogAccountChangePasswordService {

    @Override
    public void saveByParam(LogAccountChangePassword obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(LogAccountChangePassword obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<LogAccountChangePassword> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<LogAccountChangePassword> selectBy(Map<String, String> params) {
        QueryWrapper<LogAccountChangePassword> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<LogAccountChangePassword> selectPage(Map<String, String> params) {
        Page<LogAccountChangePassword> page = PageUtils.pageHandler(params);
        QueryWrapper<LogAccountChangePassword> query = getQuery(params);
        IPage<LogAccountChangePassword> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<LogAccountChangePassword> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<LogAccountChangePassword> page = PageUtils.pageHandler(params);
        QueryWrapper<LogAccountChangePassword> query = getQuery(params);
        IPage<LogAccountChangePassword> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<LogAccountChangePassword> selpageCustomSqlByMap(Map<String, String> params) {
        Page<LogAccountChangePassword> page = PageUtils.pageHandler(params);
        IPage<LogAccountChangePassword> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<LogAccountChangePassword> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "LogAccountChangePassword"), LogAccountChangePassword.class, data);
        String fileName = String.format("LogAccountChangePassword_%d.xls", System.currentTimeMillis());
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
        List<LogAccountChangePassword> dataList = new ExcelImportService().importExcelByIs(inputStream, LogAccountChangePassword.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<LogAccountChangePassword> query = new QueryWrapper<>();
        List<LogAccountChangePassword> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "LogAccountChangePassword"),
        LogAccountChangePassword.class, data);
        String fileName = String.format("LogAccountChangePassword_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<LogAccountChangePassword> getQuery(Map<String, String> params){
        QueryWrapper<LogAccountChangePassword> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
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
