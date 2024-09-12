package org.example.service.management;

import org.example.common.domain.management.SysFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 功能路由表 服务类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
public interface ISysFunctionService extends IService<SysFunction> {

    List<SysFunction> getByRoleId(String roleId, String appId);

    List<SysFunction> listFunctionsByAccountId(String accountId);


    /**
     * 根据账号id、父id、应用id、是否内置查询功能路由表
     * @param accountId
     * @param parentId
     * @param appId
     * @param inherent
     * @param funType
     * @return
     */
    List<SysFunction> listByAccountId(String accountId, String parentId, String appId, String inherent, String funType);

    /**
     * 根据父id、应用id、是否内置查询功能路由表
     * @param parentId
     * @param appId
     * @param inherent
     * @return
     */
    List<SysFunction> listByParentId(String parentId, String appId, String inherent);

    /**
     * 根据账号id、应用id、是否内置查询菜单
     * @param accountId
     * @param appId
     * @param inherent
     * @return
     */
    Set<SysFunction> listLeftMenuByAccountId(String accountId, String appId, String inherent);

    /**
     * 根据参数保存
     * @param obj
     * @param params
     * @return: void
     */
    void saveByParam(SysFunction obj,Map<String, String> params);

    /**
     * 根据参数更新
     * @param obj
     * @param params
     * @return: void
     */
    void updateByParam(SysFunction obj,Map<String, String> params);
    /**
     * 根据条件删除
     *
     * @param params
     * @return: void
     */
    void deleteBy(Map<String, String> params);

    /**
     * 根据条件查询
     *
     * @param params
     * @return: List<SysFunction>
     */
     List<SysFunction> selectBy(Map<String, String> params);

    /**
     * 分页查询
     *
     * @param params
     * @return: IPage<SysFunction>
    */
    IPage<SysFunction> selectPage(Map<String, String> params);

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param params
     * @return: IPage<SysFunction>
    */
    IPage<SysFunction> selpageCustomSqlByWrapper(Map<String, String> params);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param params
     * @return: IPage<SysFunction>
    */
    IPage<SysFunction> selpageCustomSqlByMap(Map<String, String> params);

    /**
     * 下载excel模板
     *
     * @param response HttpServletResponse
     * @param request  HttpServletRequest
     * @return: void
    */
    void downloadExcelTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception;

    /**
     * 导入数据
     * @param file
     * @throws Exception
     */
    void uploadExcel(MultipartFile file) throws Exception;

    /**
     * 导出数据
     *
     * @param response HttpServletResponse
     * @param request  HttpServletRequest
     * @param params
     * @return: void
    */
    void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception;
}
