package com.atguigu;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.shaded.protobuf.generated.HBaseProtos;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 1、创建命名空间
 * 2、判断表是否存在
 * 3、创建表
 * 4、删除表
 *
 * @author wx
 * @create 2020-06-22 10:59
 */
public class HBase01_DDL {
    private static Connection connection;
    private static Admin admin;

    static {

        // 1、创建配置信息并指定连接的集群
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");


        // 3、创建DDL的操作对象
        try {
            // 2、创建连接器
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        // 6、释放连接
        try {
            admin.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //1、创建命名空间
    public static void creatNS(String ns) throws IOException {

        // 4、创建命名空间描述器
        NamespaceDescriptor.Builder builder = NamespaceDescriptor.create(ns);
        NamespaceDescriptor namespaceDescriptor = builder.build();

        // 5、创建命名空间
        try {
            admin.createNamespace(namespaceDescriptor);
        } catch (IOException e) {
            System.out.println("命名空间已存在！");
        }


    }

    // 判断表是否存在
    public static boolean isTableExit(String tableName) throws IOException {

        return admin.tableExists(TableName.valueOf(tableName));

    }

    // 创建表
    public static void createTable(String tableName, String... cfs) throws IOException {

        //1、判断是否有列族信息
        if (cfs.length <= 0) {
            System.out.println("请输入列族信息！！！");
            return;
        }
        // 2、判断表是否存在
        if (isTableExit(tableName)) {
            System.out.println(tableName + "该表已经存在！！！");
            return;
        }

        // 3、创建表描述器
        TableDescriptorBuilder builder = TableDescriptorBuilder
                .newBuilder(TableName.valueOf(tableName));
        builder.setCoprocessor("com.atguigu.HBase04_Coprpcessor");

        // 循环放入列族信息
        for (String cf : cfs) {

            //创建列族描述器
            ColumnFamilyDescriptor columnFamilyDescriptor =
                    ColumnFamilyDescriptorBuilder
                            .newBuilder(Bytes.toBytes(cf))
                            .build();

            builder.setColumnFamily(columnFamilyDescriptor);

        }

        TableDescriptor build = builder.build();

        //3、创建表
        admin.createTable(build);

    }

    //删除表

    public static void dropTable(String tableName) throws IOException {

        TableName name = TableName.valueOf(tableName);

        //判断表是否存在
        if (!isTableExit(tableName)) {
            System.out.println(tableName + "该表不存在！");
            return;
        }

        //1、使表不可用
        admin.disableTable(name);

        //2、删除表操作
        admin.deleteTable(name);


    }


    public static void main(String[] args) throws IOException {

        // 测试创建命名空间
        //creatNS("bigtable");

        // 测试判断表是否存在
        // System.out.println(isTableExit("student"));

        //测试创建表
        //createTable("student1", "info1", "info2");

        //测试删除表
        dropTable("student1");

        close();

    }
}
