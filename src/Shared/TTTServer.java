package Shared;

import Server.Observable;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.rmi.RemoteException;

public interface TTTServer<Turn> extends Observable<Turn>
{
	void registerPlayer(PlayerClient pc) throws RemoteException;

	void makeMove(Turn turn) throws IOException, UnsupportedAudioFileException, LineUnavailableException;
}
