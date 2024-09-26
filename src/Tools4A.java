
public class Tools4A {

    public static void printArray(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(" " + tab[i]);
        }
        System.out.println();
    }


    /**
     * TP1
     * Affiche la matrice
     * @param mat
     */
    public static void printMatrix(float[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "   ");
            }
            System.out.println();
        }
    }

    /**
     * TP1
     * Affiche la liste d'adjacence
     * @param adjlist
     * @param adjlistW
     */
    public static void printAdjList(Node4A[] adjlist, WeightedNode4A[] adjlistW) {
        if(adjlist != null) {
            System.out.print("Liste non pondérée");
            for (int i = 0; i < adjlist.length; i++) {
                Node4A current = adjlist[i]; //noeud
                System.out.printf("\n%d :", i+1);
                Node4A succ = current; //successeurs du noeud
                while (succ != null) {
                    System.out.printf(" %d", succ.getVal()+1);
                    succ = succ.getNext();
                }
            }
            System.out.println();
        }
        if(adjlistW != null) {
            System.out.print("Liste pondérée");
            for (int i = 0; i < adjlistW.length; i++) {
                WeightedNode4A current = adjlistW[i]; //noeud
                System.out.printf("\n%d :", i + 1);
                WeightedNode4A succ = current; //successeurs du noeud
                while (succ != null) {
                    System.out.printf(" %d (w=%.2f)", succ.getVal() + 1, (double)(succ.getWeight()));
                    succ = succ.getNext();
                }
            }
        }
    }
}
