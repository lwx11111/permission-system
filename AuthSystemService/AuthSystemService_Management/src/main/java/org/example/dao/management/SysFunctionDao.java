package org.example.dao.management;

import org.example.common.domain.management.SysFunction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 功能路由表 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface SysFunctionDao extends BaseMapper<SysFunction> {

    List<SysFunction> getByRoleId(@Param("roleId") String roleId, @Param("appId") String appId);

    List<SysFunction> listFunctionsByAccountId(@Param("accountId") String accountId);

    /**
     * 根据账号ID查询功能路由表
     * @param accountId
     * @param parentId
     * @param appId
     * @param inherent
     * @param funType
     * @return
     */
    List<SysFunction> listByAccountId(@Param("accountId") String accountId, @Param("parentId") String parentId, @Param("appId") String appId,
                                      @Param("inherent") String inherent, @Param("funType") String funType);

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysFunction>
    */
    IPage<SysFunction> selpageCustomSqlByWrapper(Page<SysFunction> page, @Param(Constants.WRAPPER)QueryWrapper<SysFunction> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysFunction>
    */
    IPage<SysFunction> selpageCustomSqlByMap(Page<SysFunction> page, @Param("params") Map<String, String> params);
}
