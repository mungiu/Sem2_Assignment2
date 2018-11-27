package Client.SpectatorClient;

import Server.Observable;
import Shared.Observer;
import Shared.TTTServer;
import Shared.Turn;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Spectator extends JFrame implements Observer<Turn>
{
	private TTTServer tttServer;
	Container pane;
	JButton[][] board;

	public Spectator() throws RemoteException, MalformedURLException, NotBoundException
	{
		super();
		UnicastRemoteObject.exportObject(this, 0);
		tttServer = (TTTServer) Naming.lookup("rmi://localhost:1099/ttts");
		// adding the player as an observer immediately upon creation
		tttServer.addObserver(this);

		pane = getContentPane();    // available because we extend JFrame
		pane.setLayout(new GridLayout(3, 3));
		setTitle("Assignment2 TicTacToe");
		setSize(750, 750);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);    // when last window is disposed of app closes

		board = new JButton[3][3];
		setupGUI();
		setVisible(true);
	}

	private void setupGUI()
	{


		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
			{
				JButton btn = new JButton();
				setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
				board[y][x] = btn;

				// adding the created button with its listeners to the pane
				pane.add(btn);
			}
	}

	@Override
	public void notify(Observable obs, Turn arg) throws RemoteException
	{
		// executing changes inside model
		board[arg.y][arg.x].setText(arg.playerSymbol);
		// executing changes inside controller
		if (arg.yWon || arg.xWon)
			JOptionPane.showMessageDialog(null, arg.playerSymbol + " has won!");
	}
}
