package com.woowacourse.laddergame.domain.vo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LadderDto {
    private static final Pattern TOTAL_PLAYER_NAMES_PATTERN = Pattern.compile("^([A-Za-z]{1,5})(,([A-Za-z]{1,5}))+$");
    private static final Pattern SINGLE_PLAYER_NAMES_PATTERN = Pattern.compile("([A-Za-z]{1,5})");
    private static final Pattern HEIGHT_PATTERN = Pattern.compile("^([1-9])([0-9])*$");
    private static final Pattern TOTAL_RESULT_PATTERN = Pattern.compile("^([ㄱ-ㅎㅏ-ㅣ가-힣A-Za-z0-9]{1,5})(,[ㄱ-ㅎㅏ-ㅣ가-힣A-Za-z0-9]{1,5})+$");
    private static final Pattern SINGLE_RESULT_PATTERN = Pattern.compile("([ㄱ-ㅎㅏ-ㅣ가-힣A-Za-z0-9]{1,5})");
    private static final String ILLEGAL_NAME = "all";

    private String names;
    private int height;
    private String result;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        checkPlayerNames(names);
        this.names = names;
    }

    private void checkPlayerNames(String names) {
        checkNameIfNull(names);
        checkNameIfIllegal(names);
        checkNameMatchPattern(names);

        // Result가 들어와 있는상태에서 플레이어 이름을 다시 초기화 할 때
        if (!isSameLengthBetweenResultAndNames(getNamesCount(names))) {
            throw new IllegalArgumentException("Result 개수와 맞지 않습니다");
        }
        checkDuplicateNames(names);
    }

    private void checkDuplicateNames(String names) {
        List<String> nameTokens = Arrays.asList(names.split(","));
        Set<String> set = new HashSet<>(nameTokens);
        if (set.size() != nameTokens.size()) {
            throw new IllegalArgumentException("중복된 이름을 입력할 수 없습니다");
        }
    }

    private void checkNameMatchPattern(String names) {
        Matcher matcher = TOTAL_PLAYER_NAMES_PATTERN.matcher(names);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Player 이름들이 잘못되었습니다");
        }
    }

    private void checkNameIfIllegal(String names) {
        if (names.contains(ILLEGAL_NAME)) {
            throw new IllegalArgumentException("all은 player 이름으로 입력할 수 없습니다");
        }
    }

    private void checkNameIfNull(String names) {
        if (names == null) {
            throw new IllegalArgumentException("Null 은 입력할 수 없습니다");
        }
    }

    public void checkHeight(String height) {
        if (height == null) {
            throw new IllegalArgumentException("Null 은 입력할 수 없습니다");
        }

        Matcher matcher = HEIGHT_PATTERN.matcher(height);
        if (!matcher.find()) {
            throw new IllegalArgumentException("정상적인 사다리 높이가 아닙니다");
        }
    }

    public void checkResult(String result) {
        checkResultisNull(result);
        checkNamesInitialized();
        checkResultMatchPattern(result);

        // Result 초기화 시 이름 개수와 비교
        if (!isSameLengthBetweenResultAndNames(getResultCount(result))) {
            throw new IllegalArgumentException("이름과 개수가 다릅니다");
        }
    }

    private void checkResultMatchPattern(String result) {
        Matcher matcher = TOTAL_RESULT_PATTERN.matcher(result);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Result 값이 잘못 되었습니다");
        }
    }

    private void checkNamesInitialized() {
        if (names == null) {
            throw new IllegalArgumentException("이름이 먼저 초기화되야 합니다");
        }
    }

    private void checkResultisNull(String result) {
        if (result == null) {
            throw new IllegalArgumentException("Null 은 입력할 수 없습니다");
        }
    }

    private int getResultCount(String result) {
        int count = 0;

        Matcher matcher = SINGLE_RESULT_PATTERN.matcher(result);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private boolean isSameLengthBetweenResultAndNames(int count) {
        if (result != null && count != getResultCount(result)) {
            return false;
        }
        if (names != null && count != getNamesCount(names)) {
            return false;
        }
        return true;
    }

    private int getNamesCount(String result) {
        Matcher matcher = SINGLE_PLAYER_NAMES_PATTERN.matcher(result);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(String height) {
        checkHeight(height);
        this.height = Integer.parseInt(height);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        checkResult(result);
        this.result = result;
    }
}
