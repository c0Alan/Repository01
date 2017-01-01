二十三、oracle pl/sql分类三 包

包用于在逻辑上组合过程和函数，它由包规范和包体两部分组成。
1)、我们可以使用create package命令来创建包，如：
i、创建一个包sp_package
ii、声明该包有一个过程update_sal
iii、声明该包有一个函数annual_income

--声明该包有一个存储过程和一个函数
create package sp_package is
   procedure update_sal(name varchar2, newsal number);
   function annual_income(name varchar2) return number;
end;

2)、建立包体可以使用create package body命令
给包sp_package实现包体

复制代码
CREATE OR REPLACE PACKAGE BODY SP_PACKAGE IS
  --存储过程
  PROCEDURE UPDATE_SAL(NAME VARCHAR2, NEWSAL NUMBER) IS
  BEGIN
     UPDATE EMP SET SAL = NEWSAL WHERE ENAME = NAME;
     COMMIT;
  END;

  --函数
  FUNCTION ANNUAL_INCOME(NAME VARCHAR2) RETURN NUMBER IS
     ANNUAL_SALARY NUMBER;
  BEGIN
     SELECT SAL * 12 + NVL(COMM, 0) INTO ANNUAL_SALARY FROM EMP WHERE ENAME = NAME;
     RETURN ANNUAL_SALARY;
  END;
END;
/
复制代码

3)、如何调用包的过程或是函数
当调用包的过程或是函数时，在过程和函数前需要带有包名，如果要访问其它方案的包，还需要在包名前加方案名。如：

--调用存储过程
SQL> exec sp_package.update_sal('SCOTT', 8888);
--调用函数
var income NUMBER;
CALL sp_package.ANNUAL_INCOME('SCOTT') INTO:income;
print income;
特别说明：包是pl/sql 中非常重要的部分，我们在使用过程分页时，将会再次体验它的威力呵呵。

触发器
触发器是指隐含的执行的存储过程。当定义触发器时，必须要指定触发的事件和触发的操作，常用的触发事件insert,update,delete 语句，而触发操作实际就是一个pl/sql 块。可以使用create trigger 来建立触发器。
特别说明：我们会在后面详细为大家介绍触发器的使用，因为触发器是非常有用的，可维护数据库的安全和一致性。