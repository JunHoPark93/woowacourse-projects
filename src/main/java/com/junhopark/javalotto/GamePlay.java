/*
 * Class:   GamePlay
 *
 * Version: 1.0.0
 *
 * Date:    2019-04-08
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javalotto;

import com.junhopark.javalotto.domain.Lotto;
import com.junhopark.javalotto.domain.Rank;
import com.junhopark.javalotto.domain.WinningLotto;
import com.junhopark.javalotto.util.GameUtil;
import com.junhopark.javalotto.util.GameValidator;
import com.junhopark.javalotto.util.LottoGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class GamePlay {
    private static final int LOTTO_PRICE = 1000;
    private final Scanner sc;
    private long money;
    private List<Lotto> purchasedLottoList;
    private Lotto lastWeekWinningLotto;
    private WinningLotto winningLotto;
    private int bonusNumber;
    private List<Rank> rankList;
    private HashMap<Rank, Long> rankLongHashMap;

    public GamePlay() {
        sc = new Scanner(System.in);
    }

    public void play() {
        getPurchaseMoneyFromUserInput();
        generateLottoWithNumber(money / LOTTO_PRICE);
        GameUtil.printLottoList(purchasedLottoList);
        getWinningLottoNumberFromUserInput();
        getBonusNumberUsingWinLottoFromUserInput();
        makeWinningLotto();
        showHittingStatus();
    }

    private void getPurchaseMoneyFromUserInput() {
        System.out.println("구입금액을 입력해주세요.");
        long longValue;
        do {
            longValue = getLongValue();
        } while (!GameValidator.checkValidMoney(longValue));

        money = longValue;
    }

    private long getLongValue() {
        String input = sc.nextLine();
        while (!GameValidator.isParsableToLong(input)) {
            System.out.println("64비트 정수입력 범위를 벗어났습니다.");
            input = sc.nextLine();
        }
        return Long.parseLong(input);
    }

    private void generateLottoWithNumber(long purchaseNumber) {
        List<Lotto> lottoList = new ArrayList<>();
        for (long i = 0; i < purchaseNumber; i++) {
            lottoList.add(LottoGenerator.generateLotto());
        }
        purchasedLottoList = lottoList;
    }

    private void getWinningLottoNumberFromUserInput() {
        System.out.println("지난 주 당첨번호를 입력해 주세요.");
        List<Integer> list;
        do {
            list = getIntegerListWithCommaFromUser();
        } while (!GameValidator.checkValidLotto(list));

        lastWeekWinningLotto = new Lotto(list);
    }

    private List<Integer> getIntegerListWithCommaFromUser() {
        String[] input = sc.nextLine().split(",");
        List<String> list = Arrays.asList(input);
        list.replaceAll(String::trim);
        while (!GameValidator.isListAllParsableToInt(list)) {
            System.out.println("32비트 정수입력을 벗어났습니다.");
            input = sc.nextLine().split(",");
            list = Arrays.asList(input);
            list.replaceAll(String::trim);
        }
        return list.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    private void getBonusNumberUsingWinLottoFromUserInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        do {
            bonusNumber = getIntValue();
        } while (!GameValidator.checkValidBonusBall(lastWeekWinningLotto, bonusNumber));
    }

    private int getIntValue() {
        String input = sc.nextLine();
        while (!GameValidator.isParsableToInt(input)) {
            System.out.println("32비트 정수입력 범위를 벗어났습니다.");
            input = sc.nextLine();
        }
        return Integer.parseInt(input);
    }

    private void makeWinningLotto() {
        winningLotto = new WinningLotto(lastWeekWinningLotto, bonusNumber);
    }

    private void showHittingStatus() {
        initializeRankList();
        calculateLottoHit();
        GameUtil.printHittingResult(rankLongHashMap);
    }

    private void initializeRankList() {
        rankList = new ArrayList<>();
        purchasedLottoList.forEach(lotto -> rankList.add(winningLotto.match(lotto)));
    }

    private void calculateLottoHit() {
        rankLongHashMap = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> rankLongHashMap.put(rank, 0L));
        rankList.forEach(rank -> rankLongHashMap.put(rank, rankLongHashMap.get(rank) + 1));
    }
}
