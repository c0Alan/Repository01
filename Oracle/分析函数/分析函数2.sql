(Rank, Dense_rank, row_number)

①ROW_NUMBER：12345
Row_number函数返回一个唯一的值，当碰到相同数据时，排名按照记录集中记录的顺序依次递增。 
②DENSE_RANK：12223
Dense_rank函数返回一个唯一的值，除非当碰到相同数据时，此时所有相同数据的排名都是一样的。 
③RANK：12225
Rank函数返回一个唯一的值，除非遇到相同的数据时，此时所有相同数据的排名是一样的，
同时会在最后一条相同记录和下一条不同记录的排名之间空出排名。

-- ①对所有客户按订单总额进行排名
-- ②按区域和客户订单总额进行排名
-- ③找出订单总额排名前13位的客户
-- ④找出订单总额最高、最低的客户
-- ⑤找出订单总额排名前25%的客户


-- 筛选排名前12位的客户, table : user_order
-- 1.对所有客户按订单总额进行排名, 使用rownum , rownum = 13,14 的数据跟 12 的数据一样, 但是被漏掉了
select rownum, tmptb.* from 
(select * from user_order order by CUSTOMER_sales desc) tmptb
where rownum <= 12;

-- 2.按区域和客户订单总额进行排名 Rank, Dense_rank, row_number
select region_id, customer_id, 
sum(customer_sales) total,
rank() over(partition by region_id order by sum(customer_sales) desc) rank,
dense_rank() over(partition by region_id order by sum(customer_sales) desc) dense_rank,
row_number() over(partition by region_id order by sum(customer_sales) desc) row_number
from user_order
group by region_id, customer_id;
