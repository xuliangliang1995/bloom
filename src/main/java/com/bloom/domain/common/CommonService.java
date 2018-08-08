package com.bloom.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;

/**
 * 公共服务
 * @author xuliangliang
 *
 */
@Service
@Getter
public class CommonService {
	@Autowired
	private SchedulerService schedulerService;

}
