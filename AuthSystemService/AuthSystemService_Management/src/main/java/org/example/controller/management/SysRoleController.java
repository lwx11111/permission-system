package org.example.controller.management;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.common.domain.management.role.SysRoleInherit;
import org.example.dto.RoleInheritanceParams;
import org.example.dto.RoleInheritanceResult;
import org.example.service.management.role.ISysRoleInheritService;
import org.example.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.management.role.SysRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.management.role.ISysRoleService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@RestController
@Tag(name = "角色表服务")
@RequestMapping("/sysrole")
public class SysRoleController {
    @Autowired
    private ISysRoleService service;

    @Autowired
    private ISysRoleInheritService inheritService;

    @Operation(description = "获取角色对应的继承角色信息")
    @RequestMapping("/roleInheritance")
    public SimpleResponse roleInheritance(@RequestBody RoleInheritanceParams roleInheritance){
        RoleInheritanceResult result = new RoleInheritanceResult();
        ValidateUtil.validate(roleInheritance);
        String roleId = roleInheritance.getRoleId();
        String appId = roleInheritance.getAppId();
        // 所有角色信息
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getAppId, appId);
        if (roleId != null){
            queryWrapper.ne(SysRole::getRoleId, roleId);
        }
        List<SysRole> roles = service.list(queryWrapper);
        // 查询已经继承的角色
        List<String> inheritRoles = new ArrayList<>();
        if (roleId != null) {
            LambdaQueryWrapper<SysRoleInherit> wrapper = new LambdaQueryWrapper<SysRoleInherit>()
                    .eq(SysRoleInherit::getRoleId, roleId);

            List<SysRoleInherit> roleInheritList = inheritService.list(wrapper);
            inheritRoles = roleInheritList.stream().map(SysRoleInherit::getInheritRoleId).collect(Collectors.toList());
        }
        result.setInheritRoles(inheritRoles);
        result.setRoles(roles);
        return new SimpleResponse.SimpleResponseBuilder().success(result).build();
    }

    @PostMapping
    @ResponseBody
    @Operation(description = "创建角色表")
    public SimpleResponse save(@RequestBody SysRole obj){
        service.saveByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PutMapping("/{roleid}")
    @ResponseBody
    @Operation(description = "更新角色表")
    public SimpleResponse update(@PathVariable(name = "roleid") String roleid,@RequestBody SysRole obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{roleid}")
    @ResponseBody
    @Operation(description = "按ID删除角色表")
    public SimpleResponse remove(@PathVariable(name = "roleid") String roleid){
        service.removeById(roleid);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{roleid}")
    @Operation(description = "按ID查询角色表")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "roleid") String roleid) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(roleid)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个角色表")
    public SimpleResponse removes(@RequestBody List<String> roleids){
        service.removeByIds(roleids);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除角色表")
    public SimpleResponse deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PostMapping("/selby")
    @ResponseBody
    public SimpleResponse selectBy(@RequestBody(required = false) Map<String, String> params) {
        List<SysRole> sysroles = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(sysroles).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询角色表")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<SysRole> page = service.selectPage(params);
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
            IPage<SysRole> page = service.selpageCustomSqlByWrapper(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map角色表")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        try {
            IPage<SysRole> page = service.selpageCustomSqlByMap(params);
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

