package org.example;

import org.example.common.entity.MicroInfo;
import org.example.common.utils.NetworkUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "org.example.dao, org.example.common.dao")
public class Management
{
    public static void main( String[] args )
    {
        // 获取服务端IP
        MicroInfo.getInfo().setIp(NetworkUtils.getLocalIp());
        SpringApplication.run(Management.class);
    }
}
