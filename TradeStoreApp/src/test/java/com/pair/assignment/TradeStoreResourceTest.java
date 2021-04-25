package com.pair.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pair.assignment.pojo.TradeStore;
import com.pair.assignment.pojo.TradeStoreId;
import com.pair.assignment.service.TradeStoreService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TradeStoreResourceTest {

	@Autowired
	TradeStoreResource resource;

	@Test
	@Order(3)
	void findById() {
		TradeStoreId storeId = new TradeStoreId("T1", "B1", "CP-1");
		Optional<TradeStore> trade = resource.findById((storeId));
		assertNotNull(trade);
	}

	@Test
	@Order(2)
	void findAll() {
		List<TradeStore> tradeStore = resource.findAll();
		assertEquals(
				1,
				(tradeStore.stream().filter(s -> s.getTradeId().equals("T9")
						&& s.getBookId().equals("B1")
						&& s.getCounterPartyId().equals("CP-4"))).count());
	}

	@Test
	@Order(1)
	void save() {
		TradeStore book = new TradeStore("T9", 1, "CP-4", "B1",
				TradeStoreService.getYearDate(1));
		TradeStore savedBook = resource.save(book);
		assertNotNull(savedBook);
		assertNotNull(savedBook.getCreateDate());
	}

}
