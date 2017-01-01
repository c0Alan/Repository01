五、oracle 表的管理

一、表名和列名的命名规则
1)、必须以字母开头
2)、长度不能超过30个字符
3)、不能使用oracle的保留字
4)、只能使用如下字符 a-z，a-z，0-9，$,#等

二、数据类型
1)、字符类
char 长度固定，最多容纳2000个字符。
例子：char(10) ‘小韩’前四个字符放‘小韩’，后添6个空格补全，如‘小韩      ’
varchar2(20) 长度可变，最多容纳4000个字符。
例子：varchar2（10） ‘小韩’ oracle分配四个字符。这样可以节省空间。
clob(character large object) 字符型大对象，最多容纳4g
char 查询的速度极快浪费空间，适合查询比较频繁的数据字段。
varchar 节省空间
2)、数字型
number范围-10的38次方到10的38次方，可以表示整数，也可以表示小数
number(5,2)表示一位小数有5位有效数，2位小数；范围：-999.99 到999.99
number(5)表示一个5位整数；范围99999到-99999
3)、日期类型
date 包含年月日和时分秒 oracle默认格式1-1月-1999
timestamp 这是oracle9i对date数据类型的扩展。可以精确到毫秒。
4)、图片
blob 二进制数据，可以存放图片/声音4g；一般来讲，在真实项目中是不会把图片和声音真的往数据库里存放，一般存放图片、视频的路径，如果安全需要比较高的话，则放入数据库。

三、怎样创建表

--创建表
--学生表
create table student (
   xh number(4), --学号
   xm varchar2(20), --姓名
   sex char(2), --性别
   birthday date, --出生日期
   sal number(7,2) --奖学金
);

--班级表
create table class(
  classid number(2),
  cname varchar2(40)
);

--修改表
--添加一个字段
sql>alter table student add (classid number(2));
--修改一个字段的长度
sql>alter table student modify (xm varchar2(30));
--修改字段的类型或是名字（不能有数据） 不建议做
sql>alter table student modify (xm char(30));
--删除一个字段 不建议做(删了之后，顺序就变了。加就没问题，应该是加在后面)
sql>alter table student drop column sal;
--修改表的名字 很少有这种需求
sql>rename student to stu;

--删除表
sql>drop table student;

--添加数据
--所有字段都插入数据
insert into student values ('a001', '张三', '男', '01-5 月-05', 10);
--oracle中默认的日期格式‘dd-mon-yy’ dd 天 mon 月份 yy 2位的年 ‘09-6 月-99’ 1999年6月9日
--修改日期的默认格式（临时修改，数据库重启后仍为默认；如要修改需要修改注册表）
alter session set nls_date_format ='yyyy-mm-dd';
--修改后，可以用我们熟悉的格式添加日期类型：
insert into student values ('a002', 'mike', '男', '1905-05-06', 10);
--插入部分字段
insert into student(xh, xm, sex) values ('a003', 'john', '女');
--插入空值
insert into student(xh, xm, sex, birthday) values ('a004', 'martin', '男', null);
--问题来了，如果你要查询student表里birthday为null的记录，怎么写sql呢？
--错误写法：select * from student where birthday = null;
--正确写法：select * from student where birthday is null;
--如果要查询birthday不为null,则应该这样写：
select * from student where birthday is not null;

--修改数据
--修改一个字段
update student set sex = '女' where xh = 'a001';
--修改多个字段
update student set sex = '男', birthday = '1984-04-01' where xh = 'a001';
--修改含有null值的数据
不要用 = null 而是用 is null；
select * from student where birthday is null;

--删除数据
delete from student; --删除所有记录，表结构还在，写日志，可以恢复的，速度慢。
--delete的数据可以恢复。
savepoint a; --创建保存点
delete from student;
rollback to a; --恢复到保存点
一个有经验的dba，在确保完成无误的情况下要定期创建还原点。

drop table student; --删除表的结构和数据；
delete from student where xh = 'a001'; --删除一条记录；
truncate table student; --删除表中的所有记录，表结构还在，不写日志，无法找回删除的记录，速度快。