package org.example.dao.management;

import org.example.common.domain.management.SysCredentialRule;
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
 * 系统密码规则 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface SysCredentialRuleDao extends BaseMapper<SysCredentialRule> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<SysCredentialRule>
    */
    IPage<SysCredentialRule> selpageCustomSqlByWrapper(Page<SysCredentialRule> page, @Param(Constants.WRAPPER)QueryWrapper<SysCredentialRule> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<SysCredentialRule>
    */
    IPage<SysCredentialRule> selpageCustomSqlByMap(Page<SysCredentialRule> page, @Param("params") Map<String, String> params);
}
