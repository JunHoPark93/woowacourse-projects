package model.http;

import java.util.Map;

public class ModelAndView {
    private View view;
    private Map<String, Object> model;

    public ModelAndView(View view) {
        this.view = view;
    }

    public ModelAndView(View view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }

    public String getViewName() {
        return view.getName();
    }

    public String getDir() {
        return view.getViewLocation();
    }

    public String getFullPath() {
        return view.getFullPath();
    }

}
