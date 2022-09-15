package no.kristinia.poker;

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



//        --- Straight ---
        int blackStraight = 0;
        int whiteStraight = 0;
        Value blackStraightHigh = Value.TWO;
        Value whiteStraightHigh = Value.TWO;

        /*
        *   if counter gets to five it is a straight.
        *   saves the highest value of straight and compare to the other hand
        * */
        for (Value value : Value.values()) {
            if (blackFrequency.containsKey(value)) {
                blackStraight++;
                if (blackStraight == 5) {
                    blackStraightHigh =  value;
                }
            } else {
                blackStraight = 0;
            }

            if (whiteFrequency.containsKey(value)) {
                whiteStraight++;
                if (whiteStraight == 5) {
                    whiteStraightHigh =  value;
                }
            } else {
                whiteStraight = 0;
            }
        }

        if (!blackStraightHigh.equals(Value.TWO) || !whiteStraightHigh.equals(Value.TWO)) {
            if (blackStraightHigh.compareTo(whiteStraightHigh) > 0) {
                return "Black wins. - with straight: " + blackStraightHigh;
            } else if (blackStraightHigh.compareTo(whiteStraightHigh) < 0) {
                return "White wins. - with straight: " + whiteStraightHigh;
            }

            if (!blackStraightHigh.equals(Value.TWO)) {
                return "Black wins. - with straight: " + blackStraightHigh;

            } else {
                return "White wins. - with straight: " + whiteStraightHigh;
            }
        }



//        --- Three of a kind ---
        if (whiteFrequency.containsValue(3)||blackFrequency.containsValue(3)){
            Value blackThree = Value.TWO;
            Value whiteThree = Value.TWO;

            for (Map.Entry<Value, Integer> freq : blackFrequency.entrySet()) {
                if (freq.getValue() == 3 && freq.getKey().compareTo(blackThree) > 0){
                    blackThree = freq.getKey();
                }
            }

            for (Map.Entry<Value, Integer> freq : whiteFrequency.entrySet()) {
                if (freq.getValue() == 3 && freq.getKey().compareTo(whiteThree) > 0){
                    whiteThree = freq.getKey();
                }
            }

            if (blackFrequency.containsValue(3) && whiteFrequency.containsValue(3)){
                if (blackThree.compareTo(whiteThree) > 0){
                    return "Black wins. - with three of a kind card: " + blackThree;
                } else if (whiteThree.compareTo(blackThree) > 0) {
                    return "White wins. - with three of a kind card: " + whiteThree;
                }
            }
            else if(blackFrequency.containsValue(3)){
                return "Black wins. - with three of a kind card: " + blackThree;
            }
            else if(whiteFrequency.containsValue(3)){
                return "White wins. - with three of a kind card: " + whiteThree;
            }
        }



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
                    return "Black wins. - with two pair card: " + blackPairs.get(1) + " and " + blackPairs.get(0);
                }else if (blackPairs.get(1).compareTo(whitePairs.get(1)) < 0){
                    return "White wins. - with two pair card: " + whitePairs.get(1) + " and " + whitePairs.get(0);

//                If highest pair is tie. checks lower pair.
                }else if (blackPairs.get(0).compareTo(whitePairs.get(0)) > 0) {
                    return "Black wins. - with two pair card: " + blackPairs.get(1) + " and " + blackPairs.get(0);
                }else if (blackPairs.get(0).compareTo(whitePairs.get(0)) < 0) {
                    return "White wins. - with two pair card: " + whitePairs.get(1) + " and " + whitePairs.get(0);
                }

//            If only one hand has pair, pick winner
            }else if (blackPairs.size() == 2) {
                return "Black wins. - with pair card: " + blackPairs.get(1) + " and " + blackPairs.get(0);
            }else if (whitePairs.size() == 2) {
                return "White wins. - with pair card: " + whitePairs.get(1) + " and " + whitePairs.get(0);
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
        Card blackHigh = new Card(Value.TWO, Suit.SPADES);
        Card whiteHigh = new Card(Value.TWO, Suit.SPADES);

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
