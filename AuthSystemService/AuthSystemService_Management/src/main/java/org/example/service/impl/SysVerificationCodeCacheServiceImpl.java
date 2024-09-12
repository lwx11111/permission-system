package org.example.service.impl;

import org.example.common.domain.SysVerificationCodeCache;
import org.example.dao.SysVerificationCodeCacheDao;
import org.example.service.ISysVerificationCodeCacheService;
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
import org.springframework.web.multipart.MultipartFile;
import com.google.common.collect.Lists;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;

import java.io.InputStream;
import org.example.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysVerificationCodeCacheServiceImpl extends ServiceImpl<SysVerificationCodeCacheDao, SysVerificationCodeCache> implements ISysVerificationCodeCacheService {

    @Override
    public SysVerificationCodeCache getByUUID(String uuid) {
        QueryWrapper<SysVerificationCodeCache> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysVerificationCodeCache::getId, uuid);
        return getOne(queryWrapper, false);
    }
}
