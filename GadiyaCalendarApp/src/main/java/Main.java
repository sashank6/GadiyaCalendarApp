import java.io.IOException;
import java.util.List;

import com.gadiyacalendarapp.core.Service;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		Service service=new Service();
		com.google.api.services.calendar.Calendar calservice = service.getCalendarService();
		service.addEvent("TestEvent", "Hyderabad", "Test", System.currentTimeMillis(), 3);
	}

}
