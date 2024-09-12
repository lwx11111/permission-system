package org.example.service.management.account;

import org.example.common.domain.management.account.SysAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.entity.AccountBo;
import org.example.dto.LoginParams;
import org.example.dto.ModifyPasswordParams;
import org.example.dto.SavePermParams;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账号信息表 服务类
 * </p>
 *
 * @author lwx
 * @since 2024-05-04
 */
public interface ISysAccountService extends IService<SysAccount> {

    void savePerm(SavePermParams params);

    void logicDeleteByUserId(String UserId);

    /**
     * 修改密码
     */
    void modifyPass(ModifyPasswordParams params);

    /**
     * 登录
     * @param loginParams
     * @return
     */
    AccountBo login(LoginParams loginParams);

    /**
     * 重置密码
     * @param accountId
     */
    void resetPassword(String accountId);

    /**
     * 解锁
     * @param accountId
     */
    void unlock(String accountId);

    /**
     * 锁定
     * @param accountId
     */
     void lock(String accountId);

    /**
     * 根据参数保存
     * @param obj
     * @param params
     * @return: void
     */
    void saveByParam(SysAccount obj,Map<String, String> params);

    /**
     * 根据参数更新
     * @param obj
     * @param params
     * @return: void
     */
    void updateByParam(SysAccount obj,Map<String, String> params);
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
     * @return: List<SysAccount>
     */
     List<SysAccount> selectBy(Map<String, String> params);

    /**
     * 分页查询
     *
     * @param params
     * @return: IPage<SysAccount>
    */
    IPage<SysAccount> selectPage(Map<String, String> params);

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param params
     * @return: IPage<SysAccount>
    */
    IPage<SysAccount> selpageCustomSqlByWrapper(Map<String, String> params);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param params
     * @return: IPage<SysAccount>
    */
    IPage<SysAccount> selpageCustomSqlByMap(Map<String, String> params);

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
