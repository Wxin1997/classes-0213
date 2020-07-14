package com.atguigu.DDL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 1、创建命名空间
 * 2、判断表是否存在
 * 3、创建表
 * 4、删除表
 *
 * @author wx
 * @create 2020-06-22 18:43
 */
public class HBase02_DDL1 {

    private static Connection connection;
    private static Admin admin;

    static {
        // 配置信息
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");


        try {     // 创建连接
            connection = ConnectionFactory.createConnection(conf);
            // 获取DDL的连接对象
            admin = connection.getAdmin();
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
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1、创建命名空间
     *
     * @param namespace 命名空间
     * @throws IOException
     */
    public static void createNs(String namespace) throws IOException {

        NamespaceDescriptor.Builder builder = NamespaceDescriptor.create(namespace);
        NamespaceDescriptor build = builder.build();

        admin.createNamespace(build);


    }

    /**
     * 2、判断表是否存在
     *
     * @param tableName 表名
     * @return 返回true 或者 false true表示表存在 false 表示表不存在
     * @throws IOException
     */
    public static boolean isTableExit(String tableName) throws IOException {

        return admin.tableExists(TableName.valueOf(tableName));

    }

    /**
     * 3、创建表
     *
     * @param tableName 表名
     * @param cfs       不变参数=>表中不确定个数的列族名
     */
    public static void createTable(String tableName, String... cfs) throws IOException {

        // 判断列族是否<= 0
        if (cfs.length <= 0) {
            System.out.println("列族数不能为0！！！ ");
        }

        // 判断表是否存在
        if (isTableExit(tableName)) {
            System.out.println("表已经存在");
            return;
        }

        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));

        for (String cf : cfs) {

            ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf));
            ColumnFamilyDescriptor build = columnFamilyDescriptorBuilder.build();
            tableDescriptorBuilder.setColumnFamily(build);
        }
        TableDescriptor build = tableDescriptorBuilder.build();

        admin.createTable(build);

    }

    /**
     * 4、删除表
     *
     * @param tableName 表名
     */
    public static void deleteTable(String tableName) throws IOException {

        // 判断表是否存在
        if (!isTableExit(tableName)) {
            System.out.println("该表不存在！！！！");
            return;
        }

        //使表不可用
        admin.disableTable(TableName.valueOf(tableName));

        //删除表
        admin.deleteTable(TableName.valueOf(tableName));

    }


    public static void main(String[] args) {

    }
}
