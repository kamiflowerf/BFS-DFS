package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Grafo {
	
	private HashMap<String, List<String>> listAdy;
	
	public Grafo(int numVert) {
		setListAdy(new HashMap<>(numVert));
	}
	
	public void addEdge(String origin, String dest) {
		
		//crea los nodos en caso de que no existan
		getListAdy().putIfAbsent(origin, new ArrayList<>());
		getListAdy().putIfAbsent(dest, new ArrayList<>());
		
		//agregar el nodo destino a la lista de adyacencia del nodo origen
		getListAdy().get(origin).add(dest);
		
		//como el grafo es no dirigido, agregar la arista inversa
		getListAdy().get(dest).add(origin);
	}
	
	public void printList() {
		System.out.println("Lista de Adyacencia: ");
		for (String node : getListAdy().keySet()) {
			System.out.print(node + " -> ");
			System.out.println(getListAdy().get(node));
		}
	}
	
	public void getDegreeSequence(int numNodes) {
		ArrayList<Integer> sequence = new ArrayList<>(numNodes);
		
		//Itera sobre la lista de adyacencia para contar el número de vecinos
		for (String node : getListAdy().keySet()) {
			int degree = getListAdy().get(node).size(); //el tamaño de la lista de ady es el grado del nodo
			sequence.add(degree);
		}
		
		System.out.println("Secuencia de grados: " + sequence);
	}
	
	public static void bfs(Grafo graph, String initialNode) {
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();		
		
		queue.offer(initialNode);
		visited.add(initialNode);
		
		while(!queue.isEmpty()) {
			String current = queue.poll();
			System.out.print(current + " ");
			
			for(String neighbor : graph.getListAdy().get(current)) {
				if(!visited.contains(neighbor)) {
					queue.offer(neighbor);
					visited.add(neighbor);
				}
			}
		}
		System.out.println("");
	}
	
	public static void dfs(Grafo graph, String node) {
		Set<String> visited = new HashSet<>();
		dfsRecursive(graph, node, visited);
		System.out.println("");
	}
	
	private static void dfsRecursive(Grafo graph, String node, Set<String> visited) {
		visited.add(node);
		System.out.print(node + " ");
		
		for(String neighbor : graph.getListAdy().get(node)) {
			if(!visited.contains(neighbor)) {
				dfsRecursive(graph, neighbor, visited);
			}
		}
	}

	public HashMap<String, List<String>> getListAdy() {
		return listAdy;
	}

	public void setListAdy(HashMap<String, List<String>> listAdy) {
		this.listAdy = listAdy;
	}

	public static void main(String[] args) {
		Grafo graph = new Grafo(10);
		
		graph.addEdge("A", "B");
		graph.addEdge("A", "E");
		graph.addEdge("A", "G");
		graph.addEdge("B", "C");
		graph.addEdge("B", "D");
		graph.addEdge("C", "E");
		graph.addEdge("C", "H");
		graph.addEdge("C", "I");
		graph.addEdge("D", "H");
		graph.addEdge("D", "I");
		graph.addEdge("E", "J");
		graph.addEdge("F", "H");
		graph.addEdge("F", "I");
		graph.addEdge("G", "J");
		graph.addEdge("H", "I");
		graph.addEdge("H", "J");
		
		//graph.printList();
		Scanner n = new Scanner(System.in);
		System.out.println("Introduzca el nodo (A-J): ");
		String node = n.nextLine();
		n.close();
		
		System.out.print("BFS: ");
		bfs(graph, node);
		System.out.print("DFS: ");
		dfs(graph, node);
	}
}
