package pkgPokerBLL;

import java.util.ArrayList;
import java.util.UUID;

public class Table {

	private UUID TableID;
	private ArrayList<Player> TablePlayers = new ArrayList<Player>();
	
	public Table() {
		super();
		TableID = UUID.randomUUID();
	}
	
	public Table AddPlayerToTable(Player p)
	{
		//Finish: Add a player to the table (should be pretty easy)
		TablePlayers.add(p);
		return this;
	}
}
