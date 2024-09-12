package org.example.dao.monitor;

import org.example.common.domain.monitor.LogPlatformOper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface LogPlatformOperDao extends BaseMapper<LogPlatformOper> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<LogPlatformOper>
    */
    IPage<LogPlatformOper> selpageCustomSqlByWrapper(Page<LogPlatformOper> page, @Param(Constants.WRAPPER)QueryWrapper<LogPlatformOper> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<LogPlatformOper>
    */
    IPage<LogPlatformOper> selpageCustomSqlByMap(Page<LogPlatformOper> page, @Param("params") Map<String, String> params);
}
