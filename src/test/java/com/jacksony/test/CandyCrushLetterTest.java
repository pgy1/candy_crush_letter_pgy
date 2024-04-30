package com.jacksony.test;

import org.junit.Test;

/**
 * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
 * characters are identical, remove them from the string. Repeat this process until
 * there is no more than 3 identical characters sitting besides each other.
 * Example:
 * Input: aabcccbbad
 * Output:
 * -> aabbbad
 * -> aaad
 * -> d
 */
public class CandyCrushLetterTest {

    public static void main(String[] args) {
        doTest("aabcccbbad");
    }

    @Test
    public void testWord() {
        doTest("aabcccbbad");
    }

    public static void doTest(String words) {
        System.out.println("Input: " + words);
        System.out.println("Output: ");
        replaceLetter(words);
    }

    public static void replaceLetter(String words) {
        replaceLetter(words, 0);
    }

    public static String replaceLetter(String word, int start) {
        int minLength = 3;
        for (; start < word.length() && start + minLength <= word.length(); ) {
            String subWord = word.substring(start, start + minLength);
            if (isIdenticalCharacters(subWord)) {
                for (int j = start + minLength; j <= word.length(); j++) {
                    subWord = word.substring(start, j);
                    // loop until different character or ended
                    if (!isIdenticalCharacters(subWord) || j == word.length()) {
                        // if current character is the end, process the whole, else process except the latest character
                        boolean justEnd = isIdenticalCharacters(subWord) && j == word.length();
                        word = word.substring(0, start)
                                + word.substring(j - (justEnd ? 0 : 1));

                        System.out.println(word);
                        // word changes, repeat
                        return replaceLetter(word, 0);
                    }
                }
            }
            start++;

        }
        return word;
    }

    /**
     * whether the word is identical
     *
     * @param word
     * @return true identical characters or false
     */
    public static boolean isIdenticalCharacters(String word) {
        if (word == null || word.length() == 0) return false;
        char firstLetter = word.charAt(0);
        for (char l : word.toCharArray()) {
            if (l != firstLetter) return false;
        }
        return true;
    }

}
