package ucc.project.server;

import java.io.File;

import ucc.project.ipgenerator.IpGenerator;

/**
 * A server class that first initialize its ip address from IPGenerator, then register itself to serverpool.
 * finally start listening.
 * @author: Meiqi Huang
 */
public class Server {
    /**
     * initialize the ip adrress and default weight once start.
     * then register itself to serverpool
     */
    Server(){
        this.ip=getIPAddress();
        this.weight=1;
        register();
    }

    /**
     * initialize the ip adrress and specify the weight of server once start
     * then register itself to serverpool
     * @param weight
     */
    Server(int weight){

        this.ip=getIPAddress();
        this.weight=weight;
        register();
    }

    /**
     * get the ip address from IPGenerator
     * @return a avaliable ip address
     */
    private String getIPAddress() {
        String ip=getFakeIpAddress();
        return ip;
    }

    /**
     * mock a fake ip address
     * @return
     */
    private String getFakeIpAddress() {
        return IpGenerator.getIP();
    }

    /**
     * register server itself to the serverpool
     */
    private void register(){
//       ServerPool serverpool=new ServerPool();
//       serverpool.listenForServer();
    }

    /**
     * server start listening
     * create an empty file using the ip address
     */
    public void startListen(){
        try{
            File file=new File(this.ip);
            if (file.createNewFile()){

            }
            else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    String ip;

    int weight;

    /**
     * test a server
     * @param args
     */
    public static void main(String[] args) {
        Server server=new Server();
        server.startListen();
    }
}
