package org.example.service.management.impl;

import org.example.common.domain.management.SysSetting;
import org.example.dao.management.SysSettingDao;
import org.example.service.management.ISysSettingService;
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
public class SysSettingServiceImpl extends ServiceImpl<SysSettingDao, SysSetting> implements ISysSettingService {

    @Override
    public String getPasswordEncryptType(String appId) {
        final String val = getVal("password-encrypt", appId);
        if (StringUtils.equals("-1", val)) {
            return "sha256";
        } else {
            return val;
        }
    }

    @Override
    public String getVal(String name, String appId) {
        QueryWrapper<SysSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysSetting::getSettingCode, name);
        queryWrapper.lambda().eq(SysSetting::getAppId, appId);
        final List<SysSetting> settingList = list(queryWrapper);
        if (settingList.size() > 0) {
            final SysSetting setting = settingList.get(0);
            final String settingVal = setting.getSettingVal();
            return settingVal;
        }
        return "-1";
    }

    @Override
    public void saveByParam(SysSetting obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysSetting obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysSetting> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysSetting> selectBy(Map<String, String> params) {
        QueryWrapper<SysSetting> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysSetting> selectPage(Map<String, String> params) {
        Page<SysSetting> page = PageUtils.pageHandler(params);
        QueryWrapper<SysSetting> query = getQuery(params);
        IPage<SysSetting> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysSetting> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysSetting> page = PageUtils.pageHandler(params);
        QueryWrapper<SysSetting> query = getQuery(params);
        IPage<SysSetting> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysSetting> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysSetting> page = PageUtils.pageHandler(params);
        IPage<SysSetting> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysSetting> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysSetting"), SysSetting.class, data);
        String fileName = String.format("SysSetting_%d.xls", System.currentTimeMillis());
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
        List<SysSetting> dataList = new ExcelImportService().importExcelByIs(inputStream, SysSetting.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysSetting> query = new QueryWrapper<>();
        List<SysSetting> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysSetting"),
        SysSetting.class, data);
        String fileName = String.format("SysSetting_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysSetting> getQuery(Map<String, String> params){
        QueryWrapper<SysSetting> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("settingId".equals(entry.getKey())){
                query.eq("setting_id",entry.getValue());
            }
            if("settingCode".equals(entry.getKey())){
                query.eq("setting_code",entry.getValue());
            }
            if("settingName".equals(entry.getKey())){
                query.eq("setting_name",entry.getValue());
            }
            if("settingVal".equals(entry.getKey())){
                query.eq("setting_val",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("description".equals(entry.getKey())){
                query.eq("description",entry.getValue());
            }
        }
        return  query;
    }
}
