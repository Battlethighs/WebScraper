
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JsoupParse {

    private static String extractText(String input) {
        int length = input.length();
        int end = length - 1;
        String output = "";
        boolean copy = false;
        for (int i = 0; i < end; i++) {
            String character = input.substring(i, i + 1);
            if (character.equals("<")) {
                copy = false;
            }
            if (character.equals(">")) {
                copy = true;
            }
            if (copy && !(character.equals(">"))) {
                output += character;
            }
        }
        return output;
    }

    private static String findTitle(String input){
        String toFind = "\n";
        if (input.contains(toFind)){
            int index = input.indexOf(toFind);
            return input.substring(0,index);
        }
        else{
            return null;
        }
    }

    private static String removeLeadingSpace(String input){
        int length = input.length();
        int flag = 0;
        for ( int i = 0; i < length; i++){
            String character = input.substring(i,i+1);
            if (character.matches("[A-Za-z0-9]+")){     //http://stackoverflow.com/questions/12831719/fastest-way-to-check-a-string-is-alphanumeric-in-java
                flag = i;
                break;
            }
        }
        String output = input.substring(flag,length);
        return output;
    }

    private static String removeEndingSpace(String input){
        int length = input.length();
        int flag = 0;
        for ( int i = length-1; i >= 0; i--){
            String character = input.substring(i,i+1);
            if (character.matches("[A-Za-z0-9]+")){     //http://stackoverflow.com/questions/12831719/fastest-way-to-check-a-string-is-alphanumeric-in-java
                flag = i;
                break;
            }
        }
        System.out.println("Length is: " + length + " flag is: "+ flag);
        String output = input.substring(0,flag+1);
        return output;

    }

    public static void main(String[] args) throws Exception {

        String url = "http://webapps.macalester.edu/dailypiper/dailypiper-portal.cfm?expanded=true";
        Document doc = Jsoup.connect(url).get();
        List<String> textList = new LinkedList<String>();

        for (Element element : doc.select("div")) {
            if (element.hasText()) {
                String textElement = element.toString();
                String storyMarker = "class=\"story\"";
                if (textElement.substring(5, 18).equals(storyMarker)) {
                    textList.add(extractText(textElement));
                }
            }
        }

        for (String segment : textList) {
            segment = removeLeadingSpace(segment);
            String title = findTitle(segment);
            if ( title != null){
                title = removeEndingSpace(title);
                System.out.println("-----------------" + "\n" + title);
                //System.out.println("-----------------" + segment);
            }
        }
    }
}
