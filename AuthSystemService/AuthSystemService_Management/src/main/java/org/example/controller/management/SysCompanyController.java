package org.example.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.dto.ListCompanyParams;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.management.SysCompany;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.management.ISysCompanyService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公司表 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@RestController
@Tag(name = "公司表服务")
@RequestMapping("/syscompany")
public class SysCompanyController {
    @Autowired
    private ISysCompanyService service;

    @RequestMapping("/listCompany")
    public SimpleResponse listCompany(@RequestBody ListCompanyParams params) {
//        if(!roleService.isSuperRole(getAccountId(request),appId)){
//            qo.setCompanyId(userService.getById(getUserId(request)).getCompanyId());
//        }
        List<SysCompany> companyList = service.listCompany(params);
        return new SimpleResponse.SimpleResponseBuilder().success(companyList).build();
    }

    @PostMapping
    @ResponseBody
    @Operation(description = "创建公司表")
    public SimpleResponse save(@RequestBody SysCompany obj){
        service.saveByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PutMapping("/{companyid}")
    @ResponseBody
    @Operation(description = "更新公司表")
    public SimpleResponse update(@PathVariable(name = "companyid") String companyid,@RequestBody SysCompany obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{companyid}")
    @ResponseBody
    @Operation(description = "按ID删除公司表")
    public SimpleResponse remove(@PathVariable(name = "companyid") String companyid){
        service.removeById(companyid);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{companyid}")
    @Operation(description = "按ID查询公司表")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "companyid") String companyid) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(companyid)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个公司表")
    public SimpleResponse removes(@RequestBody List<String> companyids){
        service.removeByIds(companyids);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除公司表")
    public SimpleResponse deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PostMapping("/selby")
    @ResponseBody
    public SimpleResponse selectBy(@RequestBody(required = false) Map<String, String> params) {
        List<SysCompany> syscompanys = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(syscompanys).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询公司表")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<SysCompany> page = service.selectPage(params);
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
            IPage<SysCompany> page = service.selpageCustomSqlByWrapper(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map公司表")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        try {
            IPage<SysCompany> page = service.selpageCustomSqlByMap(params);
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

