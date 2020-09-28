package com.example.ZenTask.SendController;


import com.example.ZenTask.Database.Database;
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
import java.sql.SQLException;

@RestController
@RequestMapping("/api/ZenTask")
public class TaskAssign {


    @PostMapping
    public void Create(TaskValues id) {
       // String channelid = id.getChannel_id();
        //String channel_name = id.getChannel_name();
        String userid = id.getUser_id();
        String user_name = id.getUser_name();
        String text=id.getText();
        String channelid = text.substring(2 , 13);
        String channel_name = text.substring(14 , 24);

        Database DB = new Database();

        JSONParser parser = new JSONParser();
        try {
            //Gson gson = new Gson();
            JSONObject obj = (JSONObject) parser.parse(new FileReader("/Users/admin/Desktop/intialtask.json"));
            obj.put("channel", channelid);
            JSONObject obj1 = (JSONObject) parser.parse(new FileReader("/Users/admin/Desktop/task4button1.json"));
            obj1.put("channel", channelid);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, String.valueOf(obj));
            Request request = new Request.Builder()
                    .url("https://raghavsaoji.slack.com/api/chat.postMessage")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer xoxp-1207089704903-1228012751700-1290577076273-1c7699d77d6e218090cce25011e60b4c")
                    .build();
            Response response = client.newCall(request).execute();

            DB.createTable();
            Connection con = DB.getConnection();
            PreparedStatement posted1 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid, channel_name, Task) VALUES('"+userid+"','"+user_name+"','"+channelid+"', '"+channel_name+"','Task-1')");
            PreparedStatement posted2 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid, channel_name, Task) VALUES('"+userid+"','"+user_name+"','"+channelid+"', '"+channel_name+"','Task-2')");
            PreparedStatement posted3 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid, channel_name, Task) VALUES('"+userid+"','"+user_name+"','"+channelid+"', '"+channel_name+"','Task-3')");
            posted1.executeUpdate();
            posted2.executeUpdate();
            posted3.executeUpdate();

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            // Thread.sleep(60000)
                            RequestBody body1 = RequestBody.create(mediaType, String.valueOf(obj1));
                            Request request1 = new Request.Builder()
                                    .url("https://raghavsaoji.slack.com/api/chat.postMessage")
                                    .method("POST", body1)
                                    .addHeader("Content-Type", "application/json")
                                    .addHeader("Authorization", "Bearer xoxp-1207089704903-1228012751700-1290577076273-1c7699d77d6e218090cce25011e60b4c")
                                    .build();
                            try {
                                Response response1 = client.newCall(request1).execute();

                                PreparedStatement posted4 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid, channel_name, Task) VALUES('"+userid+"','"+user_name+"','"+channelid+"', '"+channel_name+"','Task-4')");
                                posted4.executeUpdate();
                                //PreparedStatement posted8 = con.prepareStatement("INSERT INTO taskcomplete (userid, user_name, channel_name, Task) VALUES('"+userid+"','"+user_name+"','4799raghav','Task-4')");
                                //posted8.executeUpdate();
                            } catch (IOException | SQLException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    60000
            );

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

