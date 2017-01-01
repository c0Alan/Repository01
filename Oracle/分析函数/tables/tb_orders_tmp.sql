create table orders_tmp (
	CUST_NBR                 NUMBER(5)  	NOT NULL,	--not null 要放最后
	REGION_ID                NUMBER(5)  	NOT NULL, 
	SALESPERSON_ID      	 NUMBER(5)		NOT NULL, 
	YEAR                     NUMBER(4)  	NOT NULL, 
	MONTH                    NUMBER(2)   	NOT NULL, 
	TOT_ORDERS               NUMBER(7)		NOT NULL, 
	TOT_SALES                NUMBER(11,2)	NOT NULL 
);



insert into orders_tmp values(11, 7, 11, 2001   , 7    , 2   , 12204);
insert into orders_tmp values( 4, 5,  4,   2001 ,  10  ,  2  ,  37802);
insert into orders_tmp values( 7, 6,  7,   2001 ,   2  ,   3 ,    3750);
insert into orders_tmp values(10, 6,  8,   2001 ,   1  ,   2 ,   21691);
insert into orders_tmp values(10, 6,  7,   2001 ,   2  ,   3 ,   42624);
insert into orders_tmp values(15, 7, 12,  2000  ,  5   ,  6  ,     24);
insert into orders_tmp values(12, 7,  9,  2000  ,  6   ,  2  ,  50658);
insert into orders_tmp values( 1, 5,  2,   2000 ,   3  ,   2 ,   44494);
insert into orders_tmp values( 1, 5,  1,   2000 ,   9  ,   2 ,   74864);
insert into orders_tmp values( 2, 5,  4,    2000,    3 ,    2,    35060);
insert into orders_tmp values( 2, 5,  4,   2000 ,   4  ,   4 ,    6454);
insert into orders_tmp values( 2, 5,  1,   2000 ,  10  ,   4 ,   35580);
insert into orders_tmp values( 4, 5,  4,   2000 ,  12  ,   2 ,   39190);

