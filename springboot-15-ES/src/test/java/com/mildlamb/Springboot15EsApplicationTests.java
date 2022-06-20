package com.mildlamb;

//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.nodes.Http;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.mildlamb.dao.RoleDao;
import com.mildlamb.pojo.Role;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class Springboot15EsApplicationTests {

//    @Autowired
//    private RoleDao roleDao;

    // 低级ES模板对象
//    @Resource
//    private ElasticsearchRestTemplate esTemplate;

    // 显式已弃用
    @Resource
    private RestHighLevelClient restHighLevelClient;

    @BeforeEach
    void beforeTest(){
        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    @AfterEach
    void AfterTest() throws IOException {
        restHighLevelClient.close();
    }

    @Test
    void testCreateIndex() throws IOException {

        // 创建一个名为roles的索引
        CreateIndexRequest request = new CreateIndexRequest("roles");
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

    }











//    @Test
//    public void create() throws IOException {
//        // 创建低级客户端
//        RestClient restClient = RestClient.builder(
//                new HttpHost("localhost", 9200)
//        ).build();
//        // 使用Jackson映射器创建传输层
//        ElasticsearchTransport transport = new RestClientTransport(
//                restClient, new JacksonJsonpMapper()
//        );
//        // 创建API客户端
//        ElasticsearchClient client = new ElasticsearchClient(transport);
//
//        // 关闭ES客户端
//        transport.close();
//        restClient.close();
//    }

}
