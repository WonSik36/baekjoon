#include <cassert>
#include <iostream>
#include <random>
using namespace std;

const int maxTC =        10; // number of testcases
const int maxN  =   20*1000; // number of caves
const int maxE  =  100*1000; // number of edges
const int maxV  =   10*1000; // cave value
const int maxC  =   10*1000; // edge cost

mt19937 gen(1337); // seed

void gen_tc(int tc, int N, int E, int edges_per_pair) {
    uniform_int_distribution<> rand_N(1, N);
    uniform_int_distribution<> rand_V(0, maxV);
    uniform_int_distribution<> rand_C(0, maxC);

	assert(N >= 2);

	cout << N << " " << E << endl;

	cout << (maxV - tc); // first cave
	for (int i = 1; i < N; i++) {
		cout << " " << rand_V(gen);
	}
	cout << endl;

	int reachable = 1;

	for (int e = 0; e < E; /* nothing */) {
		int a, b;
		if (reachable < N) {
			a = uniform_int_distribution<>(1, reachable)(gen);
			b = ++reachable;
		} else {
			do {
				a = rand_N(gen);
				b = rand_N(gen);
			} while (a == b);
			if (a > b) swap(a, b);
		}
		int kE = min(max(1, edges_per_pair), E-e - (N-reachable));
		for (int k = 0; k < kE; k++) {
			cout << a << " " << b << " " << rand_C(gen) << endl;
			if (++e >= E) {
				break;
			}
		}
	}
	assert(reachable == N);
}

int main() {
	ios::sync_with_stdio(false);

	int TC = maxTC;
	cout << TC << endl;

	for (int tc = 1; tc <= TC; tc++) {
		gen_tc(tc, maxN, maxE, tc);
	}
}
