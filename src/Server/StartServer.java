package Server;

import Shared.TTTServer;
import Shared.Turn;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class StartServer
{
	public static void main(String[] args)
	{
		try
		{
			TTTServer<Turn> s = new TicTacToeServer(new FileWriteAdapter());
			LocateRegistry.createRegistry(1099);
			Naming.rebind("ttts", s);
			System.out.println("Server started...");
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
	}
}
