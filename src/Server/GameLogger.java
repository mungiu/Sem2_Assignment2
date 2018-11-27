package Server;

import Shared.Turn;

import java.util.List;

public interface GameLogger
{
	public String[][] board = new String[3][3];

	void logMove(Turn t, String move);

	List<String> getGameMoves();
}
