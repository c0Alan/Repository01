Top/Bottom N
First/Last
NTile

-- ①对所有客户按订单总额进行排名
-- ②按区域和客户订单总额进行排名
-- ③找出订单总额排名前13位的客户
-- ④找出订单总额最高、最低的客户
-- ⑤找出订单总额排名前25%的客户


-- 此处 null 被排到第一位 , 可以加 nulls last 把null的数据放到最后
select region_id, customer_id,
sum(customer_sales) cust_sales,
sum(sum(customer_sales)) over(partition by region_id) ran_total,
rank() over(partition by region_id order by sum(customer_sales) desc /* nulls last */) rank
from user_order
group by region_id, customer_id;

-- 找出所有订单总额排名前3的大客户
select *from 
(select region_id,
	customer_id,
	sum(customer_sales) cust_total,
	rank() over(order by sum(customer_sales) desc NULLS LAST) rank
	from user_order
	group by region_id, customer_id)
where rank <= 3;

-- 找出每个区域订单总额排名前3的大客户
select *
from (select region_id,
	customer_id,
	sum(customer_sales) cust_total,
	sum(sum(customer_sales)) over(partition by region_id) reg_total,
	rank() over(partition by region_id order by sum(customer_sales) desc NULLS LAST) rank
	from user_order
	group by region_id, customer_id)
where rank <= 3;

-- min keep first last 找出订单总额最高、最低的客户
-- Min只能用于 dense_rank
-- min 函数的作用是用于当存在多个First/Last情况下保证返回唯一的记录, 去掉会出错
-- keep的作用。告诉Oracle只保留符合keep条件的记录。
select 
min(customer_id) keep (dense_rank first order by sum(customer_sales) desc) first,
min(customer_id) keep (dense_rank last order by sum(customer_sales) desc) last
from user_order
group by customer_id;


-- 出订单总额排名前1/5的客户 ntile
-- 1.将数据分成5块
select region_id,customer_id,
sum(customer_sales) sales,
ntile(5) over(order by sum(customer_sales) desc nulls last) tile
from user_order
group by region_id, customer_id;
-- 2.提取 tile=1 的数据
select * from 
(select region_id,customer_id,
sum(customer_sales) sales,
ntile(5) over(order by sum(customer_sales) desc nulls last) tile
from user_order
group by region_id, customer_id)
where tile = 1;

-- cust_nbr,month 为主键, 去重,只留下month最大的记录
-- 查找 cust_nbr 相同, month 最大的记录
select cust_nbr,
max(month) keep(dense_rank first order by month desc) max_month
from orders_tmp group by cust_nbr;

-- 去重, cust_nbr,month 为主键, cust_nbr 相同,只留下month最大的记录
delete from orders_tmp2 where (cust_nbr, month) not in 
(select cust_nbr,
max(month) keep(dense_rank first order by month desc) max_month
from orders_tmp2 tb group by cust_nbr)



