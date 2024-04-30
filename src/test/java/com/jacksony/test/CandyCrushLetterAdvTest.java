package com.jacksony.test;

import org.junit.Test;

/**
 * Instead of removing the consecutively identical characters, replace them with a
 * single character that comes before it alphabetically.
 * Example:
 * ccc -> b
 * bbb -> a
 * Input: abcccbad
 * Output:
 * -> abbbad, ccc is replaced by b
 * -> aaad, bbb is replaced by a
 * -> d
 */
public class CandyCrushLetterAdvTest {

    public static void main(String[] args) {
        doTest("abcccbad");
    }

    @Test
    public void testWord() {
        doTest("abcccbad");
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
                        // if replace, get replace character
                        String replaceCharacter = getPreviousLetter(subWord.charAt(0));
                        word = word.substring(0, start)
                                + replaceCharacter
                                + word.substring(j - (justEnd ? 0 : 1));
                        String toReplaceCharacters = subWord.substring(0, subWord.length() - (justEnd ? 0 : 1));
                        String extraInfo = word + ", " + toReplaceCharacters + " is replaced by " + replaceCharacter;

                        if(word.length()>1) {
                            System.out.println(extraInfo);
                        }else{
                            System.out.println(word);
                        }
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
     * @param character
     * @return if 'a' return "" else return the previous letter
     */
    public static String getPreviousLetter(char character) {
        char first = 'a';
        if (character == first) {
            return "";
        }
        return String.valueOf((char) (character - 1));
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
