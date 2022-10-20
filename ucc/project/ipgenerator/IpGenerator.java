package ucc.project.ipgenerator;
import java.io.File;

/**
 * 
 * A IPGenerator class that generate ipAddress. use the path of the server file to stimulate IP address
 * @author Ruyun
 */
public class IpGenerator {

    /**
     * generate a IP address and return it (followed as server1, server2, server3)
     * the address is not determined yet which depends on the main method and the relative path of server
     * @return String
     */
    public static String getIP(){
        Integer i = 1;
        String path = "server" + i.toString() + ".txt";
        File f = new File(path);
        while (f.exists()){
            i++;
            path = "server" + i.toString() + ".txt";
            f = new File(path);
        }
        return path;
    }


}
