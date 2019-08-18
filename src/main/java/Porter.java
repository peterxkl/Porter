package main.java;

import java.util.*;
import java.util.stream.Collectors;

public class Porter {
    public String judgeWhoWin(String s){
        List<String> list11 = new ArrayList<>();
        List<String> list22 = new ArrayList<>();
        String[] array = s.split(" ");
        for (int i = 0 ; i < array.length ; i++ ){
            if (i < 5) list11.add(array[i]);
            else list22.add(array[i]);
        }
//        String s1 = s.substring(0,15);
//        String s2 = s.substring(15);
        List<String> list1 = getSortedList(list11);
        List<String> list2 = getSortedList(list22);
        if(isFullHouse(list1)||isFullHouse(list2))  return  compareIncludeFullHouse(list1,list2);
        if(isFlush(list1)||isFlush(list2))  return  compareIncludeFlush(list1,list2);
        if(isStraight(list1)||isStraight(list2))  return  compareIncludeStraight(list1,list2);
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

    public Boolean isStraight(List<String> sortedList){
        for (int i = 0 ; i < sortedList.size() - 1 ; i++){
            if (StringToInt(sortedList.get(i))+1 != StringToInt(sortedList.get(i+1)))  return false;
        }
        return true;
    }
    public String compareIncludeStraight(List<String> list1 , List<String> list2){
        if (isStraight(list1)&&!isStraight(list2)) return "Person1Win";
        else if (!isStraight(list1)&&isStraight(list2)) return "Person2Win";
        else if(isStraight(list1)&&isStraight(list2)) return judgeWhoWinInHighCard( list1 , list2);
        return "error";
    }

    public Boolean isFlush(List<String> sortedList){
        String s = sortedList.get(0).substring(sortedList.get(0).length()-1);
        for (int i = 1; i < sortedList.size(); i++){
            if (!sortedList.get(i).substring(sortedList.get(i).length()-1).equals(s)) return false;
        }
        return true;
    }
    public String compareIncludeFlush(List<String> list1 , List<String> list2){
        if (isFlush(list1)&&!isFlush(list2)) return "Person1Win";
        else if (!isFlush(list1)&&isFlush(list2)) return "Person2Win";
        else if(isFlush(list1)&&isFlush(list2)){
            if (StringToInt(list1.get(list1.size()-1)) > StringToInt(list2.get(list2.size()-1))) return "Person1Win";
            else if (StringToInt(list1.get(list1.size()-1)) < StringToInt(list2.get(list2.size()-1))) return "Person2Win";
            else if (StringToInt(list1.get(list1.size()-1)) == StringToInt(list2.get(list2.size()-1))) return "Peace";
        }
        return "error";
    }

    public Boolean isFullHouse(List<String> sortedList){
        List<Integer> numberList = sortedList.stream().map(x->StringToInt(x)).collect(Collectors.toList());
        Set<Integer> numberSet = new HashSet<>(numberList);
        int size = numberList.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList()).size();

        if (numberSet.size() == 2 && size == 2) return true;
        else return false;
    }
    public String compareIncludeFullHouse(List<String> list1 , List<String> list2){
        if (isFullHouse(list1)&&!isFullHouse(list2)) return "Person1Win";
        else if (!isFullHouse(list1)&&isFullHouse(list2)) return "Person2Win";
        else if(isFullHouse(list1)&&isFullHouse(list2)){
            List<Integer> numberList = list1.stream().map(x->StringToInt(x)).collect(Collectors.toList());
            int list1Number1 = 0;
            int list1Number2 = 0;
            int list1NumberIsThree = 0;
            int list2Number1 = 0;
            int list2Number2 = 0;
            int list2NumberIsThree = 0;

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
            int num = 0;
            for (int i = 0 ; i < numberList.size(); i++){
                if (numberList.get(i) == list1Number1) num++;
            }
            list1NumberIsThree = (num == 3) ? list1Number1 : list1Number2;


            List<Integer> numberList2 = list2.stream().map(x->StringToInt(x)).collect(Collectors.toList());
            list2Number1 = numberList2.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() > 1)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList()).get(0);
            list2Number2 = numberList2.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() > 1)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList()).get(0);
            int num2 = 0;
            for (int i = 0 ; i < numberList2.size(); i++){
                if (numberList2.get(i) == list2Number1) num2++;
            }
            list2NumberIsThree = (num2 == 3) ? list2Number1 : list2Number2;

            if (list1NumberIsThree < list2NumberIsThree) return "Person2Win";
            else if (list1NumberIsThree > list2NumberIsThree) return "Person1Win";
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

    public List<String> getSortedList(List<String> list){
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
