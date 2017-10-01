
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class WordList {

    private final ArrayList<String> wordList;
    private final SizedStack<Integer> lastIndexies; //so there are no duplicates when pulling

    public WordList() {

        lastIndexies = new SizedStack<>(15);

        wordList = new ArrayList<>(
                Arrays.asList(
                        "courtship",
                        "overview",
                        "approval",
                        "ordinary",
                        "village",
                        "literacy",
                        "recording",
                        "scandal",
                        "incongruous",
                        "continuous",
                        "ceiling",
                        "democratic",
                        "enthusiasm",
                        "exposure",
                        "surface",
                        "circumstance",
                        "weakness",
                        "helpless",
                        "confront",
                        "guarantee",
                        "pedestrian",
                        "proportion",
                        "sweater",
                        "prestige",
                        "refrigerator",
                        "realism",
                        "eliminate",
                        "allocation",
                        "uncertainty",
                        "birthday")
        );

    }

    public String getRandomWord() {

        int randomIndex = -1;
        Random rand = new Random();

        do {
            randomIndex = rand.nextInt(wordList.size() - 1);
        } while (lastIndexies.search(randomIndex) != -1);

        lastIndexies.push(randomIndex);

        return wordList.get(randomIndex);
    }

    
    private class SizedStack<T> extends Stack<T> {

        private int sizeLimit;
        
        public SizedStack(int sizeLimit) {
            sizeLimit = this.sizeLimit;
        }

        @Override
        public T push(T item) {
            
            if (super.size() > sizeLimit){
                super.remove(0);
            }
            
            return super.push(item);
        }
        
        
        
        
    }
    
}
