package com.example.ZenTask.Database;

import com.example.ZenTask.SendController.TaskValues;
import okhttp3.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.springframework.http.MediaType;
import java.io.FileReader;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/list")
public class TaskList {


    @PostMapping
    public void list(TaskValues id) throws Exception {

        String channelid = id.getChannel_id();
        String text = id.getText();

        Database DB = new Database();
        Connection con = DB.getConnection();
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj1 = (JSONObject) parser.parse(new FileReader("/Users/admin/Desktop/remainingtask.json"));
            obj1.put("channel", channelid);

            JSONObject obj3 = (JSONObject) parser.parse(new FileReader("/Users/admin/Desktop/remaining.json"));
            obj3.put("channel", channelid);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");


            RequestBody body2 = RequestBody.create(mediaType, String.valueOf(obj3));
            Request request2 = new Request.Builder()
                    .url("https://raghavsaoji.slack.com/api/chat.postMessage")
                    .method("POST", body2)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer xoxp-1207089704903-1228012751700-1290577076273-1c7699d77d6e218090cce25011e60b4c")
                    .build();
            Response response2 = client.newCall(request2).execute();



            PreparedStatement statement = con.prepareStatement("SELECT Task FROM taskassign");
            ResultSet result = statement.executeQuery();
            ArrayList<String> array = new ArrayList<String>();
            while (result.next()) {
                obj1.put("text", result.getString("Task"));
                //System.out.println(result.getString("Task"));
                RequestBody body1 = RequestBody.create(mediaType, String.valueOf(obj1));
                Request request1 = new Request.Builder()
                        .url("https://raghavsaoji.slack.com/api/chat.postMessage")
                        .method("POST", body1)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "Bearer xoxp-1207089704903-1228012751700-1290577076273-1c7699d77d6e218090cce25011e60b4c")
                        .build();
                Response response1 = client.newCall(request1).execute();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
