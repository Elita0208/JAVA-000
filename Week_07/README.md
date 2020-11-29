例子见代码～
结果如下：

1、循环插入：

耗时：928746 ms  => 928s => 15.4791min

2、事务：
耗时：551017ms => 551.017s => 9.18min


3、批量：

耗时：335056ms => 335.056s => 5.58min

4、事务+批处理：
耗时：220957ms => 220.957s => 3.68min


5、source： 28s

6、load data ： 12.72s

load data local infile './shop_orders.txt' into table shop_orders


