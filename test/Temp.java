import java.util.regex.Pattern;

/**
 * Author: Zhang Yu
 * Date: 17年8月23日
 * Email: yu.zhang@7fresh.com
 */
public class Temp {
    public static void main(String[] args) {
        String test = "2a2b2Abc";
        String upperCase = test.replaceAll("[^A-Z]","");
        for (char anUpperCase : upperCase.toCharArray()) {
            test = test.replaceFirst(String.valueOf(anUpperCase), "0" + anUpperCase);
        }
        test = test.replaceAll("[^a-zA-Z0]","1");
        for (String temp : test.split("[^a-zA-Z]")) {
            System.out.println(temp);
        }
    }
}
