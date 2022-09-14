import java.util.HashMap;
import java.util.Map;

public class PokerHands {
    public String checkWinner(HashMap<Cards, Integer> blackHand, HashMap<Cards, Integer> whiteHand) {

        Cards blackHigh = Cards.TWO;
        Cards whiteHigh = Cards.TWO;

        if (blackHand.size() == 5 && whiteHand.size() == 5) {
            for (Map.Entry<Cards, Integer> card : blackHand.entrySet()){
                if (card.getKey().compareTo(blackHigh) > 0) blackHigh = card.getKey();
            }
            for (Map.Entry<Cards, Integer> card : whiteHand.entrySet()){
                if (card.getKey().compareTo(whiteHigh) > 0) whiteHigh = card.getKey();
            }
        }

        if (blackHigh.compareTo(whiteHigh) > 0) return "Black";
        else return "White";

    }


}
