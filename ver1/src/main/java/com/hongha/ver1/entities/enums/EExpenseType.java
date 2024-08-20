package com.hongha.ver1.entities.enums;

public enum EExpenseType {
	Expense_RENTAL("Mặt bằng"), Expense_ELECTRIC("Điện"), Expense_WATER("Nước"), Expense_STATIONERY("Văn phòng phẩm"),
	Expense_MONEY4FOOD("Tiền ăn"), Expense_OTHERS("Khác");

	public final String label;

	private EExpenseType(String label) {
		this.label = label;
	}
}
