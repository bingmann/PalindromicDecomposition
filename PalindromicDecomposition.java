/**
 * Diese Klasse erhält als Eingabe einen String und soll die maximale Anzahl an
 * Palindromen bestimmen, in die der String zerlegt werden kann ist. Es soll
 * sowohl die Anzahl als auch die Zerlegung des Strings bestimmt werden.
 *
 * Sie müssen die drei gegebenen Methodenrümpfe implementieren.
 */
class PalindromicDecomposition
{
    /**
     * Führen Sie hier im Konstruktor die nötige Berechnung durch.
     * @param input Der Eingabestring, für den die Zerlegung bestimmt werden soll.
     * Sie können annehmen, dass das Zeichen '|' im String nicht vorkommt.
     */
    private int plen;
    private String decomp;

    private final boolean debug = false;

    PalindromicDecomposition(String input) {

        int N = input.length();
        String w = "?" + input; // prefix input with sentinel

        // create table L[i][j] = true if s[i..j] is palindrom
        boolean[][] L = new boolean[N + 1][N + 1];

        // loop over string size
        for (int j = 1; j <= N; ++j) {
            L[j][j - 1] = true;
            L[j][j] = true;

            // loop over palindrom lengths
            for (int i = j - 1; i >= 1; --i) {
                L[i][j] = (w.charAt(i) == w.charAt(j) && L[i + 1][j - 1]);
            }
        }

        // print table
        if (debug) {
            System.out.println();
            for (int j = 1; j <= N; ++j) {
                System.out.print(w.charAt(j) + " ");
            }
            System.out.println("");

            for (int i = 1; i <= N; ++i) {
                System.out.print(w.charAt(i) + " ");

                for (int j = 1; j <= N; ++j) {
                    System.out.print(L[i][j] ? "x " : "  ");
                }
                System.out.println();
            }
        }

        // create table M[i] = minimum number of palindroms in s[1..i]
        // and p with predecessor index of minimum
        int[] M = new int[N + 1];
        int[] p = new int[N + 1];

        M[0] = 0; // sentinels for empty word
        p[0] = 0;

        for (int j = 1; j <= N; ++j) {
            M[j] = N; // initialize minimum
            for (int i = 1; i <= j; ++i) {
                if (!L[i][j])
                    continue;

                if (M[j] > M[i - 1] + 1) {
                    M[j] = M[i - 1] + 1;
                    p[j] = i - 1;
                }
            }
        }
        plen = M[N];

        if (debug) {
            for (int i = 0; i < N; ++i) {
                System.out.print(M[i] + " ");
            }
            System.out.println();
        }

        for (int i = N; i != 0; i = p[i]) {
            decomp = w.substring(p[i] + 1, i + 1)
                + (decomp == null ? "" : "|" + decomp);
        }
    }

    /**
     * Die von Ihnen berechnete Anzahl an Palindromen
     */
    public int length() {
        return plen;
    }

    /**
     * Die von Ihnen bestimmte Zerlegung. Diese wird dargestellt durch die
     * Zeichen des Eingabestrings sowie durch eingefügte Pipes: '|'. So wird zum
     * Beispiel eine korrekte Lösung für den String "abac" durch "aba|c"
     * dargestellt.
     */
    public String decomposition() {
        return decomp;
    }

    public static void main(String[] args) {
        PalindromicDecomposition pd = new PalindromicDecomposition(args[0]);
        System.out.println("p(w) = " + pd.length());
        System.out.println("Decomposition: " + pd.decomposition());
    }
}
