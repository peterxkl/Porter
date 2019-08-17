package main.java;

public class Porter {
    public int getSizeResult(String s1 , String s2) {
        int s1Size = StringToInt(s1.substring(0,1));
        int s2Size = StringToInt(s2.substring(0,1));
        if (s1Size > s2Size) return 1;
        else if (s1Size < s2Size) return 2;
        else if (s1Size == s2Size) return 0;
        return 4;//错误返回4
    }

    public int StringToInt(String s){
        char a = s.charAt(0);
        int result = 0;
        switch (a) {
            case 'J':
                result = 11;
                break;
            case 'Q':
                result = 12;
                break;
            case 'K':
                result = 13;
                break;
            case 'A':
                result = 14;
                break;
            default:
                result = Integer.parseInt(s);
                break;
        }
        return result;
    }
}
