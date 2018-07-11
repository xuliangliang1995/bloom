package com.bloom.web.retentioncurve.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class AddRetentionCurveVO {
	@Min(value = 15,message = "{retentioncurve.intervalMinutes.min}")
	private Long intervalMinutes;
	@NotEmpty(message = "{retentioncurve.note.empty}")
	private String note;

	public Long getIntervalMinutes() {
		return intervalMinutes;
	}

	public void setIntervalMinutes(Long intervalMinutes) {
		this.intervalMinutes = intervalMinutes;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

}
