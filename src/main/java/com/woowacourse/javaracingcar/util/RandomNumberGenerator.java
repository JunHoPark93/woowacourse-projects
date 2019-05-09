package com.woowacourse.javaracingcar.util;

import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public int generateNumber() {
        return new Random().nextInt(MAX + 1);
    }
}
