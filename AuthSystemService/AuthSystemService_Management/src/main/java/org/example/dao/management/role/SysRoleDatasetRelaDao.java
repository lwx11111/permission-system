package org.example.dao.management.role;

import org.example.common.domain.management.role.SysRoleDatasetRela;
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
public interface SysRoleDatasetRelaDao extends BaseMapper<SysRoleDatasetRela> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysRoleDatasetRela>
    */
    IPage<SysRoleDatasetRela> selpageCustomSqlByWrapper(Page<SysRoleDatasetRela> page, @Param(Constants.WRAPPER)QueryWrapper<SysRoleDatasetRela> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysRoleDatasetRela>
    */
    IPage<SysRoleDatasetRela> selpageCustomSqlByMap(Page<SysRoleDatasetRela> page, @Param("params") Map<String, String> params);
}
