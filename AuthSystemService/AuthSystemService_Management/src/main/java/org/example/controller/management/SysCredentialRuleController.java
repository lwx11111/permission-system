package org.example.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.management.SysCredentialRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.management.ISysCredentialRuleService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统密码规则 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@RestController
@Tag(name = "系统密码规则服务")
@RequestMapping("/syscredentialrule")
public class SysCredentialRuleController {
    @Autowired
    private ISysCredentialRuleService service;

    @PostMapping
    @ResponseBody
    @Operation(description = "创建系统密码规则")
    public SimpleResponse save(@RequestBody SysCredentialRule obj){
        service.saveByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PutMapping("/{ruleid}")
    @ResponseBody
    @Operation(description = "更新系统密码规则")
    public SimpleResponse update(@PathVariable(name = "ruleid") String ruleid,@RequestBody SysCredentialRule obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{ruleid}")
    @ResponseBody
    @Operation(description = "按ID删除系统密码规则")
    public SimpleResponse remove(@PathVariable(name = "ruleid") String ruleid){
        service.removeById(ruleid);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{ruleid}")
    @Operation(description = "按ID查询系统密码规则")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "ruleid") String ruleid) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(ruleid)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个系统密码规则")
    public SimpleResponse removes(@RequestBody List<String> ruleids){
        service.removeByIds(ruleids);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除系统密码规则")
    public SimpleResponse deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PostMapping("/selby")
    @ResponseBody
    public SimpleResponse selectBy(@RequestBody(required = false) Map<String, String> params) {
        List<SysCredentialRule> syscredentialrules = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(syscredentialrules).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询系统密码规则")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<SysCredentialRule> page = service.selectPage(params);
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
            IPage<SysCredentialRule> page = service.selpageCustomSqlByWrapper(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map系统密码规则")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        try {
            IPage<SysCredentialRule> page = service.selpageCustomSqlByMap(params);
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

