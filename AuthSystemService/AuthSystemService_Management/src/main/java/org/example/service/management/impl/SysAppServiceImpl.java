package org.example.service.management.impl;

import org.example.common.domain.management.SysApp;
import org.example.dao.management.SysAppDao;
import org.example.service.management.ISysAppService;
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
 * 项目表 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysAppServiceImpl extends ServiceImpl<SysAppDao, SysApp> implements ISysAppService {

    @Override
    public void saveByParam(SysApp obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysApp obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysApp> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysApp> selectBy(Map<String, String> params) {
        QueryWrapper<SysApp> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysApp> selectPage(Map<String, String> params) {
        Page<SysApp> page = PageUtils.pageHandler(params);
        QueryWrapper<SysApp> query = getQuery(params);
        IPage<SysApp> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysApp> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysApp> page = PageUtils.pageHandler(params);
        QueryWrapper<SysApp> query = getQuery(params);
        IPage<SysApp> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysApp> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysApp> page = PageUtils.pageHandler(params);
        IPage<SysApp> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysApp> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysApp"), SysApp.class, data);
        String fileName = String.format("SysApp_%d.xls", System.currentTimeMillis());
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
        List<SysApp> dataList = new ExcelImportService().importExcelByIs(inputStream, SysApp.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysApp> query = new QueryWrapper<>();
        List<SysApp> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysApp"),
        SysApp.class, data);
        String fileName = String.format("SysApp_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysApp> getQuery(Map<String, String> params){
        QueryWrapper<SysApp> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("appCode".equals(entry.getKey())){
                query.eq("app_code",entry.getValue());
            }
            if("appName".equals(entry.getKey())){
                query.eq("app_name",entry.getValue());
            }
            if("icon".equals(entry.getKey())){
                query.eq("icon",entry.getValue());
            }
            if("remark".equals(entry.getKey())){
                query.eq("remark",entry.getValue());
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
            if("fixed".equals(entry.getKey())){
                query.eq("fixed",entry.getValue());
            }
            if("secretKey".equals(entry.getKey())){
                query.eq("secret_key",entry.getValue());
            }
            if("appKey".equals(entry.getKey())){
                query.eq("app_key",entry.getValue());
            }
            if("status".equals(entry.getKey())){
                query.eq("status",entry.getValue());
            }
        }
        return  query;
    }
}
