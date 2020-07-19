package com.amperj.container;

public interface Container {

    public Object get(String id);

    public void set(String id, Object bean) throws Exception;

    public Boolean has(String id);

    public Object getController(String id);

    public void setController(String id, Object bean) throws Exception;

    public Boolean hasController(String id);

}
