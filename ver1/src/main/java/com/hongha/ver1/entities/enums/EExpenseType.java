package com.hongha.ver1.entities.enums;

public enum EExpenseType {
	Expense_RENTAL("Tiền thuê nhà"),
	Expense_ELECTRIC("Tiền điện"),
	Expense_WATER("Tiền nước"),
	Expense_STATIONERY("Văn phòng phẩm"),
	Expense_MONEY4FOOD("Tiền ăn"),
	Expense_OTHERS("Khác");
	public final String label;

    private EExpenseType(String label) {
        this.label = label;
    }
}
