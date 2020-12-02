package com.example.demo;


import java.math.BigDecimal;
        import java.sql.*;
        import java.util.Random;

public class InsertData {

    public static void main(String[] args){
//        insertData1();//普通循环，928746ms  => 928s => 15.4791min
//        insertData2();//事务，551017ms => 551.017s => 9.18min
//        insertData3();//批量， 335056ms => 335.056s => 5.58min
        insertData4();//事务+批量，220957ms => 220.957s => 3.68min
    }


    //1、循环插入，928746 ms  => 928s => 15.4791min
    public static void insertData1(){
        String url = "jdbc:mysql://localhost:3306/test1" ;
        String user = "root" ;
        String password = "12345678" ;


        Connection conn = null ;
        PreparedStatement pstm = null ;
        ResultSet rt = null ;
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO shop_orders(id,name,order_no,status,product_count, product_amount_total, order_amount_total, logistics_fee, address_id" +
                    ", order_logistics_id, transaction_id, user_id, paid_at, delivery_at, memo, complete_at, comment_status, receiver_name, " +
                    "receiver_phone,receiver_address, created_at, updated_at) VALUES(?, CONCAT('订单号',?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
            pstm = conn.prepareStatement(sql);
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            for ( int i = 1 ; i <= 1000000 ; i++) {
                pstm.setInt( 1 , i); //id
                pstm.setInt( 2 , i); // name, string
                pstm.setString( 3 , getRandomString(10)); //order_no, string
                pstm.setInt( 4 , rand.nextInt(8));//status, int
                pstm.setInt( 5 , rand.nextInt(1000)); //product_count ,int
                pstm.setBigDecimal( 6 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(1000.00))); // product_amount_total, decimal
                pstm.setBigDecimal( 7 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(10000.00))); //order_amount_total, decimal
                pstm.setBigDecimal( 8 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(100.00))); //logistics_fee
                pstm.setInt( 9 , rand.nextInt(10000));// address_id, int
                pstm.setInt( 10 , rand.nextInt(10000)); // order_logistics_id, int
                pstm.setString( 11 , getRandomString(11)); //transaction_id ,string
                pstm.setInt( 12 , rand.nextInt(10000)); //user_id int
                java.util.Date date = new java.util.Date();          // 获取一个Date对象
                Timestamp timeStamp = new Timestamp(date.getTime());
                pstm.setTimestamp( 13 , timeStamp); //paid_at, datetime
                pstm.setTimestamp( 14 , timeStamp); // delivery_at, datetime
                pstm.setString( 15 , ""); // memo ,string
                pstm.setTimestamp( 16 , timeStamp); //complete_at. datetime
                pstm.setInt( 17 , rand.nextInt(5)); //comment_status,int
                pstm.setString( 18 , getRandomString(10)); // receiver_name. string
                pstm.setString( 19 , "188" + rand.nextInt( 10 )+ "88" + rand.nextInt( 10 )+ rand.nextInt( 10 )+ "66" + rand.nextInt( 10 ));//receiver_phone. string
                pstm.setString( 20 , getRandomString(20)); //receiver_address，string
                pstm.setTimestamp( 21 , timeStamp); //created_at, int
                pstm.setTimestamp( 22 , timeStamp); //updated_at, int
                System.out.println(pstm.toString());
                pstm.execute();
            }
            Long endTime = System.currentTimeMillis();
            System.out.println( "OK,用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (pstm!= null ){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn!= null ){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //2、事务, 551017ms => 551.017s => 9.18min
    public static void insertData2(){
        String url = "jdbc:mysql://localhost:3306/test1" ;
        String user = "root" ;
        String password = "12345678" ;


        Connection conn = null ;
        PreparedStatement pstm = null ;
        ResultSet rt = null ;
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO shop_orders(id,name,order_no,status,product_count, product_amount_total, order_amount_total, logistics_fee, address_id" +
                    ", order_logistics_id, transaction_id, user_id, paid_at, delivery_at, memo, complete_at, comment_status, receiver_name, " +
                    "receiver_phone,receiver_address, created_at, updated_at) VALUES(?, CONCAT('订单号',?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
            pstm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            for ( int i = 1 ; i <= 1000000 ; i++) {
                pstm.setInt( 1 , i); //id
                pstm.setInt( 2 , i); // name, string
                pstm.setString( 3 , getRandomString(10)); //order_no, string
                pstm.setInt( 4 , rand.nextInt(8));//status, int
                pstm.setInt( 5 , rand.nextInt(1000)); //product_count ,int
                pstm.setBigDecimal( 6 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(1000.00))); // product_amount_total, decimal
                pstm.setBigDecimal( 7 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(10000.00))); //order_amount_total, decimal
                pstm.setBigDecimal( 8 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(100.00))); //logistics_fee
                pstm.setInt( 9 , rand.nextInt(10000));// address_id, int
                pstm.setInt( 10 , rand.nextInt(10000)); // order_logistics_id, int
                pstm.setString( 11 , getRandomString(11)); //transaction_id ,string
                pstm.setInt( 12 , rand.nextInt(10000)); //user_id int
                java.util.Date date = new java.util.Date();          // 获取一个Date对象
                Timestamp timeStamp = new Timestamp(date.getTime());
                pstm.setTimestamp( 13 , timeStamp); //paid_at, datetime
                pstm.setTimestamp( 14 , timeStamp); // delivery_at, datetime
                pstm.setString( 15 , ""); // memo ,string
                pstm.setTimestamp( 16 , timeStamp); //complete_at. datetime
                pstm.setInt( 17 , rand.nextInt(5)); //comment_status,int
                pstm.setString( 18 , getRandomString(10)); // receiver_name. string
                pstm.setString( 19 , "188" + rand.nextInt( 10 )+ "88" + rand.nextInt( 10 )+ rand.nextInt( 10 )+ "66" + rand.nextInt( 10 ));//receiver_phone. string
                pstm.setString( 20 , getRandomString(20)); //receiver_address，string
                pstm.setTimestamp( 21 , timeStamp); //created_at, int
                pstm.setTimestamp( 22 , timeStamp); //updated_at, int
                System.out.println(pstm.toString());
                pstm.execute();

            }
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println( "OK,用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (pstm!= null ){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn!= null ){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }


    //3、批处理，  335056ms => 335.056s => 5.58min
    public static void insertData3(){
        String url = "jdbc:mysql://localhost:3306/test1" ;
        String user = "root" ;
        String password = "12345678" ;


        Connection conn = null ;
        PreparedStatement pstm = null ;
        ResultSet rt = null ;
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO shop_orders(id,name,order_no,status,product_count, product_amount_total, order_amount_total, logistics_fee, address_id" +
                    ", order_logistics_id, transaction_id, user_id, paid_at, delivery_at, memo, complete_at, comment_status, receiver_name, " +
                    "receiver_phone,receiver_address, created_at, updated_at) VALUES(?, CONCAT('订单号',?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
            pstm = conn.prepareStatement(sql);
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            for ( int i = 1 ; i <= 1000000 ; i++) {
                pstm.setInt( 1 , i); //id
                pstm.setInt( 2 , i); // name, string
                pstm.setString( 3 , getRandomString(10)); //order_no, string
                pstm.setInt( 4 , rand.nextInt(8));//status, int
                pstm.setInt( 5 , rand.nextInt(1000)); //product_count ,int
                pstm.setBigDecimal( 6 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(1000.00))); // product_amount_total, decimal
                pstm.setBigDecimal( 7 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(10000.00))); //order_amount_total, decimal
                pstm.setBigDecimal( 8 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(100.00))); //logistics_fee
                pstm.setInt( 9 , rand.nextInt(10000));// address_id, int
                pstm.setInt( 10 , rand.nextInt(10000)); // order_logistics_id, int
                pstm.setString( 11 , getRandomString(11)); //transaction_id ,string
                pstm.setInt( 12 , rand.nextInt(10000)); //user_id int
                java.util.Date date = new java.util.Date();          // 获取一个Date对象
                Timestamp timeStamp = new Timestamp(date.getTime());
                pstm.setTimestamp( 13 , timeStamp); //paid_at, datetime
                pstm.setTimestamp( 14 , timeStamp); // delivery_at, datetime
                pstm.setString( 15 , ""); // memo ,string
                pstm.setTimestamp( 16 , timeStamp); //complete_at. datetime
                pstm.setInt( 17 , rand.nextInt(5)); //comment_status,int
                pstm.setString( 18 , getRandomString(10)); // receiver_name. string
                pstm.setString( 19 , "188" + rand.nextInt( 10 )+ "88" + rand.nextInt( 10 )+ rand.nextInt( 10 )+ "66" + rand.nextInt( 10 ));//receiver_phone. string
                pstm.setString( 20 , getRandomString(20)); //receiver_address，string
                pstm.setTimestamp( 21 , timeStamp); //created_at, int
                pstm.setTimestamp( 22 , timeStamp); //updated_at, int
                System.out.println(pstm.toString());
                pstm.addBatch();
                //小批量提交，避免OOM
                if (i % 10000 == 0){
                    pstm.executeBatch();
                    pstm.clearBatch();
                }
            }
            Long endTime = System.currentTimeMillis();
            System.out.println( "OK,用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (pstm!= null ){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn!= null ){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }


    //4、事务+批处理， 220957ms => 220.957s => 3.68min
    public static void insertData4(){
        String url = "jdbc:mysql://localhost:3308/test1" ;
        String user = "root" ;
        String password = "12345678" ;


        Connection conn = null ;
        PreparedStatement pstm = null ;
        ResultSet rt = null ;
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO shop_orders(id,name,order_no,status,product_count, product_amount_total, order_amount_total, logistics_fee, address_id" +
                    ", order_logistics_id, transaction_id, user_id, paid_at, delivery_at, memo, complete_at, comment_status, receiver_name, " +
                    "receiver_phone,receiver_address, created_at, updated_at) VALUES(?, CONCAT('订单号',?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
            pstm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            for ( int i = 1 ; i <= 1000000 ; i++) {
                pstm.setInt( 1 , i); //id
                pstm.setInt( 2 , i); // name, string
                pstm.setString( 3 , getRandomString(10)); //order_no, string
                pstm.setInt( 4 , rand.nextInt(8));//status, int
                pstm.setInt( 5 , rand.nextInt(1000)); //product_count ,int
                pstm.setBigDecimal( 6 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(1000.00))); // product_amount_total, decimal
                pstm.setBigDecimal( 7 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(10000.00))); //order_amount_total, decimal
                pstm.setBigDecimal( 8 , getRandomBigDecimal(new BigDecimal(10.00), new BigDecimal(100.00))); //logistics_fee
                pstm.setInt( 9 , rand.nextInt(10000));// address_id, int
                pstm.setInt( 10 , rand.nextInt(10000)); // order_logistics_id, int
                pstm.setString( 11 , getRandomString(11)); //transaction_id ,string
                pstm.setInt( 12 , rand.nextInt(10000)); //user_id int
                java.util.Date date = new java.util.Date();          // 获取一个Date对象
                Timestamp timeStamp = new Timestamp(date.getTime());
                pstm.setTimestamp( 13 , timeStamp); //paid_at, datetime
                pstm.setTimestamp( 14 , timeStamp); // delivery_at, datetime
                pstm.setString( 15 , ""); // memo ,string
                pstm.setTimestamp( 16 , timeStamp); //complete_at. datetime
                pstm.setInt( 17 , rand.nextInt(5)); //comment_status,int
                pstm.setString( 18 , getRandomString(10)); // receiver_name. string
                pstm.setString( 19 , "188" + rand.nextInt( 10 )+ "88" + rand.nextInt( 10 )+ rand.nextInt( 10 )+ "66" + rand.nextInt( 10 ));//receiver_phone. string
                pstm.setString( 20 , getRandomString(20)); //receiver_address，string
                pstm.setTimestamp( 21 , timeStamp); //created_at, int
                pstm.setTimestamp( 22 , timeStamp); //updated_at, int
                System.out.println(pstm.toString());
                pstm.addBatch();
                //小批量提交，避免OOM
                if (i % 10000 == 0){
                    pstm.executeBatch();
                    pstm.clearBatch();
                }
            }
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println( "OK,用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (pstm!= null ){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn!= null ){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //5、dump出来的sql文件，source xx.sql, 大约需要28s

    //6、load data, 12.75s. load data local infile './shop_orders.txt' into table shop_orders

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static BigDecimal getRandomBigDecimal(BigDecimal min, BigDecimal max){
        float minF = min.floatValue();
        float maxF = max.floatValue();

        //生成随机数
        BigDecimal db = new BigDecimal(Math.random() * (maxF - minF) + minF);

        //返回保留两位小数的随机数。不进行四舍五入
        return db.setScale(2,BigDecimal.ROUND_DOWN);
    }



}