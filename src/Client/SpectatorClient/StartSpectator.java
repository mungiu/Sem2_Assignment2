package Client.SpectatorClient;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StartSpectator
{
	public static void main(String[] args)
	{
		try
		{
			Spectator spectator = new Spectator();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (NotBoundException e)
		{
			e.printStackTrace();
		}
	}
}
