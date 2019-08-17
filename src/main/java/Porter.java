package main.java;

import java.util.*;
import java.util.stream.Collectors;

public class Porter {
    public String judgeWhoWin(String s){
        String s1 = s.substring(0,15);
        String s2 = s.substring(15);
        List<String> list1 = getSortedList(s1);
        List<String> list2 = getSortedList(s2);
        if(isTreeKind(list1)||isTreeKind(list2))  return  compareIncludeTreeKind(list1,list2);
        if(isTwoPairs(list1)||isTwoPairs(list2))  return  compareIncludeTwoPair(list1,list2);
        if(isPair(list1)||isPair(list2))  return  compareIncludePair(list1,list2);

        return judgeWhoWinInHighCard(list1,list2);
    }
    public String judgeWhoWinInHighCard(List<String> list1 , List<String> list2){
        String result = "";
        for (int i = list1.size()-1 ; i >= 0; i--){
            if(StringToInt(list1.get(i)) > StringToInt(list2.get(i))){
                result = "Person1Win";
                return result;
            }else if(StringToInt(list1.get(i)) < StringToInt(list2.get(i))){
                result = "Person2Win";
                return result;
            }else {
                result = "Peace";
            }
        }
        return result;
    }

    public Boolean isPair(List<String> sortedList){
        List<Integer> numberList = sortedList.stream().map(x->StringToInt(x)).collect(Collectors.toList());
        Set<Integer> numberSet = new HashSet<>(numberList);
        if (numberSet.size() == 4) return true;
        else return false;
    }
    public String compareIncludePair(List<String> list1 , List<String> list2){
       if (isPair(list1)&&!isPair(list2)) return "Person1Win";
       else if (!isPair(list1)&&isPair(list2)) return "Person2Win";
       else if(isPair(list1)&&isPair(list2)){
           List<Integer> numberList = list1.stream().map(x->StringToInt(x)).collect(Collectors.toList());
           int list1Number = numberList.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                   .entrySet().stream()
                   .filter(entry -> entry.getValue() > 1)
                   .map(entry -> entry.getKey())
                   .collect(Collectors.toList()).get(0);

           List<Integer> numberList2 = list2.stream().map(x->StringToInt(x)).collect(Collectors.toList());
           int list2Number = numberList2.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                   .entrySet().stream()
                   .filter(entry -> entry.getValue() > 1)
                   .map(entry -> entry.getKey())
                   .collect(Collectors.toList()).get(0);

           if (list1Number > list2Number) return "Person1Win";
           else if (list1Number < list2Number) return "Person2Win";
           else if (list1Number == list2Number) return judgeWhoWinInHighCard(list1,list2);
       }
       return "error";
    }

    public Boolean isTwoPairs(List<String> sortedList){
        List<Integer> numberList = sortedList.stream().map(x->StringToInt(x)).collect(Collectors.toList());
        Set<Integer> numberSet = new HashSet<>(numberList);
        int size = numberList.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList()).size();

        if (numberSet.size() == 3 && size == 2) return true;
        else return false;
    }
    public String compareIncludeTwoPair(List<String> list1 , List<String> list2){
        if (isTwoPairs(list1)&&!isTwoPairs(list2)) return "Person1Win";
        else if (!isTwoPairs(list1)&&isTwoPairs(list2)) return "Person2Win";
        else if(isTwoPairs(list1)&&isTwoPairs(list2)){
            List<Integer> numberList = list1.stream().map(x->StringToInt(x)).collect(Collectors.toList());
            int list1Number1 = 0;
            int list1Number2 = 0;
            int list2Number1 = 0;
            int list2Number2 = 0;
            list1Number1 = numberList.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() > 1)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList()).get(0);
            list1Number2 = numberList.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() > 1)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList()).get(1);

            List<Integer> numberList2 = list2.stream().map(x->StringToInt(x)).collect(Collectors.toList());
            list2Number1 = numberList2.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() > 1)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList()).get(0);
            list2Number2 = numberList.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() > 1)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList()).get(1);

            if (list1Number2 > list2Number2) return "Person1Win";
            else if (list1Number2 < list2Number2) return "Person2Win";
            else if (list1Number2 == list2Number2){
                if (list1Number1 < list2Number1) return "Person2Win";
                else if (list1Number1 > list2Number1) return "Person1Win";
                else if (list1Number1 == list2Number1) return judgeWhoWinInHighCard(list1,list2);
            }
        }
        return "error";
    }

    public Boolean isTreeKind(List<String> sortedList){
        List<Integer> numberList = sortedList.stream().map(x->StringToInt(x)).collect(Collectors.toList());
        Set<Integer> numberSet = new HashSet<>(numberList);
        int size = numberList.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList()).size();

        if (numberSet.size() == 3 && size == 1) return true;
        else return false;
    }
    public String compareIncludeTreeKind(List<String> list1 , List<String> list2){
        if (isTreeKind(list1)&&!isTreeKind(list2)) return "Person1Win";
        else if (!isTreeKind(list1)&&isTreeKind(list2)) return "Person2Win";
        else if(isTreeKind(list1)&&isTreeKind(list2)){
            List<Integer> numberList = list1.stream().map(x->StringToInt(x)).collect(Collectors.toList());
            int list1Number1 = 0;
            int list2Number1 = 0;

            list1Number1 = numberList.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() > 1)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList()).get(0);

            List<Integer> numberList2 = list2.stream().map(x->StringToInt(x)).collect(Collectors.toList());
            list2Number1 = numberList2.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() > 1)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList()).get(0);

            if (list1Number1 < list2Number1) return "Person2Win";
            else if (list1Number1 > list2Number1) return "Person1Win";
            else if (list1Number1 == list2Number1) return judgeWhoWinInHighCard(list1,list2);

        }
        return "error";
    }

    public int getSizeResult(String s1 , String s2) {
        int s1Size = StringToInt(s1);
        int s2Size = StringToInt(s2);
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
                result = Integer.parseInt(s.substring(0,s.length()-1));//注意10
                break;
        }
        return result;
    }

    public List<String> getSortedList(String s){
        List<String> list = new ArrayList<>();
        String[] array = s.split(" ");
        for (int i = 0 ; i < array.length ; i++ ){
            list.add(array[i]);
        }
        String tmp="";
        for(int i=1;i<list.size()-1;i++){
            for (int j=0;j<list.size()-i;j++){
                if(StringToInt(list.get(j)) > StringToInt(list.get(j+1))){
                    tmp = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,tmp);
                }
            }
        }
        return list;
    }
}
