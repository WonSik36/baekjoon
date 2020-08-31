import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static final char EMPTY = 0;
    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        parseBoard(board, map);
        
        while(true){
            Queue<Pos> queue = findDeleteBlock(map);
            if(queue.isEmpty())
                break;
            
            checkDeleteBlock(map, queue);
            execMap(map);
        }
        
        int answer = findEmptySpace(map);
        return answer;
    }
    
    void parseBoard(String[] board, char[][] map){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length();j++){
                map[i][j] = board[i].charAt(j);
            }
        }
    }
    
    Queue<Pos> findDeleteBlock(char[][] map){
        Queue<Pos> queue = new LinkedList<>();    
        
        for(int i=0;i<map.length-1;i++){
            for(int j=0;j<map[i].length-1;j++){
                if(map[i][j] != EMPTY && map[i][j] == map[i+1][j]
                        && map[i][j] == map[i][j+1] && map[i][j] == map[i+1][j+1]){
                    queue.add(new Pos(i,j));
                    queue.add(new Pos(i+1,j));
                    queue.add(new Pos(i,j+1));
                    queue.add(new Pos(i+1,j+1));
                }
            }
        }
        
        return queue;
    }
    
    void checkDeleteBlock(char[][] map, Queue<Pos> queue){
        while(!queue.isEmpty()){
            Pos first = queue.poll();
            map[first.y][first.x] = EMPTY;
        }
    }
    
    void execMap(char[][] map){
        for(int j=0;j<map[0].length;j++){
            for(int i=1; i<map.length; i++){
                for(int k=map.length-1;k>=i;k--){
                    if(map[k][j] == EMPTY)
                        swap(k,j,k-1,j,map);
                }
            }
        }
    }
    
    void swap(int srcY, int srcX, int dstY, int dstX, char[][] map){
        char tmp = map[srcY][srcX];
        map[srcY][srcX] = map[dstY][dstX];
        map[dstY][dstX] = tmp;
    }
    
    int findEmptySpace(char[][] map){
        int cnt = 0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j] == EMPTY)
                    cnt++;
            }
        }
        
        return cnt;
    }
}

class Pos {
    public int x;
    public int y;
    
    public Pos(int y, int x){
        this.y = y;
        this.x = x;
    }
}