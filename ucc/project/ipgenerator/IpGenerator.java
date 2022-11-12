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
     * server is stored in out repository now
     * @return String
     */
    public static String getIP(){
        Integer i = 1;
        String path = "ucc/project/out/server" + i.toString() + ".txt";
        File f = new File(path);
        while (f.exists()){
            i++;
            path = "ucc/project/out/server" + i.toString() + ".txt";
            f = new File(path);
        }
        return path;
    }


}
