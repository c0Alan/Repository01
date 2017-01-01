-- FUNCTION_NAME(<argument>,<argument>...)
-- OVER
-- (<Partition-Clause><Order-by-Clause><Windowing Clause>)
partition : 按照表达式分区(就是分组),如果省略了分区子句,则全部的结果集被看作是一个单一的组
order by : 计算中所使用的行的集合是当前分区中当前行和前面所有行,没有ORDERBY时,默认的窗口是全部的分区
例:
sum(sal) over (partition by deptno order by ename) new_alias

-- 开窗(windowing)函数
sum(t.sal) over (order by t.deptno,t.ename) running_total,
sum(t.sal) over (partition by t.deptno order by t.ename) department_total

-- 制表(reporting)函数
-- 与开窗函数同名,作用于一个分区或一组上的所有列,
-- 与开窗函数的关键不同之处在于OVER语句上缺少一个ORDER BY子句!
sum(t.sal) over () running_total2,
sum(t.sal) over (partition by t.deptno ) department_total2


-- RANGE窗口仅对NUMBERS和DATES起作用,ORDER BY中只能有一列
avg(t.sal) over(order by t.hiredate asc range 100 preceding) -- 统计前100天平均工资


-- Specifying窗口
range between 100 preceding and 100 following; --当前行100前,当前后100后


-- 按区域查找上一年度订单总额占区域订单总额20%以上的客户 table : orders_tmp
-- 1.找出2001年度区域订单总额
select o.cust_nbr customer,o.region_id region,sum(o.tot_sales) cust_sales,
sum(sum(o.tot_sales)) over(partition by o.region_id) region_sales
from orders_tmp o where o.year = 2001 group by o.region_id, o.cust_nbr;

-- 2.在1的基础上得出订单总额占区域订单总额20%以上的客户
select * from 
(select o.cust_nbr customer, o.region_id region,
	sum(o.tot_sales) cust_sales,
	sum(sum(o.tot_sales)) over(partition by o.region_id) region_sales
	from orders_tmp o where o.year = 2001
	group by o.region_id, o.cust_nbr) all_sales
where all_sales.cust_sales > all_sales.region_sales * 0.2;

-- 加上百分比 round()
select cust_nbr, region_id, cust_sales, region_sales,  -- 此处可以用tmptb.* , 但不能用 *
100 * round(cust_sales / region_sales, 2) || '%' Percent from 
(select cust_nbr, region_id,
sum(TOT_SALES) cust_sales,
sum(sum(tot_sales)) over(partition by REGION_ID) as region_sales
from orders_tmp where o.year = 2001 group by CUST_NBR, REGION_ID 
order by REGION_ID) tmptb
where cust_sales > region_sales * 0.2;






















