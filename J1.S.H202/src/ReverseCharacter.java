
public class ReverseCharacter {
    public void printReverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        //empty string, no output
        if (str.isEmpty()) {
            System.out.println("");
        } else {
            sb.reverse();
            System.out.println(sb);
        }
    }
}
