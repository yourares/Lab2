package pkgPokerBLL;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import pkgPokerEnum.eCardNo;
import pkgPokerEnum.eHandStrength;

public class Hand {

	private UUID HandID;
	private boolean bIsScored;
	private HandScore HS;
	private ArrayList<Card> CardsInHand = new ArrayList<Card>();

	public Hand() {

	}

	public void AddCardToHand(Card c) {
		CardsInHand.add(c);
	}

	public ArrayList<Card> getCardsInHand() {
		return CardsInHand;
	}

	public HandScore getHandScore() {
		return HS;
	}

	public Hand EvaluateHand() {
		Hand h = Hand.EvaluateHand(this);
		return h;
	}

	private static Hand EvaluateHand(Hand h) {

		Collections.sort(h.getCardsInHand());

		// Another way to sort
		// Collections.sort(h.getCardsInHand(), Card.CardRank);

		HandScore hs = new HandScore();
		try {
			Class<?> c = Class.forName("pkgPokerBLL.Hand");

			for (eHandStrength hstr : eHandStrength.values()) {
				Class[] cArg = new Class[2];
				cArg[0] = pkgPokerBLL.Hand.class;
				cArg[1] = pkgPokerBLL.HandScore.class;

				Method meth = c.getMethod(hstr.getEvalMethod(), cArg);
				Object o = meth.invoke(null, new Object[] { h, hs });

				// If o = true, that means the hand evaluated- skip the rest of
				// the evaluations
				if ((Boolean) o) {
					break;
				}
			}

			h.bIsScored = true;
			h.HS = hs;

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return h;
	}

	public static boolean isHandRoyalFlush(Hand h, HandScore hs) {
		boolean isHandRoyalFlush = false;
		ArrayList<Card> kickers = new ArrayList<Card>();

		if ((h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr() == 10)
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() == 14)
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr() == 13)
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr() == 12)
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr() == 11)
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
						.get(eCardNo.SecondCard.getCardNo()).geteSuit())
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteSuit() == h.getCardsInHand()
								.get(eCardNo.ThirdCard.getCardNo()).geteSuit())
				&& ((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == h.getCardsInHand()
								.get(eCardNo.FourthCard.getCardNo()).geteSuit()))
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteSuit() == h.getCardsInHand()
										.get(eCardNo.FifthCard.getCardNo()).geteSuit())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit().getiSuitNbr() == 1)) {

			hs.setHandStrength(eHandStrength.RoyalFlush);
			hs.setKickers(kickers);
		}

		return false;
	}

	public static boolean isHandStraightFlush(Hand h, HandScore hs) {
		boolean isStraightFlush = false;

		ArrayList<Card> kickers = new ArrayList<Card>();

		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() != 14)
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()
						.getiRankNbr() == h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr()
								+ 1)
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()
						.getiRankNbr() == h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()
								.getiRankNbr() + 1)
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank()
						.getiRankNbr() == h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr()
								+ 1)
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() ==
				h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr()
				+ 1)
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() ==
				h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteSuit())
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteSuit() == 
				h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteSuit())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == 
				h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteSuit())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteSuit() == 
				h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteSuit())) {
			isStraightFlush = true;
			hs.setHandStrength(eHandStrength.StraightFlush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setKickers(kickers);

		}
		return false;
	}

	public static boolean isHandFourOfAKind(Hand h, HandScore hs) {

		boolean isHandFourOfAKind = false;

		ArrayList<Card> kickers = new ArrayList<Card>();

		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())) {

			isHandFourOfAKind = true;
			hs.setHandStrength(eHandStrength.FourOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setKickers(kickers);

		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.SecondCard.getCardNo()).geteRank())) {
			isHandFourOfAKind = true;
			hs.setHandStrength(eHandStrength.FourOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		}
		return false;
	}

	public static boolean isHandFlush(Hand h, HandScore hs) {
		boolean isHandFlush = false;
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteSuit())
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteSuit() == h.getCardsInHand()
						.get(eCardNo.ThirdCard.getCardNo()).geteSuit())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteSuit())
						&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteSuit() == h.getCardsInHand()
								.get(eCardNo.FifthCard.getCardNo()).geteSuit())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()
						.getiRankNbr() != h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr()
								+ 1)
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()
						.getiRankNbr() != h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()
								.getiRankNbr() + 1)
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank()
						.getiRankNbr() != h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr()
								+ 1)
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() !=
				h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr()
				+ 1)) {

			hs.setHandStrength(eHandStrength.Flush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		}

		return false;
	}

	// TODO: Implement This Method
	public static boolean isHandStraight(Hand h, HandScore hs) {
		boolean isHandStraight = false;
		ArrayList<Card> kickers = new ArrayList<Card>();

		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() != 14)
				&& (h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr() != 10)
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank()
						.getiRankNbr() == h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank()
								.getiRankNbr() + 1)
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr() == 
				h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr()
								+ 1)
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()
						.getiRankNbr() == h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()
								.getiRankNbr() + 1)
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()
						.getiRankNbr() == h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr()
								+ 1)
				&&(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() != h.getCardsInHand()
						.get(eCardNo.SecondCard.getCardNo()).geteSuit())) {

			hs.setHandStrength(eHandStrength.Straight);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		}

		return false;
	}

	public static boolean isHandThreeOfAKind(Hand h, HandScore hs) {

		boolean isHandThreeOfAKind = false;

		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank())) {
			isHandThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		} else if ((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())) {
			isHandThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		}
		return false;
	}

	public static boolean isHandTwoPair(Hand h, HandScore hs) {
		boolean isHandTwoPair = false;

		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&&(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() != 14)) {
			isHandTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FirstCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())
				&&(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() != 14)) {
			isHandTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		} else if ((h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&&(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() != 14)) {
			isHandTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		}
		return false;
	}

	public static boolean isHandPair(Hand h, HandScore hs) {
		boolean isHandPair = false;

		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		} else if ((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		} else if ((h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteRank())) {
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		}
		return false;
	}

	public static boolean isHandHighCard(Hand h, HandScore hs) {
		boolean isHandHighCard = false;
		ArrayList<Card> kickers = new ArrayList<Card>();

		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() != h
						.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank())

				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() != h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteRank())

				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() != h
						.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank())
				
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() != h
				.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr()+1)
				
				&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr() != 
				h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr()+1)
				
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr() != h
				.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr()+1)
				
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr() != h
				.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr()+1)
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() !=
				h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteSuit())) 
		{
			hs.setHandStrength(eHandStrength.HighCard);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());

			hs.setKickers(kickers);
		}
		return false;
	}

	public static boolean isAcesAndEights(Hand h, HandScore hs) {
		boolean isAcesAndEights = false;

		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() == 14)
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr() == 8)
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()!= 
				h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()!= 
						h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank()))) {
			isAcesAndEights = true;
			hs.setHandStrength(eHandStrength.AcesAndEights);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setKickers(kickers);

		}
		else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() == 14)
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr() == 8)
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()!= 
				h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank())) 
		{
			isAcesAndEights = true;
			hs.setHandStrength(eHandStrength.AcesAndEights);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setKickers(kickers);
		}
		return false;
	}

	public static boolean isHandFullHouse(Hand h, HandScore hs) {

		boolean isFullHouse = false;

		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		}

		return isFullHouse;

	}
}

