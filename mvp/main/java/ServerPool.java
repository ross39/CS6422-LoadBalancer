package main.java; /**
 * @author : meiqi huang
 * @created : 2022-11-1
 **/

import java.util.ArrayList;

public class ServerPool {

    private static ServerPool serverPool = new ServerPool();

    //Create a arraylist of server pools
    private ArrayList<ServerDetails> pool = new ArrayList<>();

    // defalut algorithm choice
    private int algorithm_choice = Algorithm.RandomSelectNext;

    private Algorithm algorithm = new Algorithm();

    //singleton mode
    private ServerPool() {
    }

    public static ServerPool getServerPool() {
        return serverPool;
    }

    // for thread safe
    public synchronized void setPool(ArrayList<ServerDetails> pool) {
        this.pool = pool;
        algorithm.setServerDetailsArrayList(pool);
    }

    public int getAlgorithm_choice() {
        return algorithm_choice;
    }

    // for thread safe
    public synchronized void setAlgorithm_choice(int algorithm_choice) {
        this.algorithm_choice = algorithm_choice;
        // renew the list and index
        algorithm.setServerDetailsArrayList(pool);
    }

    // for thread safe
    public synchronized void add_server(ServerDetails serverDetails) {
        pool.add(serverDetails);
        algorithm.setServerDetailsArrayList(pool);


    }


    public ServerDetails getNext() {

        switch (algorithm_choice) {
            case Algorithm.RandomSelectNext:
                return algorithm.RandomSelectNext();
            case Algorithm.RoundRobinNext:
                return algorithm.RoundRobinNext();
            case Algorithm.WeightDependentNext:
                return algorithm.WeightDependentNext();
        }

        return null;
    }

    public ArrayList<ServerDetails> getPool() {
        return pool;
    }
}
