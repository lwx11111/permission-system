package org.example.service.management.notice.impl;

import org.example.common.domain.management.notice.SysNoticeReceiver;
import org.example.dao.management.notice.SysNoticeReceiverDao;
import org.example.service.management.notice.ISysNoticeReceiverService;
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
public class SysNoticeReceiverServiceImpl extends ServiceImpl<SysNoticeReceiverDao, SysNoticeReceiver> implements ISysNoticeReceiverService {

    @Override
    public void saveByParam(SysNoticeReceiver obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysNoticeReceiver obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysNoticeReceiver> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysNoticeReceiver> selectBy(Map<String, String> params) {
        QueryWrapper<SysNoticeReceiver> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysNoticeReceiver> selectPage(Map<String, String> params) {
        Page<SysNoticeReceiver> page = PageUtils.pageHandler(params);
        QueryWrapper<SysNoticeReceiver> query = getQuery(params);
        IPage<SysNoticeReceiver> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysNoticeReceiver> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysNoticeReceiver> page = PageUtils.pageHandler(params);
        QueryWrapper<SysNoticeReceiver> query = getQuery(params);
        IPage<SysNoticeReceiver> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysNoticeReceiver> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysNoticeReceiver> page = PageUtils.pageHandler(params);
        IPage<SysNoticeReceiver> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysNoticeReceiver> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysNoticeReceiver"), SysNoticeReceiver.class, data);
        String fileName = String.format("SysNoticeReceiver_%d.xls", System.currentTimeMillis());
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
        List<SysNoticeReceiver> dataList = new ExcelImportService().importExcelByIs(inputStream, SysNoticeReceiver.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysNoticeReceiver> query = new QueryWrapper<>();
        List<SysNoticeReceiver> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysNoticeReceiver"),
        SysNoticeReceiver.class, data);
        String fileName = String.format("SysNoticeReceiver_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysNoticeReceiver> getQuery(Map<String, String> params){
        QueryWrapper<SysNoticeReceiver> query  = new QueryWrapper<>();
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
            if("noticeId".equals(entry.getKey())){
                query.eq("notice_id",entry.getValue());
            }
            if("accountId".equals(entry.getKey())){
                query.eq("account_id",entry.getValue());
            }
            if("readFlag".equals(entry.getKey())){
                query.eq("read_flag",entry.getValue());
            }
            if("readTime".equals(entry.getKey())){
                query.eq("read_time",entry.getValue());
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
