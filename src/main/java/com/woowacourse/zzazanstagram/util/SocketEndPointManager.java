package com.woowacourse.zzazanstagram.util;

import com.woowacourse.zzazanstagram.config.SocketUrlMappingContext;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SocketEndPointManager {
    private final SocketUrlMappingContext socketUrlMappingContext;

    public SocketEndPointManager(SocketUrlMappingContext socketUrlMappingContext) {
        this.socketUrlMappingContext = socketUrlMappingContext;
    }

    public String createEndPoint(MemberResponse memberResponse) {
        String randomEndpoint = generateRandomEndPoint();
        socketUrlMappingContext.bindContext(randomEndpoint, memberResponse);
        return randomEndpoint;
    }

    private String generateRandomEndPoint() {
        return UUID.randomUUID().toString();
    }

    public void removeMember(MemberSession memberSession) {
        socketUrlMappingContext.removeContext(memberSession);
    }
}
