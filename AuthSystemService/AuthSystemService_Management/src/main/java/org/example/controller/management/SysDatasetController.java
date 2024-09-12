package org.example.controller.management;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.common.domain.management.role.SysRoleDatasetRela;
import org.example.dto.ListDatasetByRoleParams;
import org.example.service.management.role.ISysRoleDatasetRelaService;
import org.example.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.management.SysDataset;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.management.ISysDatasetService;


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
@RequestMapping("/sysdataset")
public class SysDatasetController {
    @Autowired
    private ISysDatasetService service;

    @Resource
    private ISysRoleDatasetRelaService roleDatasetRelaService;

    @Operation(description = "查询角色数据集信息")
    @RequestMapping("/listDatasetByRole")
    public SimpleResponse listDatasetByRole(@RequestBody ListDatasetByRoleParams params) {
        ValidateUtil.validate(params);
        String roleId = params.getRoleId();
        String appId = params.getAppId();

        LambdaQueryWrapper<SysDataset> lambdaQueryWrapper = new LambdaQueryWrapper<SysDataset>()
                .eq(SysDataset::getAppId, appId);
        List<SysDataset> datasetList = service.list(lambdaQueryWrapper);

        List<String> checkDataset = new ArrayList<>();
        if (StringUtils.isNotBlank(roleId)) {
            LambdaQueryWrapper<SysRoleDatasetRela> wrapper = new LambdaQueryWrapper<SysRoleDatasetRela>()
                    .eq(SysRoleDatasetRela::getRoleId, roleId);
            List<SysRoleDatasetRela> roleDatasetRelaList = roleDatasetRelaService.list(wrapper);
            checkDataset = roleDatasetRelaList.stream().map(SysRoleDatasetRela::getDsetId).collect(Collectors.toList());
            final Map<String, List<SysRoleDatasetRela>> datasetMap = roleDatasetRelaList.stream().collect(Collectors.groupingBy(SysRoleDatasetRela::getDsetId));
            datasetList.stream().forEach(dataset -> {
                final List<SysRoleDatasetRela> _roleDatasetRelaList = datasetMap.get(dataset.getId());
                if (_roleDatasetRelaList != null) {
                    dataset.setDsetDataList(_roleDatasetRelaList.stream().map(SysRoleDatasetRela::getDataId).collect(Collectors.joining(",")));
                }
            });
        }
        Map<String, Object> result = Maps.newHashMap();
        result.put("datasetList", datasetList);
        result.put("checkDataset", checkDataset);
        return new SimpleResponse.SimpleResponseBuilder().success(result).build();
    }

    @PostMapping
    @ResponseBody
    @Operation(description = "创建")
    public SimpleResponse save(@RequestBody SysDataset obj){
        service.saveByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PutMapping("/{dsetid}")
    @ResponseBody
    @Operation(description = "更新")
    public SimpleResponse update(@PathVariable(name = "dsetid") String dsetid,@RequestBody SysDataset obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{dsetid}")
    @ResponseBody
    @Operation(description = "按ID删除")
    public SimpleResponse remove(@PathVariable(name = "dsetid") String dsetid){
        service.removeById(dsetid);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{dsetid}")
    @Operation(description = "按ID查询")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "dsetid") String dsetid) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(dsetid)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个")
    public SimpleResponse removes(@RequestBody List<String> dsetids){
        service.removeByIds(dsetids);
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
        List<SysDataset> sysdatasets = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(sysdatasets).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<SysDataset> page = service.selectPage(params);
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
            IPage<SysDataset> page = service.selpageCustomSqlByWrapper(params);
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
            IPage<SysDataset> page = service.selpageCustomSqlByMap(params);
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

