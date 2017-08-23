import java.util.regex.Pattern;

/**
 * Author: Zhang Yu
 * Date: 17年8月23日
 * Email: yu.zhang@7fresh.com
 */
public class Temp {
    public static void main(String[] args) {
        String test = "la2jsdf;lajs d1fa;sl@df!ja;sdfj;AsDjf";
        String upperCase = test.replaceAll("[^A-Z]","");
        for (char anUpperCase : upperCase.toCharArray()) {
            test = test.replaceFirst(String.valueOf(anUpperCase), " " + anUpperCase);
        }
        for (String temp : test.split("[^a-zA-Z]")) {
            System.out.println(temp);
        }
    }
}
