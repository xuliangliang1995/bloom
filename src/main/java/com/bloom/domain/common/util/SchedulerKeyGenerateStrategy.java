package com.bloom.domain.common.util;

/**
 * 任务调度key生成策略
 * @author xuliangliang
 *
 */
public enum SchedulerKeyGenerateStrategy {
	CHECK_PETAL_PROGRESS("JOB_PETAL_PROGRESS","GROUP_JOB_PETAL_PROGRESS","TRIGGER_PETAL_PROGRESS","GROUP_TRIGGER_PETAL_PROGRESS");
	private static final String TEMPLATE = "%s::%s";
	private String jobName;
	private String jobGroupName;
	private String triggerName;
	private String triggerGroupName;
	private SchedulerKeyGenerateStrategy(String jobName, String jobGroupName, String triggerName,
			String triggerGroupName) {
		this.jobName = jobName;
		this.jobGroupName = jobGroupName;
		this.triggerName = triggerName;
		this.triggerGroupName = triggerGroupName;
	}
	public String getJobName(String mutex) {
		return String.format(TEMPLATE, jobName,mutex);
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroupName(String mutex) {
		return String.format(TEMPLATE, jobGroupName,mutex);
	}
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}
	public String getTriggerName(String mutex) {
		return String.format(TEMPLATE, triggerName,mutex);
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroupName(String mutex) {
		return String.format(TEMPLATE, triggerGroupName,mutex);
	}
	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}

	
	
	

}
