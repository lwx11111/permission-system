package org.example.common.dao;

import org.example.common.domain.SysTokenBlackList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * token黑名单 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Mapper
public interface SysTokenBlackListDao extends BaseMapper<SysTokenBlackList> {

}
