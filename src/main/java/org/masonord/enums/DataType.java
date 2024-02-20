package org.masonord.enums;

public enum DataType {
    ARRAYS("*"),
    SIMPLE_ERRORS("-"),
    SIMPLE_STRINGS("+"),
    BULK_STRING("$"),
    EMPTY_TYPE(""),
    NULL_TYPE("_"),
    BOOLEAN_TYPE("#");


    private String value;

    DataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
