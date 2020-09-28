package com.example.ZenTask.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Database {


/*public static void main(String[] args) throws Exception{
 // delete();
     post();
}

 */



    public static void delete() throws Exception {
   try{
       Connection con = getConnection();
       PreparedStatement posted1 = con.prepareStatement("DELETE FROM taskassign WHERE Task='Task-1'");
       posted1.executeUpdate();

       PreparedStatement statement = con.prepareStatement("SELECT Task FROM taskassign");
       ResultSet result = statement.executeQuery();
       ArrayList<String> array= new ArrayList<String>();
       System.out.println("Remaining Task are: ");
       while(result.next())
       {
           System.out.println(result.getString("Task"));
       }




   }catch (Exception e) {
       System.out.println(e);
   }
}




    public static void post() throws Exception{

        final String userid="D0177KNDHFS";
        final String user_name="Raghav_saoji";
        final String channelid="U016PJ12ARM";
        final String channel_name="4799raghav";



        try{
            createTable();
            Connection con = getConnection();
            PreparedStatement posted1 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid, channel_name, Task) VALUES('"+userid+"','"+user_name+"','"+channelid+"','"+channel_name+"','Task-4')");
           // PreparedStatement posted2 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid, channel_name, Task) VALUES('Task-2')");
            //PreparedStatement posted3 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid, channel_name, Task) VALUES('Task-3')");
            posted1.executeUpdate();
            //posted2.executeUpdate();
            //posted3.executeUpdate();

          /*  new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            try {
                                PreparedStatement posted4 = con.prepareStatement("INSERT INTO taskassign (userid, user_name, channelid, channel_name, Task) VALUES('Task-4')");
                                posted4.executeUpdate();
                            }catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    },
                    60000
            );

           */





        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createTable() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS taskassign(srno INT PRIMARY KEY AUTO_INCREMENT, userid VARCHAR(255), user_name VARCHAR(255), channelid VARCHAR(255), channel_name VARCHAR(255), Task VARCHAR(255))");
            create.executeUpdate();


            PreparedStatement create1 = con.prepareStatement("CREATE TABLE IF NOT EXISTS taskcomplete(srno INT PRIMARY KEY , userid VARCHAR(255), user_name VARCHAR(255), channelid VARCHAR(255) ,channel_name VARCHAR(255), Task VARCHAR(255))");
            create1.executeUpdate();



        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/task?allowPublicKeyRetrieval=true&useSSL=false";
            String username = "root";
            String password = "12345678";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
           // System.out.println("Connected");
            return conn;


        } catch(Exception e){System.out.println(e);}


        return null;
    }

}
