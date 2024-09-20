
public class Tools4A {

    public static void printArray(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(" " + tab[i]);
        }
        System.out.println();
    }


    /**
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
     * Affiche la liste d'adjacence pondérée (remarque : leurs listes ont l'air totalement mal foutues je comprends r)
     * @param list
     * @param length
     */
    public static void printAdjListW(WeightedNode4A[] list, int length) {
        for(int i = 0; i < length; i++ ) {
            System.out.printf("%d : %d -> %d (w : %.2f, %.2f)\n", i, list[i].getVal(), list[i].getNext().getVal(), (double)(list[i].getWeight()), (double)(list[i].getNext().getWeight()));
        }
    }

    /**
     * Affiche la liste d'adjacence
     * @param list
     * @param length
     */
    public static void printAdjList(Node4A[] list, int length) {
        for(int i = 0; i < length; i++ ) {
            System.out.printf("%d : %d -> %d", i, list[i].getVal(), list[i].getNext().getVal());
        }
    }
}
