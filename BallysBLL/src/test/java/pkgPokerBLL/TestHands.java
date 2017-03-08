package pkgPokerBLL;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class TestHands {

	@Test
	public void TestRoyalFlush(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a Royal Flush
		assertEquals(eHandStrength.RoyalFlush.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'Ace'
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		//	LO hand better be 'Ten'
		assertEquals(eRank.TEN.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		//	Full House has no kickers.
		
	}
	@Test
	public void TestStraightFlush(){
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.NINE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.CLUBS));		
		h.EvaluateHand();
		
			//Hand better be a StraightFlush
			assertEquals(eHandStrength.StraightFlush.getHandStrength(),
					h.getHandScore().getHandStrength().getHandStrength());
			
			
			assertEquals(eRank.KING.getiRankNbr(),
					h.getHandScore().getHiHand().getiRankNbr());
			
	}
	@Test
	public void TestHandStraight(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a Royal Flush
		assertEquals(eHandStrength.Straight.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'Five'
		assertEquals(eRank.TEN.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		//	LO hand better be 'Three'
		assertEquals(eRank.SIX.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		//	Full House has no kickers.
		assertEquals(0,h.getHandScore().getKickers().size());
		
	}
	@Test
	public void TestHandFourOfAKind(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
//		Hand better be a HandFourOfAKind
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
	
		
	
	}
	@Test
	public void TestHandFourOfAKind2(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
//		Hand better be a HandFourOfAKind
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());	
	}
	@Test
	public void TestHandThreeOfAKind() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.SPADES));		
		h.EvaluateHand();
		
		
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
}
	@Test
	public void TestTwoPair() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.SPADES));		
		h.EvaluateHand();
		
		
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.SPADES));		
		h.EvaluateHand();
		
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.TwoPair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		
		assertEquals(eRank.FIVE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());

}
	@Test
	public void TestHandPair(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		//	Hand better be a full house
		assertEquals(eHandStrength.Pair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());	
		//	LO hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		// HI hand better be 'Eight'
		assertEquals(eRank.EIGHT.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	}
	@Test
	public void TestHandPair2(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.NINE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		//	Hand better be a full house
		assertEquals(eHandStrength.Pair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());	
		//	LO hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		// HI hand better be 'Eight'
		assertEquals(eRank.NINE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	}
	@Test
	public void TestHandPair3(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.NINE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		//	Hand better be a full house
		assertEquals(eHandStrength.Pair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());	
		//	LO hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		// HI hand better be 'Eight'
		assertEquals(eRank.NINE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	}
	@Test
	public void TestHandPair4(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.NINE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.SPADES));		
		h.EvaluateHand();
		//	Hand better be a full house
		assertEquals(eHandStrength.Pair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());	
		//	LO hand better be 'TEN'
		assertEquals(eRank.SIX.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		// HI hand better be 'Eight'
		assertEquals(eRank.TEN.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
	}
	@Test
	public void TestHandHighCard() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a HandHighCard
		assertEquals(eHandStrength.HighCard.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'NINE'
		assertEquals(eRank.NINE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());

	}	
	@Test
	public void TestAcesandEights() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.SPADES));		
		h.EvaluateHand();
		assertEquals(eHandStrength.AcesAndEights.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		

		assertEquals(4,h.getHandScore().getKickers().size());
	}		
	@Test
	public void TestAcesandEights2() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.SPADES));		
		h.EvaluateHand();
		assertEquals(eHandStrength.AcesAndEights.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		

		assertEquals(4,h.getHandScore().getKickers().size());
	}
	@Test
	public void TestFullHouse() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a full house
		assertEquals(eHandStrength.FullHouse.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		//	LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		//	Full House has no kickers.
		assertEquals(0,h.getHandScore().getKickers().size());
		
		
		
	}
	@Test
	public void TestFullHouse2() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a full house
		assertEquals(eHandStrength.FullHouse.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		//	LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		
		
		
	}

}
