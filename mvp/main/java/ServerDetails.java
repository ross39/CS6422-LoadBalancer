package main.java;

/**
 * @author : meiqi huang
 * @created : 2022-11-1
 **/
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
