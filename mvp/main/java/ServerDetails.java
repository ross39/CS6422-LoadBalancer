package main.java;
/*
 * Authors Cheuk Wei Lin, Meiqi Huang,Ross Heaney, Ruyun Sun, Yiqiu Wang
 */

public class ServerDetails implements Comparable {
    int weight;
    //This will be a file corresponding to an ipaddress in the final build
    String address;

    public ServerDetails(int weight, String address) {
        this.weight = weight;
        this.address = address;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public int compareTo(Object o) {

        if (o == null)
            throw new NullPointerException("cannot compare the null object");

        ServerDetails serverDetails = (ServerDetails) o;

        if (serverDetails.getWeight() > this.weight)
            return -1;
        else if (serverDetails.getWeight() < this.weight)
            return 1;

        return 0;
    }
}
