package org.example.dao.management.group;

import org.example.common.domain.management.group.SysGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.dto.ListMembersResult;

import java.util.List;
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
public interface SysGroupDao extends BaseMapper<SysGroup> {

    List<SysGroup> listGroupByAccountId(@Param("accountId") String accountId);

    List<ListMembersResult> listMembers(@Param("groupId") String groupId, @Param("appId") String appId);

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysGroup>
    */
    IPage<SysGroup> selpageCustomSqlByWrapper(Page<SysGroup> page, @Param(Constants.WRAPPER)QueryWrapper<SysGroup> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysGroup>
    */
    IPage<SysGroup> selpageCustomSqlByMap(Page<SysGroup> page, @Param("params") Map<String, String> params);
}
