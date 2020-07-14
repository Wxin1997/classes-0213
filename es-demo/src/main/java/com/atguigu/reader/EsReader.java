package com.atguigu.reader;

import com.atguigu.bean.Student;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;

import java.io.IOException;
import java.util.List;

/**
 * @author wx
 * @create 2020-07-11 11:45
 */
public class EsReader {
    public static void main(String[] args) throws IOException {
        // 创建工厂类
        JestClientFactory jestClientFactory = new JestClientFactory();

        // 创建配置信息
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder("http://hadoop102:9200").build();

        // 添加配置
        jestClientFactory.setHttpClientConfig(httpClientConfig);

        // 创建客户端
        JestClient jestClient = jestClientFactory.getObject();

        // 创建Search 对象
        Search search = new Search.Builder("{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"filter\": {\n" +
                "        \"term\": {\n" +
                "          \"sex\": \"male\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"must\": [\n" +
                "        {\n" +
                "          \"match\": {\n" +
                "            \"favo\": \"球\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"aggs\": {\n" +
                "    \"group_by_sex\": {\n" +
                "      \"terms\": {\n" +
                "        \"field\": \"sex\",\n" +
                "        \"size\": 10\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"from\": 0,\n" +
                "  \"size\": 20\n" +
                "}")
                .addIndex("student").addType("_doc").build();

        // 执行查询操作
        SearchResult searchResult = jestClient.execute(search);

        // 解析searchResult
        System.out.println("总数：" + searchResult.getTotal() + "条");
        System.out.println("最高分：" + searchResult.getMaxScore());
        // 获取数据明细
        List<SearchResult.Hit<Student, Void>> hits = searchResult.getHits(Student.class);
        for (SearchResult.Hit<Student, Void> hit : hits) {
            Student student = hit.source;
            System.out.println("id:" + student.getStu_id());
            System.out.println("name:" + student.getName());
            System.out.println("sex:" + student.getSex());
            System.out.println("favo:" + student.getFavo());
            System.out.println("***********************");
        }

        // 获取聚合组
        MetricAggregation aggregations = searchResult.getAggregations();
        TermsAggregation group_by_sex = aggregations.getTermsAggregation("group_by_sex");
        List<TermsAggregation.Entry> buckets = group_by_sex.getBuckets();
        for (TermsAggregation.Entry bucket : buckets) {
            System.out.println("key:" + bucket.getKey() + ",value:" + bucket.getCount());
            System.out.println("----------------------");
        }

        // 关闭资源
        jestClient.close();

    }
}
