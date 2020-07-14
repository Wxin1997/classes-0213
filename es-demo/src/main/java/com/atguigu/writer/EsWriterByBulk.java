package com.atguigu.writer;

import com.atguigu.bean.Student;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

/**
 * @author wx
 * @create 2020-07-11 11:35
 */
public class EsWriterByBulk {
    public static void main(String[] args) throws IOException {
        // 创建工厂
        JestClientFactory jestClientFactory = new JestClientFactory();

        // 创建配置信息
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder("http://hadoop102:9200").build();

        // 配置信息
        jestClientFactory.setHttpClientConfig(httpClientConfig);

        // 获取客户端对象
        JestClient jestClient = jestClientFactory.getObject();


        // 创建多个index对象fe
        Student student1 = new Student("009", "李四", "male", "羽毛球");
        Student student2 = new Student("010", "王五", "female", "乒乓球");
        Student student3 = new Student("011", "赵六", "male", "网球");

        Index index1 = new Index.Builder(student1).id("1009").build();
        Index index2 = new Index.Builder(student2).id("1010").build();
        Index index3 = new Index.Builder(student3).id("1011").build();

        // 创建Bulk对象
        Bulk bulk = new Bulk.Builder()
                .addAction(index1)
                .addAction(index2)
                .addAction(index3)
                .defaultIndex("student")
                .defaultType("_doc")
                .build();

        // 执行批量插入数据操作
        jestClient.execute(bulk);

        // 关闭资源
        jestClient.close();

    }
}
