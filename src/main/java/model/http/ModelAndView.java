package model.http;

import java.util.Map;

public class ModelAndView {
    private View view;
    private Map<String, Object> model;
    private boolean isStaticFolderResource;

    public ModelAndView(View view, Map<String, Object> model, boolean isStaticFolderResource) {
        this.view = view;
        this.model = model;
        this.isStaticFolderResource = isStaticFolderResource;
    }

    public ModelAndView(View view, boolean isStaticFolderResource) {
        this.view = view;
        this.isStaticFolderResource = isStaticFolderResource;
    }

    public ModelAndView(View view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }

    public String getViewName() {
        return view.getName();
    }

    public boolean isStaticFolderResource() {
        return isStaticFolderResource;
    }
}
