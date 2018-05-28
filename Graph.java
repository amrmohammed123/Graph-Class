import java.util.*;
public class Graph 
{
	List< List<Pair> > adj;
	int size;
	Graph(int capacity)
	{
		size = capacity;
		adj = new ArrayList<>(capacity);
		for(int i = 0 ; i < capacity ; i++)
			adj.add(new ArrayList<Pair>());
	}
	public void addEdge(int start , int end , int weight) //directed edge
	{
		Pair pair = new Pair(end , weight);
		adj.get(start).add(pair);		
	}
	public int degree(int vertex)
	{
		return adj.get(vertex).size();
	}
	private ArrayList<Pair> getAdj(int vertex)
	{
		return (ArrayList<Pair>)adj.get(vertex);
	}
	public String BFS(int start)
	{
		boolean[] visited = new boolean[size];
		return BFS(visited , start);		
	}
	public String BFS(boolean[] visited , int start) 
	{		
		ArrayList<Integer> queue = new ArrayList<>();
		queue.add(start);
		String result = "";
		while(queue.size() != 0)
		{
			int node = queue.get(0);
			queue.remove(0);
			if(visited[node])
				continue;
			visited[node] = true;
			result += node + " ";
			for(int i = 0 ; i < adj.get(node).size(); i++)
				queue.add(adj.get(node).get(i).vertex);
		}
		return result;
	}
	public String DFS(int start)
	{
		boolean[] visited = new boolean[size];
		return DFS(visited ,start);
	}
	public String DFS(boolean[] visited , int start) 
	{
		String result = start + " ";
		visited[start] = true;
		for(int i = 0 ; i < adj.get(start).size(); i++)
		{
			if(visited[adj.get(start).get(i).vertex])
				continue;
			
			result += DFS(visited , adj.get(start).get(i).vertex);
		}
		return result;
	}
	public int StronglyConnectedComponents() 
	{
		int count = 0;
		boolean[] visited = new boolean[size];
		for(int i = 0 ; i < adj.size(); i++)
		{
			if(!visited[i])				
			{
				count++;
				DFS(visited,i);
			}
		}
		return count;
	}
	class Pair
	{
		int vertex;
		int weight;
		Pair(int vertex , int weight)
		{
			this.vertex = vertex;
			this.weight = weight;
		}
	}
}
