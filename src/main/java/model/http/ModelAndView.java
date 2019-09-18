package model.http;

import java.util.Map;

public class ModelAndView {
    private View view;
    private Map<String, Object> model;

    public ModelAndView(View view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }
}
