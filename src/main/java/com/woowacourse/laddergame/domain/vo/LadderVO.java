package com.woowacourse.laddergame.domain.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LadderVO {
    private static final Pattern PLAYER_NAMES_PATTERN = Pattern.compile("^([A-Za-z]{1,5})(,([A-Za-z]{1,5}))+$");
    private static final Pattern HEIGHT_PATTERN = Pattern.compile("^([1-9])([0-9])*$");
    private String names;
    private int height;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        checkPlayerNames(names);
        this.names = names;
    }

    private void checkPlayerNames(String names) {
        if (names == null) {
            throw new IllegalArgumentException("Null 은 입력할 수 없습니다");
        }

        Matcher matcher = PLAYER_NAMES_PATTERN.matcher(names);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Player 이름들이 잘못되었습니다");
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(String height) {
        checkHeight(height);
        this.height = Integer.parseInt(height);
    }

    public void checkHeight(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Null 은 입력할 수 없습니다");
        }

        Matcher matcher = HEIGHT_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("정상적인 사다리 높이가 아닙니다");
        }
    }
}
