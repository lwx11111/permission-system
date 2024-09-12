package org.example.controller.management;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.common.annotation.OperationLog;
import org.example.common.domain.SysVerificationCodeCache;
import org.example.common.utils.RandImageUtils;
import org.example.service.ISysVerificationCodeCacheService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @Author 刘文轩
 * @Date 2024/8/20 14:52
 */
@RestController
@Tag(name = "用户账号信息表服务")
@RequestMapping("/verificationCode")
@Slf4j
public class SysVerificationCodeController {

    @Resource
    private ISysVerificationCodeCacheService verificationCodeCacheService;

    @ApiImplicitParams({@ApiImplicitParam(name = "uuid", value = "唯一标识", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "width", value = "图片宽度,默认200", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "height", value = "图片高度,默认50", dataType = "String", paramType = "query")})
    @OperationLog(value = "生成验证码图片", type = 4)
    @RequestMapping(value = "/anon/create", method = {RequestMethod.POST, RequestMethod.GET})
    public void createCodeImage(
            HttpServletResponse response,
            @RequestParam("uuid") String uuid,
            @RequestParam(value = "width", required = false, defaultValue = "105") int width,
            @RequestParam(value = "height", required = false, defaultValue = "35") int height) throws IOException {
        String code = RandomStringUtils.random(4, "2345678abcdefhijkmnpqrstuvwxyzABCDEFGHJKLMNPQRTUVWXY");
        SysVerificationCodeCache verificationCodeCache = new SysVerificationCodeCache();
        verificationCodeCache.setCode(code.toLowerCase());
        verificationCodeCache.setId(uuid);
        verificationCodeCache.setCreateTime(LocalDateTime.now());
        verificationCodeCacheService.save(verificationCodeCache);
        RandImageUtils.generate(response, code, width, height);
    }
}
