package com.ximalaya.ad.ci.controller;

import com.ximalaya.ad.ci.base.Result;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author ruhong
 **/

@RestController
public class TestController {

    @GetMapping(value = "index")
    public Result index() throws IOException {
        HttpGet get = new HttpGet("http://www.baidu.com");
        //这个是用来执行get方法的
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        return Result.success().addData("result", response.getStatusLine().getStatusCode());
    }
}
