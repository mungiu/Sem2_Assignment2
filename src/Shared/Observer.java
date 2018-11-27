package Shared;

import Server.Observable;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer<Turn> extends Remote
{
	void notify(Observable obs, Turn arg) throws IOException, UnsupportedAudioFileException, LineUnavailableException;
}


