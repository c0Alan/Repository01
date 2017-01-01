十五、oracle 约束

一、维护数据的完整性
数据的完整性用于确保数据库数据遵从一定的商业和逻辑规则，在oracle中，数据完整性可以使用约束、触发器、应用程序（过程、函数）三种方法来实现，在这三种方法中，因为约束易于维护，并且具有最好的性能，所以作为维护数据完整性的首选。

二、约束
约束用于确保数据库数据满足特定的商业规则。在oracle中，约束包括：not null、 unique， primary key， foreign key和check 五种。
1)、not null(非空)
如果在列上定义了not null，那么当插入数据时，必须为列提供数据。
2)、unique(唯一)
当定义了唯一约束后，该列值是不能重复的，但是可以为null。
3)、primary key(主键)
用于唯一的标示表行的数据，当定义主键约束后，该列不但不能重复而且不能为null。
需要说明的是：一张表最多只能有一个主键，但是可以有多个unqiue约束。
4)、foreign key(外键)
用于定义主表和从表之间的关系。外键约束要定义在从表上，主表则必须具有主键约束或是unique 约束，当定义外键约束后，要求外键列数据必须在主表的主键列存在或是为null。
5)、check
用于强制行数据必须满足的条件，假定在sal列上定义了check约束，并要求sal列值在1000-2000之间如果不在1000-2000之间就会提示出错。

三、商店售货系统表设计案例一
现有一个商店的数据库，记录客户及其购物情况，由下面三个表组成：
商品goods(商品号goodsId，商品名goodsName，单价unitprice，商品类别category，供应商provider)；
客户customer(客户号customerId，姓名name，地址address，电邮email，性别sex，身份证cardId)；
购买purchase(客户号customerId，商品号goodsId，购买数量nums)；
请用SQL语言完成下列功能：
1. 建表，在定义中要求声明：
(1). 每个表的主外键；
(2). 客户的姓名不能为空值；
(3). 单价必须大于0，购买数量必须在1到30之间；
(4). 电邮不能够重复；
(5). 客户的性别必须是男或者女，默认是男；

复制代码
SQL> create table goods(
   goodsId char(8) primary key, --主键
   goodsName varchar2(30),
   unitprice number(10,2) check(unitprice>0),
   category varchar2(8),
   provider varchar2(30)
);
SQL> create table customer( 
   customerId char(8) primary key, --主键
   name varchar2(50) not null, --不为空
   address varchar2(50),
   email varchar2(50) unique, --唯一
   sex char(2) default '男' check(sex in ('男','女')), -- 一个char能存半个汉字，两位char能存一个汉字
   cardId char(18)
);
SQL> create table purchase( 
   customerId char(8) references customer(customerId),
   goodsId char(8) references goods(goodsId),
   nums number(10) check (nums between 1 and 30)
);
表是默认建在SYSTEM表空间的
复制代码

四、商店售货系统表设计案例二
如果在建表时忘记建立必要的约束，则可以在建表后使用alter table命令为表增加约束。但是要注意：增加not null约束时，需要使用modify选项，而增加其它四种约束使用add选项。
1)、增加商品名也不能为空
SQL> alter table goods modify goodsName not null;
2)、增加身份证也不能重复
SQL> alter table customer add constraint xxxxxx unique(cardId);
3)、 增加客户的住址只能是’海淀’,’朝阳’,’东城’,’西城’,’通州’,’崇文’,’昌平’；
SQL> alter table customer add constraint yyyyyy check (address in ('海淀','朝阳','东城','西城','通州','崇文','昌平'));

删除约束
当不再需要某个约束时，可以删除。
alter table 表名 drop constraint 约束名称；
特别说明一下：在删除主键约束的时候，可能有错误，比如：alter table 表名 drop primary key；这是因为如果在两张表存在主从关系，那么在删除主表的主键约束时，必须带上cascade选项 如像：alter table 表名 drop primary key cascade;

显示约束信息
1)、显示约束信息
通过查询数据字典视图user_constraints，可以显示当前用户所有的约束的信息。
select constraint_name, constraint_type, status, validated from user_constraints where table_name = '表名';
2)、显示约束列
通过查询数据字典视图user_cons_columns，可以显示约束所对应的表列信息。
select column_name, position from user_cons_columns where constraint_name = '约束名';
3)、当然也有更容易的方法，直接用pl/sql developer查看即可。简单演示一下下...

五、表级定义、列级定义
1)、列级定义
列级定义是在定义列的同时定义约束。
如果在department表定义主键约束

create table department4(
   dept_id number(12) constraint pk_department primary key,
   name varchar2(12), 
   loc varchar2(12)
);

2)、表级定义
表级定义是指在定义了所有列后，再定义约束。这里需要注意：
not null约束只能在列级上定义。
以在建立employee2表时定义主键约束和外键约束为例：

复制代码
create table employee2(
   emp_id number(4), 
   name varchar2(15),
   dept_id number(2), 
   constraint pk_employee primary key (emp_id),
   constraint fk_department foreign key (dept_id) references department4(dept_id)
);
复制代码