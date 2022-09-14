import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandsTest {

    @Test
    void highCardSingleTest(){
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = new Card[1];
        Card[] whiteHand = new Card[1];

        blackHand[0] = new Card(Value.THREE, Suit.ACE);
        whiteHand[0] = new Card(Value.TWO, Suit.ACE);

        assertEquals("Black wins. - with high card: THREE", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void highCardTest(){
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.ACE),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.ACE),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
        };

        assertEquals("White wins. - with high card: NINE", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void oneHandHavePairTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.ACE),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.ACE),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
        };

        assertEquals("Black wins. - with pair card: TWO", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void bothHavePairTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.ACE),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.ACE),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.NINE, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
        };

        assertEquals("White wins. - with pair card: NINE", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void oneHandHasTwoPairTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.ACE),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.ACE),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };

        assertEquals("White wins. - with pair card: FOUR", pokerHands.checkWinner(blackHand, whiteHand));
    }

}
