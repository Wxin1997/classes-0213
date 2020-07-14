package com.atguigu;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;

/**
 * 1、 新增和修改数据
 * 2、 单条数据查询
 * 3、 批量数据查询
 * 4、 删除数据
 *
 * @author wx
 * @create 2020-06-22 10:59
 */
public class HBase02_DML {

    private static Connection connection;


    static {

        // 1、创建配置信息并指定连接的集群
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");


        // 3、创建DDL的操作对象
        try {
            // 2、创建连接器
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        // 6、释放连接
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //新增和修改数据

    public static void putData(String tableName, String rowKey, String cf, String cn, String value) throws IOException {

        //1、获取DML的Table对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        // 创建Put对象
        Put put = new Put(Bytes.toBytes(rowKey));

        // 给Put对象添加数据
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));

        // 执行插入数据的操作
        table.put(put);

        // 释放资源
        table.close();

    }

    // 单条数据查询

    public static void select(String tableName, String rowKey, String cf, String cn) throws IOException {

        // 1、获取DML的表对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        //
        Get get = new Get(Bytes.toBytes(rowKey));

        //指定列族跟列

        //get.addFamily(Bytes.toBytes(cf));
        get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));


        // 查询结果
        Result result = table.get(get);

        // 解析result
        for (Cell cell : result.rawCells()) {
            System.out.println("CF" + Bytes.toString(CellUtil.cloneFamily(cell)) + ", CN:"
                    + Bytes.toString(CellUtil.cloneQualifier(cell)) + ",Value:"
                    + Bytes.toString(CellUtil.cloneValue(cell)));
        }


    }


    // 扫描数据Scan

    public static void scanTable(String tableName) throws IOException {

        // 获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        // 创建Scan对象
        Scan scan = new Scan();

        // scan.withStartRow(Bytes.toBytes("1000"));

        // 扫描全表
        ResultScanner results = table.getScanner(scan);

        //解析
        Iterator<Result> iterator = results.iterator();

        while (iterator.hasNext()) {
            Result result = iterator.next();
            // 解析result
            for (Cell cell : result.rawCells()) {
                System.out.println("CF" + Bytes.toString(CellUtil.cloneFamily(cell)) + ", CN:"
                        + Bytes.toString(CellUtil.cloneQualifier(cell)) + ",Value:"
                        + Bytes.toString(CellUtil.cloneValue(cell)));
            }

        }

        //释放资源
        table.close();

    }

    //删除数据
    public static void deleteData(String tableName, String rowKey, String cf, String cn) throws IOException {

        // 获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));


        // 创建Delete对象
        Delete delete = new Delete(Bytes.toBytes(rowKey));

        delete.addFamily(Bytes.toBytes(cf));


        // 执行删除操作
        table.delete(delete);

        //释放资源
        table.close();


    }


    public static void main(String[] args) throws IOException {

        // 测试插入数据
        // putData("student", "1000", "info", "name", "lisi");

        //测试Get方式获取数据


    }

}
