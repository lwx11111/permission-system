package org.example.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.dao.SysOssMapper;
import org.example.common.domain.SysOss;
import org.example.service.ISysOssService;
import org.example.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import io.minio.*;
import io.minio.errors.MinioException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwx20
 * @since 2023-11-13
 */
@Service
@Slf4j
public class SysOssServiceImpl extends ServiceImpl<SysOssMapper, SysOss> implements ISysOssService {
    @Autowired
    private SysOssMapper mapper;
    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.port}")
    private Integer port;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucket;

    @Override
    public Boolean deleteFileByUrl(String url) throws Exception {
        MinioClient minioClient = this.getMinioClient();
        // 截取字符串
        String[] parts = url.split("/" + bucket + "/");
        System.out.println(url);
        System.out.println(parts.length);
        if (parts.length <= 1){
            return false;
        }
        String storageFileName = parts[1];
        System.out.println("截取到的字符为: " + storageFileName);
        // 删除对象
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(storageFileName)
                        .build()
        );

        // 数据库删除
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("storage_file_name",storageFileName);
        mapper.deleteByMap(param);
        return true;
    }

    public void test1() throws Exception {
        try {
            // 创建MinioClient对象
            MinioClient minioClient = MinioClient.builder()
                    .endpoint("http://127.0.0.1:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();

            // 定义存储桶和对象名称
            String bucketName = "test";
            String objectName = "test.png";
            String filePath = "C:\\Users\\lwx20\\Desktop\\test.png";

            // 上传对象到存储桶
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename(filePath)
                            .build()
            );
            System.out.println("Uploaded object to bucket.");

            // 下载对象
            try (InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            )) {
                Files.copy(stream, Path.of("C:\\Users\\lwx20\\Desktop\\downloaded-" + objectName), StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("Downloaded object from bucket.");

            // 删除对象
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
            System.out.println("Deleted object from bucket.");

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }

    }

    /**
     * 获取 minio 客户端
     * @return
     */
    private MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(endpoint, port, false)
                .credentials(accessKey, secretKey)
                .build();
    }

    /**
     * 检查存储桶，不存在则创建
     * @param minioClient
     * @throws Exception
     */
    private void checkBucket(MinioClient minioClient, String bucketName) throws Exception {
        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if(!isExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
    /**
     * 对象存储
     * @param file
     * @param groupId
     * @return
     * @throws Exception
     */
    @Override
    public Map uploadOSS(MultipartFile file, String groupId,String groupName,String bizId,String groupInfoId,String groupInfoName,String fileName, String tagName) throws Exception {
        long start = System.currentTimeMillis();
        Map<String,String> params = new HashMap();
        params.put("groupId",groupId);
        params.put("bizId",bizId);
        params.put("groupName",groupName);
        params.put("groupInfoId",groupInfoId);

        // 附件 ID
        String id = IdWorker.get32UUID();
        // 附件原始名称
        String orignalFileName = file.getOriginalFilename();
        int begin = (file.getOriginalFilename().lastIndexOf("."))+1;
        int last = file.getOriginalFilename().length();

        String fileType = file.getOriginalFilename().substring(begin, last).toLowerCase(Locale.ROOT);

        //获得文件后缀名
        if(fileName !=null && !"null".equals(fileName) && !"".equals(fileName)){
            orignalFileName =  fileName +"."+ fileType;
        }
        String bucketName = bucket;

        // 如果没有 groupId，则生成一个
        if(groupId == null || "null".equals(groupId) || StrUtil.isBlank(groupId)){
            groupId = IdWorker.get32UUID();
        }

        // 为apk和wgt文件命名为同一格式：id.文件后缀
        String storageFileName = null;
        if("apk".equals(fileType) || "wgt".equals(fileType)) {
            bucketName = "app-daiwei";
            storageFileName = id + "." + fileType;
        }
        // 重命名为唯一值
        if (storageFileName == null) {
            //由于文件名过长导致不能上传至minio上
            storageFileName = id + "_." + fileType;
        }
        // 上传文件到 minio 服务器
        // 连接 minio 服务器

        log.info("手机端上传文件到minio前的工作，工单号：{}，耗时：{}ms",groupId, (System.currentTimeMillis()-start));
        start= System.currentTimeMillis();
        MinioClient minioClient = this.getMinioClient();
        // 检查存储桶是否存在，不存在就创建存储桶
        this.checkBucket(minioClient, bucketName);
        // 上传文件
        this.uploadMinio(minioClient, file, storageFileName, bucketName);
        log.info("手机端上传文件到minio，工单号：{}，耗时：{}ms",groupId, (System.currentTimeMillis()-start));
        String userId="";
        try {
            start= System.currentTimeMillis();
            userId = "1";
            log.info("手机端上传文件查询用户信息，工单号：{}，耗时：{}ms",groupId, (System.currentTimeMillis()-start));
        }catch (Exception e){
            log.error("上传文件查询用户信息报错,uploadOSS",e);
        }
        start= System.currentTimeMillis();
        // 保存附件信息
        SysOss sysOss = new SysOss()
                .setId(id)
                .setGroupId(groupId)
                .setName(orignalFileName)
                .setStorageFileName(storageFileName)
                .setBucket(bucketName)
                .setBizId(bizId)
                .setCreatedById(userId)
                .setGroupName(groupName)
                .setStatus(1)
                .setGroupInfoId(groupInfoId)
                .setGroupInfoName(groupInfoName)
                .setTagName(tagName);
        this.save(sysOss);

        log.info("手机端上传文件保存到数据库，工单号：{}，耗时：{}ms",groupId, (System.currentTimeMillis()-start));
        // 返回附件信息
        Map<String, String> map = Maps.newHashMap();
        map.put("storageFileName",storageFileName);
        map.put("groupId", groupId);
        map.put("result", "true");
        map.put("bucket", bucket);
        map.put("message", "上传成功");
        return map;
    }

    @Override
    public Boolean deleteFileByGroupId(String groupId) throws Exception {
        return null;
    }

    @Override
    public Boolean deleteFileByStorageFileName(String storageFileName) throws Exception {
        MinioClient minioClient = this.getMinioClient();
        // json 形式 为“str”
        storageFileName = storageFileName.substring(1,storageFileName.length() - 1);
        System.out.println(storageFileName);
        // 删除对象
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(storageFileName)
                        .build()
        );

        // 数据库删除
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("storage_file_name",storageFileName);
        System.out.println(storageFileName);
        mapper.deleteByMap(param);
        return true;
    }

    /**
     * 上传文件至 minio
     * @param minioClient
     * @param file
     * @param storageFileName
     * @throws Exception
     */
    private void uploadMinio (MinioClient minioClient, MultipartFile file, String storageFileName, String bucketName) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(storageFileName).stream(
                                bais, bais.available(), -1)
                        .build());
        bais.close();
    }
    @Override
    public void saveByParam(SysOss obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(SysOss obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<SysOss> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<SysOss> selectBy(Map<String, String> params) {
        QueryWrapper<SysOss> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<SysOss> selectPage(Map<String, String> params) {
        Page<SysOss> page = PageUtils.pageHandler(params);
        QueryWrapper<SysOss> query = getQuery(params);
        IPage<SysOss> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<SysOss> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<SysOss> page = PageUtils.pageHandler(params);
        QueryWrapper<SysOss> query = getQuery(params);
        IPage<SysOss> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<SysOss> selpageCustomSqlByMap(Map<String, String> params) {
        Page<SysOss> page = PageUtils.pageHandler(params);
        IPage<SysOss> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<SysOss> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysOss"), SysOss.class, data);
        String fileName = String.format("SysOss_%d.xls", System.currentTimeMillis());
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
        List<SysOss> dataList = new ExcelImportService().importExcelByIs(inputStream, SysOss.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<SysOss> query = new QueryWrapper<>();
        List<SysOss> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "SysOss"),
        SysOss.class, data);
        String fileName = String.format("SysOss_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<SysOss> getQuery(Map<String, String> params){
        QueryWrapper<SysOss> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("id".equals(entry.getKey())){
                query.eq("id",entry.getValue());
            }
            if("groupId".equals(entry.getKey())){
                query.eq("group_id",entry.getValue());
            }
            if("name".equals(entry.getKey())){
                query.eq("name",entry.getValue());
            }
            if("storageFileName".equals(entry.getKey())){
                query.eq("storage_file_name",entry.getValue());
            }
            if("createdTime".equals(entry.getKey())){
                query.eq("created_time",entry.getValue());
            }
            if("createdBy".equals(entry.getKey())){
                query.eq("created_by",entry.getValue());
            }
            if("createdById".equals(entry.getKey())){
                query.eq("created_by_id",entry.getValue());
            }
            if("status".equals(entry.getKey())){
                query.eq("status",entry.getValue());
            }
            if("bizId".equals(entry.getKey())){
                query.eq("biz_id",entry.getValue());
            }
            if("tagName".equals(entry.getKey())){
                query.eq("tag_name",entry.getValue());
            }
            if("percentage".equals(entry.getKey())){
                query.eq("percentage",entry.getValue());
            }
        }
        return  query;
    }
}
