package Server;

import Shared.Turn;

import java.util.List;

public class FileWriteAdapter implements GameLogger
{
	private WriteToFile writeToFile;

	public FileWriteAdapter()
	{
		writeToFile = new WriteToFile();
	}

	private void hasWinner(Turn t)
	{
		/*
		 * BOARD LAYOUT NUMBERING (for reference)
		 * 1 2 3
		 * 4 5 6
		 * 7 8 9
		 */


		// Checking if squared are filled up before calling .equals()
		boolean squares147Filled = board[0][0] != null &
				board[1][0] != null &
				board[2][0] != null;

		boolean squares258Filled = board[0][1] != null &
				board[1][1] != null &
				board[2][1] != null;

		boolean squares369Filled = board[0][2] != null &
				board[1][2] != null &
				board[2][2] != null;

		boolean squares123Filled = board[0][0] != null &
				board[0][1] != null &
				board[0][2] != null;

		boolean squares456Filled = board[1][0] != null &
				board[1][1] != null &
				board[1][2] != null;

		boolean squares789Filled = board[2][0] != null &
				board[2][1] != null &
				board[2][2] != null;

		boolean diagonal1Filled = board[0][0] != null &
				board[1][1] != null &
				board[2][2] != null;

		boolean diagonal3Filled = board[2][0] != null &
				board[1][1] != null &
				board[0][2] != null;

		// Using previous boolean flags to avoid
		// calling .equals() on a null object and causing a null pointer exception
		// since once a statement returns false, analysis stop before the next &&
		boolean squares147Win = squares147Filled &&
				board[0][0].equals(t.playerSymbol) &&
				board[1][0].equals(t.playerSymbol) &&
				board[2][0].equals(t.playerSymbol);

		boolean squares258Win = squares258Filled &&
				board[0][1].equals(t.playerSymbol) &&
				board[1][1].equals(t.playerSymbol) &&
				board[2][1].equals(t.playerSymbol);

		boolean squares369Win = squares369Filled &&
				board[0][2].equals(t.playerSymbol) &&
				board[1][2].equals(t.playerSymbol) &&
				board[2][2].equals(t.playerSymbol);

		boolean squares123Win = squares123Filled &&
				board[0][0].equals(t.playerSymbol) &&
				board[0][1].equals(t.playerSymbol) &&
				board[0][2].equals(t.playerSymbol);

		boolean squares456Win = squares456Filled &&
				board[1][0].equals(t.playerSymbol) &&
				board[1][1].equals(t.playerSymbol) &&
				board[1][2].equals(t.playerSymbol);

		boolean squares789Win = squares789Filled &&
				board[2][0].equals(t.playerSymbol) &&
				board[2][1].equals(t.playerSymbol) &&
				board[2][2].equals(t.playerSymbol);

		boolean diagonal1Win = diagonal1Filled &&
				board[0][0].equals(t.playerSymbol) &&
				board[1][1].equals(t.playerSymbol) &&
				board[2][2].equals(t.playerSymbol);

		boolean diagonal3Win = diagonal3Filled &&
				board[2][0].equals(t.playerSymbol) &&
				board[1][1].equals(t.playerSymbol) &&
				board[0][2].equals(t.playerSymbol);

		if (squares123Win || squares147Win || squares258Win || squares369Win || squares456Win || squares789Win
				|| diagonal1Win || diagonal3Win)
		{
			if (t.playerSymbol.equals("X"))
				t.xWon = true;
			else if (t.playerSymbol.equals("O"))
				t.yWon = true;
		}
	}

	@Override
	public void logMove(Turn t, String move)
	{
		board[t.y][t.x] = t.playerSymbol;

		String stringForFile = t.playerSymbol + " occupies: " + move;
		writeToFile.writeLine(stringForFile);

		hasWinner(t);
	}

	@Override
	public List<String> getGameMoves()
	{
		List<String> recoveredLogList = writeToFile.getAllLines();
		return recoveredLogList;
	}
}
