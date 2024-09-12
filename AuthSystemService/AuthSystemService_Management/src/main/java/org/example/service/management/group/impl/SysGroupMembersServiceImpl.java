package org.example.service.management.group.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.domain.management.group.SysGroupMembers;
import org.example.dao.management.group.SysGroupMembersDao;
import org.example.service.management.group.ISysGroupMembersService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysGroupMembersServiceImpl extends ServiceImpl<SysGroupMembersDao, SysGroupMembers> implements ISysGroupMembersService {

    @Override
    public void removeByAccountId(String accountId) {
        QueryWrapper<SysGroupMembers> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysGroupMembers::getAccountId, accountId);
        remove(queryWrapper);
    }

    @Override
    public void saveGroupMembers(String accountId, List<String> groupIds) {
        removeByAccountId(accountId);
        if (groupIds != null) {
            List<SysGroupMembers> relaList = groupIds.stream().map(groupId -> {
                SysGroupMembers rela = new SysGroupMembers();
                rela.setAccountId(accountId);
                rela.setGroupId(groupId);
                rela.setCreateTime(LocalDateTime.now());
                return rela;
            }).collect(Collectors.toList());
            saveBatch(relaList);
        }
    }

    @Override
    public List<SysGroupMembers> listByGroupIds(List<String> groupIds) {
        LambdaQueryWrapper<SysGroupMembers> queryWrapper = new LambdaQueryWrapper<SysGroupMembers>()
                .in(SysGroupMembers::getGroupId, groupIds);
        return this.list(queryWrapper);
    }

    @Override
    public void saveByParam(SysGroupMembers obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysGroupMembers obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysGroupMembers> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysGroupMembers> selectBy(Map<String, String> params) {
        QueryWrapper<SysGroupMembers> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysGroupMembers> selectPage(Map<String, String> params) {
        Page<SysGroupMembers> page = PageUtils.pageHandler(params);
        QueryWrapper<SysGroupMembers> query = getQuery(params);
        IPage<SysGroupMembers> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysGroupMembers> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysGroupMembers> page = PageUtils.pageHandler(params);
        QueryWrapper<SysGroupMembers> query = getQuery(params);
        IPage<SysGroupMembers> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysGroupMembers> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysGroupMembers> page = PageUtils.pageHandler(params);
        IPage<SysGroupMembers> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysGroupMembers> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysGroupMembers"), SysGroupMembers.class, data);
        String fileName = String.format("SysGroupMembers_%d.xls", System.currentTimeMillis());
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
        List<SysGroupMembers> dataList = new ExcelImportService().importExcelByIs(inputStream, SysGroupMembers.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysGroupMembers> query = new QueryWrapper<>();
        List<SysGroupMembers> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysGroupMembers"),
        SysGroupMembers.class, data);
        String fileName = String.format("SysGroupMembers_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysGroupMembers> getQuery(Map<String, String> params){
        QueryWrapper<SysGroupMembers> query  = new QueryWrapper<>();
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
            if("accountId".equals(entry.getKey())){
                query.eq("account_id",entry.getValue());
            }
            if("groupId".equals(entry.getKey())){
                query.eq("group_id",entry.getValue());
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
