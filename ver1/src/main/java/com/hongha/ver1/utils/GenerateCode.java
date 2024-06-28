package com.hongha.ver1.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class GenerateCode {

	public String GenInvoiceCode(int countInYear, String invoiceType) {
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
}
