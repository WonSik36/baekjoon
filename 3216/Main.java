/*
    baekjoon online judge
    problem number 3216
    https://www.acmicpc.net/problem/3216

    Parametric Search with Binary Search
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main{
    static int TOTAL_LENGTH = 0;
    static int TOTAL_DOWNLOAD = 0;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        List<Music> list = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int length = Integer.parseInt(st.nextToken());
            int download = Integer.parseInt(st.nextToken());

            TOTAL_LENGTH += length;
            TOTAL_DOWNLOAD += download;

            list.add(new Music(length, download));
        }

        int result = binarySearch(list);
        bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }

    static int binarySearch(List<Music> list){
        int lo = 0, hi = TOTAL_DOWNLOAD + 1;
        
        while(lo+1 < hi){
            int mid = (lo+hi) / 2;
            
            if(isPossible(mid, list))
                hi = mid;
            else
                lo = mid;
        }

        return hi;
    }

    static boolean isPossible(int time, List<Music> list){
        boolean[] part = new boolean[list.size()];

        // calc current download
        Music curDownMusic = null;
        int downIdx;
        for(downIdx=0; downIdx<list.size(); downIdx++){
            curDownMusic = list.get(downIdx);
            int download = curDownMusic.getDownload();

            if(download > time){
                curDownMusic.setCurDown(time);
                break;
            }else{
                time -= download;
                part[downIdx] = true;
            }
        }

        if(downIdx == list.size())
            return true;

        // play music
        int musicIdx = 0;
        Music curMusic = list.get(musicIdx);
        curMusic.startMusic();
        for(int i=0; i< TOTAL_DOWNLOAD - time; i++){
            // System.out.printf("downl idx: %d, cur: %d\n", downIdx, curDownMusic.getCurDown());
            // System.out.printf("music idx: %d, cur: %d\n", musicIdx, curMusic.getCurrent());
            
            if(curDownMusic.isDownloaded()){
                part[downIdx] = true;
                downIdx++;

                if(downIdx == list.size())
                    return true;

                curDownMusic = list.get(downIdx);
                curDownMusic.startDown();
            }

            curDownMusic.down();

            if(curMusic.isPlayed()){
                musicIdx++;
                
                if(musicIdx==list.size()||!part[musicIdx])
                    return false;

                curMusic = list.get(musicIdx);
                curMusic.startMusic();
            }

            curMusic.play();
        }

        if(part[list.size()-1])
            return true;
        else
            return false;
    }
}

class Music {
    private final int length;
    private final int download;
    private int cur;
    private int curDown;

    public Music(int length, int download){
        this.length = length;
        this.download = download;
    }

    public int getLength(){
        return length;
    }

    public int getDownload(){
        return download;
    }

    public int getCurrent(){
        return cur;
    }

    public int getCurDown(){
        return curDown;
    }

    public void setCurDown(int curDown){
        this.curDown = curDown;
    }

    public void startMusic(){
        cur = 0;
    }

    public void startDown(){
        curDown = 0;
    }

    public void play(){
        cur++;
    }

    public void down(){
        curDown++;
    }

    public boolean isPlayed(){
        return cur == length;
    }

    public boolean isDownloaded(){
        return curDown == download;
    }
}