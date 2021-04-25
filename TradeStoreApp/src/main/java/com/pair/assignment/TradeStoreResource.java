package com.pair.assignment;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pair.assignment.pojo.TradeStore;
import com.pair.assignment.pojo.TradeStoreId;

@Repository
public interface TradeStoreResource extends
		JpaRepository<TradeStore, TradeStoreId> {

	@Modifying
	@Transactional
	@Query(value = "update TRADESTORE  set expired = 'Y'  where trunc(maturity_date) < trunc(sysdate)", nativeQuery = true)
	int updateStoreSetExpiryFlag();

}
