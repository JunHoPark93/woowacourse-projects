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

    public static int inputDoneOrNot() {
        System.out.println("## 예약을 종료하고 결제를 진행하려면 1번, 추가예약을 진행하려면 2");
        try {
            int input = scanner.nextInt();
            checkValidDoneOrNot(input);
            return input;
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다.");
            scanner.nextLine();
            return inputDoneOrNot();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            scanner.nextLine();
            return inputDoneOrNot();
        }
    }

    private static void checkValidDoneOrNot(int input) {
        if (input != 1 && input != 2) {
            throw new IllegalArgumentException("1 혹은 2를 입력하세요");
        }
    }
}
