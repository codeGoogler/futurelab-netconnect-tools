package com.net.tools.futurelabnetconnecttools.controller;


import com.net.tools.futurelabnetconnecttools.common.req.CommonResponseFactory;
import com.net.tools.futurelabnetconnecttools.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api(value = "TestController" , tags = "机构管理前端控制器")
@RestController
@RequestMapping("/sys/TestController")
public class TestController {

    @Autowired
    RestTemplate restTemplate;



    @GetMapping("/postCheckPrint")
    @ApiOperation(value = "岗位数据对比，打印", httpMethod = "GET")
    public String postCheckPrint() {
        String response = restTemplate.getForObject( String.format("https://www.baidu.com"), String.class);
        System.out.println(response);
        return    CommonResponseFactory.getInstance().success(ResultUtil.resultCode(200,"SUCCESS")).toString();
    }


    String downLoadUrl = "http://file.futurelab.sit.zretchome.com/competencyPdf%2F%E4%B8%AA%E4%BA%BA%E8%81%8C%E4%B8%9A%E7%B4%A0%E5%85%BB%E7%BB%BC%E5%90%88%E6%B5%8B%E8%AF%84%E6%8A%A5%E5%91%8A1607770248864.pdf";
    @GetMapping("/postCheckPrint2")
    @ApiOperation(value = "下载文件", httpMethod = "GET")
    public String postCheckPrint2() {
        //String response = restTemplate.getForObject( String.format("https://www.baidu.com"), String.class);
         ResponseEntity<byte[]> rsp =  restTemplate.getForEntity(downLoadUrl,byte[].class);
         System.out.println("文件下载请求结果状态码：" + rsp.getStatusCode());
        return    CommonResponseFactory.getInstance().success(ResultUtil.resultCode(200,"SUCCESS")).toString();
    }
}
