package org.example.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.management.SysUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.management.ISysUserService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 账号信息表 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@RestController
@Tag(name = "账号信息表服务")
@RequestMapping("/sysuser")
public class SysUserController {
    @Autowired
    private ISysUserService service;

    @Autowired
    protected HttpServletRequest request;


    @GetMapping("/lock/{uid}")
    public SimpleResponse lock(@PathVariable String uid) {
        try {
            service.lock(uid);
            return new SimpleResponse.SimpleResponseBuilder().success().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @GetMapping("/unlock/{uid}")
    public SimpleResponse unlock(@PathVariable String uid) {
        try {
            service.unlock(uid);
            return new SimpleResponse.SimpleResponseBuilder().success().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping
    @ResponseBody
    @Operation(description = "创建账号信息表")
    public SimpleResponse save(@RequestBody SysUser obj){
        try {
            service.saveByParam(obj,obj.getParams());
            return new SimpleResponse.SimpleResponseBuilder().success().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }

    }

    @PutMapping("/{userid}")
    @ResponseBody
    @Operation(description = "更新账号信息表")
    public SimpleResponse update(@PathVariable(name = "userid") String userid,@RequestBody SysUser obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{userid}")
    @ResponseBody
    @Operation(description = "按ID删除账号信息表")
    public SimpleResponse remove(@PathVariable(name = "userid") String userid){
        service.logicDeleteByUserId(userid);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{userid}")
    @Operation(description = "按ID查询账号信息表")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "userid") String userid) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(userid)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个账号信息表")
    public SimpleResponse removes(@RequestBody List<String> userids){
        for (String userid : userids){
            service.logicDeleteByUserId(userid);
        }
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除账号信息表")
    public SimpleResponse deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PostMapping("/selby")
    @ResponseBody
    public SimpleResponse selectBy(@RequestBody(required = false) Map<String, String> params) {
        List<SysUser> sysusers = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(sysusers).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询账号信息表")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {

//        String appId = AppIdUtil.getAppId(request);
//        userQo.setAppId(appId);
//        userQo.setMobile(GStringUtils.toLike(userQo.getMobile()));
//        userQo.setCn(GStringUtils.toLike(userQo.getCn()));
//        userQo.setEmail(GStringUtils.toLike(userQo.getEmail()));
//        Map<String, Object> data = new HashMap<>();
//        SysUser thisUser = userService.getById(getUserId(request));
//        //判断用户角色信息
//        if (!sysRoleService.isSuperRole(getAccountId(request), appId)) {
//            userQo.setParentOrgCode(GStringUtils.toLeftLike(thisUser.getOrgCode()));
//            userQo.setCompanyId(thisUser.getCompanyId());
//        }
//        IPage<Map<String, Object>> page = userService.pageQo(userQo);
//        List<SysUserFieldConfig> userFieldConfigs = userFieldConfigService.getByAppId(userQo.getAppId());
//        final List<Map<String, Object>> records = page.getRecords();
//        for (Map<String, Object> user : records) {
//            final String userId = Objects.toString(user.get("userId"));
//            int accountCount = accountService.countByUserId(userId);
//            user.put("accountCount", accountCount);
//            for (SysUserFieldConfig userFieldConfig : userFieldConfigs) {
//                if (StringUtils.equals("dict", userFieldConfig.getComponent())) {
//                    String fieldAttrVal = user.getOrDefault(userFieldConfig.getFieldAttrName(), "").toString();
//                    final SysDict dict = dictService.getByVal(userFieldConfig.getFieldAttrName().toUpperCase(), fieldAttrVal);
//                    if (dict != null) {
//                        user.put(userFieldConfig.getFieldAttrName() + "cn", dict.getDictName());
//                    }
//                }
//            }
//        }
//        List<Map<String, Object>> map = page.getRecords();
//        for (int i = 0; i < map.size(); i++) {
//            String provinceId = map.get(i).get("provinceId") == null ? "" : map.get(i).get("provinceId").toString();
//            String cityId = map.get(i).get("cityId") == null ? "" : map.get(i).get("cityId").toString();
//            map.get(i).put("cityId", sysRegionService.getSysRegionById(cityId).getRegionName());
//            map.get(i).put("provinceId", sysRegionService.getSysRegionById(provinceId).getRegionName());
//        }
//        data.put("fields", userFieldConfigs);
//        data.put("result", page);
//        return createResponse().withData(data).build();

        try {
            IPage<SysUser> page = service.selectPage(params);
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
            IPage<SysUser> page = service.selpageCustomSqlByWrapper(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map账号信息表")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        try {
            IPage<SysUser> page = service.selpageCustomSqlByMap(params);
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

