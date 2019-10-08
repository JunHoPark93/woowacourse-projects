package nextstep.mvc.tobe.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nextstep.web.support.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class JsonView implements View {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = response.getWriter();

        if (isModelEmpty(model)) {
            return;
        }

        responseJson(model, writer);
    }

    private boolean isModelEmpty(Map<String, ?> model) {
        return model.size() == 0;
    }

    private void responseJson(Map<String, ?> model, PrintWriter writer) throws JsonProcessingException {
        if (model.size() == 1) {
            String singleValue = OBJECT_MAPPER.writeValueAsString(model.values().toArray()[0]);
            writer.println(singleValue);
            writer.flush();
            return;
        }

        writer.println(OBJECT_MAPPER.writeValueAsString(model));
        writer.flush();
    }
}
