import java.util.LinkedList;
import java.util.List;

public class PiperEvent {

    private boolean hasFood;
    private String title;
    private String time;
    private String place;
    private String description;
    private List<String> foodWords = new LinkedList<String>();
    private List<String> placeWords = new LinkedList<String>();
    private List<String> timeWords = new LinkedList<String>();

    public PiperEvent (String title, String body){

        this.title = title;
        //TODO add case sensitivity
        foodWords.add("[Bb]reakfast");
        foodWords.add("[Ll]unch");
        foodWords.add("[Dd]inner");
        foodWords.add("[Ss]nack");
        foodWords.add("[Rr]efreshments");
        foodWords.add("[Pp]ie ");
        foodWords.add("[Cc]ake");
        foodWords.add("ice cream");
        foodWords.add("[Tt]ea");
        foodWords.add("[Cc]offee");
        foodWords.add("[Cc]hocolate");
        foodWords.add("[Cc]andy");
        foodWords.add("[Dd]rinks");
        foodWords.add("[Pp]izza");

        placeWords.add("[Oo]lin-Rice");
        placeWords.add("Carnegie");
        placeWords.add("[Cc]ampus [Cc]enter");

        timeWords.add("noon");
        timeWords.add("[0-9][\\:][0-9][0-9]");
        timeWords.add("[0-9] [pa]\\.m");

        //TODO add regex for other forms of punctuation
        String[] sentences = body.split("\\.");

        for ( String foodWord : foodWords ){
            boolean breakFlag = false;
            for ( String sentence : sentences ){
                String[] words = sentence.split(" ");
                for ( String word : words){
                    if(word.matches(foodWord)){
                    System.out.println(foodWord);
                        this.hasFood = true;
                        this.description = sentence;
                        breakFlag = true;
                        break;
                    }
                }
            }
            if (breakFlag) {
                break;
            }
            this.hasFood = false;
            this.description = null;
            this.time = null;
            this.place = null;
        }

        if ( hasFood ){
            for ( String placeWord : placeWords  ){
                boolean breakFlag = false;
                for ( String sentence : sentences ){
                    if ( sentence.contains(placeWord) ){
                        this.place = sentence;
                        breakFlag = true;
                        break;
                    }
                }
                if (breakFlag){
                    break;
                }
            }

            for (String timeWord : timeWords){
                boolean breakFlag = false;
                for (String sentence : sentences){
                    if (sentence.contains(timeWord)){
                        this.time = sentence;
                        breakFlag = true;
                        break;
                    }
                }
                if (breakFlag){
                    break;
                }
            }
        }
    }

    public boolean getHasFood() { return hasFood; }
    public String getTitle(){
        return title;
    }
    public String getTime(){
        return time;
    }
    public String getPlace(){
        return place;
    }
    public String getDescription(){
        return description;
    }

}