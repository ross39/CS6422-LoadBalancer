package main.java;

import java.util.ArrayList;

public class LoadBalancer {

    // defalut algorithm choice
    private int algorithm_choice = Algorithm.WeightDependentNext;

    private Algorithm algorithm = new Algorithm();

    //Create a arraylist of server pools
    private ArrayList<Server> server_list = new ArrayList<>();

    private static LoadBalancer loadBalancer = new LoadBalancer();

    // singleton mode
    private LoadBalancer(){}

    public static LoadBalancer getInstance(){
        return loadBalancer;
    }

    // for thread safe
    public synchronized Server getNext() {
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

    // for thread safe
    public synchronized void setAlgorithm_choice(int algorithm_choice) {
        this.algorithm_choice = algorithm_choice;
        // renew the list and index
        algorithm.setServerList(server_list);
    }

    public void setServer_list(ArrayList<Server> server_list) {
        this.server_list = server_list;
        algorithm.setServerList(server_list);
    }

    public ArrayList<Server> getServer_list() {
        return server_list;
    }

    public int getAlgorithm_choice() {
        return algorithm_choice;
    }



}
