package com.hongha.ver1.entities.enums;

public enum ECustomerType {
	ECustomerType_INVIDUAL("Khách lẻ"),
	ECustomerType_ORGANIZATION("Doanh nghiệp");
	public final String label;

    private ECustomerType(String label) {
        this.label = label;
    }
}
