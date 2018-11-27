package Server;

import Shared.Observer;
import Shared.PlayerClient;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface Observable<Turn> extends Remote
{
	List<Observer> observers = new ArrayList<>();

	/**
	 * Ads an observer
	 *
	 * @param obs
	 * @throws RemoteException
	 */
	default void addObserver(Observer<Turn> obs) throws RemoteException
	{
		if (obs == null) throw new NullPointerException();
		if (!observers.contains(obs))
			observers.add(obs);

		if (observers.size() == 2)
		{
			int count = 0;
			for (Observer o : observers)
			{
				if (count == 0)
					((PlayerClient) o).startGame("X");
				else
					((PlayerClient) o).startGame("O");

				count++;
			}
		}
	}

	default void deleteObserver(Observer<Turn> obs) throws RemoteException
	{
		observers.remove(obs);
	}

	default void notifyObservers(Turn arg) throws IOException, UnsupportedAudioFileException, LineUnavailableException
	{
		for (Observer observer : observers)
		{
			observer.notify(this, arg);
		}
	}
}


