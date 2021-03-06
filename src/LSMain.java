//package Algorigthms.LocalSearch;

//import Graph.Graph;
//import Graph.Edge;
//import Graph.GraphUtil;
//import Graph.IndexMinPQ;

import java.util.*;
import java.util.stream.Collectors;

public class LSMain {
    //new LS branch
    //LS1 Edge Deletion + delete redundant ==>hill climbing
    //LS2 Edge Deletion + delete redundant + 2-improvement
    private final  Graph g;
    private final HashSet<Integer> vc;
    private final HashMap<Integer,Integer> tightness;


    public LSMain(Graph g) {
        this.g = new Graph(g);
        this.vc = new HashSet<>();
        this.tightness = new HashMap<>(g.numOfVertices());
    }

    public void LS2(){
        System.out.println("LS2");

        long startTime = System.nanoTime();
        HashSet<Edge> edges = new HashSet<>(g.getEdges());
        LS1();
        System.out.println(checkVC());
        calTight();


        boolean next = true;
        while (next){
            LinkedList<Integer> tight1 = new LinkedList<>();
            for (Map.Entry<Integer,Integer> entry : tightness.entrySet()) {
                if (entry.getValue()==1) {
                    tight1.add(entry.getKey());
                }
            }
            boolean run = true;
            int i =1;
            int j, k;
            int vi, vj;
            while ( i<tight1.size() && run){
                j = i+1;
                while( j<tight1.size() && run){

                    vi = tight1.get(i); vj = tight1.get(j);
                    k = 1;
                    if (!edges.contains(new Edge(vi,vj)) ){
                        while(k<=g.numOfVertices() && run){
                            if((!vc.contains(k)) && edges.contains(new Edge(vi,k)) && edges.contains(new Edge(vj,k))){
                                this.vc.remove(vi);
                                this.vc.remove(vj);
                                this.vc.add(k);
                                run = false;
                                updateTight(vi,vj,k);
                                long curTime = System.nanoTime();
                                System.out.println("VC = "+ this.vc.size()+"   " +
                                        "Running Time = "+ (curTime-startTime)/1e9 + " s" +
                                        " replace " +vi +" & " +vj +" with " +k);
                                System.out.println(checkVC());

                            }
                            k++;
                        }
                    }
                    j++;
                }
                i++;
            }
            if(run) next=false;
        }






        long endTime = System.nanoTime();
        System.out.println("VC = "+ this.vc.size()+"   Running Time = "+ (endTime-startTime)/1e9 + " s");

    }

    public void calTight(){
        for(int i=1; i<=this.g.numOfVertices(); i++){
            int cur_tight = 0;
            if(vc.contains(i)){
                ArrayList<Integer> adj_i = this.g.getAdjV(i);
                Iterator<Integer> iterator = adj_i.iterator();
                while(iterator.hasNext()){
                    int j = iterator.next();
                    if(!vc.contains(j)) cur_tight++;
                }
            }
            tightness.put(i,cur_tight);
        }

    }
    public boolean checkVC(){
        for(int i=1; i<=this.g.numOfVertices();i++){
            if( !this.vc.contains(i)){
                boolean coverI = false;
                ArrayList<Integer> adj_i = this.g.getAdjV(i);
                Iterator<Integer> iterator = adj_i.iterator();
                while(iterator.hasNext()){
                    int j = iterator.next();
                    if(this.vc.contains(j)) {
                        coverI = true;
                        break;
                    }
                }
                if (!coverI) return false;

            }
        }
        return true;
    }

    public void updateTight(int vi,int vj, int vx){
        int cur_tight = 0;
        if(vc.contains(vx)){
            ArrayList<Integer> adj_i = this.g.getAdjV(vx);
            Iterator<Integer> iterator = adj_i.iterator();
            while(iterator.hasNext()){
                int j = iterator.next();
                if(!vc.contains(j)) cur_tight++;
            }
        }
        tightness.put(vx,cur_tight);
        tightness.put(vi,0);
        tightness.put(vj,0);

    }

    public void LS1(){
        long startTime = System.nanoTime();
        EdgeDeletion();

        Iterator<Integer> iterator = this.vc.iterator();
        IndexMinPQ pq = new IndexMinPQ(this.g.numOfVertices()+1);
        while(iterator.hasNext()){
            Integer i = iterator.next();
            pq.insert(i,g.getDegree(i));
        }
        ArrayList<Integer> adjV;
        while(!pq.isEmpty()){
            int curV = pq.delMin();
            adjV = g.getAdjV(curV);
            if (this.vc.containsAll(adjV)){
                this.vc.remove(curV);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("VC = "+ this.vc.size()+"   Running Time = "+ (endTime-startTime)/1e9 + " s");

    }


    private void EdgeDeletion(){
        HashSet<Edge> edges = new HashSet<>(g.getEdges());

        while(!edges.isEmpty()){
            Iterator<Edge> iterator = edges.iterator();
            Edge e = iterator.next();
            this.vc.add(e.getTwoEnds()[0]);
            this.vc.add(e.getTwoEnds()[1]);
            edges.remove(e);
            List<Edge> adjE1 = g.getAdj()[e.getTwoEnds()[0]];
            edges.removeAll(adjE1);
            List<Edge> adjE2 = g.getAdj()[e.getTwoEnds()[1]];
            edges.removeAll(adjE2);

        }
    }




}
