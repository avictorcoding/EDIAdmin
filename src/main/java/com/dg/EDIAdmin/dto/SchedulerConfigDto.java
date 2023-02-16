package com.dg.EDIAdmin.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class SchedulerConfigDto {
	private String schedule;
	
	// every hour
	private int frequency;
	
	private Date scheduleDateOfMonth;
	
	//It should be numeric 0: sunday
	private int scheduleDayOfWeek;
	
	//24hr format
	private String scheduleTimeOfDay;
	
	private boolean enabled;
	
	
	private boolean fromDatasource;
	

}
