package com.miku.lab.controller;/*
 *@author 邓涛
 *@data 2021/8/1 21:20
 *@version:1.1
 */

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/*
*@author miku
*@data 2021/8/01 21:28
*@version:1.1
*/

@RestController
@RequestMapping("/push")
@Api(value="PushController",tags="微信推送接口")
public class PushController {

    public  String getAccessToken() {
        String appId = "wx53295620b3e8adf1";
        String appSecret = "f2d8491867cc5926deee9df2dddd7955";
        String result = cn.hutool.http.HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        return jsonObject.getStr("access_token");
    }

    @GetMapping("/send")
    public String send(){
        JSONObject body=new JSONObject();
        /**
         * phrase1：预约结果
         * thing6：预约业务
         * character_string4：预约时段
         * thing9：预约地点
         * thing8：温馨提示
         */
        body.set("touser","oAhSV4p4qtOsRFXbDaf_ES_B-wmU");
        body.set("template_id","Y9FgbI6u8_xp7UOfxGUtQ8xZ8_QyeW5tMNWQanOHa9g");
        JSONObject json=new JSONObject();
        json.set("phrase1",new JSONObject().set("value","初音未来"));
        json.set("thing6",new JSONObject().set("value","我的未来"));
        json.set("character_string4",new JSONObject().set("value","2022-08-01"));
        json.set("thing9",new JSONObject().set("value", "我爱初音"));
        json.set("thing8",new JSONObject().set("value","世界第一公主殿下"));
        body.set("data",json);

        //发送
        String accessToken= getAccessToken();
        String post =  cn.hutool.http.HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, body.toString());
        return post;
    }

}
