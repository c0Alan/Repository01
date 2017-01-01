RATIO_TO_REPORT

-- 列出上一年度每个月的销售总额、年底销售额以及每个月的销售额占全年总销售额的比例
-- 方法①:
select all_sales.*,
100 * round(cust_sales / region_sales, 2) || '%' Percent from 
(select o.cust_nbr customer,o.region_id region,
sum(o.tot_sales) cust_sales,
sum(sum(o.tot_sales)) over(partition by o.region_id) region_sales
from orders_tmp o
where o.year = 2001 group by o.region_id, o.cust_nbr) all_sales
where all_sales.cust_sales > all_sales.region_sales * 0.2;

-- 方法②：
select region_id, salesperson_id, 
sum(tot_sales) sp_sales,
round(sum(tot_sales) / sum(sum(tot_sales)) over (partition by region_id), 2) percent_of_region
from orders where year = 2001
group by region_id, salesperson_id
order by region_id, salesperson_id;

-- 方法③
select region_id, salesperson_id, 
sum(tot_sales) sp_sales,
(round(ratio_to_report(sum(tot_sales)) over (partition by region_id), 2)) * 100 || '%' sp_ratio
from orders_tmp where year = 2001
group by region_id, salesperson_id
order by region_id, salesperson_id;



