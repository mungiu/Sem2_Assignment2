package Client;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class View extends JFrame
{
	//	private JMenuBar menuBar;
//	private JMenu menu;
//	private JMenuItem quit;
//	private JMenuItem newGame;
	private Container pane;
	private JButton[][] board;

	private Controller controller;

	public View()
	{
		super();

		pane = getContentPane();    // available because we extend JFrame
		pane.setLayout(new GridLayout(3, 3));
		setTitle("Assignment2 TicTacToe");
		setSize(750, 750);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);    // when last window is disposed of app closes

		board = new JButton[3][3];
		initializeBoard();
//		initializeMenuBar();
		setVisible(true);

	}

	public void setController(Controller controller)
	{
		this.controller = controller;
	}

	private void initializeBoard()
	{
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
			{
				JButton btn = new JButton();
				setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
				board[y][x] = btn;

				////////////////////////////////// NOT TESTED /////////////////////////////////////////////
//				// TODO: check if these integers aree working as intended
//				// making sure old coordinates are memorized and new coordinates are passed to the current action listener
//				Integer tempX = new Integer(x);
//				Integer tempY = new Integer(y);
				///////////////////////////////////////////////////////////////////////////////////////////

				final int finalX = x;
				final int finalY = y;

				// LAMBDA EXPRESSION: "e" becomes " controller.attemptPutPiece(finalX, finalY)
				// "->"  == B E C O M E S
				btn.addActionListener(e -> controller.attemptPutPiece(finalX, finalY));

				// adding the created button with its listeners to the pane
				pane.add(btn);
			}
	}

	public void setBoard(String player, int x, int y)
	{
		board[y][x].setText(player);
	}

	public void showWinnerWindow(String player) throws IOException, UnsupportedAudioFileException, LineUnavailableException
	{
		// NOTE: It is apparently not possible to close the window by hitting "X"
		// one must add a button and click that to make the window go away
		// TODO see why imputed text is not displayed
		String soundName = "movie_1.wav";
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();

		SwingUtilities.invokeLater(() -> {
			final ImageIcon icon = new ImageIcon("week3.jpg");
			JOptionPane.showMessageDialog(null, "Week3!", "Victory!", JOptionPane.OK_OPTION, icon);
			if (JOptionPane.OK_OPTION == 0)
			{
				try
				{
					showWinnerWindow(player);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (UnsupportedAudioFileException e)
				{
					e.printStackTrace();
				}
				catch (LineUnavailableException e)
				{
					e.printStackTrace();
				}
			}
		});


	}
//	private void initializeMenuBar()
//	{
//		menuBar = new JMenuBar();
//		menu = new JMenu("File");
//
//		newGame = new JMenuItem("New Game");
//		quit = new JMenuItem("Quit");
//
//		newGame.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//				resetBoard();
//			}
//		});
//
//		quit.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//				// since program closes when all windows are closed, it will trigger the following system exit code
//				System.exit(0);
//
//			}
//		});
//
//		// appending the created menu items to the menu
//		menu.add(newGame);
//		menu.add(quit);
//		// appending the "File" menu to the menuBar
//		menuBar.add(menu);
//		// sets the menu bar for this frame
//		setJMenuBar(menuBar);
//	}
//
//	private void resetBoard()
//	{
//		//looping through each board square and resetting its value
//		for (int y = 0; y < 3; y++)
//			for (int x = 0; x < 3; x++)
//				board[y][x].setText("");
//	}
}
