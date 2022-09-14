import java.util.HashMap;
import java.util.Map;

public class PokerHands {

    public String checkWinner(Card[] blackHand, Card[] whiteHand) {
        HashMap<Value, Integer> blackFrequency = new HashMap<>();
        HashMap<Value, Integer> whiteFrequency = new HashMap<>();

        for (Card card : blackHand) {
            blackFrequency.merge(card.value, 1, Integer::sum);
        }
        for (Card card : whiteHand) {
            whiteFrequency.merge(card.value, 1, Integer::sum);
        }


//        --- Pair ---
        if (whiteFrequency.containsValue(2) && blackFrequency.containsValue(2)){
            Value blackHighPair = Value.TWO;
            Value whiteHighPair = Value.TWO;

            for (Map.Entry<Value, Integer> freq : blackFrequency.entrySet()) {
                if (freq.getValue() == 2 && freq.getKey().compareTo(blackHighPair) > 0) {
                    blackHighPair = freq.getKey();
                }
            }
            for (Map.Entry<Value, Integer> freq : whiteFrequency.entrySet()) {
                if (freq.getValue() == 2 && freq.getKey().compareTo(whiteHighPair) > 0) {
                    whiteHighPair = freq.getKey();
                }
            }

            if (whiteHighPair.compareTo(blackHighPair) > 0){
                return "White";
            } else {
                return "Black";
            }
        }

        if (whiteFrequency.containsValue(2)){
            return "Black";
        }if (blackFrequency.containsValue(2)){
            return "Black";
        }


//        --- HighCard ---
        Card blackHigh = new Card(Value.TWO, Suit.ACE);
        Card whiteHigh = new Card(Value.TWO, Suit.ACE);

        for (Card card : blackHand){
            if (card.value.compareTo(blackHigh.value) > 0) blackHigh.value = card.value;
        }
        for (Card card : whiteHand){
            if (card.value.compareTo(whiteHigh.value) > 0) whiteHigh.value = card.value;
        }

        if (blackHigh.value.compareTo(whiteHigh.value) > 0) return "Black";
        else if (blackHigh.value.compareTo(whiteHigh.value) < 0)return "White";

        return "Tie";
    }


}
