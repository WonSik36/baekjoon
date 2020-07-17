/*
    baekjoon online judge
    problem number 15898
    https://www.acmicpc.net/problem/15898

    Brute Force Algorithm

    Be careful when number of memory allocation(new construct operation) is lot
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{
    static final int KILN_SIZE = 5;
    static final int ITEM_SIZE = 4;
    static final int ITEM_ATTR = 2;
    static final int QUALITY = 0;
    static final int COLOR = 0;
    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;
    static final int GREEN = 3;
    static final int YELLOW = 4;

    static final int[] adX = {0, 1, 0, 1};
    static final int[] adY = {0, 0, 1, 1};

    static int N;
    static int[][][][] items;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        items = new int[N][ITEM_ATTR][ITEM_SIZE][ITEM_SIZE];

        for(int i=0;i<N;i++){
            for(int y=0;y<ITEM_SIZE;y++){
                st = new StringTokenizer(br.readLine());
                for(int x=0;x<ITEM_SIZE;x++){
                    items[i][0][y][x] = Integer.parseInt(st.nextToken());
                }
            }

            for(int y=0;y<ITEM_SIZE;y++){
                st = new StringTokenizer(br.readLine());
                for(int x=0;x<ITEM_SIZE;x++){
                    char ch = st.nextToken().toCharArray()[0];
                    switch(ch){
                        case 'W':
                            items[i][1][y][x] = WHITE;
                            break;
                        case 'R':
                            items[i][1][y][x] = RED;
                            break;
                        case 'G':
                            items[i][1][y][x] = GREEN;
                            break;
                        case 'B':
                            items[i][1][y][x] = BLUE;
                            break;
                        case 'Y':
                            items[i][1][y][x] = YELLOW;
                            break;
                        default:
                            throw new AssertionError("Unknown Color");
                    }
                    
                }
            }
        }
        
        // printItems();

        int max = 0 ;
        int[][][] kiln = makeKiln();
        // choose items
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(j == i)
                    continue;

                for(int k=0;k<N;k++){
                    if(k == i || k == j)
                        continue;
                    

                    // rotate
                    for(int a=0;a<4;a++){
                        for(int b=0;b<4;b++){
                            for(int c=0;c<4;c++){

                                // position
                                for(int l=0;l<4;l++){
                                    for(int m=0;m<4;m++){
                                        for(int n=0;n<4;n++){
                                            clearArr(kiln);
                                            // int[][][] kiln = makeKiln();

                                            rotateAndAdd(kiln, adY[l], adX[l], items[i], a);
                                            rotateAndAdd(kiln, adY[m], adX[m], items[j], b);
                                            rotateAndAdd(kiln, adY[n], adX[n], items[k], c);

                                            max = Math.max(max, calcRes(kiln));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        bw.write(Integer.toString(max));
        bw.write("\n");

        bw.close();
        br.close();
    }

    static int[][][] makeKiln(){
        return new int[ITEM_ATTR][KILN_SIZE][KILN_SIZE];
    }

    static int[][][] makeItem(){
        return new int[ITEM_ATTR][ITEM_SIZE][ITEM_SIZE];
    }

    static void rotateAndAdd(int[][][] kiln, int y, int x, int[][][] item, int angle){
        if(angle == 0){
            for(int a=0;a<ITEM_SIZE;a++){
                for(int b=0;b<ITEM_SIZE;b++){
                    kiln[0][a+y][b+x] = addValue(kiln[0][a+y][b+x], item[0][a][b]);
                }
            }
    
            for(int a=0;a<ITEM_SIZE;a++){
                for(int b=0;b<ITEM_SIZE;b++){
                    kiln[1][a+y][b+x] = addColor(kiln[1][a+y][b+x], item[1][a][b]);
                }
            }
        }else if(angle == 1){
            for(int a=0;a<ITEM_SIZE;a++){
                for(int b=0;b<ITEM_SIZE;b++){
                    kiln[0][a+y][b+x] = addValue(kiln[0][a+y][b+x], item[0][b][ITEM_SIZE - a - 1]);
                }
            }
    
            for(int a=0;a<ITEM_SIZE;a++){
                for(int b=0;b<ITEM_SIZE;b++){
                    kiln[1][a+y][b+x] = addColor(kiln[1][a+y][b+x], item[1][b][ITEM_SIZE - a - 1]);
                }
            }
        }else if(angle == 2){
            for(int a=0;a<ITEM_SIZE;a++){
                for(int b=0;b<ITEM_SIZE;b++){
                    kiln[0][a+y][b+x] = addValue(kiln[0][a+y][b+x], item[0][ITEM_SIZE - a - 1][ITEM_SIZE - b - 1]);
                }
            }
    
            for(int a=0;a<ITEM_SIZE;a++){
                for(int b=0;b<ITEM_SIZE;b++){
                    kiln[1][a+y][b+x] = addColor(kiln[1][a+y][b+x], item[1][ITEM_SIZE - a - 1][ITEM_SIZE - b - 1]);
                }
            }
        }else{
            for(int a=0;a<ITEM_SIZE;a++){
                for(int b=0;b<ITEM_SIZE;b++){
                    kiln[0][a+y][b+x] = addValue(kiln[0][a+y][b+x], item[0][ITEM_SIZE - b - 1][a]);
                }
            }
    
            for(int a=0;a<ITEM_SIZE;a++){
                for(int b=0;b<ITEM_SIZE;b++){
                    kiln[1][a+y][b+x] = addColor(kiln[1][a+y][b+x], item[1][ITEM_SIZE - b - 1][a]);
                }
            }
        }
    }

    static int calcRes(int[][][] kiln){
        int sum = 0;
        for(int i=0;i<KILN_SIZE;i++){
            for(int j=0;j<KILN_SIZE;j++){
                switch(kiln[1][i][j]){
                    case WHITE:
                        break;
                    case RED:
                        sum += 7 * kiln[0][i][j];
                        break;
                    case GREEN:
                        sum += 3 * kiln[0][i][j];
                        break;
                    case BLUE:
                        sum += 5 * kiln[0][i][j];
                        break;
                    case YELLOW:
                        sum += 2 * kiln[0][i][j];
                        break;
                }
            }
        }

        return sum;
    }

    static void clearArr(int[][][] arr3){
        for(int[][] arr2 : arr3){
            for(int[] arr : arr2){
                Arrays.fill(arr, 0);
            }
        }
    }

    static int addValue(int a, int b){
        int res = a + b;
        return res < 0 ? 0 : (res > 9 ? 9 : res);
    }

    static int addColor(int a, int b){
        return b == WHITE ? a : b;
    }

    static void printItems(){
        for(int[][][] item : items){
            printItem(item);
        }
    }

    static void printItem(int[][][] item){
        for(int i=0;i<item[0].length;i++){
            for(int j=0;j<item[0][i].length;j++){
                System.out.printf("%d ", item[0][i][j]);
            }
            System.out.println();
        }

        for(int i=0;i<item[1].length;i++){
            for(int j=0;j<item[1][i].length;j++){
                switch(item[1][i][j]){
                    case WHITE:
                        System.out.print("W ");
                        break;
                    case RED:
                        System.out.print("R ");
                        break;
                    case GREEN:
                        System.out.print("G ");
                        break;
                    case BLUE:
                        System.out.print("B ");
                        break;
                    case YELLOW:
                        System.out.print("Y ");
                        break;
                    default:
                        throw new AssertionError("Unknown Color");
                }
            }
            System.out.println();
        }
    }
}
