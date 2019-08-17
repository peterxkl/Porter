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
}
