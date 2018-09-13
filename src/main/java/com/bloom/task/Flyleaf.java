package com.bloom.task;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 【飞叶流】
 * @author xuliangliang
 *
 */
public class Flyleaf extends QuartzJobBean{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info(String.format("%s start:%s", "飞叶流",DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")));
		System.out.println("进行中。。。");
		logger.info(String.format("%s end:%s", "飞叶流",DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")));
	}

}
