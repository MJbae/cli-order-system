- 웹 기반 소스 데이터 변환 서비스 활용(CSV to SQL)
- 데이터 변환 서비스 출처: https://www.convertcsv.com/csv-to-sql.htm

```sql
# DDL
CREATE TABLE item
(
    item_id        BIGINT NOT NULL,
    item_name      VARCHAR(64),
    price          INT    NOT NULL,
    stock_quantity INT    NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (item_id)
);

# DML
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (768848,'[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종',21000,45);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (748943,'디오디너리 데일리 세트 (Daily set)',19000,89);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (779989,'버드와이저 HOME DJing 굿즈 세트',35000,43);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (779943,'Fabrik Pottery Flat Cup & Saucer - Mint',24900,89);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (768110,'네페라 손 세정제 대용량 500ml 이더블유지',7000,79);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (517643,'에어팟프로 AirPods PRO 블루투스 이어폰(MWP22KH/A)',260800,26);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (706803,'ZEROVITY™ Flip Flop Cream 2.0 (Z-FF-CRAJ-)',38000,81);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (759928,'마스크 스트랩 분실방지 오염방지 목걸이',2800,85);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (213341,'20SS 오픈 카라/투 버튼 피케 티셔츠 (6color)',33250,99);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (377169,'[29Edition.]_[스페셜구성] 뉴코튼베이직 브라렛 세트 (브라1+팬티2)',24900,60);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (744775,'SHUT UP [TK00112]',28000,35);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (779049,'[리퍼브/키친마켓] Fabrik Pottery Cup, Saucer (단품)',10000,64);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (611019,'플루크 new 피그먼트 오버핏 반팔티셔츠 FST701 / 7color M',19800,7);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (628066,'무설탕 프로틴 초콜릿 틴볼스',12900,8);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (502480,'[29Edition.]_[스페셜구성] 렉시 브라렛 세트(브라1+팬티2)',24900,41);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (782858,'폴로 랄프로렌 남성 수영복반바지 컬렉션 (51color)',39500,50);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (760709,'파버카스텔 연필1자루',200,70);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (778422,'캠핑덕 우드롤테이블',45000,7);
INSERT INTO item(item_id,item_name,price,stock_quantity) VALUES (648418,'BS 02-2A DAYPACK 26 (BLACK)',238000,5);
```
