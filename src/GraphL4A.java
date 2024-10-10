import java.util.LinkedList;
import java.util.Scanner;

public class GraphL4A {

    private int n;
    private int type; //0 if undirected, 1 if directed
    private int weighted; // 0 if unweighted, 1 otherwise
    private WeightedNode4A[] adjlistW; //one of adjlistW and adjlist is null, depending on the type of the graph
    private Node4A[] adjlist;

    //TP2
    private int[][] arcType; // 1:tree arc, 2:forward arc, 3:backward arc, 4:cross arc
    private int[] debut;
    private int[] fin;
    private LinkedList<Integer> cycle; //liste de sommets visités, pour détecter les cycles


    public GraphL4A(Scanner sc) {
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
        if (this.weighted == 0) {
            this.adjlist = new Node4A[this.n];
            for (int i = 0; i < this.n; i++)
                adjlist[i] = null;
            adjlistW = null;
        } else {
            this.adjlistW = new WeightedNode4A[this.n];
            for (int i = 0; i < this.n; i++)
                adjlistW[i] = null;
            adjlist = null;
        }


        for (int k = 0; k < this.n; k++) {
            String[] line = sc.nextLine().split(" : ");
            int i = Integer.parseInt(line[0]); //the vertex "source"
            if (weighted == 0) {
                if ((line.length > 1) && (line[1].charAt(0) != ' ')) {
                    String[] successors = line[1].split(", ");
                    System.out.println(i + " " + successors.length);
                    for (int h = 0; h < successors.length; h++) {
                        Node4A node = new Node4A(Integer.parseInt(successors[h]) - 1, null);
                        node.setNext(adjlist[i - 1]);
                        adjlist[i - 1] = node;
                    }
                }
            } else {
                line = line[1].split(" // ");
                if ((line.length == 2) && (line[1].charAt(0) != ' ')) {// if there really are some successors, then we must have something different from " " after "// "
                    String[] successors = line[0].split(", ");
                    String[] theirweights = line[1].split(", ");
                    for (int h = 0; h < successors.length; h++) {
                        WeightedNode4A nodeW = new WeightedNode4A(Integer.parseInt(successors[h]) - 1, null, Float.parseFloat(theirweights[h]));
                        nodeW.setNext(adjlistW[i - 1]);
                        adjlistW[i - 1] = nodeW;
                    }
                }
            }
        }
    }

    //method to be applied only when type=0 ----------------------------------------------------------------------------

    //method to be applied only when type=0 and weighted=0
    public int[] degree() {
        int[] tmp = new int[this.n];
        for (int i = 0; i < this.n; i++)
            tmp[i] = 0;
        for (int i = 0; i < this.n; i++) {
            Node4A p = adjlist[i];
            while (p != null) {
                tmp[i] = tmp[i] + 1;
                p = p.getNext();
            }
        }
        return (tmp);
    }

    //method to be applied only when type=0 and weighted=1
    public int[] degreeW() {
        int[] tmp = new int[this.n];
        for (int i = 0; i < this.n; i++)
            tmp[i] = 0;
        for (int i = 0; i < this.n; i++) {
            WeightedNode4A p = adjlistW[i];
            while (p != null) {
                tmp[i] = tmp[i] + 1;
                p = p.getNext();
            }
        }
        return (tmp);
    }

    /**
     * TP1 Exercise 2
     * @param sequence of vertices
     * @return whether there is a path
     */
    public boolean hasPath(int[] sequence) {
        if(this.weighted == 0) { //unweighted
            for(int i = 0; i < sequence.length-1; i++) {
                Node4A p = adjlist[sequence[i]-1];
                while(p.getVal() != sequence[i+1]-1) { //si le successeur n'est pas celui recherché, alors on continue de chercher
                    if(p.getNext() == null) //si on n'a pas trouvé mais qu'il n'y a plus de successeur
                        return false;
                    p = p.getNext();
                }
            }
        } else {                 //weighted
            for(int i = 0; i < sequence.length-1; i++) {
                WeightedNode4A p = adjlistW[sequence[i]-1];
                while(p.getVal() != sequence[i+1]-1) { //si le successeur n'est pas celui recherché, alors on continue de chercher
                    if(p.getNext() == null) //si on n'a pas trouvé mais qu'il n'y a plus de successeur
                        return false;
                    p = p.getNext();
                }
            }
        }
        return true;

    }


    //method to be applied only when type=1 ----------------------------------------------------------------------------

    //method to be applied only when type=1 and weighted=0
    public TwoArrays4A degrees() {
        int[] tmp1 = new int[this.n]; //indegrees
        int[] tmp2 = new int[this.n]; //outdegrees
        for (int i = 0; i < this.n; i++) {
            tmp1[i] = 0;
            tmp2[i] = 0;
        }
        for (int i = 0; i < this.n; i++) {
            Node4A p = adjlist[i];
            while (p != null) {
                tmp2[i] = tmp2[i] + 1;
                tmp1[p.getVal()] = tmp1[p.getVal()] + 1;
                p = p.getNext();
            }
        }
        return (new TwoArrays4A(tmp1, tmp2));
    }

    //method to be applied only when type=1 and weighted=1
    public TwoArrays4A degreesW() {
        int[] tmp1 = new int[this.n]; //indegrees
        int[] tmp2 = new int[this.n]; //outdegrees
        for (int i = 0; i < this.n; i++) {
            tmp1[i] = 0;
            tmp2[i] = 0;
        }
        for (int i = 0; i < this.n; i++) {
            WeightedNode4A p = adjlistW[i];
            while (p != null) {
                tmp2[i] = tmp2[i] + 1;
                tmp1[p.getVal()] = tmp1[p.getVal()] + 1;
                p = p.getNext();
            }
        }
        return (new TwoArrays4A(tmp1, tmp2));
    }


    /**
     * TP1 Exercise 1
     *
     * @param outList display the result as an adjacency list (or as an adjacency matrix)
     */
    public void printTransposed(boolean outList) {
        if (outList)
            Tools4A.printAdjList(transposedLL(), transposedLLW());
        else
            Tools4A.printMatrix(transposedLM());
    }

    /**
     * TP1 Exercise 1
     * Compute the transposed graph, represented by an unweighted adjacency list
     */
    private Node4A[] transposedLL() {
        if (this.weighted == 1) return null;

        Node4A[] trans = new Node4A[this.n];
        for (int i = 0; i < this.n; i++) {
            Node4A p = this.adjlist[i];
            while (p != null) {
                if (trans[p.getVal()] == null) // si on a pas encore mis de noeud
                    trans[p.getVal()] = new Node4A(i, null);
                else {                      // s'il y a déjà des noeuds
                    Node4A next = new Node4A(trans[p.getVal()].getVal(), trans[p.getVal()].getNext());
                    trans[p.getVal()] = new Node4A(i, next);
                }
                p = p.getNext();
            }
        }
        return trans;
    }

    /**
     * TP1 Exercise 1
     * Compute the transposed graph, represented by a weighted adjacency list
     */
    private WeightedNode4A[] transposedLLW() {
        if (this.weighted == 0) return null;

        WeightedNode4A[] trans = new WeightedNode4A[this.n];
        for (int i = 0; i < this.n; i++) {
            WeightedNode4A p = this.adjlistW[i];
            while (p != null) {
                if (trans[p.getVal()] == null) // si on a pas encore mis de noeud
                    trans[p.getVal()] = new WeightedNode4A(i, null, p.getWeight());
                else {                      // s'il y a déjà des noeuds
                    WeightedNode4A next = new WeightedNode4A(trans[p.getVal()].getVal(), trans[p.getVal()].getNext(), trans[p.getVal()].getWeight());
                    trans[p.getVal()] = new WeightedNode4A(i, next, p.getWeight());
                }
                p = p.getNext();
            }
        }
        return trans;
    }

    /**
     * TP1 Exercise 1
     * Compute the transposed graph, represented by an adjacency matrix
     */
    private float[][] transposedLM() {
        if (weighted == 0) {
            float[][] transM = new float[this.adjlist.length][this.adjlist.length];
            for (int i = 0; i < adjlist.length; i++) {
                Node4A node = adjlist[i];
                while (node != null) {
                    transM[node.getVal()][i] = 1;
                    node = node.getNext();
                }
            }
            return transM;
        } else {
            float[][] transM = new float[this.adjlistW.length][this.adjlistW.length];
            for (int i = 0; i < adjlistW.length; i++) {
                WeightedNode4A node = adjlistW[i];
                while (node != null) {
                    transM[node.getVal()][i] = node.getWeight();
                    node = node.getNext();
                }
            }
            return transM;
        }
    }


    /**
     * TP2
     * @param s the vertex root of the tree provided by the DFSnum algorithm
     * @param sval the value of the node s (on n'y a pas vraiment accès si on le garde pas)
     */
    private void DFS_Num(Node4A s, int sval) {
        //récursivité sur chaque successeur de s
        for (Node4A next = s; next != null; next = next.getNext()){
    		if (this.debut[next.getVal()] == 0) {
                //tree arc
                this.nb += 1;
                this.debut[next.getVal()] = this.nb;
                this.arcType[sval][next.getVal()] = 1;
                this.cycle.add(next.getVal());
    			DFS_Num(adjlist[next.getVal()], next.getVal());
    		}
            else {
                //not tree arc
                if (this.fin[next.getVal()] == 0) {
                    if (this.debut[sval] < this.debut[next.getVal()])
                        this.arcType[sval][next.getVal()] = 2; //forward (d[s]<d[n]) arc
                    else {
                        this.arcType[sval][next.getVal()] = 3; //backward (d[s]>d[n]) arc (=cycle)
                        //tous les noeuds entre s et n dans this.cycle sont dans le cycle
                        System.out.println("Présence d'un cycle :");
                        for(int i=this.cycle.indexOf(next.getVal()); i<this.cycle.indexOf(sval)+1; i++)
                            System.out.print(this.cycle.get(i) + 1 + " ");
                        System.out.println();
                    }
                }
                else
                    this.arcType[sval][next.getVal()] = 4; //cross arc
            }
    	}
        this.nb += 1;
        this.fin[sval] = this.nb;
        this.cycle.remove((Integer) sval);
    }
    // exactement la même fonction qu'au dessuss
    private void DFS_NumW(WeightedNode4A s, int sval) {
        //récursivité sur chaque successeur de s
        for (WeightedNode4A next = s; next != null; next = next.getNext()){
    		if (this.debut[next.getVal()] == 0) {
                //tree arc
                this.nb += 1;
                this.debut[next.getVal()] = this.nb;
                this.arcType[sval][next.getVal()] = 1;
                this.cycle.add(next.getVal());
    			DFS_NumW(adjlistW[next.getVal()], next.getVal());
    		}
            else {
                //not tree arc
                if (this.fin[next.getVal()] == 0) {
                    if (this.debut[sval] < this.debut[next.getVal()])
                        this.arcType[sval][next.getVal()] = 2; //forward (d[s]<d[n]) arc
                    else {
                        this.arcType[sval][next.getVal()] = 3; //backward (d[s]>d[n]) arc (=cycle)
                        //tous les noeuds entre s et n dans this.cycle sont dans le cycle
                        System.out.println("Présence d'un cycle :");
                        for(int i=this.cycle.indexOf(next.getVal()); i<this.cycle.indexOf(sval)+1; i++)
                            System.out.print(this.cycle.get(i) + 1 + " ");
                        System.out.println();
                    }
                }
                else
                    this.arcType[sval][next.getVal()] = 4; //cross arc
            }
    	}
        this.nb += 1;
        this.fin[sval] = this.nb;
        this.cycle.remove((Integer) sval);
    }

    private int nb = 0; //pour remplir d[] et f[]
    /**
     * TP2
     * Perform a graph search using the DFSnum algorithm
     */
    public void search() {
        this.debut = new int[this.n];
        this.fin = new int[this.n];
        this.arcType = new int[this.n][this.n];
        this.cycle = new LinkedList<Integer>();
        for (int i=0; i<this.n; i++) { //parcours de tous les noeuds (juste les noeuds, pas leurs successeurs)
            if (this.debut[i] == 0) {
                this.nb += 1;
                this.debut[i] = this.nb;
                this.cycle.add(i);
                if (this.weighted == 0)
                    DFS_Num(adjlist[i], i);
                else
                    DFS_NumW(adjlistW[i], i);
            }
        }
    }
    


    public int getType() {
        return this.type;
    }

    public int getWeighted() {
        return this.weighted;
    }

    //TP1
    public WeightedNode4A[] getAdjlistW() {
        return adjlistW;
    }
    public Node4A[] getAdjlist() {
        return adjlist;
    }

    //TP2
    public int[][] getArcType() {
        return arcType;
    }
    public int[] getDebut() {
        return debut;
    }
    public int[] getFin() {
        return fin;
    }
}
		
	

	 
