package Server;

import Shared.PlayerClient;
import Shared.TTTServer;
import Shared.Turn;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TicTacToeServer implements TTTServer<Turn>
{
	private GameLogger fileWriteAdapter;

	public TicTacToeServer(GameLogger logger) throws RemoteException
	{
		UnicastRemoteObject.exportObject(this, 0);
		fileWriteAdapter = logger;
	}

	@Override
	public void registerPlayer(PlayerClient pc) throws RemoteException
	{
		addObserver(pc);
	}

	/**
	 * Receives the move, logs it and broadcasts it to all observing clients
	 *
	 * @param t
	 * @throws RemoteException
	 */
	@Override
	public void makeMove(Turn t) throws IOException, UnsupportedAudioFileException, LineUnavailableException
	{
		String movesToString = "X: " + t.x + " Y: " + t.y;

		// loggin the move
		fileWriteAdapter.logMove(t, movesToString);

		notifyObservers(t);
	}
}
