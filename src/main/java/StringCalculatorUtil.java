import java.util.LinkedList;
import java.util.Queue;

public class StringCalculatorUtil {


    public static StringCalculator parseCalculator(String input) {
        Queue<Character> operatorQueue = new LinkedList<>();
        Queue<Integer> numberQueue = new LinkedList<>();
        String[] tokens = input.split(" ");

        numberQueue.add(Integer.parseInt(tokens[0]));
        int length = tokens.length;

        for (int i = 1; i < length; i += 2) {
            checkIfValidTokens(tokens, length, i);
            operatorQueue.add(tokens[i].charAt(0));
            numberQueue.add(Integer.parseInt(tokens[i+1]));
        }

        return new StringCalculator(operatorQueue, numberQueue);
    }

    private static void checkIfValidTokens(String[] tokens, int length, int i) {
        if (!isOperator(tokens[i]) || i + 1 >= length || !isNumber(tokens[i+1])) {
            throw new IllegalArgumentException("올바르지 않은 식입니다.");
        }
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String str) {
        if (str.length() == 0) {
            return false;
        }
        char c = str.charAt(0);

        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
