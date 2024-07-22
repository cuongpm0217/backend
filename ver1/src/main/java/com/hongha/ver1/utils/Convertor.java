package com.hongha.ver1.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.annotation.Bean;

public class Convertor {
	public Date ddMMYYYY2Date(String ddMMYYYY) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date result = new Date();
		try {
			result = df.parse(ddMMYYYY);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String Date2ddMMyyyy(Date date) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String result = df.format(date);
		return result;
	}

	public String Date2ddMMyyyyhhmm(Date date) {
		String pattern = "dd/MM/yyyy HH:mm";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String result = df.format(date);
		return result;
	}
	public Date Date2ddMMyyyyhhmmDate(Date date) {
		String pattern = "dd/MM/yyyy HH:mm";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String result = df.format(date);		
		try {
			return df.parse(result);
		} catch (ParseException e) {			
			e.printStackTrace();
			return new Date();
		}
	}
	public String Date2HHmm(Date date) {
		String pattern = "HH:mm";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String result = df.format(date);
		return result;
	}

	public Date stringTimeStamp2Date(String timeStamp) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date result = null;
		try {
			df.parse(timeStamp);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return result;
	}
	
	public static Date date2ddMMyyyy(Date date) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String dateFormated = df.format(date);
		Date result = null;
		try {
			result = df.parse(dateFormated);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * convert number to string VietNamese currency
	 */
	private String[] one_digit = { "không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín" };
	private String[] units = { "", " mốt", " hai", " ba", " bốn", " lăm", " sáu", " bảy", " tám", " chín" };
	private String[] tens = { "linh", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi",
			"tám mươi", "chín mươi" };
	private String[] hundreds = { "không trăm", "một trăm", "hai trăm", "ba trăm", "bốn trăm", "năm trăm", "sáu trăm",
			"bảy trăm", "tám trăm", "chín trăm" };
	private String[] bigger = { "", " nghìn", " triệu", " tỉ", " nghìn tỉ", " triệu tỉ", " tỉ tỉ" };

	private String read_class(String s) {
		int n = Integer.parseInt(s);
		String _hundreds = hundreds[n / 100];
		String _tens = tens[(n / 10) % 10];
		String _units = units[n % 10];
		if (s.length() == 1)
			return one_digit[n];
		else if (s.length() == 2)
			return tens[n / 10] + _units;
		else {
			if (s == "000")
				return "";
			else if ((n / 10) % 10 == 0) {
				if (n % 10 == 0)
					if (n == 0) {
						return "";
					} else
						return _hundreds;
				else
					return _hundreds + " " + _tens + " " + one_digit[n % 10];
			} else
				return _hundreds + " " + _tens + _units;
		}

	}

	public String thanhTien(String long2String) {
		ArrayList<String> classes = new ArrayList<String>();

		for (int i = long2String.length() - 1; i > 1; i = i - 3)
			classes.add(long2String.substring(i - 2, i + 1));

		if (long2String.length() % 3 != 0)
			classes.add(long2String.substring(0, long2String.length() % 3));

		String res = "";
		for (int i = 0; i < classes.size(); i++) {
			String named_class = read_class(classes.get(i));
			if (named_class != "")
				res = named_class + bigger[i] + " " + res;
		}
		return res + " đồng chẵn";
	}

}
