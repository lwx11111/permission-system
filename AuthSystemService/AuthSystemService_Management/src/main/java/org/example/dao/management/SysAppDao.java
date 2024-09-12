package org.example.dao.management;

import org.example.common.domain.management.SysApp;
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
 * 项目表 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface SysAppDao extends BaseMapper<SysApp> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysApp>
    */
    IPage<SysApp> selpageCustomSqlByWrapper(Page<SysApp> page, @Param(Constants.WRAPPER)QueryWrapper<SysApp> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysApp>
    */
    IPage<SysApp> selpageCustomSqlByMap(Page<SysApp> page, @Param("params") Map<String, String> params);
}
