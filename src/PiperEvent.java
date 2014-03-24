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
        foodWords.add("breakfast");
        foodWords.add("lunch");
        foodWords.add("dinner");
        foodWords.add("snack");
        foodWords.add("refreshments");
        foodWords.add("pie ");
        foodWords.add("cake");
        foodWords.add("ice cream");
        foodWords.add("tea");
        foodWords.add("coffee");
        foodWords.add("chocolate");
        foodWords.add("candy");
        foodWords.add("drinks");
        foodWords.add("pizza");

        placeWords.add("Olin-Rice");
        placeWords.add("Carnegie");
        placeWords.add("campus center");

        timeWords.add("noon");

        //TODO add regex for other forms of punctuation
        String[] sentences = body.split("\\.");

        for ( String foodWord : foodWords ){
            boolean breakFlag = false;
            for ( String sentence : sentences ){
                if ( sentence.contains(foodWord) ){
                    this.hasFood = true;
                    this.description = sentence;
                    breakFlag = true;
                    break;
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