package Algorigthms.LocalSearch;

import Graph.*;

import java.io.IOException;

/**
 * Created by wqshen on 11/14/17.
 */


public class testLS {

    public static void main(String[] args) throws IOException {
        Graph g = GraphUtil.loadGraph("Data/star.graph");
        LSMain LS1 = new LSMain(g);
        LS1.LS1();
        //System.out.print(i);

    }
}