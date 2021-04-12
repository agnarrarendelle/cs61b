import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){

        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("a"));

        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("vc"));
        assertFalse(palindrome.isPalindrome("five"));
    }

    @Test
    public void testIsPalindromeOffByOne(){
        CharacterComparator cc = new OffByOne();

        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("n", cc));
        assertTrue(palindrome.isPalindrome("acefdb", cc));
        assertTrue(palindrome.isPalindrome("", cc));

        assertFalse(palindrome.isPalindrome("noon", cc));
        assertFalse(palindrome.isPalindrome("apple", cc));
        assertFalse(palindrome.isPalindrome("cat", cc));
    }
}
