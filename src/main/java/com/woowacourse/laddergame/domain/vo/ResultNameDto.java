package com.woowacourse.laddergame.domain.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultNameDto {
    private static final Pattern SINGLE_PLAYER_NAMES_PATTERN = Pattern.compile("^([A-Za-z]{1,5})$");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkName(name);
        this.name = name;
    }

    private void checkName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Null 은 입력할 수 없습니다");
        }

        Matcher matcher = SINGLE_PLAYER_NAMES_PATTERN.matcher(name);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Result Name 이 잘못되었습니다");
        }
    }
}
