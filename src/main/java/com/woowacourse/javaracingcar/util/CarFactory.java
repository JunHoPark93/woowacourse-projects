package com.woowacourse.javaracingcar.util;

import com.woowacourse.javaracingcar.domain.Cars;

import java.util.List;

public class CarFactory {

    public static Cars getCarsWithNames(List<String> names) {
        return new Cars(names);
    }
}
