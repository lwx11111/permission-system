package org.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.common.domain.SysOss;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwx20
 * @since 2023-11-13
 */
@Mapper
public interface SysOssMapper extends BaseMapper<SysOss> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysOss>
    */
    IPage<SysOss> selpageCustomSqlByWrapper(Page<SysOss> page, @Param(Constants.WRAPPER)QueryWrapper<SysOss> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysOss>
    */
    IPage<SysOss> selpageCustomSqlByMap(Page<SysOss> page, @Param("params") Map<String, String> params);
}
