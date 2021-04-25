package com.pair.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pair.assignment.exception.LowerVersionTradeException;
import com.pair.assignment.pojo.TradeStore;
import com.pair.assignment.service.TradeStoreService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TradeStoreServiceTest {

	@Autowired
	private TradeStoreService service;

	// @InjectMocks
	// private TradeStoreService service=new TradeStoreService();

	@Test
	@Order(1)
	void testExistingTrade() {

		TradeStore store = new TradeStore("T1", 1, "CP-1", "B1", getYearDate(1));
		TradeStore savedData = service.save(store);
		assertNotNull(savedData);
	}

	@Test
	@Order(2)
	void testExistingTradeOverride() {
		TradeStore store = new TradeStore("T1", 2, "CP-1", "B1", getYearDate(1));
		TradeStore savedData = service.save(store);
		assertNotNull(savedData);
		assertEquals(2, savedData.getVersion());
		assertEquals(getYearDate(1), savedData.getMaturityDate());
		assertEquals("N", savedData.getExpired());
	}

	@Test
	@Order(3)
	void testNewTrade() {
		TradeStore store = new TradeStore("T2", 2, "CP-2", "B3", getYearDate(1));
		TradeStore savedData = service.save(store);
		assertEquals(2, savedData.getVersion());
		assertEquals("B3", savedData.getBookId());
		assertEquals("N", savedData.getExpired());
	}

	@Test
	@Order(4)
	void testExistingTradeLowVersion() {
		TradeStore store = new TradeStore("T1", 1, "CP-1", "B1", getYearDate(1));
		Assertions.assertThrows(LowerVersionTradeException.class, () -> {
			service.save(store);
		});
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
