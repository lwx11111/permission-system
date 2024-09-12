package org.example.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.dto.ListOrgsByCompanyParams;
import org.example.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.management.SysOrg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.management.ISysOrgService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公司部门表 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@RestController
@Tag(name = "公司部门表服务")
@RequestMapping("/sysorg")
public class SysOrgController {
    @Autowired
    private ISysOrgService service;

    @PostMapping("/listOrgsByCompany")
    public SimpleResponse listOrgsByCompany(@RequestBody ListOrgsByCompanyParams params) {
        ValidateUtil.validate(params);
        List<SysOrg> list = service.listOrgsByCompany(params);
        return new SimpleResponse.SimpleResponseBuilder().success(list).build();
    }

    @PostMapping
    @ResponseBody
    @Operation(description = "创建公司部门表")
    public SimpleResponse save(@RequestBody SysOrg obj){
        service.saveByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PutMapping("/{orgcode}")
    @ResponseBody
    @Operation(description = "更新公司部门表")
    public SimpleResponse update(@PathVariable(name = "orgcode") String orgcode,@RequestBody SysOrg obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{orgcode}")
    @ResponseBody
    @Operation(description = "按ID删除公司部门表")
    public SimpleResponse remove(@PathVariable(name = "orgcode") String orgcode){
        service.removeById(orgcode);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{orgcode}")
    @Operation(description = "按ID查询公司部门表")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "orgcode") String orgcode) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(orgcode)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个公司部门表")
    public SimpleResponse removes(@RequestBody List<String> orgcodes){
        service.removeByIds(orgcodes);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除公司部门表")
    public SimpleResponse deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PostMapping("/selby")
    @ResponseBody
    public SimpleResponse selectBy(@RequestBody(required = false) Map<String, String> params) {
        List<SysOrg> sysorgs = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(sysorgs).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询公司部门表")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<SysOrg> page = service.selectPage(params);
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
            IPage<SysOrg> page = service.selpageCustomSqlByWrapper(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map公司部门表")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        try {
            IPage<SysOrg> page = service.selpageCustomSqlByMap(params);
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

