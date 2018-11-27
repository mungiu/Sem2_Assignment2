package Shared;

import java.io.Serializable;

public class Turn implements Serializable
{
	public String playerSymbol;
	public int x;
	public int y;
	public boolean xWon = false;
	public boolean yWon = false;
}
