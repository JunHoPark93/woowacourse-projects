package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMovieId() {
        System.out.println("## 예약할 영화를 선택하세요.");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다.");
            scanner.nextLine();
            return inputMovieId();
        }
    }

    public static int inputMovieSchedule() {
        System.out.println("## 예약할 시간표를 선택하세요.");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다.");
            scanner.nextLine();
            return inputMovieSchedule();
        }
    }

    public static int inputPeopleCapacity() {
        System.out.println("## 예약할 인원을 입력하세요.");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다.");
            scanner.nextLine();
            return inputPeopleCapacity();
        }
    }
}
