package org.example.controller.management;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.example.dto.LeftMenuParams;
import org.example.dto.ListFunctionsByAccountParams;
import org.example.utils.TreeBuilder;
import org.example.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.management.SysFunction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.management.ISysFunctionService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能路由表 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@RestController
@Tag(name = "功能路由表服务")
@RequestMapping("/sysfunction")
public class SysFunctionController {
    @Autowired
    private ISysFunctionService service;

    @PostMapping("/listFunctionsByAccount")
    @Operation(description = "获取账户对应的私有功能项")
    public SimpleResponse listFunctionsByAccount(@RequestBody ListFunctionsByAccountParams params) {
        ValidateUtil.validate(params);
        String accountId = params.getAccountId();
        String roleId = params.getRoleId();
        String appId = params.getAppId();

        Map<String, Object> data = new HashMap<>();

        LambdaQueryWrapper<SysFunction> queryWrapper = new LambdaQueryWrapper<SysFunction>()
                .eq(SysFunction::getAppId, appId)
                .eq(SysFunction::getStatus, "1");
        List<SysFunction> functionList = service.list(queryWrapper);
        functionList = TreeBuilder.buildTree(functionList);

        if (accountId != null){
            List<SysFunction> checkMenuList = service.listFunctionsByAccountId(accountId);
            List<String> checkMenuIds = checkMenuList.stream()
                    .filter(function -> {
                        if (StringUtils.equals("1", function.getLeaf())) {
                            return true;
                        }
                        return false;
                    })
                    .map(SysFunction::getFunId)
                    .collect(Collectors.toList());
            data.put("menus", functionList);
            data.put("checkMenuIds", checkMenuIds);
            return new SimpleResponse.SimpleResponseBuilder().success(data).build();
        } else if (roleId != null){
            List<String> checkMenuIds = new ArrayList<>();
            List<SysFunction> checkMenuList = service.getByRoleId(roleId, appId);
            checkMenuIds = checkMenuList.stream()
                    .filter(function -> {
                        if (StringUtils.equals("1", function.getLeaf())) {
                            return true;
                        }
                        return false;
                    })
                    .map(SysFunction::getFunId).collect(Collectors.toList());
//            service.setRoleResData(functionList, roleId, checkMenuList);
            data.put("menus", functionList);
            data.put("checkMenuIds", checkMenuIds);
            return new SimpleResponse.SimpleResponseBuilder().success(data).build();
        }
        return new SimpleResponse.SimpleResponseBuilder().failure("Aid roleid null").build();
    }

    @PostMapping("/left-menu")
    @ResponseBody
    @Operation(description = "创建功能路由表")
    public SimpleResponse getLeftMenu(@RequestBody LeftMenuParams params) {
        // 验证参数是否符合要求
        ValidateUtil.validate(params);
        String accountId = params.getAccountId();
        String appId = params.getAppId();

        Set<SysFunction> functionList = service.listLeftMenuByAccountId(accountId, appId, "1");
        List<SysFunction> data = functionList.stream().sorted(
                Comparator.comparing(SysFunction::getOrderCode,
                        Comparator.nullsLast(String::compareTo))).collect(Collectors.toList());
        for (SysFunction sysFunction : data) {
            System.out.println(sysFunction.getFunName());
        }
        return new SimpleResponse.SimpleResponseBuilder().success(data).build();
    }


    @PostMapping
    @ResponseBody
    @Operation(description = "创建功能路由表")
    public SimpleResponse save(@RequestBody SysFunction obj){
        service.saveByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PutMapping("/{funid}")
    @ResponseBody
    @Operation(description = "更新功能路由表")
    public SimpleResponse update(@PathVariable(name = "funid") String funid,@RequestBody SysFunction obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{funid}")
    @ResponseBody
    @Operation(description = "按ID删除功能路由表")
    public SimpleResponse remove(@PathVariable(name = "funid") String funid){
        service.removeById(funid);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{funid}")
    @Operation(description = "按ID查询功能路由表")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "funid") String funid) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(funid)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个功能路由表")
    public SimpleResponse removes(@RequestBody List<String> funids){
        service.removeByIds(funids);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除功能路由表")
    public SimpleResponse deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PostMapping("/selby")
    @ResponseBody
    public SimpleResponse selectBy(@RequestBody(required = false) Map<String, String> params) {
        List<SysFunction> sysfunctions = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(sysfunctions).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询功能路由表")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<SysFunction> page = service.selectPage(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByWrapper")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByWrapper(@RequestBody Map<String, String> params) {
        try {
            IPage<SysFunction> page = service.selpageCustomSqlByWrapper(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map功能路由表")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        try {
            IPage<SysFunction> page = service.selpageCustomSqlByMap(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    /**
     * 下载excel模板
     * @param response
     * @param request
     * @throws Exception
     */
    @PostMapping("/downloadExcelTemplate")
    @ResponseBody
    public void downloadExcelTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {
        service.downloadExcelTemplate(response, request);
    }

    /**
     * excel导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/uploadExcel")
    @ResponseBody
    public void uploadExcel(@RequestParam MultipartFile file) throws Exception {
        service.uploadExcel(file);
    }

    @PostMapping("/excel")
    @ResponseBody
    public void excel(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, String> params) throws Exception {
        service.excel(response, request, params);
    }
}

