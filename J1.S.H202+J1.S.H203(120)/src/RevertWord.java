
import java.util.*;

public class RevertWord {
    public String input(){
        Scanner sc = new Scanner(System.in);
        String rs = sc.nextLine();
        return rs;
    }

    // reverse in a sentence
    public String revertInsentence(String str) {
        // initialize material variable for the function
        StringTokenizer st = new StringTokenizer(str);
        StringBuilder result = new StringBuilder();
        ArrayList arr = new ArrayList<>();
        // add each word in the sentence to the arraylist
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            arr.add(word);
        }
        // reverse each word in the arraylist
        for (int i = arr.size() - 1; i >= 0; i--) {
            result.append(arr.get(i));
            result.append(" ");
        }
        return result.toString().trim();
    }

    // reverse each part split by "."
    public void printReverse(String str) {
        //check input is empty or not
        while(str.trim().length() == 0){
            System.out.println("empty string, enter new string:");
            str = input();
        }
        //check if sentance is one sentance or not
        boolean hasDot = false;
        // initialize material variable for the function
        ArrayList<String> wordList = new ArrayList<String>();
        //check if contain "."
        if (str.contains(".")) {
            hasDot = true;
        }
        //split by to sentence
        StringTokenizer st = new StringTokenizer(str, ".");
        StringBuilder sb = new StringBuilder();
        // add each word in the sentence to the arraylist
        while (st.hasMoreTokens()) {
            wordList.add(st.nextToken());
        }

        //revert sentence
        for (int i = wordList.size() - 1; i >= 0; i--) {
            //revert each word in sentence
            String word = revertInsentence(wordList.get(i));
            //convert to uppercase, convert character at index 0 + remain string
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            //add each sentence to reduce new string
            sb.append(word);
            //if it in the end of sentence or the text has one sentance
            if(hasDot && i!=0){
                sb.append(". ");
            }
        }
        System.out.println(sb.toString());
    }

}
