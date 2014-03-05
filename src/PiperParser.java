/**
 * Created by polevault25 on 2/19/14.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PiperParser extends StringUtility {

    private static StringUtility s = new StringUtility();

    public String url = "http://webapps.macalester.edu/dailypiper/dailypiper-portal.cfm?expanded=true";

    public static void main(String[] args) throws Exception {
        List<String> finalList = new ArrayList<String>();
        String url = "http://webapps.macalester.edu/dailypiper/dailypiper-portal.cfm?expanded=true";
        Document doc = Jsoup.connect(url).get();
        String fullPage = doc.toString();
        String[] subStringArray = s.stringSegment(fullPage);
        int numStories = 0;

        for (String subString : subStringArray) {
            boolean isStory = s.isStory(subString);
            //System.out.println("-----------------NEW SUBSTRING!-----------------");
            //System.out.println("is this a story? " + isStory);
            if (isStory) {
                finalList.add(subString);
            }
            //System.out.println(subString);
        }

        for (String subString : finalList){
            numStories ++;
            System.out.println("-----------------NEW SUBSTRING!-----------------");
            //System.out.println(subString);
            String words = s.cleanup(subString);
            System.out.println(words);

        }

        //System.out.println("There are " + numStories + " stories!");


    }

}

