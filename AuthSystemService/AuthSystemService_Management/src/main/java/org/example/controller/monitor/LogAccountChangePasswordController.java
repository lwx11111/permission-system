package org.example.controller.monitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.monitor.LogAccountChangePassword;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.mointor.ILogAccountChangePasswordService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@RestController
@Tag(name = "服务")
@RequestMapping("/logaccountchangepassword")
public class LogAccountChangePasswordController {
    @Autowired
    private ILogAccountChangePasswordService service;

    @PostMapping
    @ResponseBody
    @Operation(description = "创建")
    public SimpleResponse save(@RequestBody LogAccountChangePassword obj){
        service.saveByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Operation(description = "更新")
    public SimpleResponse update(@PathVariable(name = "id") String id,@RequestBody LogAccountChangePassword obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(description = "按ID删除")
    public SimpleResponse remove(@PathVariable(name = "id") String id){
        service.removeById(id);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{id}")
    @Operation(description = "按ID查询")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "id") String id) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(id)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个")
    public SimpleResponse removes(@RequestBody List<String> ids){
        service.removeByIds(ids);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除")
    public SimpleResponse deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PostMapping("/selby")
    @ResponseBody
    public SimpleResponse selectBy(@RequestBody(required = false) Map<String, String> params) {
        List<LogAccountChangePassword> logaccountchangepasswords = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(logaccountchangepasswords).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<LogAccountChangePassword> page = service.selectPage(params);
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
            IPage<LogAccountChangePassword> page = service.selpageCustomSqlByWrapper(params);
            return new SimpleResponse.SimpleResponseBuilder().success(page).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
        }
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map")
    @ResponseBody
    public SimpleResponse selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        try {
            IPage<LogAccountChangePassword> page = service.selpageCustomSqlByMap(params);
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

