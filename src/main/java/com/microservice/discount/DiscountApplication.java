package com.microservice.discount;

import io.grpc.netty.NettyServerBuilder;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DiscountApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscountApplication.class, args);
    }

@Bean
    public GrpcServerConfigurer keepAliveServerConfigurer(){
        return serverBuilder -> {
            if (serverBuilder instanceof NettyServerBuilder){
                ((NettyServerBuilder) serverBuilder).keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(5,TimeUnit.SECONDS)
                        .permitKeepAliveWithoutCalls(true);
            }
        };
    }



}
