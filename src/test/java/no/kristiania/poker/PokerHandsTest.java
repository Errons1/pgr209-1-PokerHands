package no.kristiania.poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PokerHandsTest {

    @Test
    void highCardSingleTest(){
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = new Card[1];
        Card[] whiteHand = new Card[1];

        blackHand[0] = new Card(Value.THREE, Suit.SPADES);
        whiteHand[0] = new Card(Value.TWO, Suit.SPADES);

        Assertions.assertEquals("Black wins. - with high card: THREE", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void highCardTest(){
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
        };

        Assertions.assertEquals("White wins. - with high card: NINE", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void singleHandHavePairTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
        };

        Assertions.assertEquals("Black wins. - with pair card: TWO", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void bothHandsHavePairTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.NINE, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
        };

        Assertions.assertEquals("White wins. - with pair card: NINE", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void singleHandHasTwoPairTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };

        Assertions.assertEquals("White wins. - with pair card: FOUR and TWO", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void bothHandsHasTwoPairTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.NINE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.HEART),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.NINE, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.HEART),
                new Card(Value.TWO, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };

        Assertions.assertEquals("Black wins. - with two pair card: NINE and TWO", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void singleHandHasTreeOfAKindTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.QUEEN, Suit.HEART),
                new Card(Value.EIGHT, Suit.CLUBS),
                new Card(Value.ACE, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.JACK, Suit.DIAMONDS),
                new Card(Value.TEN, Suit.HEART),
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.TEN, Suit.DIAMONDS),
        };

        Assertions.assertEquals("White wins. - with three of a kind card: TEN", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void bothHandsHasTreeOfAKindTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.KING, Suit.HEART),
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.EIGHT, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.JACK, Suit.DIAMONDS),
                new Card(Value.TEN, Suit.HEART),
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.TEN, Suit.DIAMONDS),
        };

        Assertions.assertEquals("Black wins. - with three of a kind card: KING", pokerHands.checkWinner(blackHand, whiteHand));
    }
    @Test
    void singleHandHasStraightTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.KING, Suit.DIAMONDS),
                new Card(Value.KING, Suit.HEART),
                new Card(Value.KING, Suit.CLUBS),
                new Card(Value.EIGHT, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.EIGHT, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEART),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };

        Assertions.assertEquals("White wins. - with straight: EIGHT", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void bothHandsHasStraightTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.JACK, Suit.SPADES),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.KING, Suit.HEART),
                new Card(Value.QUEEN, Suit.CLUBS),
                new Card(Value.TEN, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.EIGHT, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.HEART),
                new Card(Value.SEVEN, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };

        Assertions.assertEquals("Black wins. - with straight: ACE", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void singleHandHasFlushTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.JACK, Suit.SPADES),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.HEART),
                new Card(Value.QUEEN, Suit.CLUBS),
                new Card(Value.TEN, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.SEVEN, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
        };

        Assertions.assertEquals("White wins. - with flush: EIGHT SPADES", pokerHands.checkWinner(blackHand, whiteHand));
    }

    @Test
    void bothHandsHasFlushTest() {
        PokerHands pokerHands = new PokerHands();

        Card[] blackHand = {
                new Card(Value.JACK, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.TWO, Suit.DIAMONDS),
                new Card(Value.QUEEN, Suit.DIAMONDS),
                new Card(Value.TEN, Suit.DIAMONDS),
        };
        Card[] whiteHand = {
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.TWO, Suit.SPADES),
                new Card(Value.EIGHT, Suit.SPADES),
                new Card(Value.SEVEN, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
        };

        Assertions.assertEquals("Black wins. - with flush: ACE DIAMONDS", pokerHands.checkWinner(blackHand, whiteHand));
    }

}
