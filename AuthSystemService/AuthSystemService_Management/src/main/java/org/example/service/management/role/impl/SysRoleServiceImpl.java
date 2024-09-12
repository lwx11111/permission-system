package org.example.service.management.role.impl;

import jakarta.annotation.Resource;
import org.example.common.domain.management.role.SysRoleDatasetRela;
import org.example.common.domain.management.role.SysRole;
import org.example.common.domain.management.role.SysRoleFunctionRela;
import org.example.dao.management.role.SysRoleDao;
import org.example.common.domain.management.role.SysRoleInherit;
import org.example.service.management.role.ISysRoleDatasetRelaService;
import org.example.service.management.role.ISysRoleFunctionRelaService;
import org.example.service.management.role.ISysRoleInheritService;
import org.example.service.management.role.ISysRoleService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private ISysRoleInheritService sysRoleInheritService;

    @Resource
    private ISysRoleFunctionRelaService sysRoleFunctionRelaService;

    @Resource
    private ISysRoleDatasetRelaService sysRoleDatasetRelaService;

    @Override
    public List<SysRole> getRolesByAccountId(String accountId, String appId) {
        return sysRoleDao.getRolesByAccountId(accountId, appId);
    }

    @Override
    public List<SysRole> getRoleByRoleIds(List<String> roleIds) {
        if(roleIds.size()==0 || roleIds == null){
            return new ArrayList<>();
        }
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (roleIds.size() > 0) {
            queryWrapper.lambda().in(SysRole::getRoleId, roleIds);
        }
        return list(queryWrapper);
    }

    @Override
    public boolean isSuperRole(String accountId, String appId) {
        List<SysRole> roleList = this.getRolesByAccountId(accountId, appId);
        if (roleList.size() > 0) {
            List<SysRoleInherit> list = sysRoleInheritService.listInheritRoles(roleList.get(0).getRoleId());
            List<String> ids = list.stream().map(id -> id.getRoleId()).collect(Collectors.toList());
            List<SysRole> roles = getRoleByRoleIds(ids);
            roleList.addAll(roles);
        }
        boolean isSuper=roleList.stream().anyMatch(role -> StringUtils.equals("super", role.getRoleCode()));
        return isSuper;
    }

    @Override
    public void saveByParam(SysRole obj,Map<String, String> params){
        obj.setCreatedBy("admin");
        obj.setCreateTime(LocalDateTime.now());
        this.save(obj);
        String roleId = obj.getRoleId();

        List<String> functions = obj.getFunctions();
        if (functions != null){
            List<SysRoleFunctionRela> relaList = functions.stream().map(funId -> {
                SysRoleFunctionRela roleFunctionRela = new SysRoleFunctionRela();
                roleFunctionRela.setFunId(funId);
                roleFunctionRela.setRoleId(roleId);
                roleFunctionRela.setCreateTime(LocalDateTime.now());
                roleFunctionRela.setCreatedBy("admin");
                return roleFunctionRela;
            }).collect(Collectors.toList());
            sysRoleFunctionRelaService.saveBatch(relaList);
        }

        List<String> dataset = obj.getDataset();
        if (dataset != null){
            List<SysRoleDatasetRela> relaList = dataset.stream().map(dsetId -> {
                SysRoleDatasetRela roleDatasetRela = new SysRoleDatasetRela();
                roleDatasetRela.setDsetId(dsetId);
                roleDatasetRela.setDataId("");
                roleDatasetRela.setRoleId(roleId);
                roleDatasetRela.setCreateTime(LocalDateTime.now());
                roleDatasetRela.setCreatedBy("admin");
                return roleDatasetRela;
            }).collect(Collectors.toList());
            sysRoleDatasetRelaService.saveBatch(relaList);
        }

        List<String> inheritRolesList = obj.getInheritRoles();
        if (inheritRolesList != null){
            List<SysRoleInherit> relaList = inheritRolesList.stream().map(inheritRoleId -> {
                SysRoleInherit roleInherit = new SysRoleInherit();
                roleInherit.setRoleId(roleId);
                roleInherit.setInheritRoleId(inheritRoleId);
                return roleInherit;
            }).collect(Collectors.toList());
            sysRoleInheritService.saveBatch(relaList);
        }
    }

    @Override
    public void updateByParam(SysRole obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysRole> selectBy(Map<String, String> params) {
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysRole> selectPage(Map<String, String> params) {
        Page<SysRole> page = PageUtils.pageHandler(params);
        QueryWrapper<SysRole> query = getQuery(params);
        IPage<SysRole> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysRole> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysRole> page = PageUtils.pageHandler(params);
        QueryWrapper<SysRole> query = getQuery(params);
        IPage<SysRole> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysRole> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysRole> page = PageUtils.pageHandler(params);
        IPage<SysRole> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysRole> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysRole"), SysRole.class, data);
        String fileName = String.format("SysRole_%d.xls", System.currentTimeMillis());
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
        List<SysRole> dataList = new ExcelImportService().importExcelByIs(inputStream, SysRole.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        List<SysRole> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysRole"),
        SysRole.class, data);
        String fileName = String.format("SysRole_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysRole> getQuery(Map<String, String> params){
        QueryWrapper<SysRole> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("roleId".equals(entry.getKey())){
                query.eq("role_id",entry.getValue());
            }
            if("roleCode".equals(entry.getKey())){
                query.eq("role_code",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("roleName".equals(entry.getKey())){
                query.eq("role_name",entry.getValue());
            }
            if("orderCode".equals(entry.getKey())){
                query.eq("order_code",entry.getValue());
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
            if("customRoleId".equals(entry.getKey())){
                query.eq("custom_role_id",entry.getValue());
            }
        }
        return  query;
    }
}
