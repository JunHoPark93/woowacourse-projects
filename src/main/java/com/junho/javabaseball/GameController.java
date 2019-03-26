/*
 * Class:   GameController
 *
 * Version: 1.0.0
 *
 * Date:    2019-03-26
 *
 * Author:  Jun Ho Park
 *
 */

package com.junho.javabaseball;

import com.junho.javabaseball.util.BaseBallUtil;

import java.util.Scanner;

public class GameController {
    private static final int RESTART_CODE = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int playingStatus;

        do {
            int[] generateNumbers = BaseBallUtil.generateThreeDigitNumbers();
            GameHelper.play(sc, generateNumbers);
            playingStatus = GameHelper.isContinue(sc);
        } while (playingStatus == RESTART_CODE);

        sc.close();
    }
}
