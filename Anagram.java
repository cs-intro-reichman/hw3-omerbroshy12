import java.util.Random;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) 
	{
		String temp1 = preProcess(str1);
        String temp2 = preProcess(str2);

        if (temp1.length() != temp2.length()) 
		{
            return false;
        }
        for (int i = 0; i < temp1.length(); i++)
		{
            char letter = temp1.charAt(i);
            int matchIndex = temp2.indexOf(letter);
            
            if (matchIndex == -1) 
			{
                return false;
            }
                     
            String part1 = temp2.substring(0, matchIndex);
            String part2 = temp2.substring(matchIndex + 1);
            temp2 = part1 + part2;
        }
        return temp2.length() == 0;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) 
	{
		String result = "";
		for (int i = 0; i < str.length(); i++)
		{
			char l = str.charAt(i);
			if (Character.isLetter(l))
			{
				l = Character.toLowerCase(l);
				result += l;
			}
		}
		return result;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str)
    {
		String result = "";
        String temp = str;
        
        while (temp.length() > 0)
	    {
            int currentLength = temp.length();
            int rndI = (int) (Math.random() * currentLength);
            char rndLetter = temp.charAt(rndI);
            result += rndLetter;  
            String part1 = temp.substring(0, rndI);  
            String part2 = temp.substring(rndI + 1); 
            temp = part1 + part2;
        }
        
        return result;
	}
}
