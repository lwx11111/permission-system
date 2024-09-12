package org.example.controller.management;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.example.common.domain.management.account.SysAccount;
import org.example.dto.ListGroupsParams;
import org.example.dto.ListMembersParams;
import org.example.dto.ListMembersResult;
import org.example.dto.SaveSysGroupParams;
import org.example.service.management.group.ISysGroupMembersService;
import org.example.service.management.account.ISysAccountService;
import org.example.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.domain.management.group.SysGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.management.group.ISysGroupService;


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
@RequestMapping("/sysgroup")
public class SysGroupController {
    @Autowired
    private ISysGroupService service;

    @Autowired
    private ISysGroupMembersService membersService;

    @Autowired
    private ISysAccountService accountService;


    @Operation(description = "查询组成员信息")
    @RequestMapping("/listGroups")
    public SimpleResponse listGroups(@RequestBody ListGroupsParams params) {
        ValidateUtil.validate(params);
        LambdaQueryWrapper<SysGroup> queryWrapper = new LambdaQueryWrapper<SysGroup>()
                .eq(SysGroup::getAppId, params.getAppId());
        List<SysGroup> list = service.list(queryWrapper);
        return new SimpleResponse.SimpleResponseBuilder().success(list).build();
    }

    @Operation(description = "查询组成员信息")
    @RequestMapping("/listMembers")
    public SimpleResponse listMembers(@RequestBody ListMembersParams params) {
        ValidateUtil.validate(params);
        Map<String, Object> data = new HashMap<>();
        List<String> memberIds = new ArrayList<>();
        // 查询已有的成员
        String appId = params.getAppId();
        String groupId = params.getGroupId();
        if(StringUtils.isNotBlank(groupId)){
            List<ListMembersResult> members = service.listMembers(groupId, appId);
            memberIds = members.stream().map(ListMembersResult::getAccountId).collect(Collectors.toList());
        }
        data.put("members", memberIds);
        // 查询用户信息
        Map<String,String> accountParams = new HashMap<>();
        accountParams.put("appId", appId);
        accountParams.put("pageSize", params.getPageSize().toString());
        accountParams.put("pageNum", params.getPageNum().toString());
        IPage<SysAccount> users = accountService.selectPage(accountParams);
        data.put("allUsers", users);
        return new SimpleResponse.SimpleResponseBuilder().success(data).build();
    }

    @PostMapping
    @ResponseBody
    @Operation(description = "创建")
    public SimpleResponse save(@RequestBody SaveSysGroupParams params){
        service.saveByParam(params);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @PutMapping("/{groupid}")
    @ResponseBody
    @Operation(description = "更新")
    public SimpleResponse update(@PathVariable(name = "groupid") String groupid,@RequestBody SysGroup obj){
        service.updateByParam(obj,obj.getParams());
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @DeleteMapping("/{groupid}")
    @ResponseBody
    @Operation(description = "按ID删除")
    public SimpleResponse remove(@PathVariable(name = "groupid") String groupid){
        service.removeById(groupid);
        return new SimpleResponse.SimpleResponseBuilder().success().build();
    }

    @GetMapping("/{groupid}")
    @Operation(description = "按ID查询")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "groupid") String groupid) {
        return new SimpleResponse.SimpleResponseBuilder().success(service.getById(groupid)).build();
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个")
    public SimpleResponse removes(@RequestBody List<String> groupids){
        service.removeByIds(groupids);
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
        List<SysGroup> sysgroups = service.selectBy(params);
        return new SimpleResponse.SimpleResponseBuilder().success(sysgroups).build();
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        try {
            IPage<SysGroup> page = service.selectPage(params);
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
            IPage<SysGroup> page = service.selpageCustomSqlByWrapper(params);
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
            IPage<SysGroup> page = service.selpageCustomSqlByMap(params);
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

