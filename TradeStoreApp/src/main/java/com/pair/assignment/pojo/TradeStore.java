package com.pair.assignment.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pair.assignment.service.TradeStoreService;

@Entity
@Table(name = "TRADESTORE")
@IdClass(TradeStoreId.class)
public class TradeStore implements Serializable {

	private static final long serialVersionUID = 1L;

	public TradeStore() {
		super();
	}

	public TradeStore(
			@NotBlank String tradeId,
			@Digits(integer = 4, fraction = 0) Integer version,
			String counterPartyId,
			String bookId,
			@FutureOrPresent(message = "maturity date should be current or future date") Date maturityDate,
			String expired, Date createDate) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.expired = maturityDate.before(TradeStoreService.currentDate()) ? "Y"
				: "N";
		this.createDate = new Date();
	}

	public TradeStore(
			@NotBlank String tradeId,
			@Digits(integer = 4, fraction = 0) Integer version,
			String counterPartyId,
			String bookId,
			@FutureOrPresent(message = "maturity date should be current or future date") Date maturityDate) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.expired = maturityDate.before(TradeStoreService.currentDate()) ? "Y"
				: "N";
		this.createDate = new Date();
	}

	@Digits(integer = 2, fraction = 0)
	@Column(name = "VERSION")
	Integer version;

	@NotBlank
	@Id
	@Column(name = "COUNTER_PARTY_ID")
	String counterPartyId;

	@NotBlank
	@Id
	@Column(name = "BOOK_ID")
	String bookId;

	@NotBlank
	@Id
	@Column(name = "TRADE_ID")
	String tradeId;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@FutureOrPresent(message = "maturity date should be current or future date")
	@Column(name = "MATURITY_DATE")
	Date maturityDate;

	@Column(name = "EXPIRED")
	String expired;

	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	@Column(name = "CREATED_DATE")
	Date createDate;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate == null ? new Date() : createDate;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

}
