package main.java;
/**
 * @author: meiqi huang
 * @created: 2022-11-1
 */

import java.util.ArrayList;
import java.util.Random;

public class Algorithm {

    public final static int RandomSelectNext = 1;
    public final static int RoundRobinNext = 2;
    public final static int WeightDependentNext = 3;

    private ArrayList<Server> serverList = new ArrayList<>();

    private int current_index_roundrobin = 0;

    private int current_index_weightdepend = serverList.size() - 1;

    private void RenewCurrentIndex() {
        current_index_roundrobin = 0;
        current_index_weightdepend = serverList.size() - 1;
    }

    public void setServerList(ArrayList<Server> serverList) {
        this.serverList = serverList;
        RenewCurrentIndex();
    }

    public Server RandomSelectNext() {

        int list_number = serverList.size();
        Random random = new Random();
        int random_index = random.nextInt(list_number);
        Server server = serverList.get(random_index);
        return server;

    }

    public Server RoundRobinNext() {

        Server server;

        if (current_index_roundrobin < serverList.size()) {
            server = serverList.get(current_index_roundrobin);
            current_index_roundrobin++;
        } else {
            current_index_roundrobin = 0;
            server = serverList.get(current_index_roundrobin);
            current_index_roundrobin++;
        }

        return server;


    }

    public Server WeightDependentNext() {

        Server server;
        serverList.sort(null);

        if (current_index_weightdepend >= 0) {
            server = serverList.get(current_index_weightdepend);
            current_index_weightdepend--;
        } else {
            current_index_weightdepend = serverList.size() - 1;
            server = serverList.get(current_index_weightdepend);
            current_index_weightdepend--;
        }

        return server;


    }


}