package com.hongha.ver1.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class GenerateCode {

	public static String GenInvoiceCode(int countInYear, String invoiceType) {
		String code = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String date = df.format(new Date());
		DecimalFormat nf = new DecimalFormat("000000");
		if (countInYear == 0) {
			countInYear = 1;
		}
		String count = nf.format(countInYear);
		switch (invoiceType) {
		case "Purchase": {
			code = "PM";
		}
		case "RepairBill": {
			code = "DV";
		}
		}
		return code += date + count;
	}

//	key Redis
	public static String getKeyList(Class<?> responseClass, String searchText, int pageNo, int pageSize, String sortBy,
			String sortType) {
		searchText = searchText != null ? searchText : "";
		String key = "all%s:%s:%d:%d:%s:%s".formatted(responseClass.getSimpleName(), searchText, pageNo, pageSize,
				sortBy, sortType);
		return key;
	}

	public static String getKeyOne(Class<?> responseClass, UUID uuid) {
		String key = responseClass.getSimpleName() + "#" + uuid.toString();
		return key;
	}

	public static String getKeyPagination(Class<?> responseClass, String searchText) {
		searchText = searchText != null ? searchText : "";
		String key = "pagination%s:%s".formatted(responseClass.getSimpleName(), searchText);
		return key;
	}
}
