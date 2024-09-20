import java.io.File;
import java.util.Scanner;


public class Main4A {

    public static void main(String args[]) {

        try {
            File file = new File("resources/ExempleTP1.txt"); // remplacement de arg[0] par le path (comme ça pas besoin de le mettre dans les config du Run)
            File file2 = new File("resources/ExempleTP1-smallgraph-undirected.txt");
            File file3 = new File("resources/ExempleTP1-smallgraph-directed.txt");

            file = file3;

            //If we choose the representation by adjacency matrix
            Scanner sc = new Scanner(file);
            GraphM4A graphM = new GraphM4A(sc);

            System.out.println("Affichage de la matrice d'adjacence :");
            Tools4A.printMatrix(graphM.getAdjmat());

            if (graphM.getType() == 0) { //undirected
                int[] degree = graphM.degree();
                System.out.println("(Matrix) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");
                Tools4A.printArray(degree);
            } else { //directed
                TwoArrays4A pair = graphM.degrees();
                int[] indegree = pair.in(); //the result of graphM.degrees() is a pair of arrays, indegree and outdegree
                int[] outdegree = pair.out();
                System.out.println("(Matrix)Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(indegree);
                System.out.println("(Matrix)Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(outdegree);

                System.out.println("Exercice 1");
                System.out.println("Affichage de la matrice d'adjacence de la transposée :");
                graphM.printTransposed(true);
                /* TODO
                System.out.println("Affichage de la liste d'adjacence de la transposée :");
                graphM.printTransposed(false);
                 */
            }

            // If we choose the representation by adjacency lists
            sc = new Scanner(file);
            GraphL4A graphL = new GraphL4A(sc);
            if (graphL.getType() == 0 && graphL.getWeighted() == 0) { //undirected and unweighted
                int[] degree = graphL.degree();
                System.out.println("(List) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");
                Tools4A.printArray(degree);
            }
            if (graphL.getType() == 0 && graphL.getWeighted() == 1) { //undirected and weighted
                int[] degree = graphL.degreeW();
                System.out.println("(List) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");
                Tools4A.printArray(degree);
            }
            if (graphL.getType() == 1 && graphL.getWeighted() == 0) { //directed and unweighted
                TwoArrays4A pair = graphL.degrees();
                int[] indegree = pair.in();
                int[] outdegree = pair.out();
                System.out.println("(List) Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(indegree);
                System.out.println("(List) Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(outdegree);

                System.out.println("Affichage de la liste d'adjacence :");
                Tools4A.printAdjList(graphL.getAdjlist(), graphL.getN());
                /* TODO
                System.out.println("Exercice 1");
                System.out.println("Affichage de la liste d'adjacence de la transposée :");
                graphL.printTransposed(true);
                System.out.println("Affichage de la matrice d'adjacence de la transposée :");
                graphL.printTransposed(false);
                 */
            }
            if (graphL.getType() == 1 && graphL.getWeighted() == 1) { //directed and weighted
                TwoArrays4A pair = graphL.degreesW();
                int[] indegree = pair.in();
                int[] outdegree = pair.out();
                System.out.println("(List)Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(indegree);
                System.out.println("(List)Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");
                Tools4A.printArray(outdegree);

                /* TODO
                System.out.println("Exercice 1");
                System.out.println("Affichage de la liste d'adjacence de la transposée :");
                graphL.printTransposed(true);
                System.out.println("Affichage de la matrice d'adjacence de la transposée :");
                graphL.printTransposed(false);
                 */
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
