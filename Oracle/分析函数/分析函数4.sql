窗口函数
first_value/last_value
rows between ...preceding and ... following
range between interval
current row
lag(sum(tot_sales),1), lead

-- ①列出每月的订单总额以及全年的订单总额
-- ②列出每月的订单总额以及截至到当前月的订单总额
-- ③列出上个月、当月、下一月的订单总额以及全年的订单总额
-- ④列出每天的营业额及一周来的总营业额
-- ⑤列出每天的营业额及一周来每天的平均营业额

-- ①通过指定一批记录：例如从当前记录开始直至某个部分的最后一条记录结束
-- ②通过指定一个时间间隔：例如在交易日之前的前30天
-- ③通过指定一个范围值：例如所有占到当前交易量总额5%的记录


-- 列出每月的订单总额以及全年的订单总额
1.实现方法1
select month,
sum(tot_sales) month_sales,
sum(sum(tot_sales)) over (order by month rows between unbounded preceding and unbounded following) total_sales
from orders
group by month;
2.实现方法2
select month,
sum(tot_sales) month_sales,
sum(sum(tot_sales)) over(/*order by month*/) all_sales  -- 加上Order by month , 则数逐条记录递增
from orders group by month;

-- 列出每月的订单总额以及截至到当前月的订单总额
1.实现方法1
select month,
sum(tot_sales) month_sales,
sum(sum(tot_sales)) over(order by month rows between unbounded preceding and current row) current_total_sales
from orders group by month;

2.实现方法2
select month,
sum(tot_sales) month_sales,
sum(sum(tot_sales)) over(order by month) all_sales  -- 加上Order by month , 则是前面记录累加到当前记录
from orders group by month;

-- 有时可能是针对全年的数据求平均值，有时会是针对截至到当前的所有数据求平均值。很简单，只需要将：
-- sum(sum(tot_sales))换成avg(sum(tot_sales))即可。

-- 统计当天销售额和五天内的平均销售额 range between interval
select trunc(order_dt) day,
sum(sale_price) daily_sales,
avg(sum(sale_price)) over 
(order by trunc(order_dt) range between interval '2' day preceding and interval '2' day following) five_day_avg
from cust_order
where sale_price is not null 
and order_dt between to_date('01-jul-2001','dd-mon-yyyy')
and to_date('31-jul-2001','dd-mon-yyyy')

-- 显示当前月、上一个月、后一个月的销售情况，以及每3个月的销售平均值
select month,
first_value(sum(tot_sales)) over 
(order by month rows between 1 preceding and 1 following) prev_month,
sum(tot_sales) monthly_sales,
last_value(sum(tot_sales)) over 
(order by month rows between 1 preceding and 1 following) next_month,
avg(sum(tot_sales)) over 
(order by month rows between 1 preceding and 1 following) rolling_avg
from orders_tmp
where year = 2001 and region_id = 6
group by month order by month;

-- 显示当月的销售额和上个月的销售额
-- first_value(sum(tot_sales) over (order by month rows between 1 precedingand 0 following))
-- lag(sum(tot_sales),1)中的1表示以1月为间隔基准, 对应为lead
select  month,            
sum(tot_sales) monthly_sales,
lag(sum(tot_sales), 1) over (order by month) prev_month_sales
from orders_tmp
where year = 2001 and region_id = 6
group by month order by month;





