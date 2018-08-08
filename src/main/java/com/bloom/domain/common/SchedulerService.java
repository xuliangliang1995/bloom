package com.bloom.domain.common;

import java.util.function.Supplier;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

/**
 * 任务调度服务
 * @author xuliangliang
 *
 */
public interface SchedulerService {
	
	void addScheduler(Supplier<JobDetail> jobDetail,Supplier<Trigger> trigger) throws SchedulerException;

}
