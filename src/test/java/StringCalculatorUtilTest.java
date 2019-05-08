import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorUtilTest {

    Queue<Character> expectedOperatorQueue;
    Queue<Integer> expectedNumberQueue;

    @BeforeEach
    void setUp() {
        expectedOperatorQueue = new LinkedList<>();
        expectedNumberQueue = new LinkedList<>();
    }

    @Test
    void 정상문자열 () {
        expectedOperatorQueue.add('+');
        expectedOperatorQueue.add('*');
        expectedNumberQueue.add(4);
        expectedNumberQueue.add(2);
        expectedNumberQueue.add(5);

        String expression = "4 + 2 * 5";
        Queue<Character> actualOperatorQueue = StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
        Queue<Integer> actualNumberQueue = StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        int length = expectedNumberQueue.size() - 1;

        assertThat(expectedNumberQueue.poll()).isEqualTo(actualNumberQueue.poll());
        for (int i = 0; i < length; i++) {
            assertThat(expectedNumberQueue.poll()).isEqualTo(actualNumberQueue.poll());
            assertThat(expectedOperatorQueue.poll()).isEqualTo(actualOperatorQueue.poll());
        }
    }

    @Test
    void 비정상문자열 () {
        String expression = "4 + 2 -";
        assertThrows(IllegalArgumentException.class, () -> {
            StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
            StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        });

    }

    @Test
    void 공백이_2개이상인_문자열() {
        String expression = "4  + 2 * 5";

        assertThrows(IllegalArgumentException.class, () -> {
            StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
            StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        });
    }

    @Test
    void 맨처음_공백_2개() {
        String expression = "  4 + 2 * 5";

        assertThrows(IllegalArgumentException.class, () -> {
            StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
            StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        });
    }

    @Test
    void 불규칙_문자열() {
        String expression = "dfdfsdfda";

        assertThrows(IllegalArgumentException.class, () -> {
            StringCalculatorUtil.parseCalculator(expression).getOperatorQueue();
            StringCalculatorUtil.parseCalculator(expression).getNumberQueue();
        });
    }
}
