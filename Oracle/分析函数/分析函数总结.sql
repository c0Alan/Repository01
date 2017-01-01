一、统计方面：
Sum() Over ([Partition by ] [Order by ])

Sum() Over ([Partition by ] [Order by ]  Rows Between  Preceding And  Following)
       
Sum() Over ([Partition by ] [Order by ] Rows Between  Preceding And Current Row)

Sum() Over ([Partition by ] [Order by ]
Range Between Interval '' 'Day' Preceding And Interval '' 'Day' Following )

二、排列方面：
Rank() Over ([Partition by ] [Order by ] [Nulls First/Last])

Dense_rank() Over ([Patition by ] [Order by ] [Nulls First/Last])
   
Row_number() Over ([Partitionby ] [Order by ] [Nulls First/Last])
   
Ntile() Over ([Partition by ] [Order by ])

三、最大值/最小值查找方面：
Min()/Max() Keep (Dense_rank First/Last [Partition by ] [Order by ])

四、首记录/末记录查找方面：
First_value / Last_value(Sum() Over ([Patition by ] [Order by ] Rows Between  Preceding And  Following  ))

五、相邻记录之间比较方面：
Lag(Sum(), 1) Over([Patition by ] [Order by ])
