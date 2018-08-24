package cn.wukun.dto;

public class SearchCondition {
    private String type;

    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SearchCondition(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
