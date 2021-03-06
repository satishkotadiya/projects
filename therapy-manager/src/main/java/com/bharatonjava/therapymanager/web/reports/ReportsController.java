package com.bharatonjava.therapymanager.web.reports;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bharatonjava.therapymanager.services.ReportService;
import com.bharatonjava.therapymanager.utils.Constants;
import com.bharatonjava.therapymanager.utils.Utils;

@Controller
@RequestMapping(value = "/reports")
public class ReportsController {

	private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);

	private ReportService reportService;

	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@RequestMapping(value = "/dailyEarnings", method = RequestMethod.GET)
	public ModelAndView getDailyEarningsReport(@RequestParam(name="date",required=false) Date date,
			@RequestParam(name="year", required=false) Integer selectedYear,
			@RequestParam(name="month", required=false) Integer selectedMonth) {
		
		logger.info("date: {}, selectedYear={}, selectedMonth={}", date, selectedYear, selectedMonth);

		if(selectedYear == null && selectedMonth == null){
			//selectedYear = 0;
			//selectedMonth = 0;
			Calendar cal = Calendar.getInstance();
			selectedYear = cal.get(Calendar.YEAR);
			selectedMonth = cal.get(Calendar.MONTH) + 1;
		}
		
		
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("dailyEarnings", this.reportService.getEarnings(selectedYear, selectedMonth));
		mav.addObject("years", this.reportService.getYearsForSittings());
		mav.addObject("selectedYear", selectedYear);
		mav.addObject("selectedMonth", selectedMonth);
		mav.addObject("months", Utils.monthsMap);
		
		if(date != null){
			mav.addObject("perDayStats", this.reportService.getEarningsPerDay(date));
		}
		
		mav.setViewName(Constants.VIEW_DAILY_EARNINGS);
		return mav;
	}
}
