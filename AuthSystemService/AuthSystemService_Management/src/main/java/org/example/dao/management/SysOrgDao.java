package org.example.dao.management;

import org.example.common.domain.management.SysOrg;
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
 * 公司部门表 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface SysOrgDao extends BaseMapper<SysOrg> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysOrg>
    */
    IPage<SysOrg> selpageCustomSqlByWrapper(Page<SysOrg> page, @Param(Constants.WRAPPER)QueryWrapper<SysOrg> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysOrg>
    */
    IPage<SysOrg> selpageCustomSqlByMap(Page<SysOrg> page, @Param("params") Map<String, String> params);
}
