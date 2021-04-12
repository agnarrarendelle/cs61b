import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    static CharacterComparator offByOne = new OffByOne();

   @Test
    public void testEqualChars(){
       assertTrue(offByOne.equalChars('a', 'b'));
       assertTrue(offByOne.equalChars('b', 'a'));
       assertTrue(offByOne.equalChars('w', 'x'));

       assertFalse(offByOne.equalChars('r', 'r'));
       assertFalse(offByOne.equalChars('a', 'c'));
       assertFalse(offByOne.equalChars('A', 'a'));
       assertFalse(offByOne.equalChars('z', 't'));
   }
}
