package test.java;

import main.java.Porter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PorterTest {
    @Test
    public void should_return_1_when_2C_JC(){
        //given
        Porter porter = new Porter();
        //when
        int result1 = porter.getSizeResult("2C","JC");
        int result2 = porter.getSizeResult("AC","JC");
        int result3 = porter.getSizeResult("AC","AS");
        //then
        assertEquals(2,result1);
        assertEquals(1,result2);
        assertEquals(0,result3);
    }
    @Test
    public void should_return_Person2Win_when_2H_3H_5C_6D_8S_5H_6S_8S_9C_10C(){
        //given
        Porter porter = new Porter();
        //when
        String result1 = porter.judgeWhoWin("2H 3H 5C 6D 8S 5H 6S 8S 9C 10C");
        String result2 = porter.judgeWhoWin("2H 3H 5C 6D 8S 2H 3H 5C 6D 8S");
        String result3 = porter.judgeWhoWin("2H 3H 5C JD QS 5H 6S 8S 9C QC");
        //then
        assertEquals("Person2Win",result1);
        assertEquals("Peace",result2);
        assertEquals("Person1Win",result3);
    }

    @Test
    public void should_return_Person2Win_when_2H_2H_5C_6D_8S_5H_6S_8S_9C_10C(){
        //given
        Porter porter = new Porter();
        //when
        String result1 = porter.judgeWhoWin("2H 2H 5C 6D 8S 5H 6S 8S 9C 10C");
        String result2 = porter.judgeWhoWin("2H 3H 5C 6D 8S 5H 5S 8S 9C 10C");
        String result3 = porter.judgeWhoWin("2H 2H 5C 6D QS 5H 5S 8S 9C 10C");
        //then
        assertEquals("Person1Win",result1);
        assertEquals("Person2Win",result2);
        assertEquals("Person2Win",result3);
    }

    @Test
    public void should_return_Person1Win_when_2H_2H_5C_5D_8S_5H_5S_8S_9C_10C(){
        //given
        Porter porter = new Porter();
        //when
        String result1 = porter.judgeWhoWin("2H 2S 5C 5D 8S 5H 5S 8S 9C 10C");
        String result2 = porter.judgeWhoWin("2H 2S 5C 5D 8S 2C 2D 5H 5S 10C");
        String result3 = porter.judgeWhoWin("2H 2S 5C 5D 8S 2C 2D 6H 6S 10C");
        //then
        assertEquals("Person1Win",result1);
        assertEquals("Person2Win",result2);
        assertEquals("Person2Win",result3);
    }

    @Test
    public void should_return_Person1Win_when_2H_2D_2C_5D_8S_5H_5S_8S_9C_10C(){
        //given
        Porter porter = new Porter();
        //when
        String result1 = porter.judgeWhoWin("2H 2S 2C 5D 8S 5H 5S 8S 9C 10C");
        String result2 = porter.judgeWhoWin("2H 2S 2C 5D JS 3C 3D 3H 5S 10C");
        //then
        assertEquals("Person1Win",result1);
        assertEquals("Person2Win",result2);
    }

    @Test
    public void should_return_Person2Win_when_2H_2D_2C_5D_8S_5H_6S_7S_8C_9C(){
        //given
        Porter porter = new Porter();
        //when
        String result1 = porter.judgeWhoWin("2H 2S 2C 5D 8S 5H 6S 7S 8C 9C");
        String result2 = porter.judgeWhoWin("5H 6S 7S 8C 9C 6S 7S 8C 9C 10S");
        //then
        assertEquals("Person2Win",result1);
        assertEquals("Person2Win",result2);
    }

    @Test
    public void should_return_Person1Win_when_2H_3H_4H_10H_JH_8C_9C_10D_JC_QH(){
        //given
        Porter porter = new Porter();
        //when
        String result1 = porter.judgeWhoWin("2H 3H 4H 10H JH 8C 9C 10D JC QH");
        //then
        assertEquals("Person1Win",result1);
    }

    @Test
    public void should_return_Person2Win_when_2H_3H_4H_10H_JH_2C_2S_5D_5C_2D(){
        //given
        Porter porter = new Porter();
        //when
        String result1 = porter.judgeWhoWin("2H 3H 4H 10H JH 2C 2S 5D 5C 2D");
        //then
        assertEquals("Person2Win",result1);
    }
}
