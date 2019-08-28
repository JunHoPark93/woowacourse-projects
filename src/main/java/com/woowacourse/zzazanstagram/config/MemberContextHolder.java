package com.woowacourse.zzazanstagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberContextHolder {
    @Bean
    public SocketUrlMappingContext socketUrlMappingContext() {
        return new SocketUrlMappingContext();
    }
}
