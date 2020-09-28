class Solution {
    public static void main(String[] args){
        int[][] board = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,4,0,0,0},{0,0,0,0,0,4,4,0,0,0},{0,0,0,0,3,0,4,0,0,0},{0,0,0,2,3,0,0,0,5,5},{1,2,2,2,3,3,0,0,0,5},{1,1,1,0,0,0,0,0,0,5}};

        Solution sol = new Solution();
        int res = sol.solution(board);

        System.out.println(res);
    }

    private static final int[][] bx = {{0, 0, 1, 2}, {0,0,0,-1}, {0,0,0,1}, {0,0,-1,-2}, {0,-1,0,1}};
    private static final int[][] by = {{0, 1, 1, 1}, {0,1,2,2}, {0,1,2,2}, {0,1,1,1}, {0,1,1,1}};
    private static final int[][] cx = {{1,2}, {-1}, {1}, {-2, -1}, {-1,1}};
    private static final int[][] cy = {{0,0}, {1}, {1}, {0, 0}, {0,0}};

    public int solution(int[][] board) {
        int cnt = 0;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == 0)
                    continue;

                int type = blockType(i,j,board);
                if(type == -1)
                    continue;

                if(isBreakable(i, j, type, board)){
                    breakBlock(i, j, type, board);
                    cnt++;
                }
            }

            for(int j=board[i].length-1;j>=0;j--){
                if(board[i][j] == 0)
                    continue;

                int type = blockType(i,j,board);
                if(type == -1)
                    continue;

                if(isBreakable(i, j, type, board)){
                    breakBlock(i, j, type, board);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private int blockType(int y, int x, int[][] board){
        int N = board.length;
        int base = board[y][x];
        
        for(int i=0;i<bx.length;i++){
            boolean flag = true;
            for(int j=1;j<bx[i].length;j++){
                int ny = y+by[i][j];
                int nx = x+bx[i][j];

                if(ny >= N || ny < 0 || nx >= N || nx < 0){
                    flag = false;
                    break;
                }

                if(base != board[ny][nx]){
                    flag = false;
                    break;
                }
            }

            if(flag)
                return i;
        }

        return -1;
    }

    private boolean isBreakable(int y, int x, int type, int[][] board){
        for(int i=0;i<cx[type].length;i++){
            int nx = x+cx[type][i];
            for(int ny=y+cy[type][i]; ny>=0; ny--){
                if(board[ny][nx] != 0)
                    return false;
            }
        }

        return true;
    }

    private void breakBlock(int y, int x, int type, int[][] board){
        for(int i=0;i<bx[type].length;i++){
            board[y+by[type][i]][x+bx[type][i]] = 0;
        }
    }
}