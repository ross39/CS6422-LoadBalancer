package main.java;

import java.io.File;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

public class Server implements Comparable{
    /**
     * initialize the ip adrress and default weight once start.
     * then register itself to serverpool
     */
    Server(){
        this.ip=getIPAddress();
        this.weight=1;
        register();
    }

    public Server(int weight,String ip) {
        this.ip = ip;
        this.weight = weight;
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
        return "server1.txt";
    }

    /**
     * register server itself to the serverpool
     */
    private void register(){
        ServerPool serverPool = ServerPool.getServerPool();
        serverPool.add_server(this);
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

    @Override
    public int compareTo(Object o) {

        if (o == null)
            throw new NullPointerException("cannot compare the null object");

        Server server = (Server) o;
        if (server.getWeight() > this.weight)
            return -1;
        else if (server.getWeight() < this.weight)
            return 1;

        return 0;
    }
    /**
     * test a server
     * @param args
     */
    public static void main(String[] args) {
        Server server=new Server();
        server.startListen();
    }
}
