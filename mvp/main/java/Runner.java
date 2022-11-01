package main.java;
/**
 * @author: meiqi huang
 * @date: 2022-11-1
 */

import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {
        ArrayList<ServerDetails> servers = new ArrayList<ServerDetails>() {{
            add(new ServerDetails(1, "ip1"));
            add(new ServerDetails(2, "ip2"));
            add(new ServerDetails(3, "ip3"));
            add(new ServerDetails(4, "ip4"));
            add(new ServerDetails(1, "ip1-1"));
            add(new ServerDetails(2, "ip2-2"));
            add(new ServerDetails(3, "ip3-3"));
            add(new ServerDetails(4, "ip4-4"));
        }};

        ServerPool serverPool = ServerPool.getServerPool();
        serverPool.setPool(servers);
        System.out.println(" \n default serverlist in server pool ");
        ArrayList<ServerDetails> pool = serverPool.getPool();
        for (int i = 0; i < pool.size(); i++) {
            System.out.print(pool.get(i).getAddress() + " ");
        }

        System.out.println(" \n default algorithm is random algorithm ");
        for (int i = 0; i < 20; i++) {
            System.out.print(serverPool.getNext().getAddress() + " ");
        }


        System.out.println("\n choose RoundRobin algorithm ");
        serverPool.setAlgorithm_choice(Algorithm.RoundRobinNext);
        for (int i = 0; i < 20; i++) {
            System.out.print(serverPool.getNext().getAddress() + " ");
        }

        System.out.println("\n choose WeightDependent algorithm ");
        serverPool.setAlgorithm_choice(Algorithm.WeightDependentNext);
        for (int i = 0; i < 20; i++) {
            System.out.print(serverPool.getNext().getAddress() + " ");
        }

        System.out.println("\n \n now add a new server with weight 5 ");
        serverPool.add_server(new ServerDetails(5, "ip5"));

        System.out.println("\n choose RoundRobin algorithm ");
        serverPool.setAlgorithm_choice(Algorithm.RoundRobinNext);
        for (int i = 0; i < 20; i++) {
            System.out.print(serverPool.getNext().getAddress() + " ");
        }

        System.out.println("\n choose WeightDependent algorithm");
        serverPool.setAlgorithm_choice(Algorithm.WeightDependentNext);
        for (int i = 0; i < 20; i++) {
            System.out.print(serverPool.getNext().getAddress() + " ");
        }


    }


}
