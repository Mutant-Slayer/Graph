#include <bits/stdc++.h>
using namespace std;

// for more accurate code go to gfg account

vector<int> adj[4];

void addEdge(int u,int v)
{
	adj[v].push_back(u);
	adj[u].push_back(v);
}

void adjacencylist(int m)
{

	for(int i=0;i<m;i++) // space complexity (n+2*e)
	{
		addEdge(0, 1);
    	addEdge(0, 2);
    	addEdge(1, 2);
    	addEdge(2, 0);
    	addEdge(2, 3);
    	addEdge(3, 3);
	}
}

vector<int> bfs(int v, vector<int> adj[])
{
	vector<int> b;
	vector<int> visited(v,0);

	for(int i=0;i<v;i++)
	{
		if(!visited[i])
		{
			queue<int> q;
			q.push(i);
			visited[i]=1;
			
			while(!q.empty())
			{
				int node=q.front();
				q.pop();
				b.push_back(node);
				
				for(auto it: adj[node]) // first explore all adjacent nodes in bfs but in dfs we explore the first non visited node
				{
					if(!visited[it])
					{
						q.push(it);
						visited[it]=1;
					}
				}
			}
		}
	}
	
	return b;
}

int main()
{

	adjacencylist(4);
	
	vector<int> ans=bfs(4,adj);
	
	for(int i=0;i<ans.size();i++)
	cout <<ans[i]<<" ";

    return 0;
}

