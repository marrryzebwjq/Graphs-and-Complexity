import java.util.Scanner;

public class GraphM4A {

    private int n;
    private int type; //0 if undirected, 1 if directed
    private int weighted; // 0 if unweighted, 1 otherwise
    private float[][] adjmat;


    public GraphM4A(Scanner sc) {
        String[] firstline = sc.nextLine().split(" ");
        this.n = Integer.parseInt(firstline[0]);
        System.out.println("Number of vertices " + this.n);
        if (firstline[1].equals("undirected"))
            this.type = 0;
        else
            this.type = 1;
        System.out.println("Type= " + this.type);
        if (firstline[2].equals("unweighted"))
            this.weighted = 0;
        else
            this.weighted = 1;
        System.out.println("Weighted= " + this.weighted);

        this.adjmat = new float[this.n][this.n];
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                adjmat[i][j] = 0; // replace 0 with something else if the weights can be 0

        for (int k = 0; k < this.n; k++) {
            String[] line = sc.nextLine().split(" : ");
            int i = Integer.parseInt(line[0]); //the vertex "source"
            if (weighted == 0) {
                if ((line.length > 1) && (line[1].charAt(0) != ' ')) {
                    String[] successors = line[1].split(", ");
                    System.out.println(i + " " + successors.length);
                    for (int h = 0; h < successors.length; h++)
                        this.adjmat[i - 1][Integer.parseInt(successors[h]) - 1] = 1;
                }
            } else {
                line = line[1].split(" // ");
                if ((line.length == 2) && (line[1].charAt(0) != ' ')) {// if there really are some successors, then we must have something different from " " after "// "
                    String[] successors = line[0].split(", ");
                    String[] theirweights = line[1].split(", ");
                    for (int h = 0; h < successors.length; h++)
                        this.adjmat[i - 1][Integer.parseInt(successors[h]) - 1] = Float.parseFloat(theirweights[h]);
                }
            }
        }
    }

    //method to be applied only when type=0 ----------------------------------------------------------------------------

    public int[] degree() {
        int[] tmp = new int[this.n];
        for (int i = 0; i < this.n; i++)
            tmp[i] = 0;
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.adjmat[i][j] != 0)
                    tmp[i] = tmp[i] + 1;
        return tmp;
    }

    /**
     * TP1 Exercise 2
     * @param sequence of vertices
     * @return whether there is a path
     */
    public boolean hasPath(int[] sequence) {
        return false;
    }


    //method to be applied only when type=1 ----------------------------------------------------------------------------

    public TwoArrays4A degrees() {
        int[] tmp1 = new int[this.n]; //indegrees
        int[] tmp2 = new int[this.n]; //outdegrees
        for (int i = 0; i < this.n; i++) {
            tmp1[i] = 0;
            tmp2[i] = 0;
        }
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.adjmat[i][j] != 0) {
                    tmp2[i] = tmp2[i] + 1;
                    tmp1[j] = tmp1[j] + 1;
                }
        return (new TwoArrays4A(tmp1, tmp2));
    }

    /**
     * TP1 Exercise 1
     * @param outMatrix display the result as an adjacency matrix (or as an adjacency list)
     */
    public void printTransposed(boolean outMatrix) {
        if (outMatrix)
            Tools4A.printMatrix(transposedMM());
        else
            Tools4A.printAdjList(transposedML(), transposedMLW());
    }

    /**
     * TP1 Exercise 1
     * Compute the transposed graph, represented by an adjacency matrix
     */
    private float[][] transposedMM() {
        float[][] trans = new float[this.adjmat.length][this.adjmat.length];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                trans[i][j] = this.adjmat[j][i];
            }
        }
        return trans;
    }

    /**
     * TP1 Exercise 1
     * Compute the transposed graph, represented by an unweighted adjacency list
     */
    private Node4A[] transposedML() {
        if (this.weighted == 1) return null;

        Node4A[] adjlist = new Node4A[this.n];
        //liste des successeurs du noeud j
        for (int j = 0; j < this.n; j++) {
            int[] successeurs = new int[this.n];
            int k = 0;
            for (int i = 0; i < this.n; i++) {
                if (this.adjmat[i][j] != 0) {
                    successeurs[k] = i;
                    k++;
                }
            }
            //ajout du noeud j et de ses successeurs dans adjlist
            Node4A n = new Node4A(successeurs[0], null); //n.val = premier successeur de n
            adjlist[j] = n;
            k = 1;
            while (successeurs[k] != 0) { //n.next = autre successeur de n
                Node4A s = new Node4A(successeurs[k], null);
                n.setNext(s);
                n = s;
                k++;
            }
        }
        return adjlist;
    }

    /**
     * TP1 Exercise 1
     * Compute the transposed graph, represented by a weighted adjacency list
     */
    private WeightedNode4A[] transposedMLW() {
        if (this.weighted == 0) return null;

        WeightedNode4A[] adjlistW = new WeightedNode4A[this.n];
        //liste des successeurs du noeud j
        for (int j = 0; j < this.n; j++) {
            int[] successeurs = new int[this.n];
            float[] poids = new float[this.n];
            int k = 0;
            for (int i = 0; i < this.n; i++) {
                if (this.adjmat[i][j] != 0) {
                    successeurs[k] = i;
                    poids[k] = this.adjmat[i][j];
                    k++;
                }
            }
            //ajout du noeud j et de ses successeurs dans adjlist
            WeightedNode4A n = new WeightedNode4A(successeurs[0], null, poids[0]); //n.val = premier successeur de n
            adjlistW[j] = n;
            k = 1;
            while (successeurs[k] != 0) { //n.next = autre successeur de n
                WeightedNode4A s = new WeightedNode4A(successeurs[k], null, poids[k]);
                n.setNext(s);
                n = s;
                k++;
            }
        }
        return adjlistW;
    }



    public int getType() {
        return this.type;
    }

    //TP1
    public float[][] getAdjmat() {
        return adjmat;
    }
}
