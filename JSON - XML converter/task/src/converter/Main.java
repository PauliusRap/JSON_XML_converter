package converter;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String toConvert = scanner.nextLine();
        if (toConvert.charAt(0) == '<') {
            System.out.println(toJSON(toConvert));
        } else System.out.println(toXML(toConvert));
    }

    public static String toXML(String toConvert) {
        Matcher matcher = Pattern.compile("\\{ *(\\w+) *: *(\\w+) *\\}").matcher(toConvert);
        if (matcher.matches()) {
            String first = matcher.group(1).substring(1, matcher.group(1).length() - 1);
            String second = matcher.group(2);
            if (second.equals("null")) {
                return "<" + first +"/>";
            } else {
                return "<" + first + ">" + second.substring(1, second.length() - 1) + "</" + first + ">";
            }
        } else return "Check your regex!";
    }

    public static String toJSON(String toConvert) {
        Matcher matcher = Pattern.compile("<(\\w+)>(\\w+)</\\w+>").matcher(toConvert);
        Matcher matcherNull = Pattern.compile("<(\\w+)/>").matcher(toConvert);
        if (matcherNull.matches()) {
            return "{\"" + matcherNull.group(1) + "\": null }";
        } else if (matcher.matches()) {
            return "{\"" + matcher.group(1) + "\":\"" + matcher.group(2) + "\"}";
        } else return "Check your regex!";
    }
}
