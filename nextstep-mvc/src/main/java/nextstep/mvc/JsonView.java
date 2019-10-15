package nextstep.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import nextstep.mvc.View;
import nextstep.web.support.MediaType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class JsonView implements View {
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        Optional<Object> renderObject = toJsonObject(model);
        render(renderObject, response.getOutputStream());
    }

    private void render(Optional<Object> renderObject, ServletOutputStream outputStream) throws IOException {
        if (renderObject.isPresent()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputStream, renderObject.get());
        }
    }

    private Optional<Object> toJsonObject(Map<String, ?> model) {
        if (model == null || model.isEmpty()) {
            return Optional.empty();
        }

        if (model.size() == 1) {
            return Optional.of(model.values().stream().findFirst().get());
        }

        return Optional.of(model);
    }
}
