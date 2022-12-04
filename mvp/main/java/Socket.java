package main.java;

public class Socket {

  private String clientip;
  private String serverip;
  private String message;
  private String clientinfo;

  public String getClientip() {
    return clientip;
  }

  public void setClientip(String clientip) {
    this.clientip = clientip;
  }

  public String getServerip() {
    return serverip;
  }

  public void setServerip(String serverip) {
    this.serverip = serverip;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Socket(String clientip, String serverip, String message) {

    this.clientip = clientip;
    this.serverip = serverip;
    this.message = message;
  }

  public Socket(String clientinfo, Server server) {

    this.clientinfo = clientinfo;
    this.serverip = server.getIp();
  }

  public String getClientinfo() {
    return clientinfo;
  }

  public void setClientinfo(String clientinfo) {
    this.clientinfo = clientinfo;
  }
}
