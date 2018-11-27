package Client;

import Server.Observable;
import Shared.PlayerClient;
import Shared.TTTServer;
import Shared.Turn;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Player implements PlayerClient
{
	private Controller controller;
	private TTTServer tttServer;

	public Player(Controller controller) throws RemoteException, MalformedURLException, NotBoundException
	{
		this.controller = controller;

		UnicastRemoteObject.exportObject(this, 0);
		tttServer = (TTTServer) Naming.lookup("rmi://localhost:1099/ttts");
		// adding the player as an observer immediately upon creation
		tttServer.addObserver(this);

	}

	/**
	 * When players wants to maek a move and send the request to the server
	 *
	 * @param playerSymbol
	 * @param x
	 * @param y
	 */
	public void makeMove(String playerSymbol, int x, int y)
	{
		Turn currentTurn = new Turn();
		currentTurn.playerSymbol = playerSymbol;
		currentTurn.y = y;
		currentTurn.x = x;

		try
		{
			tttServer.makeMove(currentTurn);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Receives an X or O from servers that defines playerSymbol time
	 *
	 * @param player
	 */
	@Override
	public void startGame(String player)
	{
		controller.setPlayer(player);
	}

	@Override
	public void notify(Observable obs, Turn arg) throws IOException, UnsupportedAudioFileException, LineUnavailableException
	{
		controller.setPiece(arg.playerSymbol, arg.x, arg.y);
		if (arg.yWon || arg.xWon)
			controller.showWinner(arg.playerSymbol);
	}
}
