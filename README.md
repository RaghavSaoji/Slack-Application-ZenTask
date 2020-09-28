# Slack-Application-ZenTask
Slack application to automate workflow
Step 1 : Download the Json files from Zentask Json files folder and keep all files on Desktop
                                OR
          Change the path of file in Code                      
          
Step 2: In all Response files update your id at Key "channel".

Step 3 : From  slack appication:
          - Update value in code at URL
          -Update Bearer token of application
          
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
      
