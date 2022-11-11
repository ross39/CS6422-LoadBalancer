package main.java;

public class Request {

    private int clientid;
    private String serveraddress;
    private String clientreqstr;

    private String token;

    public Request(int clientid, String serveraddress, String clientreqstr, String token) {
        this.clientid = clientid;
        this.serveraddress = serveraddress;
        this.clientreqstr = clientreqstr;
        this.token = token;
    }

    public void setClientId() {
        this.clientid = clientid;
    }

    public int getClientId() {
        return clientid;
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
