package com.woowacourse.zzazanstagram.model.follow.service;

import com.woowacourse.zzazanstagram.model.follow.repository.FollowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class FollowServiceTest {
    @InjectMocks
    private FollowService followService;

    @Mock
    private FollowRepository followRepository;

    @Test
    void name() {

    }
}
