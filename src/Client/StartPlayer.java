package Client;

public class StartPlayer
{
	public static void main(String[] args)
	{
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(view, model);
		view.setController(controller);




//		// uses internal queue for GUI changes outside event dispatch thread
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				new View();
//			}
//		});
	}
}
