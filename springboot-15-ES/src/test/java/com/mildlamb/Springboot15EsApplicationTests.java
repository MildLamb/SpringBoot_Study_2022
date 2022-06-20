package com.mildlamb;

//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.nodes.Http;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.alibaba.fastjson.JSON;
import com.mildlamb.dao.RoleDao;
import com.mildlamb.pojo.Role;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class Springboot15EsApplicationTests {

    @Autowired
    private RoleDao roleDao;

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

    @Test
    void testCreateIndexByIK() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("roles");
        // 设置请求的参数
        String json = "{\n" +
                "    \"mappings\":{\n" +
                "        \"properties\":{\n" +
                "            \"id\":{\n" +
                "                \"type\":\"keyword\",\n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"name\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\",\n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"age\":{\n" +
                "                \"type\":\"integer\"\n" +
                "            },\n" +
                "            \"all\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        request.source(json, XContentType.JSON);
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }


    @Test
    void testCreateDoc() throws IOException {

        /*
            一次插入一条数据
         */
/*        Role role = roleDao.selectById(1);

        IndexRequest request = new IndexRequest("roles").id(role.getId().toString());
        String json = JSON.toJSONString(role);
        request.source(json,XContentType.JSON);
        restHighLevelClient.index(request,RequestOptions.DEFAULT);*/

        /*
            批处理插入数据
         */
        List<Role> roles = roleDao.selectList(null);

        // 创建批处理请求 容器
        BulkRequest bulk = new BulkRequest();

        // 通过循环 将请求添加到容器中，准备一次执行
        for (Role role : roles) {
            IndexRequest request = new IndexRequest("roles").id(role.getId().toString());
            String json = JSON.toJSONString(role);
            request.source(json,XContentType.JSON);
            bulk.add(request);
        }
        restHighLevelClient.bulk(bulk,RequestOptions.DEFAULT);

    }


    @Test
    // 按文档id查询
    void testGetByDocId() throws IOException {
        GetRequest getRrequest = new GetRequest("roles","1");
        GetResponse documentFields = restHighLevelClient.get(getRrequest, RequestOptions.DEFAULT);
        String result = documentFields.getSourceAsString();
        System.out.println(result);

    }


    @Test
    // 按条件查询
    void testGetByQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("roles");
        // 创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("name","kindred"));
        searchRequest.source(builder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            String result = hit.getSourceAsString();
            Role role = JSON.parseObject(result,Role.class);
            System.out.println(role);
        }
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
