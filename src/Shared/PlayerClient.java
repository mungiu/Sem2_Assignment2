package Shared;

import java.rmi.RemoteException;

public interface PlayerClient extends Observer<Turn>
{
	void startGame(String player) throws RemoteException;
}
