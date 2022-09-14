import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandsTest {

    @Test
    void highCardTest(){
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = new Card[1];
        Card[] whiteHand = new Card[1];

        blackHand[0] = new Card(Value.THREE, Suit.ACE);
        whiteHand[0] = new Card(Value.TWO, Suit.ACE);

        assertEquals("Black", pokerHands.checkWinner(blackHand, whiteHand));
    }
}
