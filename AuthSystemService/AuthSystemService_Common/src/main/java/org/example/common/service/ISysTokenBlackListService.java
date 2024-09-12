package org.example.common.service;

import org.example.common.domain.SysTokenBlackList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * token黑名单 服务类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
public interface ISysTokenBlackListService extends IService<SysTokenBlackList> {

    SysTokenBlackList getByToken(String token);

    void removeYesterday();
}
