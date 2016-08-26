import java.io.IOException;

import javax.swing.JFrame;

import com.gadiyacalendar.ui.UI;
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
		UI ui=new UI();
		ui.setTitle("Gadiya Calendar");
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setVisible(true);
		ui.setSize(500, 300);
	}

}
