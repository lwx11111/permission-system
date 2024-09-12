package org.example.dao.management.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.domain.management.account.SysAccountRoleRela;

import java.util.Map;

/**
 * <p>
 * 账号角色对应关系表 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface SysAccountRoleRelaDao extends BaseMapper<SysAccountRoleRela> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysAccountRoleRela>
    */
    IPage<SysAccountRoleRela> selpageCustomSqlByWrapper(Page<SysAccountRoleRela> page, @Param(Constants.WRAPPER)QueryWrapper<SysAccountRoleRela> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysAccountRoleRela>
    */
    IPage<SysAccountRoleRela> selpageCustomSqlByMap(Page<SysAccountRoleRela> page, @Param("params") Map<String, String> params);
}
