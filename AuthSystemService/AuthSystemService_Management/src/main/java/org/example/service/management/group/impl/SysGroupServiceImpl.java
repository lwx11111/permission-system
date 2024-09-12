package org.example.service.management.group.impl;

import jakarta.annotation.Resource;
import org.example.common.domain.management.group.SysGroupDatasetRela;
import org.example.common.domain.management.group.SysGroupFunctionRela;
import org.example.common.domain.management.group.SysGroup;
import org.example.common.domain.management.group.SysGroupMembers;
import org.example.dao.management.group.SysGroupDao;
import org.example.dto.ListMembersResult;
import org.example.dto.SaveSysGroupParams;
import org.example.service.management.group.ISysGroupDatasetRelaService;
import org.example.service.management.group.ISysGroupFunctionRelaService;
import org.example.service.management.group.ISysGroupService;
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
import org.springframework.transaction.annotation.Transactional;
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
public class SysGroupServiceImpl extends ServiceImpl<SysGroupDao, SysGroup> implements ISysGroupService {

    @Resource
    private SysGroupMembersServiceImpl membersService;

    @Resource
    private ISysGroupFunctionRelaService groupFunctionRelaService;

    @Resource
    private ISysGroupDatasetRelaService groupDatasetRelaService;

    @Override
    public List<SysGroup> listGroupByAccountId(String accountId) {
        return baseMapper.listGroupByAccountId(accountId);
    }

    @Override
    public List<ListMembersResult> listMembers(String groupId, String appId) {
        return this.baseMapper.listMembers(groupId, appId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveByParam(SaveSysGroupParams params){
        SysGroup group = params.getGroup();
        // 保存基本信息
        this.save(group);
        // 保存成员信息
        for (String accountId : params.getAccountIds()){
            SysGroupMembers members = new SysGroupMembers();
            members.setAccountId(accountId);
            members.setGroupId(group.getGroupId());
            members.setCreateTime(LocalDateTime.now());
            members.setCreatedBy("admin");
            membersService.save(members);
        }


        List<String> functions = params.getFunctions();
        if (functions != null){
            List<SysGroupFunctionRela> relaList = functions.stream().map(funId -> {
                SysGroupFunctionRela groupFunctionRela = new SysGroupFunctionRela();
                groupFunctionRela.setFunId(funId);
                groupFunctionRela.setGroupId(group.getGroupId());
                groupFunctionRela.setCreateTime(LocalDateTime.now());
                groupFunctionRela.setCreatedBy("admin");
                return groupFunctionRela;
            }).collect(Collectors.toList());
            groupFunctionRelaService.saveBatch(relaList);
        }

        List<String> dataset = params.getDataset();
        if (dataset != null){
            List<SysGroupDatasetRela> relaList = dataset.stream().map(dsetId -> {
                SysGroupDatasetRela groupDatasetRela = new SysGroupDatasetRela();
                groupDatasetRela.setDsetId(dsetId);
                groupDatasetRela.setDataId("");
                groupDatasetRela.setGroupId(group.getGroupId());
                groupDatasetRela.setCreateTime(LocalDateTime.now());
                groupDatasetRela.setCreatedBy("admin");
                return groupDatasetRela;
            }).collect(Collectors.toList());
            groupDatasetRelaService.saveBatch(relaList);
        }
    }

    @Override
    public void updateByParam(SysGroup obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysGroup> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysGroup> selectBy(Map<String, String> params) {
        QueryWrapper<SysGroup> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysGroup> selectPage(Map<String, String> params) {
        Page<SysGroup> page = PageUtils.pageHandler(params);
        QueryWrapper<SysGroup> query = getQuery(params);
        IPage<SysGroup> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysGroup> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysGroup> page = PageUtils.pageHandler(params);
        QueryWrapper<SysGroup> query = getQuery(params);
        IPage<SysGroup> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysGroup> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysGroup> page = PageUtils.pageHandler(params);
        IPage<SysGroup> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysGroup> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysGroup"), SysGroup.class, data);
        String fileName = String.format("SysGroup_%d.xls", System.currentTimeMillis());
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
        List<SysGroup> dataList = new ExcelImportService().importExcelByIs(inputStream, SysGroup.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysGroup> query = new QueryWrapper<>();
        List<SysGroup> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysGroup"),
        SysGroup.class, data);
        String fileName = String.format("SysGroup_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysGroup> getQuery(Map<String, String> params){
        QueryWrapper<SysGroup> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("groupId".equals(entry.getKey())){
                query.eq("group_id",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("groupCode".equals(entry.getKey())){
                query.eq("group_code",entry.getValue());
            }
            if("groupName".equals(entry.getKey())){
                query.eq("group_name",entry.getValue());
            }
            if("status".equals(entry.getKey())){
                query.eq("status",entry.getValue());
            }
            if("supervisor".equals(entry.getKey())){
                query.eq("supervisor",entry.getValue());
            }
            if("email".equals(entry.getKey())){
                query.eq("email",entry.getValue());
            }
            if("mobile".equals(entry.getKey())){
                query.eq("mobile",entry.getValue());
            }
            if("orderCode".equals(entry.getKey())){
                query.eq("order_code",entry.getValue());
            }
            if("description".equals(entry.getKey())){
                query.eq("description",entry.getValue());
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
            if("customGroupId".equals(entry.getKey())){
                query.eq("custom_group_id",entry.getValue());
            }
        }
        return  query;
    }
}
