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

    private ArrayList<ServerDetails> serverDetailsArrayList = new ArrayList<>();

    private int current_index_roundrobin = 0;

    private int current_index_weightdepend = serverDetailsArrayList.size() - 1;

    private void RenewCurrentIndex() {
        current_index_roundrobin = 0;
        current_index_weightdepend = serverDetailsArrayList.size() - 1;
    }

    public void setServerDetailsArrayList(ArrayList<ServerDetails> serverDetailsArrayList) {
        this.serverDetailsArrayList = serverDetailsArrayList;
        RenewCurrentIndex();
    }

    public ServerDetails RandomSelectNext() {

        int list_number = serverDetailsArrayList.size();
        Random random = new Random();
        int random_index = random.nextInt(list_number);
        ServerDetails serverDetails = serverDetailsArrayList.get(random_index);
        return serverDetails;

    }

    public ServerDetails RoundRobinNext() {

        ServerDetails serverDetails;

        if (current_index_roundrobin < serverDetailsArrayList.size()) {
            serverDetails = serverDetailsArrayList.get(current_index_roundrobin);
            current_index_roundrobin++;
        } else {
            current_index_roundrobin = 0;
            serverDetails = serverDetailsArrayList.get(current_index_roundrobin);
            current_index_roundrobin++;
        }

        return serverDetails;


    }

    public ServerDetails WeightDependentNext() {

        ServerDetails serverDetails;
        serverDetailsArrayList.sort(null);

        if (current_index_weightdepend >= 0) {
            serverDetails = serverDetailsArrayList.get(current_index_weightdepend);
            current_index_weightdepend--;
        } else {
            current_index_weightdepend = serverDetailsArrayList.size() - 1;
            serverDetails = serverDetailsArrayList.get(current_index_weightdepend);
            current_index_weightdepend--;
        }

        return serverDetails;


    }


}