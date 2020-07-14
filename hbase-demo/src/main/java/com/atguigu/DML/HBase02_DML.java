package com.atguigu.DML;

import javafx.scene.control.Tab;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import sun.util.resources.cldr.cgg.CalendarData_cgg_UG;

import java.io.IOException;
import java.util.Iterator;

/**
 * 1、 新增和修改数据
 * 2、 单条数据查询
 * 3、 批量数据查询
 * 4、 删除数据
 *
 * @author wx
 * @create 2020-06-22 19:22
 */
public class HBase02_DML {

    private static Connection connection;

    static {
        // 配置信息
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");


        try {     // 创建连接
            connection = ConnectionFactory.createConnection(conf);
            // 获取DDL的连接对象
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 关闭资源
     */
    public static void close() {
        try {

            // 释放资源
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
     * @param cf        列族名
     * @param cn        列名
     * @param value     值
     */
    public static void putData(String tableName, String rowKey, String cf, String cn, String value) throws IOException {

        // 获取DML的table对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        // 创建对应rowKey的Put对象
        Put put = new Put(Bytes.toBytes(rowKey));

        // 将对应列族，列的数据添加到put对象当中
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));

        // 将put对象添加到table中
        table.put(put);

        // 释放资源
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

        // 获取DML对应table对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        // 创建对应rowKey的Get对象
        Get get = new Get(Bytes.toBytes(rowKey));

        // 将所要查询的列族名和列名放到get对象中去
        get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));

        // 查询数据返回为Result类型
        Result result = table.get(get);

        // 将Result类型转换成Cell类型的数组将其遍历输出
        for (Cell cell : result.rawCells()) {
            System.out.println("列族名：" + CellUtil.cloneFamily(cell) + ", 列名：" + CellUtil.cloneQualifier(cell) + ",值：" + CellUtil.cloneValue(cell));
        }

        // 释放资源
        table.close();
    }

    // 3、 批量数据查询

    public static void scanData(String tableName) throws IOException {

        // 获取DML中table的对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        // 新建Scan对象
        Scan scan = new Scan();

        // 转换成
        ResultScanner results = table.getScanner(scan);

        Iterator<Result> iterator = results.iterator();

        while (iterator.hasNext()) {
            Result result = iterator.next();
            // 将Result类型转换成Cell类型的数组将其遍历输出
            for (Cell cell : result.rawCells()) {
                System.out.println("列族名：" + CellUtil.cloneFamily(cell) + ", 列名：" + CellUtil.cloneQualifier(cell) + ",值：" + CellUtil.cloneValue(cell));
            }
        }

        // 释放资源
        table.close();

    }

    /**
     * 4、删除数据
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @param cf        列族名
     * @param cn        列名
     */

    public static void deleteData(String tableName, String rowKey, String cf, String cn) throws IOException {

        // 获取DML中table的对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        //创建对应rowKey的delete对象
        Delete delete = new Delete(Bytes.toBytes(rowKey));

        // 向delete添加所要删除对应列族的信息 =>Type deleteFamily
        delete.addFamily(Bytes.toBytes(cf));

        // 向delete添加所要删除对应列族和列的信息，但只删除最新的version => Type delete
        delete.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));

        // 向delete添加所要删除对应列族和列的信息，但删除全部的version => Type deleteColumn
        delete.addColumns(Bytes.toBytes(cf), Bytes.toBytes(cn));

        // 删除对应delete的信息
        table.delete(delete);

        // 释放资源
        table.close();

    }

    public static void main(String[] args) {

    }
}
