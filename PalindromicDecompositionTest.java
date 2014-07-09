import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromicDecompositionTest {

    @Test
    public void test1() {
        String w = "abacababacadcd";
        PalindromicDecomposition pd = new PalindromicDecomposition(w);
        assertTrue(testDecomposition(w, 4, pd.decomposition(), pd.length()));
    }

    @Test
    public void test2() {
        String w = "abacaba";
        PalindromicDecomposition pd = new PalindromicDecomposition(w);
        assertTrue(testDecomposition(w, 1, pd.decomposition(), pd.length()));
    }

    @Test
    public void test3() {
        String w = "kayakruderer";
        PalindromicDecomposition pd = new PalindromicDecomposition(w);
        assertTrue(testDecomposition(w, 6, pd.decomposition(), pd.length()));
    }

    @Test
    public void test4() {
        String w = "abacaadadadaacabcabcabcabcabcabcabcbacbacbacbabcbabcabcbacbabcbacbabcabc";
        PalindromicDecomposition pd = new PalindromicDecomposition(w);
        assertTrue(testDecomposition(w, 14, pd.decomposition(), pd.length()));
    }

    private boolean testDecomposition(String s, int opt, String decomp, int length) {
        System.out.println();
        if(length == opt) {
            System.out.println("Du hast für diese Instanz die richtige Anzahl an Palindromen bestimmt.");
        } else {
            System.out.println("Die von dir bestimmte Anzahl an Palindromen ist nicht optimal.");
            return false;
        }
        String[] r = decomp.split("\\|");
        if(r.length != length) {
            System.out.println("Deine errechnete Anzahl der Palindrome stimmt nicht mit deiner Zerteilung überein.");
            return false;
        }
        for(String p : r) {
            if(!isPalindrome(p)) {
                System.out.println("Deine Zerteilung ist inkorrekt.");
                return false;
            }
        }
        System.out.println("Deine Zerteilung ist korrekt.");
        return true;
    }

    private boolean isPalindrome(String p) {
        int i1 = 0, i2 = p.length() - 1;
        while (i2 > i1) {
            if (p.charAt(i1) != p.charAt(i2)) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }
}
