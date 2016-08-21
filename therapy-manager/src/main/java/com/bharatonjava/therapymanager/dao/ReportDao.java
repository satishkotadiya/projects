package com.bharatonjava.therapymanager.dao;

import java.util.List;

import com.bharatonjava.therapymanager.domain.DailyEarningsDto;

public interface ReportDao {

	public List<DailyEarningsDto> getEarnings(Integer year, Integer month);
	
	public List<Long> getYearsForSittings();
}
