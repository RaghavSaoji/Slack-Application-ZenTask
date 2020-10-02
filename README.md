# Slack-Application-ZenTask
Slack application to automate workflow


In Slack :

Go to Slack and built an application.
Add bot to it.
Add webhook feature.
You will get token in oAuath and permission.
Save this token 



In JAVA BACKEND:

Step 1 : Download the Json files from Zentask Json files folder and keep all files on Desktop
                                OR
          Change the path of file in Code     
          (USE / instead of \ in path)
          
          Eg :
          JSONObject obj = (JSONObject) parser.parse(new FileReader("YOUR_FILE_PATH/intialtask.json"))
          
          
Step 2: In all Response files update your id at Key "channel".

Step 3 : From  slack appication:
          - Update value in code at URL ()
          -Update Bearer token of application
          
          Eg:
        Request request = new Request.Builder()
         HERE-->   .url("https://YOUR-WORKSPACE-NAME.slack.com/api/chat.postMessage")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
         HERE-->    .addHeader("Authorization", "Bearer YOUR _APP_TOKEN")
                    .build();
            Response response = client.newCall(request).execute();
          
          
          
          
          
          
Step 4: In slack application feature:

        - Select Slash command feature add values as:
         1)
          .use command as /zentask
          .in url -> "(your url)/api/ZenTask"
          .allow escape feature
         2) 
          .also add /list slash command 
          .in url -> "(your url)/list"
          
          
       -Also add Interactive component feature
        .In url -> "(your url)/task/response"
        
Step 5: Install your application        
      
