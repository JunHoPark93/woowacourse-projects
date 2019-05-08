import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {
    Queue<Character> operationQueue;
    Queue<Integer> numberQueue;
    StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        operationQueue = new LinkedList<>();
        numberQueue = new LinkedList<>();
    }

    @Test
    void 정상입력() {
        operationQueue.add('+');
        operationQueue.add('*');
        numberQueue.add(4);
        numberQueue.add(2);
        numberQueue.add(5);
        stringCalculator = new StringCalculator(operationQueue, numberQueue);

        assertThat(stringCalculator.calculate()).isEqualTo(30);
    }

    @Test
    void 정상입력_0으로_나누기() {
        operationQueue.add('+');
        operationQueue.add('/');
        numberQueue.add(-2);
        numberQueue.add(3);
        numberQueue.add(0);
        stringCalculator = new StringCalculator(operationQueue, numberQueue);

        assertThrows(ArithmeticException.class, () -> stringCalculator.calculate());
    }

    @Test
    void 연산자_비정상입력() {
        operationQueue.add('+');
        operationQueue.add('*');
        operationQueue.add('*');
        numberQueue.add(4);
        numberQueue.add(2);
        numberQueue.add(5);

        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator = new StringCalculator(operationQueue, numberQueue);
        });
    }

    @Test
    void 피연산자_비정상입력() {
        operationQueue.add('+');
        operationQueue.add('*');
        numberQueue.add(4);
        numberQueue.add(2);
        numberQueue.add(5);
        numberQueue.add(5);

        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator = new StringCalculator(operationQueue, numberQueue);
        });
    }

    @Test
    void 연산자_비정상문자입력() {
        operationQueue.add('?');
        operationQueue.add('*');
        numberQueue.add(4);
        numberQueue.add(2);
        numberQueue.add(5);

        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator = new StringCalculator(operationQueue, numberQueue);
            stringCalculator.calculate();
        });
    }
}
