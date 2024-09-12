package org.example.common.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * Date: 2023/8/25 10:30
 * @Description: 分页参数处理
 */
public class PageUtils {

    private PageUtils() {
    }

    private static final String PAGE = "pageIndex";

    private static final String LIMIT = "pageSize";

    public static Page pageHandler(Map<String, String> params) {
        Long currentPage = 1L;
        Long pageSize = 10L;
        if (params.containsKey(LIMIT) && NumberUtil.isLong(params.get(LIMIT))) {
            pageSize = NumberUtil.parseLong(params.get(LIMIT));
        }
        if (params.containsKey(PAGE) && NumberUtil.isLong(params.get(PAGE))) {
            currentPage = NumberUtil.parseLong(params.get(PAGE));
        }
        return new Page(currentPage, pageSize);
    }

    public static Page pageHandlerObj(Map<String, Object> params) {
        Long currentPage = 1L;
        Long pageSize = 10L;
        if (params.containsKey(LIMIT) && NumberUtil.isLong(Convert.toStr(params.get(LIMIT)))) {
            pageSize = NumberUtil.parseLong(Convert.toStr(params.get(LIMIT)));
        }
        if (params.containsKey(PAGE) && NumberUtil.isLong(Convert.toStr(params.get(PAGE)))) {
            currentPage = NumberUtil.parseLong(Convert.toStr(params.get(PAGE)));
        }
        return new Page(currentPage, pageSize);
    }
}
