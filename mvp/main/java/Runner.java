package main.java;
/**
 * @author: meiqi huang
 * @date: 2022-11-1
 */

import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {
        // automatically add itself to serverPool. No need to add to serverpool.
        ArrayList<Server> servers = new ArrayList<Server>() {{
            add(new Server(1, "ip1"));
            add(new Server(2, "ip2"));
            add(new Server(3, "ip3"));
            add(new Server(4, "ip4"));
            add(new Server(1, "ip1-1"));
            add(new Server(2, "ip2-2"));
            add(new Server(3, "ip3-3"));
            add(new Server(4, "ip4-4"));
        }};

        ServerPool serverPool = ServerPool.getServerPool();

        System.out.println(" \n default serverlist in server pool ");
        ArrayList<Server> pool = serverPool.getPool();
        for (int i = 0; i < pool.size(); i++) {
            System.out.print(pool.get(i).getIp() + " ");
        }

        LoadBalancer loadBalancer = LoadBalancer.getInstance();
        loadBalancer.setServer_list(serverPool.getPool());
        System.out.println(" \n default algorithm is WeightDependentNext algorithm ");
        for (int i = 0; i < 20; i++) {
            System.out.print(loadBalancer.getNext().getIp() + " ");
        }

        System.out.println("\n choose RoundRobin algorithm ");
        loadBalancer.setAlgorithm_choice(Algorithm.RoundRobinNext);
        for (int i = 0; i < 20; i++) {
            System.out.print(loadBalancer.getNext().getIp() + " ");
        }

        System.out.println("\n choose WeightDependent algorithm ");
        loadBalancer.setAlgorithm_choice(Algorithm.WeightDependentNext);
        for (int i = 0; i < 20; i++) {
            System.out.print(loadBalancer.getNext().getIp() + " ");
        }

        System.out.println("\n \n now add a new server with weight 5 ");
        // automatically add itself to serverPool. No need to add to serverpool.
        new Server(5, "ip5");

        System.out.println("\n choose RoundRobin algorithm ");
        loadBalancer.setAlgorithm_choice(Algorithm.RoundRobinNext);
        for (int i = 0; i < 20; i++) {
            System.out.print(loadBalancer.getNext().getIp() + " ");
        }

        System.out.println("\n choose WeightDependent algorithm");
        loadBalancer.setAlgorithm_choice(Algorithm.WeightDependentNext);
        for (int i = 0; i < 20; i++) {
            System.out.print(loadBalancer.getNext().getIp() + " ");
        }

    }


}
