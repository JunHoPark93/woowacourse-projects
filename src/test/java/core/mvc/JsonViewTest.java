package core.mvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import study.jackson.Car;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonViewTest {
    private static final Logger logger = LoggerFactory.getLogger( JsonViewTest.class );
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private View view;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        view = new JsonView();
    }

    @Test
    void render_no_element() throws Exception {
        view.render(new HashMap<>(), request, response);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(response.getContentAsString()).isBlank();
    }

    @Test
    void render_one_element() throws Exception {
        Map<String, Object> model = new HashMap<>();
        Car expected = new Car("Black", "Sonata");
        model.put("car", expected);

        view.render(model, request, response);

        Car actual = JsonUtils.toObject(response.getContentAsString(), Car.class);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void render_over_two_element() throws Exception {
        Map<String, Object> model = new HashMap<>();
        Car expected = new Car("Black", "Sonata");
        model.put("car", expected);
        model.put("name", "포비");

        view.render(model, request, response);

        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        logger.debug("response body : {}", response.getContentAsString());
    }
}
