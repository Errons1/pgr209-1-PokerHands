import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandsTest {

    @Test
    void highCardTest(){
        PokerHands pokerHands = new PokerHands();

        HashMap<Cards, Integer> blackHand = new HashMap<>();
        HashMap<Cards, Integer> whiteHand = new HashMap<>();

        blackHand.put(Cards.THREE, 1);

        whiteHand.put(Cards.TWO, 1);

        assertEquals("White", pokerHands.checkWinner(blackHand, whiteHand));
    }
}
