package main.java;

public class Request {

    private int id;
    private String serveraddress;
    private String clientreqstr;

    private String token;

    public Request(int id, String serveraddress, String clientreqstr, String token) {
        this.id = id;
        this.serveraddress = serveraddress;
        this.clientreqstr = clientreqstr;
        this.token = token;
    }

    public void setId() {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setServerAddress() {
        this.serveraddress = serveraddress;
    }

    public String getServerAddress() {
        return serveraddress;
    }

    public void setClientReqStr() {
        this.clientreqstr = clientreqstr;
    }

    public String getClientReqStr() {
        return clientreqstr;
    }

    public void setToken() {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
