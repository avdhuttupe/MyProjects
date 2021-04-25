package com.pair.assignment;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pair.assignment.pojo.TradeStore;
import com.pair.assignment.service.TradeStoreService;

@RestController
@RequestMapping("/tradestore")
public class TradeStoreController {

	@Autowired
	TradeStoreService tradeStoreServie;

	@PostMapping("/save")
	public ResponseEntity<TradeStore> createTradeStore(
			@Valid @RequestBody TradeStore store) {
		TradeStore savedStore = tradeStoreServie.save(store);
		return new ResponseEntity<>(savedStore, HttpStatus.OK);
	}

	@GetMapping("/get")
	public List<TradeStore> getTradeStore() {
		return tradeStoreServie.getStore();
	}

}
