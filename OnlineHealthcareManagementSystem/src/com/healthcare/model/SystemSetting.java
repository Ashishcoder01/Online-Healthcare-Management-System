// File: src/com/healthcare/model/SystemSetting.java
package com.healthcare.model;

public class SystemSetting {
    private int id;
    private String key;
    private String value;

    public SystemSetting() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
