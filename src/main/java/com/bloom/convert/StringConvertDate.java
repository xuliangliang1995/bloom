package com.bloom.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.convert.converter.Converter;

public class StringConvertDate implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat = getSimpleDateFormat(source);
        Date date = null;
        try {
			date = simpleDateFormat.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return date;
	}
	
	private SimpleDateFormat getSimpleDateFormat(String source) {
        SimpleDateFormat simpleDateFormat;
        if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", source)) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else if (Pattern.matches("^\\d{4}/\\d{2}/\\d{2}$", source)) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        } else if (Pattern.matches("^\\d{4}\\d{2}\\d{2}$", source)) {
            simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        } else {
            throw new TypeMismatchException("", Date.class);
        }

        return simpleDateFormat;
    }

}
