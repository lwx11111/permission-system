package org.example.service.management.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.common.domain.management.SysFunction;
import org.example.dao.management.SysFunctionDao;
import org.example.service.management.ISysFunctionService;
import org.example.service.management.role.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.utils.TreeBuilder;
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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 功能路由表 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
@Slf4j
public class SysFunctionServiceImpl extends ServiceImpl<SysFunctionDao, SysFunction> implements ISysFunctionService {

    @Resource
    private ISysRoleService roleService;

    @Override
    public List<SysFunction> getByRoleId(String roleId, String appId) {
        return baseMapper.getByRoleId(roleId, appId);
    }

    @Override
    public List<SysFunction> listFunctionsByAccountId(String accountId) {
        return baseMapper.listFunctionsByAccountId(accountId);
    }

    @Override
    public List<SysFunction> listByParentId(String parentId, String appId, String inherent) {
        QueryWrapper<SysFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysFunction::getParentId, parentId);
        queryWrapper.lambda().eq(SysFunction::getAppId, appId);
        queryWrapper.lambda().eq(SysFunction::getStatus, "1");
        if (StringUtils.isNotBlank(inherent)) {
//            queryWrapper.lambda().eq(SysFunction::getInherent, inherent);
        }
        queryWrapper.lambda().orderByAsc(SysFunction::getOrderCode);
        return list(queryWrapper);
    }

    /**
     * 递归查询父级菜单
     * @param id
     * @return
     */
    private SysFunction findZeroMenu(String id) {
        SysFunction function = getById(id);
        if (!StringUtils.equals(function.getParentId(), "0")) {
            return findZeroMenu(function.getParentId());
        } else {
            return function;
        }
    }

    @Override
    public List<SysFunction> listByAccountId(String accountId, String parentId, String appId, String inherent, String funType) {
        return this.baseMapper.listByAccountId(accountId, parentId, appId, inherent, funType);
    }

    @Override
    public Set<SysFunction> listLeftMenuByAccountId(String accountId, String appId, String inherent) {
        final boolean superRole = roleService.isSuperRole(accountId, appId);
        Set<SysFunction> menus = new LinkedHashSet<>();
        if (!superRole) {
            //先查询拥有的角色的第一级的数据
            List<SysFunction> hasFunction = this.listByAccountId(accountId, null, appId, inherent, null);
            for (SysFunction function : hasFunction) {
                if (!StringUtils.equals(function.getParentId(), "0")) {
                    menus.add(this.findZeroMenu(function.getFunId()));
                } else {
                    menus.add(function);
                }
            }
            //在查询拥有角色第二级的数据
            for (SysFunction menu : menus) {
                if (menu.getLeaf().equals("0")) {
                    List<SysFunction> children = listByAccountId(accountId, menu.getFunId(), appId, inherent, "0");
                    menu.setChildren(children);
                }
            }
        } else {
            menus = new LinkedHashSet<>(this.listByParentId("0", appId, inherent));
            for (SysFunction menu : menus) {
                if ("0".equals(menu.getLeaf())) {
                    List<SysFunction> children = listByParentId(menu.getFunId(), appId, inherent);
                    menu.setChildren(children);
                }
            }
        }
        return menus;
    }

    @Override
    public void saveByParam(SysFunction obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysFunction obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysFunction> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysFunction> selectBy(Map<String, String> params) {
        QueryWrapper<SysFunction> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysFunction> selectPage(Map<String, String> params) {
        Page<SysFunction> page = PageUtils.pageHandler(params);
        QueryWrapper<SysFunction> query = getQuery(params);
        List<SysFunction> result = this.list(query);
        result = TreeBuilder.buildTree(result);
        // 树形
        IPage<SysFunction> res = page.setRecords(result);
        return res;
    }

    @Override
    public IPage<SysFunction> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysFunction> page = PageUtils.pageHandler(params);
        QueryWrapper<SysFunction> query = getQuery(params);
        IPage<SysFunction> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysFunction> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysFunction> page = PageUtils.pageHandler(params);
        IPage<SysFunction> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysFunction> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysFunction"), SysFunction.class, data);
        String fileName = String.format("SysFunction_%d.xls", System.currentTimeMillis());
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
        List<SysFunction> dataList = new ExcelImportService().importExcelByIs(inputStream, SysFunction.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysFunction> query = new QueryWrapper<>();
        List<SysFunction> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysFunction"),
        SysFunction.class, data);
        String fileName = String.format("SysFunction_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysFunction> getQuery(Map<String, String> params){
        QueryWrapper<SysFunction> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("funId".equals(entry.getKey())){
                query.eq("fun_id",entry.getValue());
            }
            if("funType".equals(entry.getKey())){
                query.eq("fun_type",entry.getValue());
            }
            if("funCode".equals(entry.getKey())){
                query.eq("fun_code",entry.getValue());
            }
            if("funName".equals(entry.getKey())){
                query.eq("fun_name",entry.getValue());
            }
            if("url".equals(entry.getKey())){
                query.eq("url",entry.getValue());
            }
            if("extendurl".equals(entry.getKey())){
                query.eq("extendurl",entry.getValue());
            }
            if("query".equals(entry.getKey())){
                query.eq("query",entry.getValue());
            }
            if("icon".equals(entry.getKey())){
                query.eq("icon",entry.getValue());
            }
            if("leaf".equals(entry.getKey())){
                query.eq("leaf",entry.getValue());
            }
            if("permKey".equals(entry.getKey())){
                query.eq("perm_key",entry.getValue());
            }
            if("parentId".equals(entry.getKey())){
                query.eq("parent_id",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("status".equals(entry.getKey())){
                query.eq("status",entry.getValue());
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
            if("inherent".equals(entry.getKey())){
                query.eq("inherent",entry.getValue());
            }
            if("customFunId".equals(entry.getKey())){
                query.eq("custom_fun_id",entry.getValue());
            }
            if("orderCode".equals(entry.getKey())){
                query.eq("order_code",entry.getValue());
            }
        }
        return  query;
    }
}
