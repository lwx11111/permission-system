package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.SysOss;
import org.example.service.ISysOssService;
import org.example.common.web.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwx20
 * @since 2023-11-13
 */
@RestController
@Tag(name = "服务")
@RequestMapping("/sysoss")
public class SysOssController {
    @Autowired
    private ISysOssService service;

    /**
     * 对象存储
     * @param file
     * @param groupId
     * @return
     * @throws Exception
     */
    @PostMapping("/anon/uploadOSS")
    @ResponseBody
    @Operation(description = "上传附件")
    public SimpleResponse uploadOSS(@RequestParam MultipartFile file, @RequestParam(required = false) String groupId,
                                    @RequestParam(required = false) String groupName,
                                    @RequestParam(required = false) String bizId,
                                    @RequestParam(required = false) String groupInfoId,
                                    @RequestParam(required = false) String groupInfoName,
                                    @RequestParam(required = false) String fileName,
                                    @RequestParam(required = false) String tagName) throws Exception {
        SimpleResponse response = new SimpleResponse();
        try {
            response.setData(service.uploadOSS(file, groupId,groupName,bizId,groupInfoId,groupInfoName,fileName, tagName));
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 删除文件
     * @return
     * @throws Exception
     */
    @PostMapping("/anon/deleteFileByUrl")
    @ResponseBody
    @Operation(description = "上传附件")
    public SimpleResponse deleteFileByUrl(@RequestBody Map<String, String> json) throws Exception {
        SimpleResponse response = new SimpleResponse();
        try {
            String url = json.get("url");
            response.setData(service.deleteFileByUrl(url));
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 删除文件
     * @param groupId
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteFileByGroupId")
    @ResponseBody
    @Operation(description = "上传附件")
    public SimpleResponse deleteFileByGroupId(@RequestBody String groupId) throws Exception {
        SimpleResponse response = new SimpleResponse();
        try {
            if (service.deleteFileByGroupId(groupId)){
                response.setMessage("删除成功");
            } else {
                response.setCode(201);
                response.setMessage("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 删除文件
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteFileByStorageFileName")
    @ResponseBody
    @Operation(description = "上传附件")
    public SimpleResponse deleteFileByStorageFileName(@RequestBody String storageFileName){
        SimpleResponse response = new SimpleResponse();
        try {
            if (service.deleteFileByStorageFileName(storageFileName)){
                response.setMessage("删除成功");
            } else {
                response.setCode(201);
                response.setMessage("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping
    @ResponseBody
    @Operation(description = "创建")
    public SimpleResponse save(@RequestBody SysOss obj){
        SimpleResponse response = new SimpleResponse();
        try {
            service.saveByParam(obj,obj.getParams());
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Operation(description = "更新")
    public SimpleResponse update(@PathVariable(name = "id") String id,@RequestBody SysOss obj){
        SimpleResponse response = new SimpleResponse();
        try {
            service.updateByParam(obj,obj.getParams());
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(description = "按ID删除")
    public SimpleResponse remove(@PathVariable(name = "id") String id){
        SimpleResponse response = new SimpleResponse();
        try {
        service.removeById(id);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    @Operation(description = "按ID查询")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "id") String id) {
        SimpleResponse response = new SimpleResponse();
        try {
            response.setData(service.getById(id));
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个")
    public SimpleResponse removes(@RequestBody List<String> ids){
        SimpleResponse response = new SimpleResponse();
        try {
            service.removeByIds(ids);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除")
    public void deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
    }

    @PostMapping("/selby")
    @ResponseBody
    public List<SysOss> selectBy(@RequestBody(required = false) Map<String, String> params) {
        return  service.selectBy(params);
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        SimpleResponse response = new SimpleResponse();
        try {
            IPage<SysOss> page = service.selectPage(params);
            response.setData(page);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/selpageCustomSqlByWrapper")
    @ResponseBody
    public IPage<SysOss> selpageCustomSqlByWrapper(@RequestBody Map<String, String> params) {
        return service.selpageCustomSqlByWrapper(params);
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map")
    @ResponseBody
    public IPage<SysOss> selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        return service.selpageCustomSqlByMap(params);
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

