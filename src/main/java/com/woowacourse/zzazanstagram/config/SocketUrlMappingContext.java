package com.woowacourse.zzazanstagram.config;

import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SocketUrlMappingContext {
    private final Map<String, MemberResponse> sessionMap = new ConcurrentHashMap<>();

    public List<String> findTargetEndPoints(MemberResponse target) {
        return sessionMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(target))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void bindContext(String randomEndpoint, MemberResponse memberResponse) {
        sessionMap.put(randomEndpoint, memberResponse);
    }

    public void removeContext(MemberSession memberSession) {
        sessionMap.entrySet().stream()
                .filter(entry -> entry.getValue().getNickName().equals(memberSession.getNickName()))
                .map(Map.Entry::getKey)
                .forEach(sessionMap::remove);
    }

    public void removeAllContext() {
        sessionMap.clear();
    }

    public int getMappedUrlCount() {
        return sessionMap.size();
    }
}
