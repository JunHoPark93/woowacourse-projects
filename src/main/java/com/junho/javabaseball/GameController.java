package com.junho.javabaseball;

import com.junho.javabaseball.util.BaseBallUtil;

import java.util.Scanner;

/**
 * @author junho.park
 */
public class GameController {
    private static final int RESTART = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int playingStatus;

        do {
            int[] generateNumbers = BaseBallUtil.generateThreeDigitNumbers();

            GameHelper.play(sc, generateNumbers);
            playingStatus = GameHelper.isContinue(sc);
        } while (playingStatus == RESTART);

        sc.close();
    }
}
