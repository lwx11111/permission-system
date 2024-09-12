package org.example.dao.management.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.domain.management.account.SysAccountFunctionRela;

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
public interface SysAccountFunctionRelaDao extends BaseMapper<SysAccountFunctionRela> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysAccountFunctionRela>
    */
    IPage<SysAccountFunctionRela> selpageCustomSqlByWrapper(Page<SysAccountFunctionRela> page, @Param(Constants.WRAPPER)QueryWrapper<SysAccountFunctionRela> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysAccountFunctionRela>
    */
    IPage<SysAccountFunctionRela> selpageCustomSqlByMap(Page<SysAccountFunctionRela> page, @Param("params") Map<String, String> params);
}
