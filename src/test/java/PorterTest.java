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
}
