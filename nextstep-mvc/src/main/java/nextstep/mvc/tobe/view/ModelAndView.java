package nextstep.mvc.tobe.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private String viewName;
    private Map<String, Object> model = new HashMap<String, Object>();

    private ModelAndView() {
    }

    public static ModelAndView from() {
        return new ModelAndView();
    }

    public void setViewName(String view) {
        this.viewName = view;
    }

    public ModelAndView addObject(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
        return this;
    }

    public Object getObject(String attributeName) {
        return model.get(attributeName);
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, Object> getModel() {
        return Collections.unmodifiableMap(model);
    }

    public boolean isViewNameExist() {
        return viewName != null;
    }
}
