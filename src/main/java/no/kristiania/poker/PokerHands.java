package no.kristiania.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PokerHands {

    public String checkWinner(Card[] blackHand, Card[] whiteHand) {
        HashMap<Value, Integer> blackValueFrequency = new HashMap<>();
        HashMap<Value, Integer> whiteValueFrequency = new HashMap<>();

        HashMap<Suit, Integer> blackSuitFrequency = new HashMap<>();
        HashMap<Suit, Integer> whiteSuitFrequency = new HashMap<>();

        for (Card card : blackHand) {
            blackValueFrequency.merge(card.value, 1, Integer::sum);
            blackSuitFrequency.merge(card.suit, 1, Integer::sum);
        }
        for (Card card : whiteHand) {
            whiteValueFrequency.merge(card.value, 1, Integer::sum);
            whiteSuitFrequency.merge(card.suit, 1, Integer::sum);
        }



//      -- Flush --
        if (blackSuitFrequency.containsValue(5) || whiteSuitFrequency.containsValue(5)){
            ArrayList<Value> blackValues = new ArrayList<>();
            ArrayList<Value> whiteValues = new ArrayList<>();

            for (Map.Entry<Value, Integer> freq : blackValueFrequency.entrySet()) {
                blackValues.add(freq.getKey());
            }
            for (Map.Entry<Value, Integer> freq : whiteValueFrequency.entrySet()) {
                whiteValues.add(freq.getKey());
            }

            blackValues.sort(null);
            whiteValues.sort(null);

            if (blackSuitFrequency.containsValue(5) && !whiteSuitFrequency.containsValue(5)){
                return "Black wins. - with flush: " + blackValues.get(blackValues.size() -1)
                        + " " + blackSuitFrequency.keySet().toArray()[0];
            }
            if (!blackSuitFrequency.containsValue(5) && whiteSuitFrequency.containsValue(5)) {
                return "White wins. - with flush: " + whiteValues.get(whiteValues.size() -1)
                        + " " + whiteSuitFrequency.keySet().toArray()[0];
            }

            if (blackValues.get(blackValues.size() -1).compareTo(whiteValues.get(whiteValues.size() -1)) > 0){
                return "Black wins. - with flush: " + blackValues.get(blackValues.size() -1)
                        + " " + blackSuitFrequency.keySet().toArray()[0];
            }
            if (blackValues.get(blackValues.size() -1).compareTo(whiteValues.get(whiteValues.size() -1)) < 0){
                return "White wins. - with flush: " + whiteValues.get(whiteValues.size() -1)
                        + " " + whiteSuitFrequency.keySet().toArray()[0];
            }
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
            if (blackValueFrequency.containsKey(value)) {
                blackStraight++;
                if (blackStraight == 5) {
                    blackStraightHigh =  value;
                }
            } else {
                blackStraight = 0;
            }

            if (whiteValueFrequency.containsKey(value)) {
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
            }
            if (blackStraightHigh.compareTo(whiteStraightHigh) < 0) {
                return "White wins. - with straight: " + whiteStraightHigh;
            }

            if (!blackStraightHigh.equals(Value.TWO)) {
                return "Black wins. - with straight: " + blackStraightHigh;
            } else {
                return "White wins. - with straight: " + whiteStraightHigh;
            }

        }



//        --- Three of a kind ---
        if (whiteValueFrequency.containsValue(3)||blackValueFrequency.containsValue(3)){
            Value blackThree = Value.TWO;
            Value whiteThree = Value.TWO;

            for (Map.Entry<Value, Integer> freq : blackValueFrequency.entrySet()) {
                if (freq.getValue() == 3 && freq.getKey().compareTo(blackThree) > 0){
                    blackThree = freq.getKey();
                }
            }

            for (Map.Entry<Value, Integer> freq : whiteValueFrequency.entrySet()) {
                if (freq.getValue() == 3 && freq.getKey().compareTo(whiteThree) > 0){
                    whiteThree = freq.getKey();
                }
            }

            if (blackValueFrequency.containsValue(3) && whiteValueFrequency.containsValue(3)){
                if (blackThree.compareTo(whiteThree) > 0){
                    return "Black wins. - with three of a kind card: " + blackThree;
                }
                if (whiteThree.compareTo(blackThree) > 0) {
                    return "White wins. - with three of a kind card: " + whiteThree;
                }
            }
            if(blackValueFrequency.containsValue(3)){
                return "Black wins. - with three of a kind card: " + blackThree;
            }
            if(whiteValueFrequency.containsValue(3)){
                return "White wins. - with three of a kind card: " + whiteThree;
            }
        }



//        --- Pair ---
        if (whiteValueFrequency.containsValue(2) || blackValueFrequency.containsValue(2)){
            var blackPairs = new ArrayList<Value>();
            var whitePairs = new ArrayList<Value>();

            for (Map.Entry<Value, Integer> freq : blackValueFrequency.entrySet()) {
                if (freq.getValue() == 2) {
                    blackPairs.add(freq.getKey());
                }
            }
            for (Map.Entry<Value, Integer> freq : whiteValueFrequency.entrySet()) {
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
                }
                if (blackPairs.get(1).compareTo(whitePairs.get(1)) < 0){
                    return "White wins. - with two pair card: " + whitePairs.get(1) + " and " + whitePairs.get(0);

//                If highest pair is tie. checks lower pair.
                }
                if (blackPairs.get(0).compareTo(whitePairs.get(0)) > 0) {
                    return "Black wins. - with two pair card: " + blackPairs.get(1) + " and " + blackPairs.get(0);
                }
                if (blackPairs.get(0).compareTo(whitePairs.get(0)) < 0) {
                    return "White wins. - with two pair card: " + whitePairs.get(1) + " and " + whitePairs.get(0);
                }

//            If only one hand has pair, pick winner
            }
            if (blackPairs.size() == 2) {
                return "Black wins. - with pair card: " + blackPairs.get(1) + " and " + blackPairs.get(0);
            }
            if (whitePairs.size() == 2) {
                return "White wins. - with pair card: " + whitePairs.get(1) + " and " + whitePairs.get(0);
            }

//            Checks single pair
            if (blackPairs.size() == 1 && whitePairs.size() == 1) {
                if (blackPairs.get(0).compareTo(whitePairs.get(0)) > 0) {
                    return "Black wins. - with pair card: " + blackPairs.get(0);
                }
                if (blackPairs.get(0).compareTo(whitePairs.get(0)) < 0) {
                    return "White wins. - with pair card: " + whitePairs.get(0);
                }
            }

//            If only one hand has single pair
            if (whiteValueFrequency.containsValue(2)){
                return "White wins. - with pair card: " + whitePairs.get(0);
            }
            if (blackValueFrequency.containsValue(2)){
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
        if (blackHigh.value.compareTo(whiteHigh.value) < 0){
            return "White wins. - with high card: " + whiteHigh.value;
        }

        return "Tie";
    }

}
