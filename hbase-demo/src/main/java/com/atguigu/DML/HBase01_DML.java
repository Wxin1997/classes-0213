package com.atguigu.DML;

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
 * * 1、 新增和修改数据
 * * 2、 单条数据查询
 * * 3、 批量数据查询
 * * 4、 删除数据
 *
 * @author wx
 * @create 2020-06-22 15:44
 */
public class HBase01_DML {
    private static Connection connection;

    static {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");

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

    /**
     * 1、 新增和修改数据
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @param cf        列族
     * @param cn        列名
     * @param value     值
     */
    public static void putData(String tableName, String rowKey, String cf, String cn, String value) throws IOException {

        //获取DML的table对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        // 新建put对象
        Put put = new Put(Bytes.toBytes(rowKey));


        // 将数据添加到put
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));

        // 添加数据
        table.put(put);

        //释放资源
        table.close();


    }

    /**
     * 2、 单条数据查询
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @param cf        列族名
     * @param cn        列名
     */
    public static void selectData(String tableName, String rowKey, String cf, String cn) throws IOException {

        //获取DML的table对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        //新建对应rowKey的Get对象
        Get get = new Get(Bytes.toBytes(rowKey));

        //确定对应rowkey的列族和列名以确定对应的值
        get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));

        // 获取对应的值
        Result result = table.get(get);

        // 解析result
        for (Cell cell : result.rawCells()) {

            System.out.println("列族名：" + CellUtil.cloneFamily(cell) + ",列名：" + CellUtil.cloneQualifier(cell) + "值：" + CellUtil.cloneValue(cell));

        }

        //释放资源
        table.close();

    }

    /**
     * 3、 批量数据查询
     *
     * @param tableName 表名
     */
    public static void scanData(String tableName) throws IOException {

        // 获取DML的table对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        // 新建Scan对象
        Scan scan = new Scan();

        // 查询结果
        ResultScanner results = table.getScanner(scan);

        // 解析result => 将result转换成 迭代器
        Iterator<Result> iterator = results.iterator();

        // 遍历迭代器 将其转换成 Result对象
        while (iterator.hasNext()) {
            Result result = iterator.next();

            // 将result转换成 Cell对象 遍历输出
            for (Cell cell : result.rawCells()) {
                //输出
                System.out.println("列族名：" + CellUtil.cloneFamily(cell) + ",列名：" + CellUtil.cloneQualifier(cell) + "值：" + CellUtil.cloneValue(cell));

            }
        }

        // 释放资源
        table.close();
    }

    /**
     * 4、 删除数据
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @param cf        列族名
     * @param cn        列名
     */
    public static void deleteData(String tableName, String rowKey, String cf, String cn) throws IOException {

        // 获取DML的table对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        Delete delete = new Delete(Bytes.toBytes(rowKey));

        // 删除对应 列族
        delete.addFamily(Bytes.toBytes(cf));

        // 删除对应列族和列名的信息 只删除最新的version
        delete.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));

        // 删除对应列族和列名的信息 删除所有的version的信息
        delete.addColumns(Bytes.toBytes(cf), Bytes.toBytes(cn));


        table.delete(delete);


    }


    public static void main(String[] args) {


        close();

    }
}
