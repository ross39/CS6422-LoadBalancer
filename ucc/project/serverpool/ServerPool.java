package ucc.project.serverpool;
import java.util.LinkedList;
public class ServerPool {

    /* 
    * Make this static so that methods can access the serverList
    * Been a while since I have used java so not sure if this is good
    * For now it takes object but once server has been created it will
    * take server objects
    * */
     static LinkedList<Object> serverList;
    public void createServerPool(){
        ServerPool.serverList = new LinkedList<>();
    }

    public void listenForServer(){
        /*
        * Anytime a new server is created, call updateServerList() to update the hashmap
        * Can't do much as server class has not been created. Once it has been created
        * I can add code to listen for any new servers
        */
    }

    public void updateServerList(){
        //Take new server, and add it to the serverList
        //ServerPool.serverList.addLast(ucc.project.server.Server);

    }

    public static LinkedList<Object> giveServerList(){
        //Simply return the serverlist when called
        return ServerPool.serverList;

    }

}
