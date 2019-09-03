package study.ant;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class AntMatcherTest {
    private static final Logger logger = LoggerFactory.getLogger(AntMatcherTest.class);

    @Test
    void match() {
        String fqdn = fullQualifiedName(ArrayList.class);
        logger.debug("Full Qualified Name : {}", fqdn);

        assertThat(matches("java.util.*", fqdn)).isTrue();
        assertThat(matches("java.util.ArrayList", fqdn)).isTrue();
        assertThat(matches("java.util.Array*", fqdn)).isTrue();
        assertThat(matches("java.util.ArrayList.*", "java.util.ArrayList.add")).isTrue();
        assertThat(matches("java.util.ArrayList", "java.util.ArrayList.add")).isFalse();
        assertThat(matches("java.util.ArrayList.", "java.util.ArrayList.add")).isFalse();
    }

    private String fullQualifiedName(Class clazz) {
        return clazz.getName();
    }

    public static boolean matches(String pattern, String inputStr) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return antPathMatcher.match(pattern, inputStr);
    }
}
