package org.example.service.management.notice.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import jakarta.annotation.Resource;
import org.example.common.domain.management.notice.SysNotice;
import org.example.common.domain.management.notice.SysNoticeGroupRela;
import org.example.common.domain.management.notice.SysNoticeReceiver;
import org.example.common.domain.management.group.SysGroupMembers;
import org.example.dao.management.notice.SysNoticeDao;
import org.example.service.management.group.impl.SysGroupMembersServiceImpl;
import org.example.service.management.notice.ISysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.collect.Lists;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;

import java.io.InputStream;
import org.example.common.utils.PageUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeDao, SysNotice> implements ISysNoticeService {

    @Resource
    private SysNoticeGroupRelaServiceImpl groupRelaService;

    @Resource
    private SysNoticeReceiverServiceImpl receiverService;

    @Resource
    private SysGroupMembersServiceImpl membersService;

//    @OperationLog(value = "公告推送", type = 6, sensitive = 4)
//    @PostMapping("/push")
//    public SimpleResponse push(@RequestParam("groupIds") String groupIds, @RequestParam("noticeId") String noticeId) {
//        log.info("groupIds={},noticeId={}", groupIds, noticeId);
//        SimpleResponse.SimpleResponseBuilder simpleResponseBuilder = createResponse();
//
//        if (StringUtils.isNotBlank(groupIds) && StringUtils.isNotBlank(noticeId)) {
//            final SysNotice notice = noticeService.getById(noticeId);
//            if (notice != null) {
//                final List<SysGroupMembers> groupMembersList = groupMembersService.listByGroupIds(Arrays.asList(groupIds.split(",")));
//                final List<String> accountIdList = groupMembersList.stream().map(SysGroupMembers::getAccountId).distinct().collect(Collectors.toList());
//                WebSocketUtils.ONLINE_USER_SESSIONS.forEach((accountId, session) -> {
//                    if (accountIdList.indexOf(accountId) != -1) {
//                        String message = JSON.toJSONStringWithDateFormat(notice, JSON.DEFFAULT_DATE_FORMAT);
//                        log.info("{} online,send notice {}", accountId, message);
//                        WebSocketUtils.sendMessage(session, message);
//                    }
//                });
//            } else {
//                throw new AuthException("无效的公告");
//            }
//        }
//        return simpleResponseBuilder.build();
//    }

    @Override
    public void saveByParam(SysNotice obj,Map<String, String> params){
        this.save(obj);
        this.publish(obj, obj.getGroupIds());
//        if (StringUtils.equals("1", push)) {
//            websocketMicroService.push(groupIds, notice.getNoticeId());
//        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean publish(SysNotice notice, List<String> groupIdList) {
        // 删除旧的关联关系
        Wrapper<SysNoticeGroupRela> wrapper = new QueryWrapper<SysNoticeGroupRela>()
                .eq("notice_id", notice.getId());
        groupRelaService.remove(wrapper);
        Wrapper<SysNoticeReceiver> receiverWrapper = new QueryWrapper<SysNoticeReceiver>()
                .eq("notice_id", notice.getId());
        receiverService.remove(receiverWrapper);
        // 添加新的关联关系
        final List<SysNoticeGroupRela> noticeGroupRelaList = groupIdList.stream().map(groupId -> {
            SysNoticeGroupRela rela = new SysNoticeGroupRela();
            rela.setGroupId(groupId);
            rela.setNoticeId(notice.getId());
            return rela;
        }).collect(Collectors.toList());
        groupRelaService.saveBatch(noticeGroupRelaList);
        // 组内联系人
        final List<SysGroupMembers> groupMembersList = membersService.listByGroupIds(groupIdList);
        final List<String> accountIdList = groupMembersList.stream().map(SysGroupMembers::getAccountId)
                .distinct().toList();
        // 添加新的接收人
        final List<SysNoticeReceiver> noticeReceiverList = accountIdList.stream().map(accountId -> {
            SysNoticeReceiver noticeReceiver = new SysNoticeReceiver();
            noticeReceiver.setAccountId(accountId);
            noticeReceiver.setCreatedBy(notice.getCreatedBy());
            noticeReceiver.setCreateTime(LocalDateTime.now());
            noticeReceiver.setNoticeId(notice.getId());
            noticeReceiver.setReadFlag("0");
            return noticeReceiver;
        }).collect(Collectors.toList());
        receiverService.saveBatch(noticeReceiverList);
        return true;
    }

    @Override
    public void updateByParam(SysNotice obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysNotice> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysNotice> selectBy(Map<String, String> params) {
        QueryWrapper<SysNotice> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysNotice> selectPage(Map<String, String> params) {
        Page<SysNotice> page = PageUtils.pageHandler(params);
        QueryWrapper<SysNotice> query = getQuery(params);
        IPage<SysNotice> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysNotice> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysNotice> page = PageUtils.pageHandler(params);
        QueryWrapper<SysNotice> query = getQuery(params);
        IPage<SysNotice> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysNotice> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysNotice> page = PageUtils.pageHandler(params);
        IPage<SysNotice> result = this.baseMapper.selpageCustomSqlByMap(page, params);
        return result;
    }

    /**
     * 下载excel模板
     *
     * @param response HttpServletResponse
     * @param request  HttpServletRequest
     * @return: void
    */
    @Override
    public void downloadExcelTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception{
        List<SysNotice> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysNotice"), SysNotice.class, data);
        String fileName = String.format("SysNotice_%d.xls", System.currentTimeMillis());
        response.setHeader("Content-Disposition", "attachment;Filename="+ fileName);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * 导入数据
     * @param file
     * @throws Exception
     */
    @Override
    public void uploadExcel(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ImportParams params = new ImportParams();
        // bean 导入
        List<SysNotice> dataList = new ExcelImportService().importExcelByIs(inputStream, SysNotice.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysNotice> query = new QueryWrapper<>();
        List<SysNotice> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysNotice"),
        SysNotice.class, data);
        String fileName = String.format("SysNotice_%d.xls", System.currentTimeMillis());
        response.setHeader("Content-Disposition", "attachment;Filename="+ fileName);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * 定义数据查询条件
     * @param params
     * @return
     */
    private  QueryWrapper<SysNotice> getQuery(Map<String, String> params){
        QueryWrapper<SysNotice> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("noticeId".equals(entry.getKey())){
                query.eq("notice_id",entry.getValue());
            }
            if("appId".equals(entry.getKey())){
                query.eq("app_id",entry.getValue());
            }
            if("noticeTitle".equals(entry.getKey())){
                query.eq("notice_title",entry.getValue());
            }
            if("noticeContent".equals(entry.getKey())){
                query.eq("notice_content",entry.getValue());
            }
            if("influenceScope".equals(entry.getKey())){
                query.eq("influence_scope",entry.getValue());
            }
            if("noticeReason".equals(entry.getKey())){
                query.eq("notice_reason",entry.getValue());
            }
            if("contactPerson".equals(entry.getKey())){
                query.eq("contact_person",entry.getValue());
            }
            if("contactPersonTel".equals(entry.getKey())){
                query.eq("contact_person_tel",entry.getValue());
            }
            if("remark".equals(entry.getKey())){
                query.eq("remark",entry.getValue());
            }
            if("status".equals(entry.getKey())){
                query.eq("status",entry.getValue());
            }
            if("createdBy".equals(entry.getKey())){
                query.eq("created_by",entry.getValue());
            }
            if("createTime".equals(entry.getKey())){
                query.eq("create_time",entry.getValue());
            }
            if("updatedBy".equals(entry.getKey())){
                query.eq("updated_by",entry.getValue());
            }
            if("updatedTime".equals(entry.getKey())){
                query.eq("updated_time",entry.getValue());
            }
        }
        return  query;
    }
}
