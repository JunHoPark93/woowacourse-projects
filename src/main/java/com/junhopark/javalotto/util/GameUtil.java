/*
 * Class:   GameUtil
 *
 * Version: 1.0.0
 *
 * Date:    2019-04-09
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javalotto.util;

import com.junhopark.javalotto.domain.Lotto;
import com.junhopark.javalotto.domain.Rank;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameUtil {
    public static void printLottoList(List<Lotto> purchasedLottoList) {
        purchasedLottoList.stream().map(Lotto::getNumbers).forEach(System.out::println);
    }

    public static void printHittingResult(HashMap<Rank, Long> map) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .filter(e -> e.getKey().getWinningMoney() != 0)
                .forEach(e -> e.getKey().printResult(map.get(e.getKey())));
    }
}
