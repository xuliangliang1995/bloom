package com.bloom.domain.common.impl;

import java.util.function.Supplier;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.bloom.domain.common.SchedulerService;

@Service
public class SchedulerServiceImpl implements SchedulerService {
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	

	@Override
	public void addScheduler(Supplier<JobDetail> jobDetail, Supplier<Trigger> trigger) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.scheduleJob(jobDetail.get(),trigger.get());
	}

}
