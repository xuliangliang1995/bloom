package com.bloom.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.bloom.domain.petal.PetalProgressService;

/**
 * 【飞叶流】
 * @author xuliangliang
 *
 */
@Component
public class Flyleaf extends QuartzJobBean{
	@Autowired
	private PetalProgressService petalProgressServiceImpl;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info(String.format("%s start:%s", "飞叶流",DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")));
		
		List<Long> progressIds = petalProgressServiceImpl.outdatedNoFireProgerssIdList();
		
		progressIds.stream().forEach(id -> petalProgressServiceImpl.fire(id));
		
		logger.info(String.format("%s end:%s", "飞叶流",DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")));
	}

}
