import java.io.File;
import java.util.Scanner;


public class Main4A {

    public static void main(String args[]) {

        try {
            //File file= new File(args[0]);
            File file = new File("resources/ExempleTP1.txt"); // remplacement de arg[0] par le path (comme ça pas besoin de le mettre dans les config du Run)
            File file1 = new File("resources/ExempleTP1-smallgraph-undirected.txt");
            File file2 = new File("resources/ExempleTP1-smallgraph-undirectedW.txt");
            File file3 = new File("resources/ExempleTP1-smallgraph-directed.txt");
            File file4 = new File("resources/ExempleTP1-smallgraph-directedW.txt");

            file = file1;

            int[] v1 = {1,2,3,4,5,4,2,1,3}; //exo 2
            int[] v2 = {1,2,3,5};

            System.out.println("If we choose the representation by adjacency matrix ---------------------------------");
            Scanner sc = new Scanner(file);
            GraphM4A graphM = new GraphM4A(sc);

            System.out.println("(TP1) Matrice d'adjacence :");
            Tools4A.printMatrix(graphM.getAdjmat());

            if (graphM.getType() == 0) { //undirected
                int[] degree = graphM.degree();
                System.out.println("(Matrix) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");
                Tools4A.printArray(degree);

                System.out.println("TP1 Exercice 2 :");
                System.out.println("Teste s'il y a un chemin dans le graphe pour la séquence de sommets donnée");
                Tools4A.printArray(v1);
                System.out.println(graphM.hasPath(v1));
                Tools4A.printArray(v2);
                System.out.println(graphM.hasPath(v2));

            } else { //directed
                TwoArrays4A pair = graphM.degrees();
                int[] indegree = pair.in(); //the result of graphM.degrees() is a pair of arrays, indegree and outdegree
                int[] outdegree = pair.out();
                System.out.println("(Matrix)Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(indegree);
                System.out.println("(Matrix)Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(outdegree);

                System.out.println("TP1 Exercice 1");
                System.out.println("Matrice d'adjacence de la transposée :");
                graphM.printTransposed(true);
                System.out.println("Liste d'adjacence de la transposée :");
                graphM.printTransposed(false);
            }


            System.out.println("If we choose the representation by adjacency lists -----------------------------------");
            sc = new Scanner(file);
            GraphL4A graphL = new GraphL4A(sc);

            System.out.println("(TP1) Liste d'adjacence :");
            Tools4A.printAdjList(graphL.getAdjlist(), graphL.getAdjlistW());

            if (graphL.getType() == 0 && graphL.getWeighted() == 0) { //undirected and unweighted
                int[] degree = graphL.degree();
                System.out.println("(List) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");
                Tools4A.printArray(degree);

                System.out.println("TP1 Exercice 2 :");
                System.out.println("Teste s'il y a un chemin dans le graphe pour la séquence de sommets donnée");
                Tools4A.printArray(v1);
                System.out.println(graphL.hasPath(v1));
                Tools4A.printArray(v2);
                System.out.println(graphL.hasPath(v2));
            }
            else if (graphL.getType() == 0 && graphL.getWeighted() == 1) { //undirected and weighted
                int[] degree = graphL.degreeW();
                System.out.println("(List) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");
                Tools4A.printArray(degree);

                System.out.println("TP1 Exercice 2 :");
                System.out.println("Teste s'il y a un chemin dans le graphe pour la séquence de sommets donnée");
                Tools4A.printArray(v1);
                System.out.println(graphL.hasPath(v1));
                Tools4A.printArray(v2);
                System.out.println(graphL.hasPath(v2));
            }
            else if (graphL.getType() == 1 && graphL.getWeighted() == 0) { //directed and unweighted
                TwoArrays4A pair = graphL.degrees();
                int[] indegree = pair.in();
                int[] outdegree = pair.out();
                System.out.println("(List) Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(indegree);
                System.out.println("(List) Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(outdegree);

                System.out.println("TP1 Exercice 1");
                System.out.println("Liste d'adjacence de la transposée :");
                graphL.printTransposed(true);
                System.out.println("Matrice d'adjacence de la transposée :");
                graphL.printTransposed(false);
            }
            else if (graphL.getType() == 1 && graphL.getWeighted() == 1) { //directed and weighted
                TwoArrays4A pair = graphL.degreesW();
                int[] indegree = pair.in();
                int[] outdegree = pair.out();
                System.out.println("(List)Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(indegree);
                System.out.println("(List)Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(outdegree);

                System.out.println("TP1 Exercice 1");
                System.out.println("Liste d'adjacence de la transposée :");
                graphL.printTransposed(true);
                System.out.println("Matrice d'adjacence de la transposée :");
                graphL.printTransposed(false);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
