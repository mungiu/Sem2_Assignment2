package Client;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Controller
{
	private Player player;
	private View view;
	private Model model;

	public Controller(View view, Model model)
	{
		this.view = view;
		this.model = model;
		try
		{
			player = new Player(this);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		catch (NotBoundException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Used when this client requests
	 *
	 * @param x
	 * @param y
	 */
	public void attemptPutPiece(int x, int y)
	{
		player.makeMove(model.player, x, y);
	}

	/**
	 * Used when server requests
	 *
	 * @param player
	 * @param x
	 * @param y
	 */
	public void setPiece(String player, int x, int y)
	{
		// executing changes inside model
		model.board[y][x] = player;
		// executing changes inside controller
		view.setBoard(player, x, y);
//		hasWinner();
	}

	public void setPlayer(String player)
	{
		model.player = player;

	}

	public void showWinner(String playerSymbol) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		view.showWinnerWindow(playerSymbol);
	}
}
