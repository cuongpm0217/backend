package com.hongha.ver1.entities.enums;

public enum ECustomerType {
	ECustomerType_INVIDUAL("Khách lẻ"),
	ECustomerType_ORGANIZATION("Doanh nghiệp"),
	ECustomerType_PARTNER("Đối tác");
	public final String label;

    private ECustomerType(String label) {
        this.label = label;
    }
}
