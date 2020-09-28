package com.example.ZenTask.ResponseController;

import com.example.ZenTask.Database.Database;
import com.example.ZenTask.SendController.TaskValues1;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@RestController
@RequestMapping("/task/response")
public class TaskResponse {
    Database DB = new Database();

    @PostMapping
    public void Create(TaskValues1 id) {
        String type = id.getType();
        Object payload = id.getPayload();

        JSONParser parser = new JSONParser();
        try {
            JSONObject payloadJson = (JSONObject) parser.parse(String.valueOf(payload));

            Object channel = payloadJson.get("channel");
            JSONObject channelJson = (JSONObject) parser.parse(String.valueOf(channel));
            String channelId = (String) channelJson.get("id");
            String channelName= (String) channelJson.get("name");

            Object user = payloadJson.get("user");
            JSONObject userJson = (JSONObject) parser.parse(String.valueOf(user));
            String userName= (String) userJson.get("name");
            String userId = (String) userJson.get("id");
            //String reassign = null;
            //String[] reassign;

            JSONArray jsonArray = (JSONArray) payloadJson.get("actions");
            Object value = jsonArray.get(0);
            JSONObject valueJson = (JSONObject) parser.parse(String.valueOf(value));
            String valueid = (String) valueJson.get("value");
            int i=Integer.parseInt(valueid);

            Object text = jsonArray.get(0);
            JSONObject textJson = (JSONObject) parser.parse(String.valueOf(text));
            Object text1= textJson.get("text");
            JSONObject text1Json = (JSONObject) parser.parse(String.valueOf(text1));
            String Text = (String) text1Json.get("text");

            //JSONObject objectReassign = (JSONObject) parser.parse(new FileReader("/Users/admin/Desktop/reassign"+ i + ".json"));
            //objectReassign.put("channel", userId);

            if (Text.equals("Done")) {


                JSONObject obj = (JSONObject) parser.parse(new FileReader("/Users/admin/Desktop/remainingtask.json"));
                //obj.put("channel", userId);
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");

                JSONObject objectDone = (JSONObject) parser.parse(new FileReader("/Users/admin/Desktop/Response" + i + ".json"));
                //objectDone.put("channel", userId);




                RequestBody body = RequestBody.create(mediaType, String.valueOf(objectDone));
                Request request = new Request.Builder()
                        .url("https://raghavsaoji.slack.com/api/chat.postMessage")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "Bearer xoxp-1207089704903-1228012751700-1290577076273-1c7699d77d6e218090cce25011e60b4c")
                        .build();
                Response response = client.newCall(request).execute();

                Connection con = DB.getConnection();
                PreparedStatement posted1 = con.prepareStatement("INSERT INTO taskcomplete (srno, userid, user_name, channelid, channel_name, Task) select * from taskassign where task='Task-" +i+ "'");
                posted1.executeUpdate();
                PreparedStatement posted2 = con.prepareStatement(" DELETE FROM taskassign WHERE Task='Task-" + i + "'");
                posted2.executeUpdate();

                if (i == 3) {
                    JSONObject obj5 = (JSONObject) parser.parse(new FileReader("/Users/admin/Desktop/task5.json"));
                    obj5.put("channel", channelId);
                    OkHttpClient client5 = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType5 = MediaType.parse("application/json");
                    RequestBody body5 = RequestBody.create(mediaType, String.valueOf(obj5));
                    Request request5 = new Request.Builder()
                            .url("https://raghavsaoji.slack.com/api/chat.postMessage")
                            .method("POST", body5)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Bearer xoxp-1207089704903-1228012751700-1290577076273-1c7699d77d6e218090cce25011e60b4c")
                            .build();
                    Response response5 = client.newCall(request5).execute();
                    PreparedStatement posted5 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid, channel_name, Task) VALUES('" + userId + "','" + userName + "','" + channelId + "', '" + channelName + "' ,'Task-5')");
                    posted5.executeUpdate();
                }

                PreparedStatement statement = con.prepareStatement("SELECT Task FROM taskassign");
                ResultSet result = statement.executeQuery();
                ArrayList<String> array = new ArrayList<String>();
                while (result.next()) {
                    obj.put("text", result.getString("Task"));
                    RequestBody body11 = RequestBody.create(mediaType, String.valueOf(obj));
                    Request request11 = new Request.Builder()
                            .url("https://raghavsaoji.slack.com/api/chat.postMessage")
                            .method("POST", body11)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Bearer xoxp-1207089704903-1228012751700-1290577076273-1c7699d77d6e218090cce25011e60b4c")
                            .build();
                    Response response11 = client.newCall(request11).execute();
                }
            }


            else {

                String Task = ("Task-" +i);
                Connection con = DB.getConnection();
                PreparedStatement statement = con.prepareStatement("select channelid from taskcomplete where task='Task-" +i+ "'");
                ResultSet result = statement.executeQuery();
                ArrayList<String> array = new ArrayList<String>();
               // String reassignid = result.getString("channelid");

                while (result.next()) {
                    String reaasignid = result.getString("channelid");
                    JSONObject objectReassign = (JSONObject) parser.parse(new FileReader("/Users/admin/Desktop/reassign" + i + ".json"));
                    objectReassign.put("channel", result.getString("channelid"));
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody body = RequestBody.create(mediaType, String.valueOf(objectReassign));
                    Request request = new Request.Builder()
                            .url("https://raghavsaoji.slack.com/api/chat.postMessage")
                            .method("POST", body)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Bearer xoxp-1207089704903-1228012751700-1290577076273-1c7699d77d6e218090cce25011e60b4c")
                            .build();
                    Response response = client.newCall(request).execute();


                    PreparedStatement posted1 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid ,channel_name, Task) VALUES('" + userId + "','" + userName + "','" + reaasignid + "', '" + channelName + "','" + Task + "')");
                    posted1.executeUpdate();
                    PreparedStatement posted2 = con.prepareStatement(" DELETE FROM taskcomplete WHERE Task='Task-" + i + "'");
                    posted2.executeUpdate();
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






