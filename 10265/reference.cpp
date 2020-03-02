/*
    baekjoon online judge
    Reference of problem number 10265
    https://jason9319.tistory.com/107
*/
#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>
#include <cstring>
#define MAX_N 1000
using namespace std;
int n, k, disc[MAX_N + 1], scc[MAX_N + 1], x, c, s, cost[MAX_N + 1], ma[MAX_N + 1], mi[MAX_N + 1], in[MAX_N + 1], dx[MAX_N + 1], dy[MAX_N + 1], r, dp[MAX_N + 1][MAX_N + 1];
vector<vector<int>> vt;
vector<vector<int>> svt;
stack<int> st;

int dfs(int here) {
    st.push(here);
    disc[here] = ++c;
    int ret = disc[here];

    // dfs
    for (int there : vt[here]) {
        // didn't visit before
        if (!disc[there])
            ret = min(ret, dfs(there));
        
        // visited before and was't contain in SCC
        // cycle -> find SCC
        else if (!scc[there])
            ret = min(ret, disc[there]);
    }

    // if here is root node(in SCC or itself)
    if (ret == disc[here]) {
        // s: SCC id, high indegree get low scc id
        s++;
        // make all visited node to be contain in SCC
        while (1) {
            int v = st.top();
            st.pop();
            scc[v] = s; // SCC id of v is s
            cost[s]++;  // total member of SCC
            if (v == here)break;
        }
    }
    return ret;
}

int df(int here) {
    // svt is order by decresing indegree
    int ret = ma[here];
    for (int there : svt[here]) {
        ma[here] += ma[there];
        df(there);
    }
    return ret;
}

int knapsack(int pos, int val) {
    if (pos > r)
        return 0;
    int &ret = dp[pos][val];
    if (ret != -1)return ret;
    ret = knapsack(pos + 1, val);
    if (val >= dx[pos]) {
        // only deferent thing to plain knapsack
        // weight is changeable
        for (int i = dx[pos]; i <= dy[pos]; i++) {
            if (i > val)
                break;
            ret = max(ret, knapsack(pos + 1, val - i) + i);
        }
    }
    return ret;
}

int main() {
    // get input
    // if node A needs B than make edge B -> A
    scanf("%d%d", &n, &k);
    vt.resize(n + 1);
    for (int i = 1; i <= n; i++) {
        scanf("%d", &x);
        vt[x].push_back(i);
    }

    // SCC-1 DFS: get SCC
    for (int i = 1; i <= n; i++)
        if (!disc[i])
            dfs(i);

    // SCC-2: get scalable connected component and number
    svt.resize(s + 1);  // number of scc
    for (int i = 1; i <= n; i++) {
        for (int next : vt[i]) {
            if (scc[next] == scc[i])
                continue;
            svt[scc[i]].push_back(scc[next]);
            in[scc[next]]++;
        }
    }

    for (int i = 1; i <= s; i++)
        ma[i] = mi[i] = cost[i];
    // calculate maximum number of scc
    for (int i = 1; i <= s; i++) 
        df(i);
    for (int i = 1; i <= s; i++) {
        // eliminate subset of connected component to remain root node(indegree = 0)
        if (!in[i]) {
            dx[++r] = mi[i];
            dy[r] = ma[i];
        }
    }
    memset(dp, -1, sizeof(dp));
    printf("%d\n", knapsack(1, k));
    return 0;
}