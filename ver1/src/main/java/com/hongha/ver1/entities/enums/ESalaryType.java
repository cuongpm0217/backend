package com.hongha.ver1.entities.enums;

public enum ESalaryType {
	ESalaryType_BASIC("LƯƠNG CƠ BẢN"),
	ESalaryType_ALLOWANCE("PHỤ CẤP"),
	ESalaryType_OVERTIME("TĂNG CA"),
	ESalaryType_REWARD("THƯỞNG");
	public final String label;

    private ESalaryType(String label) {
        this.label = label;
    }
}
