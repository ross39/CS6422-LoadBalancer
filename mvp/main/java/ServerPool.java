package main.java; 
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

import java.util.ArrayList;

public class ServerPool {

    private static ServerPool serverPool = new ServerPool();

    private boolean pool_switch = true;

    //Create a arraylist of server pools
    private ArrayList<Server> pool = new ArrayList<>();

    //singleton mode
    private ServerPool() {
    }

    public static ServerPool getServerPool() {
        return serverPool;
    }

    // for thread safe
    public synchronized void setPool(ArrayList<Server> pool) {
        this.pool = pool;

    }

    // for thread safe
    public synchronized void add_server(Server server) {
        pool.add(server);

    }

    public ArrayList<Server> getPool() {
        return pool;
    }

    public void closePool(){

        for (Server server : pool){

            server.setServer_switch(false);

            while (!server.isServer_off()){

            }

        }

        pool_switch = false;

    }

    public boolean isPool_switch() {
        return pool_switch;
    }
}
