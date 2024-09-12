package org.example.controller.monitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.common.annotation.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.monitor.LogPlatformOper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.mointor.ILogPlatformOperService;


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
@RequestMapping("/logplatformoper")
public class LogPlatformOperController {
    @Autowired
    private ILogPlatformOperService service;

//    @PostMapping
//    @ResponseBody
//    @Operation(description = "创建")
//    public SimpleResponse save(@RequestBody LogPlatformOper obj){
//        service.saveByParam(obj,obj.getParams());
//        return new SimpleResponse.SimpleResponseBuilder().success().build();
//    }
//
//    @PutMapping("/{logid}")
//    @ResponseBody
//    @Operation(description = "更新")
//    public SimpleResponse update(@PathVariable(name = "logid") String logid,@RequestBody LogPlatformOper obj){
//        service.updateByParam(obj,obj.getParams());
//        return new SimpleResponse.SimpleResponseBuilder().success().build();
//    }

    @DeleteMapping("/{logid}")
    @ResponseBody
    @Operation(description = "按ID删除")
    public SimpleResponse remove(@PathVariable(name = "logid") String logid){
        service.removeById(logid);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{logid}")
    @Operation(description = "按ID查询")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "logid") String logid) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(logid)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个")
    public SimpleResponse removes(@RequestBody List<String> logids){
        service.removeByIds(logids);
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
        List<LogPlatformOper> logplatformopers = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(logplatformopers).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询")
    @ResponseBody
    @OperationLog(value = "分页查询", type = 3)
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<LogPlatformOper> page = service.selectPage(params);
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
            IPage<LogPlatformOper> page = service.selpageCustomSqlByWrapper(params);
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
            IPage<LogPlatformOper> page = service.selpageCustomSqlByMap(params);
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

