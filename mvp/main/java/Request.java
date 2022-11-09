package main.java;

public class Request {

    private int id;
    private String serveraddress;
    private String clientreqstr;

    public Request(int id, String serveraddress, String clientreqstr) {
        this.id = id;
        this.serveraddress = serveraddress;
        this.clientreqstr = clientreqstr;
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

}
