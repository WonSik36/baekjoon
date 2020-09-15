import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.comparingDouble;

class Solution {
    public static void main(String[] args){
        String word = "blind";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};

        Solution sol = new Solution();
        int res = sol.solution(word, pages);

        System.out.println(res);
    }

    public int solution(String word, String[] pages) {
        List<Page> list = new ArrayList<>();
        for(int i=0;i<pages.length;i++){
            list.add(new Page(i,pages[i],word));
        }

        for(Page p: list){
            for(String parent: p.parents){
                int idx = list.indexOf(new Page(parent));
                if(idx == -1)
                    continue;
                Page parentPage = list.get(idx);

                parentPage.children.add(p);
            }
        }

        for(Page p : list){
            calcLinkScore(p);
            p.matchingScore = p.score + p.linkScore;
        }

        // for(Page p: list){
        //     System.out.println(p);
        // }
        
        list.sort(comparingDouble((Page p) -> p.matchingScore).reversed().thenComparingInt((Page p) -> p.index));

        return list.get(0).index;
    }

    static void calcLinkScore(Page p){
        p.linkScore = 0;
        if(p.children.size() == 0)
            return;

        for(int i=0;i<p.children.size();i++){
            Page c = p.children.get(i);

            p.linkScore += (double)c.score / c.parents.size();
        }
    }
}

class Page {
    public int index;
    public String url;
    public List<Page> children;
    public List<String> parents;

    public int score;
    public double linkScore;
    public double matchingScore;

    public Page(String url){
        this.url = url;
    }

    public Page(int index, String page, String word){
        this.index = index;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
        
        word = word.toLowerCase();
        page = page.toLowerCase();

        int l, r;
        // url
        l = page.indexOf("<meta property=\"og:url\"");
        l = page.indexOf("content=\"", l);
        l += 9;
        r = page.indexOf("\"", l);
        this.url = page.substring(l,r);

        // parents
        l = page.indexOf("<a");
        while(l != -1){
            l = page.indexOf("href=\"", l);
            l += 6;
            r = page.indexOf("\"", l);
            this.parents.add(page.substring(l,r));
            l = page.indexOf("<a", l+1);
        }

        // score
        l = page.indexOf(word);
        while(l != -1){
            if((page.charAt(l+word.length()) <'a' || page.charAt(l+word.length()) >'z')
                    && (page.charAt(l-1) <'a' || page.charAt(l-1) >'z'))
                this.score++;

            l = page.indexOf(word, l+1);
        }
    }

    @Override
    public boolean equals(Object obj){
        Page that = (Page)obj;

        return this.url.equals(that.url);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("index: ");
        sb.append(index);
        sb.append("\nurl: ");
        sb.append(url);
        sb.append("\nscore: ");
        sb.append(score);
        sb.append("\nlink score: ");
        sb.append(linkScore);
        sb.append("\nmatching score: ");
        sb.append(matchingScore);
        sb.append("\nparents:\n");
        for(String url: parents){
            sb.append(url);
            sb.append("\n");
        }
        sb.append("chlidren:\n");
        for(Page c: children){
            sb.append(c.url);
            sb.append("\n");
        }

        return sb.toString();
    }
}