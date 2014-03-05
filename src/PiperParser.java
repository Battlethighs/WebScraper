/**
 * Created by polevault25 on 2/19/14.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PiperParser{

    private static StringUtility s = new StringUtility();

    public String url = "http://webapps.macalester.edu/dailypiper/dailypiper-portal.cfm?expanded=true";

    private static List<PiperEvent> allPiperEvents = new ArrayList<PiperEvent>();

    public static void main(String[] args) throws Exception {
        List<String> eventList = new ArrayList<String>();
        String url = "http://webapps.macalester.edu/dailypiper/dailypiper-portal.cfm?expanded=true";
        Document doc = Jsoup.connect(url).get();
        String fullPage = doc.toString();
        String[] possibleEventArray = s.stringSegment(fullPage);
        int numStories = 0;

        for (String possibleEvent : possibleEventArray) {
            boolean isStory = s.isStory(possibleEvent);
            if (isStory) {
                eventList.add(possibleEvent);
            }
        }

        for (String subString : eventList){

//            String title = s.titleFinder(subString);
//
//            String body = s.bodyFinder(subString);
            System.out.println("-----------------NEW SUBSTRING!-----------------");
            String words = s.cleanup(subString);

            System.out.println(words);

//            PiperEvent event = new PiperEvent(title,body);
//            allPiperEvents.add(event);

        }

//        for ( PiperEvent event : allPiperEvents ){
//
//            String title = event.getTitle();
//            String body = event.getBody();
//
//            System.out.println("TITLE: " + title);
//            System.out.println("BODY: " + body);
//        }
//
//        System.out.println("There are " + numStories + " stories!");


    }

}

