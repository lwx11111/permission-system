package org.example.dao.management.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.domain.management.role.SysRoleFunctionRela;

import java.util.Map;

/**
 * <p>
 * 功能和角色对应表 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface SysRoleFunctionRelaDao extends BaseMapper<SysRoleFunctionRela> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysRoleFunctionRela>
    */
    IPage<SysRoleFunctionRela> selpageCustomSqlByWrapper(Page<SysRoleFunctionRela> page, @Param(Constants.WRAPPER)QueryWrapper<SysRoleFunctionRela> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysRoleFunctionRela>
    */
    IPage<SysRoleFunctionRela> selpageCustomSqlByMap(Page<SysRoleFunctionRela> page, @Param("params") Map<String, String> params);
}
