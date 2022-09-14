import java.util.ArrayList;
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

//        --- TwoPair ---



//        --- Pair ---
        if (whiteFrequency.containsValue(2) || blackFrequency.containsValue(2)){
            var blackPairs = new ArrayList<Value>();
            var whitePairs = new ArrayList<Value>();

            for (Map.Entry<Value, Integer> freq : blackFrequency.entrySet()) {
                if (freq.getValue() == 2) {
                    blackPairs.add(freq.getKey());
                }
            }
            for (Map.Entry<Value, Integer> freq : whiteFrequency.entrySet()) {
                if (freq.getValue() == 2) {
                    whitePairs.add(freq.getKey());
                }
            }

            blackPairs.sort(null);
            whitePairs.sort(null);

//            Checks two pairs
            if (blackPairs.size() == 2 && whitePairs.size() == 2) {

//                Checks the highest pair
                if (blackPairs.get(1).compareTo(whitePairs.get(1)) > 0) {
                    return "Black wins. - with pair card: " + blackPairs.get(1);
                }else if (blackPairs.get(1).compareTo(whitePairs.get(1)) < 0){
                    return "White wins. - with pair card: " + whitePairs.get(1);

//                If highest pair is tie. checks lower pair.
                }else if (blackPairs.get(0).compareTo(whitePairs.get(0)) > 0) {
                    return "Black wins. - with pair card: " + blackPairs.get(0);
                }else if (blackPairs.get(0).compareTo(whitePairs.get(0)) < 0) {
                    return "White wins. - with pair card: " + whitePairs.get(0);
                }

//            If only one hand has pair, pick winner
            }else if (blackPairs.size() == 2) {
                return "Black wins. - with pair card: " + blackPairs.get(1);
            }else if (whitePairs.size() == 2) {
                return "White wins. - with pair card: " + whitePairs.get(1);
            }

//            Checks single pair
            if (blackPairs.size() == 1 && whitePairs.size() == 1) {
                if (blackPairs.get(0).compareTo(whitePairs.get(0)) > 0) {
                    return "Black wins. - with pair card: " + blackPairs.get(0);
                }else if (blackPairs.get(0).compareTo(whitePairs.get(0)) < 0) {
                    return "White wins. - with pair card: " + whitePairs.get(0);
                }
            }

//            If only one hand has single pair
            if (whiteFrequency.containsValue(2)){
                return "White wins. - with pair card: " + whitePairs.get(0);
            }
            if (blackFrequency.containsValue(2)){
                return "Black wins. - with pair card: " + blackPairs.get(0);
            }

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

        if (blackHigh.value.compareTo(whiteHigh.value) > 0) {
            return "Black wins. - with high card: " + blackHigh.value;
        }
        else if (blackHigh.value.compareTo(whiteHigh.value) < 0){
            return "White wins. - with high card: " + whiteHigh.value;
        }

        return "Tie";
    }


}
