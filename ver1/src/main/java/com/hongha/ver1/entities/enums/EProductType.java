package com.hongha.ver1.entities.enums;

public enum EProductType {
	EProductType_PRODUCT("SẢN PHẨM"),
	EProductType_TOOL("CÔNG CỤ"),
	EProductType_TASK("CÔNG VIỆC"),
	EProductType_MATERIAL("VẬT LIỆU");
	public final String label;
	private EProductType(String label) {
		this.label = label;
	}
}
