package com.pair.assignment.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TradeStoreId implements Serializable {

	private static final long serialVersionUID = 2L;

	String tradeId;
	String bookId;
	String counterPartyId;

	public TradeStoreId() {
		super();
	}

	public TradeStoreId(String tradeId, String bookId, String counterPartyId) {
		super();
		this.tradeId = tradeId;
		this.bookId = bookId;
		this.counterPartyId = counterPartyId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

}
