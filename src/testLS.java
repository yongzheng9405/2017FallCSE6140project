//package Algorigthms.LocalSearch;

//import Graph.*;

import java.io.IOException;

/**
 * Created by wqshen on 11/14/17.
 */


public class testLS {

    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Please enter graph name");
            return;
        }
        String now_dir = System.getProperty("user.dir");
        String file_path = now_dir + "/Data/" + args[0];
        Graph g = GraphUtil.loadGraph(file_path);
        LSMain LS1 = new LSMain(g);
        LS1.LS1();
        //System.out.print(i);

    }
}