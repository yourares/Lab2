package pkgPokerBLL;

import java.util.ArrayList;
import java.util.UUID;

import pkgPokerEnum.eRank;

public class Deck {

	private UUID DeckID;
	private ArrayList<Card> DeckCards = new ArrayList<Card>();
	
	public Deck()
	{
		//Finish: Implement This Constructor (no-arg Deck should build up a deck with 52 cards)
		this.DeckID = UUID.randomUUID();
		//This method will do a for/each, returning each rank in the enum.
		for (eRank Rank : eRank.values()) {
			System.out.println(Rank.getiRankNbr());
		}
	}
	
	public Card DrawCard()
	{
		
		return DeckCards.remove(0);
	}
}
