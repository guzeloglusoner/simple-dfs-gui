public class Main {

    public static void main(String[] args) throws InterruptedException {


        Graph myGraph = new Graph();
        myGraph.addVertex("A", 1, 0);
        myGraph.addVertex("B", 0, 1);
        myGraph.addVertex("C", 0, 2);
        myGraph.addVertex("D", 2, 1);
        myGraph.addVertex("E", 2, 2);
        myGraph.addVertex("F", 1, 3);

        myGraph.addEdge(0, 1);
        myGraph.addEdge(1, 2);
        myGraph.addEdge(0, 3);
        myGraph.addEdge(3, 4);
        myGraph.addEdge(4, 5);
        myGraph.addEdge(1, 3);

        myGraph.initializeUI();
        myGraph.dfs();
        System.out.println();


    }


}