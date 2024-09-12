package org.example.service.management.role.impl;

import org.example.common.domain.management.role.SysRoleDatasetRela;
import org.example.dao.management.role.SysRoleDatasetRelaDao;
import org.example.service.management.role.ISysRoleDatasetRelaService;
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
public class SysRoleDatasetRelaServiceImpl extends ServiceImpl<SysRoleDatasetRelaDao, SysRoleDatasetRela> implements ISysRoleDatasetRelaService {

    @Override
    public void saveByParam(SysRoleDatasetRela obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysRoleDatasetRela obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysRoleDatasetRela> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysRoleDatasetRela> selectBy(Map<String, String> params) {
        QueryWrapper<SysRoleDatasetRela> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysRoleDatasetRela> selectPage(Map<String, String> params) {
        Page<SysRoleDatasetRela> page = PageUtils.pageHandler(params);
        QueryWrapper<SysRoleDatasetRela> query = getQuery(params);
        IPage<SysRoleDatasetRela> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysRoleDatasetRela> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysRoleDatasetRela> page = PageUtils.pageHandler(params);
        QueryWrapper<SysRoleDatasetRela> query = getQuery(params);
        IPage<SysRoleDatasetRela> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysRoleDatasetRela> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysRoleDatasetRela> page = PageUtils.pageHandler(params);
        IPage<SysRoleDatasetRela> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysRoleDatasetRela> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysRoleDatasetRela"), SysRoleDatasetRela.class, data);
        String fileName = String.format("SysRoleDatasetRela_%d.xls", System.currentTimeMillis());
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
        List<SysRoleDatasetRela> dataList = new ExcelImportService().importExcelByIs(inputStream, SysRoleDatasetRela.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysRoleDatasetRela> query = new QueryWrapper<>();
        List<SysRoleDatasetRela> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysRoleDatasetRela"),
        SysRoleDatasetRela.class, data);
        String fileName = String.format("SysRoleDatasetRela_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysRoleDatasetRela> getQuery(Map<String, String> params){
        QueryWrapper<SysRoleDatasetRela> query  = new QueryWrapper<>();
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
            if("roleId".equals(entry.getKey())){
                query.eq("role_id",entry.getValue());
            }
            if("dataId".equals(entry.getKey())){
                query.eq("data_id",entry.getValue());
            }
            if("createTime".equals(entry.getKey())){
                query.eq("create_time",entry.getValue());
            }
            if("createdBy".equals(entry.getKey())){
                query.eq("created_by",entry.getValue());
            }
        }
        return  query;
    }
}
