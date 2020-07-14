package com.atguigu.writer;

import com.atguigu.bean.Student;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

/**
 * @author wx
 * @create 2020-07-11 9:16
 */
public class EsWriter {
    public static void main(String[] args) throws IOException {

        // 创建工厂
        JestClientFactory jestClientFactory = new JestClientFactory();

        // 创建配置信息
        HttpClientConfig build = new HttpClientConfig.Builder("http://hadoop102:9200").build();

        // 设置配置信息
        jestClientFactory.setHttpClientConfig(build);

        // 获取JestClient
        JestClient jestClient = jestClientFactory.getObject();

        // 创建index对象
        Student student = new Student("008", "张三", "male", "打篮球");
        Index index = new Index.Builder(student).index("student").type("_doc").id("1008").build();


        //触发操作
        jestClient.execute(index);

        jestClient.close();
    }
}
