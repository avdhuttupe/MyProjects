insert into TradeStore(trade_Id, version,counter_party_id, book_id, maturity_date, created_date,expired)
 values('T1',1,'CP-1','B1',to_date('20/05/2020','DD/MM/YYYY'),to_date('24/04/2021','DD/MM/YYYY'),'N');
 
 insert into TradeStore(trade_Id, version,counter_party_id, book_id, maturity_date, created_date,expired)
 values('T2',2,'CP-2','B1',to_date('20/05/2021','DD/MM/YYYY'),to_date('24/04/2021','DD/MM/YYYY'),'N');
 
 insert into TradeStore(trade_Id, version,counter_party_id, book_id, maturity_date, created_date,expired)
 values('T2',1,'CP-1','B1',to_date('20/05/2021','DD/MM/YYYY'),to_date('14/03/2015','DD/MM/YYYY'),'N');
 
 insert into TradeStore(trade_Id, version,counter_party_id, book_id, maturity_date, created_date,expired)
 values('T3',3,'CP-3','B2',to_date('20/05/2014','DD/MM/YYYY'),to_date('24/04/2021','DD/MM/YYYY'),'Y');
 
 commit; 