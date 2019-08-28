package com.woowacourse.zzazanstagram.config;

import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO 포장
@Configuration
public class MemberContextHolder {
    private Map<String, MemberResponse> sessionMap = new ConcurrentHashMap<>();

    @Bean
    public Map<String, MemberResponse> sessionMap() {
        return sessionMap;
    }
}
