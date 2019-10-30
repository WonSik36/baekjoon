/*
    baekjoon online judge
    problem number 17070
    https://www.acmicpc.net/problem/17070

    DFS problem, using 2d array
    also use enum
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Iterator;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        Pos.setMaxLen(N);

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cases = DFS(N,map);

        bw.write(Integer.toString(cases)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // return all posible cases
    // N: map length, map: room structure map
    public static int DFS(int N, int[][] map){
        int[][][] dp = new int[N][N][PipeType.size()]; 
        initDP(dp);

        return _DFS(new Pos(0,0,PipeType.HORIZON),N,map,dp);
    } 


    // cur: current position, N: size of room
    // map: description of the room, dp: memoization of pos
    public static int _DFS(Pos cur, int N, int[][] map, int[][][] dp){
        // reach to final pos
        Pos curEnd = cur.getEndPos();
        if(curEnd.x==N-1 && curEnd.y==N-1)
            return 1;
        // if visited before than return memoized value
        if(dp[cur.y][cur.x][cur.type.getValue()] != -1)
            return dp[cur.y][cur.x][cur.type.getValue()];

        int sum = 0;
        ArrayList<Pos> list = cur.getNextPosList();
        for(int i=0;i<list.size();i++){
            Pos tmp = list.get(i);
            if(!isPossiblePos(tmp, map))
                continue;
            sum += _DFS(tmp,N,map,dp);
        }

        // memoize value
        dp[cur.y][cur.x][cur.type.getValue()] = sum;
        return sum;
    }

    public static boolean isPossiblePos(Pos pos, int[][] map){
        Pos endPos = pos.getEndPos();
        if(PosType.valueOf(map[pos.y][pos.x]) == PosType.WALL)
            return false;
        if(PosType.valueOf(map[endPos.y][endPos.x]) == PosType.WALL)
            return false;
            
        // if tmp pipe type is Diagonal than check 2 pos more
        if(pos.type == PipeType.DIAGONAL){
            if(PosType.valueOf(map[pos.y+1][pos.x]) == PosType.WALL)
                return false;
            if(PosType.valueOf(map[pos.y][pos.x+1]) == PosType.WALL)
                return false;
        }

        return true;
    }

    public static void initDP(int[][][] dp){
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
    }

    public static class Pos{
        private static int maxX; //N
        private static int maxY; //N
        public int x;
        public int y;
        public PipeType type;

        public Pos(int x, int y, PipeType type){
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public static void setMaxLen(int len){
            maxX = len;
            maxY = len;
        }

        // get next position which is in range 0~N
        public ArrayList<Pos> getNextPosList(){
            ArrayList<Pos> list = type.getNextPos(x,y);
            Iterator<Pos> it = list.iterator();
            while(it.hasNext()){
                if(!isPossiblePos(it.next()))
                    it.remove();
            }

            return list;
        }

        public Pos getEndPos(){
            return type.getEndPos(x, y);
        }

        // return true when list is in range 0~N
        private static boolean isPossiblePos(Pos pos){
            Pos end = pos.type.getEndPos(pos.x,pos.y);
            
            if(pos.x<0)
                return false;
            if(pos.x>=maxX)
                return false;
            if(pos.y<0)
                return false;
            if(pos.y>=maxY)
                return false;
            
            if(end.x<0)
                return false;
            if(end.x>=maxX)
                return false;
            if(end.y<0)
                return false;
            if(end.y>=maxY)
                return false;

            return true;
        }
    }

    public static enum PipeType{
        HORIZON(0), VERTICAL(1), DIAGONAL(2);

        private int value;

        private PipeType(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }

        public ArrayList<Pos> getNextPos(int x, int y){
            ArrayList<Pos> list = new ArrayList<Pos>();
            
            switch(this){
                case HORIZON:
                    list.add(new Pos(x+1,y,HORIZON));
                    list.add(new Pos(x+1,y,DIAGONAL));    
                    break;
                case VERTICAL:
                    list.add(new Pos(x,y+1,VERTICAL));
                    list.add(new Pos(x,y+1,DIAGONAL));
                    break;    
                case DIAGONAL:
                    list.add(new Pos(x+1,y+1,HORIZON));
                    list.add(new Pos(x+1,y+1,VERTICAL));
                    list.add(new Pos(x+1,y+1,DIAGONAL));
                    break;     
                default: throw new AssertionError("Unkown Value: "+value);
            }

            return list;
        }

        public Pos getEndPos(int x, int y){
            switch(this){
                case HORIZON:
                    return new Pos(x+1,y,HORIZON);
                case VERTICAL:
                    return new Pos(x,y+1,VERTICAL);
                case DIAGONAL:
                    return new Pos(x+1,y+1,DIAGONAL);
                default: throw new AssertionError("Unkown Value: "+value);
            }
        }

        public static PipeType valueOf(int value){
            switch(value){
                case 0: return HORIZON;
                case 1: return VERTICAL;
                case 2: return DIAGONAL;
                default: throw new AssertionError("Unkown Value: "+value);
            }
        }

        public static int size(){
            return 3;
        }
    }

    public static enum PosType{
        EMPTY(0), WALL(1);

        private int value;

        private PosType(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }

        public static PosType valueOf(int value){
            switch(value){
                case 0: return EMPTY;
                case 1: return WALL;
                default: throw new AssertionError("Unkown Value: "+value);
            }
        }

        public static int size(){
            return 2;
        }
    }
}
