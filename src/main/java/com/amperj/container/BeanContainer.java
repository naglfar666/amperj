package com.amperj.container;

import java.util.HashMap;
import java.util.Map;

public class BeanContainer implements Container {

    private final Map<String, Object> beans = new HashMap<>();

    private final Map<String, Object> controllerBeans = new HashMap<>();

    private final Map<String, Object> initializedBeans = new HashMap<>();

    @Override
    public Object get(String id) {
        return initializedBeans.get(id);
    }

    @Override
    public void set(String id, Object bean) throws Exception {
        if (initializedBeans.get(id) != null) {
            throw new Exception("Bean with id already registered " + id);
        }
        initializedBeans.put(id, bean);
    }

    @Override
    public Boolean has(String id) {
        return initializedBeans.containsKey(id);
    }

    @Override
    public Object getController(String id) {
        return controllerBeans.get(id);
    }

    @Override
    public void setController(String id, Object bean) throws Exception {
        if (controllerBeans.get(id) != null) {
            throw new Exception("Controller with id already registered " + id);
        }
        controllerBeans.put(id, bean);
    }

    @Override
    public Boolean hasController(String id) {
        return controllerBeans.containsKey(id);
    }

}
