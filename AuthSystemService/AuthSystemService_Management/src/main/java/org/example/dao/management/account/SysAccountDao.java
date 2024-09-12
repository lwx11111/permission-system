package org.example.dao.management.account;

import org.example.common.domain.management.account.SysAccount;
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
 * 用户账号信息表 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface SysAccountDao extends BaseMapper<SysAccount> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysAccount>
    */
    IPage<SysAccount> selpageCustomSqlByWrapper(Page<SysAccount> page, @Param(Constants.WRAPPER)QueryWrapper<SysAccount> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysAccount>
    */
    IPage<SysAccount> selpageCustomSqlByMap(Page<SysAccount> page, @Param("params") Map<String, String> params);
}
