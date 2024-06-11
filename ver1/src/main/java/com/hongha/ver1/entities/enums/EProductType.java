package com.hongha.ver1.entities.enums;

public enum EProductType {
	EProductType_Product("SẢN PHẨM"),
	EProductType_Tool("CÔNG CỤ"),
	EProductType_Job("CÔNG VIỆC"),
	EProductType_MATERIAL("VẬT LIỆU");
	public final String label;
	private EProductType(String label) {
		this.label = label;
	}
}
