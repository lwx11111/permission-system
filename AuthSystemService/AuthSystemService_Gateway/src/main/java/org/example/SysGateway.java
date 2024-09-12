package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("org.example.common.dao")
public class SysGateway
{
    public static void main( String[] args )
    {
        new SpringApplicationBuilder(SysGateway.class).allowCircularReferences(true).run(args);
    }
}
