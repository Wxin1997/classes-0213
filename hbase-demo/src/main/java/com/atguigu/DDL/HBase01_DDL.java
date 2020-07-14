package com.atguigu.DDL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * * 1、创建命名空间
 * * 2、判断表是否存在
 * * 3、创建表
 * * 4、删除表
 *
 * @author wx
 * @create 2020-06-22 15:44
 */
public class HBase01_DDL {
    private static Connection connection = null;
    private static Admin admin;

    static {
        //配置信息
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");


        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void close() {
        try {
            connection.close();
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 创建命名空间
     *
     * @param nameSpace 命名空间的名称
     * @throws IOException
     */
    public static void createNamespace(String nameSpace) throws IOException {


        //创建命名空间描述器
        NamespaceDescriptor.Builder builder = NamespaceDescriptor.create(nameSpace);
        NamespaceDescriptor build = builder.build();

        //创建命名空间
        admin.createNamespace(build);

    }

    /**
     * 2、判断表是否存在
     *
     * @param tableName 表名称
     * @return 是否存在 true表示存在 false 表示不存在
     * @throws IOException
     */
    public static boolean isTableExit(String tableName) throws IOException {
        return admin.tableExists(TableName.valueOf(tableName));
    }

    /**
     * 3、创建表
     *
     * @param tableName 表名称
     * @param cfs       可变形参表示列族的名称
     */
    public static void createTable(String tableName, String... cfs) throws IOException {

        //判断是否有列族信息
        if (cfs.length <= 0) {
            System.out.println("请输入列族的信息！！！");
            return;
        }

        // 判断表是否存在
        if (isTableExit(tableName)) {
            System.out.println("表已经存在");
            return;
        }

        // 获取表的描述器
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));

        // 遍历列族的可变形参将列族放到表的描述器中
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
     * @param tableName
     */
    public static void dropTable(String tableName) throws IOException {

        // 判断表是否存在
        if (!isTableExit(tableName)) {
            System.out.println("表不存在");
            return;
        }

        TableName name = TableName.valueOf(tableName);

        //使表不可用
        admin.disableTable(name);

        //删除表
        admin.deleteTable(name);


    }

    public static void main(String[] args) throws IOException {

        //创建表
        // createTable("student1", "info1", "info2");

        //删除表
        dropTable("student1");
    }
}
