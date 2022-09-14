import java.util.HashMap;
import java.util.Map;

public class PokerHands {


    public String checkWinner(Card[] blackHand, Card[] whiteHand) {
        Card blackHigh = new Card(Value.TWO, Suit.ACE);
        Card whiteHigh = new Card(Value.TWO, Suit.ACE);

        for (Card card : blackHand){
            if (card.value.compareTo(blackHigh.value) > 0) blackHigh.value = card.value;
        }
        for (Card card : whiteHand){
            if (card.value.compareTo(whiteHigh.value) > 0) whiteHigh.value = card.value;
        }

        if (blackHigh.value.compareTo(whiteHigh.value) > 0) return "Black";
        else return "White";

    }


}
