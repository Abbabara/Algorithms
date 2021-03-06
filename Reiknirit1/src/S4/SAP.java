package S4;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;

public class SAP {
	/*
		*	There may exist two common ancestors which have the same combined length to v and w. 
		 	Any such ties are broken by their id's, i.e. the ancestor with the higher graph id wins.
		 
	X	*	If the input graph is not a DAG then throw an IllegalArgumentException with the message Graph is not acyclic
			*	i.e. throw new IllegalArgumentException("Graph is not acyclic");
			
	X	* 	If the input graph is not rooted then throw an IllegalArgumentException with the message Graph is not rooted
			* 	i.e. throw new IllegalArgumentException("Graph is not rooted");

	X	* 	You must first test whether the graph is acyclic before testing whether it is rooted

		*	A Time limit exceeded does not imply that your solution is correct.
	 */
	
	private Digraph graph;
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		graph = G;
		
		// sorting the graph topologically to check if it has any cycles
		Topological t = new Topological(G);
		
		//if there is no cycles
		if(!t.hasOrder()){
			//throwing argument
			throw new IllegalArgumentException("Graph is not acyclic");
		}
		int count = 0;
		//checking if the digraph is rooted, so, for every vertices in the digraph, check how many has 0 directed edges (is a root). We can only have one root.
		for(int i = 0; i < G.V(); i++) {
			if(G.outdegree(i) == 0) {
				count++;
			}
		}
		//if the count of roots is not 1, it has more than one root. (or 0)
		if(count != 1) {
			throw new IllegalArgumentException("Graph is not rooted");
		}
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		
		/*
		if (!checkIfValid(v, w)){
			throw new IndexOutOfBoundsException();
		}
		
		BreadthFirstDirectedPaths BFDPV = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths BFDPW = new BreadthFirstDirectedPaths(graph, w);
		
		int commonAnc = ancestor(v, w);
		if (commonAnc == -1){ // If there is no common ancestor 
			return -1;
		}
		
		return BFDPV.distTo(commonAnc) + BFDPW.distTo(commonAnc); // length equals the distance from v to Anc + distance w to Anc
	
		 */
		return 0;
	}

	// a shortest common common ancestor of v and w; -1 if no such path
	public int ancestor(int v, int w) {
		return 0;
	}
	
	// length of shortest ancestral path of vertex subsets A and B; -1 if no such path
	public int length(Iterable <Integer > A, Iterable <Integer > B) {
		return 0;
	}

	// a shortest common ancestor of vertex subsets A and B; -1 if no such path
	public int ancestor(Iterable <Integer > A, Iterable <Integer > B) {
		return 0;
	}

	// do unit testing of this class
	public static void main(String[] args) {
		
	}
}
