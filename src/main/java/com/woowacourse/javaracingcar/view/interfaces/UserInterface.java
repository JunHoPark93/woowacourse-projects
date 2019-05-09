package com.woowacourse.javaracingcar.view.interfaces;

import com.woowacourse.javaracingcar.dto.CarDto;

import java.util.List;

public interface UserInterface {
    List<String> promptUserNames();
    int promptTries();
    void printResult(List<CarDto> cars);
    void printWinners(List<CarDto> winners);

    List<String> onInvalidUserNames();
    int onInvalidTries();
}
