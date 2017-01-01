八、oracle 分页

oracle的分页一共有三种方式

方法一 根据rowid来分

复制代码
SELECT *
  FROM EMP
 WHERE ROWID IN
       (SELECT RID
          FROM (SELECT ROWNUM RN, RID
                  FROM (SELECT ROWID RID, EMPNO FROM EMP ORDER BY EMPNO DESC)
                 WHERE ROWNUM <= ( (currentPage-1) * pageSize + pageSize )) --每页显示几条
         WHERE RN > ((currentPage-1) * pageSize) ) --当前页数
 ORDER BY EMPNO DESC;

eg、
-- 5 = (currentPage-1) * pageSize + pageSize   每页显示几条
-- 0 = (currentPage-1) * pageSize              当前页数
SELECT *
  FROM EMP
 WHERE ROWID IN
       (SELECT RID
          FROM (SELECT ROWNUM RN, RID
                  FROM (SELECT ROWID RID, EMPNO FROM EMP ORDER BY EMPNO DESC)
                 WHERE ROWNUM <= ( (1-1) * 5 + 5 )) --每页显示几条
         WHERE RN > ((1-1) * 5) ) --当前页数
 ORDER BY EMPNO DESC;
复制代码

方法二 按分析函数来分

复制代码
SELECT *
FROM (SELECT T.*, ROW_NUMBER() OVER(ORDER BY empno DESC) RK FROM emp T)
WHERE RK <= ( (currentPage-1) * pageSize + pageSize ) --每页显示几条
AND RK > ( (currentPage-1) * pageSize ); --当前页数

eg、
-- 5 = (currentPage-1) * pageSize + pageSize   每页显示几条
-- 0 = (currentPage-1) * pageSize              当前页数
SELECT *
FROM (SELECT T.*, ROW_NUMBER() OVER(ORDER BY empno DESC) RK FROM emp T)
WHERE RK <= 5
AND RK > 0;
复制代码

方法三 按rownum 来分

复制代码
SELECT *
  FROM (SELECT T.*, ROWNUM RN
          FROM (SELECT * FROM EMP ORDER BY EMPNO DESC) T
         WHERE ROWNUM <= ( (currentPage-1) * pageSize + pageSize )) --每页显示几条
 WHERE RN > ( (currentPage-1) * pageSize ); --当前页数

eg、
-- 5 = (currentPage-1) * pageSize + pageSize   每页显示几条
-- 0 = (currentPage-1) * pageSize              当前页数
SELECT *
  FROM (SELECT T.*, ROWNUM RN
          FROM (SELECT * FROM EMP ORDER BY EMPNO DESC) T
         WHERE ROWNUM <= 5)
 WHERE RN > 0;
复制代码
其中emp为表名称，empno 为表的主键id，获取按empno降序排序后的第1-5条记录，emp表有70000 多条记录。
个人感觉方法一的效率最好，方法三 次之，方法二 最差。

下面通过方法三来分析oracle怎么通过rownum分页的

复制代码
1、
SELECT * FROM emp;
2、显示rownum，由oracle分配的
SELECT e.*, ROWNUM rn FROM (SELECT * FROM emp) e; --rn相当于Oracle分配的行的ID号 
3、先查出1-10条记录
正确的: SELECT e.*, ROWNUM rn FROM (SELECT * FROM emp) e WHERE ROWNUM<=10;
错误的：SELECT e.*, ROWNUM rn FROM (SELECT * FROM emp) e WHERE rn<=10;
4、然后查出6-10条记录
SELECT * FROM (SELECT e.*, ROWNUM rn FROM (SELECT * FROM emp) e WHERE ROWNUM<=10) WHERE rn>=6;
复制代码