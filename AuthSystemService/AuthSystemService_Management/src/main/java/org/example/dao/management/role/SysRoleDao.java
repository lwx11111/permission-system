package org.example.dao.management.role;

import org.example.common.domain.management.role.SysRole;
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
 * 角色表 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 根据账号ID查询角色
     * @param accountId
     * @param appId
     * @return
     */
    List<SysRole> getRolesByAccountId(@Param("accountId") String accountId, @Param("appId") String appId);

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysRole>
    */
    IPage<SysRole> selpageCustomSqlByWrapper(Page<SysRole> page, @Param(Constants.WRAPPER)QueryWrapper<SysRole> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysRole>
    */
    IPage<SysRole> selpageCustomSqlByMap(Page<SysRole> page, @Param("params") Map<String, String> params);
}
