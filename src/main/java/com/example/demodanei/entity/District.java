package com.example.demodanei.entity;

import java.io.Serializable;

public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String parent; //父编号
    private String code; //当前编号
    private String name; //名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("District{");
        sb.append("id=").append(id);
        sb.append(", parent='").append(parent).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
