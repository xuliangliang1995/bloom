package bloom;

import java.text.ParseException;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import com.bloom.task.Flyleaf;

public class Test {
	public static void main(String[] args) throws SchedulerException, ParseException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		
		JobDetailFactoryBean job = new JobDetailFactoryBean();
		job.setJobClass(Flyleaf.class);
		job.setDurability(true);
		job.setRequestsRecovery(true);
		job.afterPropertiesSet();
		JobDetail jd  = job.getObject();
		
		CronTriggerFactoryBean tb = new CronTriggerFactoryBean();
		tb.setCronExpression("0/5 * * * * ?");
		tb.setJobDetail(jd);
		tb.afterPropertiesSet();
		
		scheduler.scheduleJob(jd, tb.getObject());
	}

}
