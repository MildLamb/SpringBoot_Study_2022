package com.mildlamb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

// 开启虚拟MVC调用
@AutoConfigureMockMvc
public class WebTest {
    @Test
    void testWeb(){

    }

    @Test
    // 注入虚拟MVC调用对象
    void testMVC(@Autowired MockMvc mockMvc) throws Exception {
        // localhost:8080/role/info
        // 创建虚拟请求，当前访问 /role/info
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/info");
        // 执行对应的虚拟请求
        mockMvc.perform(builder);
    }


    @Test
    void testStatus(@Autowired MockMvc mvc) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/info");
        ResultActions resultActions = mvc.perform(builder);

        // 设定预期值，与真实值进行比较，成功则测试通过，失败则测试不通过
        // 定义本次调用的预期值
        StatusResultMatchers status = MockMvcResultMatchers.status();
        // 预计本次调用是成功的，状态200
        ResultMatcher statusOk = status.isOk();
        // 添加预计值到本次调用过程中进行匹配
        resultActions.andExpect(statusOk);
    }

    @Test
    void testRespBody(@Autowired MockMvc mvc) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/info");
        ResultActions resultActions = mvc.perform(builder);

        // 设定预期值，与真实值进行比较，成功则测试通过，失败则测试不通过
        // 定义本次调用的预期值
        ContentResultMatchers content = MockMvcResultMatchers.content();
        // 预计本次虚拟调用返回的结果是
        ResultMatcher respInfo = content.string("这是珏宝的一些信息");
        // 添加预计值到本次调用过程中进行匹配
        resultActions.andExpect(respInfo);

    }


    @Test
    void testJson(@Autowired MockMvc mvc) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/myrole");
        ResultActions resultActions = mvc.perform(builder);

        // 设定预期值，与真实值进行比较，成功则测试通过，失败则测试不通过
        // 定义本次调用的预期值
        ContentResultMatchers content = MockMvcResultMatchers.content();
        // 预计响应的json结果
        String jsonResp = "{\"name\":\"Gnar\",\"age\":1500}";
        // 预计本次虚拟调用返回的结果是
        ResultMatcher respInfo = content.json(jsonResp);
        // 添加预计值到本次调用过程中进行匹配
        resultActions.andExpect(respInfo);

    }



    @Test
    void testContentType(@Autowired MockMvc mvc) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/info");
        ResultActions resultActions = mvc.perform(builder);

        // 设定预期值，与真实值进行比较，成功则测试通过，失败则测试不通过
        // 定义本次调用的预期值
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher contextType = header.string("Content-Type", "application/json");
        // 添加预计值到本次调用过程中进行匹配
        resultActions.andExpect(contextType);

    }


    // 实际的测试应该是
    @Test
    void testGetById(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/role/myrole");
        ResultActions result = mvc.perform(builder);

        // 测试虚拟请求的响应码是否成功
        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher ok = status.isOk();
        result.andExpect(ok);

        // 测试虚拟请求的响应类型是否为 json 格式
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher headerType = header.string("Content-Type", "application/json");
        result.andExpect(headerType);

        // 测试响应的json数据内容是否正确
        ContentResultMatchers content = MockMvcResultMatchers.content();
        // 预计响应的json结果
        String jsonResp = "{\"name\":\"Kindred\",\"age\":1500}";
        ResultMatcher responseContext = content.string(jsonResp);
        result.andExpect(responseContext);

    }

}
