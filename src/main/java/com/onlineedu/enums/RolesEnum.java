package com.onlineedu.enums;

public enum RolesEnum {

                       ADMIN(1, "ADMIN"), TEACHER(2, "TEACHER"), USER(3, "USER");

    private int enumVal;
    private String enumName;

    private RolesEnum(int enumVal, String enumName) {
        this.enumVal = enumVal;
        this.enumName = enumName;
    }

    public int getEnumVal() {
        return enumVal;
    }

    public void setEnumVal(int enumVal) {
        this.enumVal = enumVal;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

}
