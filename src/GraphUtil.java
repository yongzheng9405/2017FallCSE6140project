package Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphUtil {
    /**
     * Load Graph from file
     * @param file_path
     * @return
     * @throws IOException
     */
    public static Graph loadGraph (String file_path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        String line = br.readLine();
        String[] split_array = line.split(" ");
        int numVertex = Integer.parseInt(split_array[0]);
        int numEdges = Integer.parseInt(split_array[1]);
        Graph g = new Graph(numVertex);
        for (int i = 1; i <= numVertex; i++){
            line = br.readLine();
            if(!line.equals("")){
                split_array = line.split(" ");

                for (String vertex : split_array){
                    g.addEdge(i, Integer.parseInt(vertex));
                }
            }

        }
        br.close();
        if (g.numOfEdges() != numEdges) g = null;
        return g;
    }

}
