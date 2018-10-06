package com.bloom.util.time;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class GeneralDateUtils {
	
	public static Date start(Date time) {
		String timeDate = DateFormatUtils.format(time, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
		try {
			return DateUtils.parseDate(timeDate, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
		} catch (ParseException e) {
			//ignore,这里不会出现问题
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date end(Date time) {
		String timeDate = DateFormatUtils.format(time, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
		timeDate = timeDate + " 23:59:59";
		try {
			return DateUtils.parseDate(timeDate, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()
					+ " "
					+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.getPattern());
		} catch (ParseException e) {
			//ignore,这里不会出现问题
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date todayStart() {
		return start(new Date());
	}
	
	public static Date todayEnd() {
		return end(new Date());
	}
}
