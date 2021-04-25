package com.pair.assignment.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pair.assignment.TradeStoreResource;
import com.pair.assignment.exception.LowerVersionTradeException;
import com.pair.assignment.pojo.TradeStore;
import com.pair.assignment.pojo.TradeStoreId;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Component
public class TradeStoreService {
	@Autowired
	TradeStoreResource resource;

	@PersistenceContext
	EntityManager entityManager;
	
	private static final Logger logger = LogManager.getLogger(TradeStoreService.class);

	public TradeStore save(TradeStore store) {

		store.setCreateDate(new Date());
		store.setExpired(store.getMaturityDate().before(currentDate()) ? "Y"
				: "N");

		Optional<TradeStore> uniqueStore = resource.findById(new TradeStoreId(
				store.getTradeId(), store.getBookId(), store
						.getCounterPartyId()));
		
		logger.info("Application_Log - Trade Exist ....."+uniqueStore.isPresent());

		if (uniqueStore.isPresent()) {
			TradeStore trade = uniqueStore.get();
			if (trade.getVersion() > store.getVersion()) {
				throw new LowerVersionTradeException(
						"Trade Rejected : trade version " + store.getVersion()
								+ " is lower than existing trade version");
			}
		}

		return resource.save(store);
	}

	public List<TradeStore> getStore() {
		return resource.findAll();
	}

	// To Run Scheduler Daily to refresh Expiry Flag
	// @Scheduled(cron = "1/5 * * * * *") //Run Every 5 seconds
	@Scheduled(cron = "* * 1 * * *")
	public int refreshExpiry() {
		logger.info("Application_Log - refreshExpiry called ....."+new Date());
		return resource.updateStoreSetExpiryFlag();

	}

	public static Date currentDate() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = format.format(new Date());
		DateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = parser.parse(strDate);
		} catch (ParseException e) {

		}
		return date;
	}

	public static Date getYearDate(int year) {
		Date date = null;
		DateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String strDate = format.format(new Date());
			date = parser.parse(strDate);
			date.setYear(new Date().getYear() + year);
		} catch (ParseException e) {
			return date;
		}
		return date;
	}
}
