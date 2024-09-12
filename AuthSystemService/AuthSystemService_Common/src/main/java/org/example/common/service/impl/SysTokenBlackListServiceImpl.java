package org.example.common.service.impl;

import org.example.common.dao.SysTokenBlackListDao;
import org.example.common.domain.SysTokenBlackList;
import org.example.common.service.ISysTokenBlackListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 * token黑名单 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
@Service
public class SysTokenBlackListServiceImpl extends ServiceImpl<SysTokenBlackListDao, SysTokenBlackList> implements ISysTokenBlackListService {

    @Override
    public SysTokenBlackList getByToken(String token) {
        QueryWrapper<SysTokenBlackList> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysTokenBlackList::getToken, token);
        return getOne(queryWrapper, false);
    }

    @Override
    public void removeYesterday() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.minusDays(1);
        LocalDateTime yesterday = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        QueryWrapper<SysTokenBlackList> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().le(SysTokenBlackList::getCreateTime, yesterday);
        remove(queryWrapper);

    }
}
