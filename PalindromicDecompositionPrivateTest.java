import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.JUnitCore;

public class PalindromicDecompositionPrivateTest {

    @Test
    public void test1() {
        System.out.println("----------------------------------------");
        System.out.println("1. Instanz");
        String w = generateRandomString(new char[]{'a','b','c','d'}, 3000, 5);
        PalindromicDecomposition pd = new PalindromicDecomposition(w);
        assertTrue(testDecomposition(w, 1302, pd.decomposition(), pd.length(), 500));
        System.out.println("----------------------------------------");
    }

    @Test
    public void test2() {
        System.out.println("----------------------------------------");
        System.out.println("2. Instanz");
        String w = generateRandomString(new char[]{'a', 'a', 'a', 'b', 'b','c'}, 6000, 42);
        PalindromicDecomposition pd = new PalindromicDecomposition(w);
        assertTrue(testDecomposition(w, 1657, pd.decomposition(), pd.length(), 3000));
        System.out.println("----------------------------------------");
    }

    @Test
    public void test3() {
        System.out.println("----------------------------------------");
        System.out.println("3. Instanz");
        String w = generateRandomString(new char[]{'a', 'a', 'a', 'a', 'a', 'b'}, 10000, 23);
        PalindromicDecomposition pd = new PalindromicDecomposition(w);
        assertTrue(testDecomposition(w, 859, pd.decomposition(), pd.length(), 10000));
        System.out.println("----------------------------------------");
    }

    private boolean testDecomposition(String s, int opt, String decomp, int length, int msTimeout) {
        // Zeitmessung (Minimum von drei Durchläufen, alle Methoden einmal aufrufen)
        int foo = 0;
        long bestElapsed = Integer.MAX_VALUE;
        for(int i = 1; i <= 3; ++i) {
            long start = System.nanoTime();
            PalindromicDecomposition pd = new PalindromicDecomposition(s);
            foo += pd.decomposition().length();
            foo += pd.length();
            long msElapsed = (System.nanoTime() - start) / 1000 / 1000;
            System.out.println("Im " + i + ". Durchlauf: " + msElapsed + "ms.");
            if(msElapsed < bestElapsed) bestElapsed = msElapsed;
        }

        if(foo == 1) System.out.print(" ");

        if(bestElapsed < msTimeout) {
            System.out.println("Du hast die Zeitvorgabe von " + msTimeout + "ms eingehalten.");
        } else {
            System.out.println("Du hast die Zeitvorgabe von " + msTimeout + "ms nicht eingehalten.");
            return false;
        }

        if(length == opt) {
            System.out.println("Du hast für diese Instanz die richtige Anzahl an Palindromen bestimmt. Dafür erhälst du einen Punkt.");
        } else {
            System.out.println("Die von dir bestimmte Anzahl an Palindromen ist nicht optimal.");
            return false;
        }
        if(!s.equals(decomp.replaceAll("\\|", ""))) {
            System.out.println("Deine Zerteilung zerteilt nicht den ursprünglichen String.");
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
        System.out.println("Deine Zerteilung ist korrekt. Du erhälst einen weiteren Punkt.");
        return true;
    }

    private boolean isPalindrome(String p) {
        if(p.length() == 1) return true;
        int i1 = 0;
        int i2 = p.length() - 1;
        while (i2 > i1) {
            if (p.charAt(i1) != p.charAt(i2)) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }

    private String generateRandomString(char[] base, int length, long seed) {
        Random rand = new Random(seed);
        StringBuilder builder = new StringBuilder(length);
        for(int i = 0; i < length; ++i)
            builder.append(base[rand.nextInt(base.length)]);
        return builder.toString();
    }

    public static void main(String[] args) throws Exception {
        // mit JUnit ausfuehren:
        JUnitCore.main(PalindromicDecompositionPrivateTest.class.getName());
    }
}
