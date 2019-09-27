/*
    ref by: https://www.acmicpc.net/board/view/25480
*/

#include<iostream>
#include<algorithm>
using namespace std;

void print2DArray(int t[][50], int N);

int main()
{
	int t[106][50], M[101];
	int i, j, o, m, N;
	cin >> N >> m;
	while (m--){
		cin >> M[0];
		M[M[0]] = -1;
	}
	for (i = 0; i <= N; i++)
		for (j = 0; j <= 50; j++)
			t[i][j] = 1e9;
	t[0][0] = 0;
	for (i = 0; i <= N; i++) {
		for (j = 0; j <= 43; j++)
		{
			if (M[i + 1] == -1)t[i + 1][j] = min(t[i][j], t[i + 1][j]);

			t[i + 1][j] = min(t[i + 1][j], t[i][j] + 10000);
			t[i + 3][j + 1] = min(t[i + 3][j + 1], t[i][j] + 25000);
			t[i + 5][j + 2] = min(t[i + 5][j + 2], t[i][j] + 37000);

			for (o = 3; o <= j; o++)
				t[i + 1][j - 3] = min(t[i + 1][j - 3], t[i][j]);
		}
	}
	m = t[N][0];
	for (i = 1; i < 43; i++)
		if (m > t[N][i])
			m = t[N][i];
	cout << m;
    // print2DArray(t, N);

	return 0;
}

void print2DArray(int t[][50], int N){
    for(int i=0;i<N;i++){
        for(int j=0;j<43;j++){
            if(t[i][j] != 1e9)
                cout<<t[i][j]<<" ";
        }
        cout<<endl;
    }
}