package com.atguigu;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.RegionObserver;
import org.apache.hadoop.hbase.wal.WALEdit;

import java.io.IOException;

/**
 * @author wx
 * @create 2020-06-23 15:13
 */
public class HBase04_Coprpcessor implements RegionObserver {

    public void postPut(ObserverContext<RegionCoprocessorEnvironment> c, Put put, WALEdit edit, Durability durability) throws IOException {
        // 获取连接
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.qourmn", "hadoop102,hadoop103,hadoop104");
        Connection connection = ConnectionFactory.createConnection(conf);

        // 获取表对象
        Table table = connection.getTable(TableName.valueOf("stu"));

        table.put(put);

        // 关闭资源
        table.close();
        connection.close();

    }
}
