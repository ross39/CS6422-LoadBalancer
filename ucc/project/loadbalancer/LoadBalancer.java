package ucc.project.loadbalancer;
import ucc.project.serverpool.ServerPool;

public class LoadBalancer {


    public void getServerList(){
        //Currently this does not give any serverlist as no server class has been created
        ServerPool.giveServerList();
    }

    public void pickServer(){
        //pick and remove the head
        //1 -> 2 -> 3 will be 2 -> 3 and then call again 3
        //Therefore we iterate through the list, passing each poll
        //to the algorithm class
        ServerPool.giveServerList().poll();
    }

    public void getServerAddress(){

    }

}
