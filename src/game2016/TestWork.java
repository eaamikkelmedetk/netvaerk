package game2016;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class TestWork extends Service<String> {
	
	@Override
	protected Task<String> createTask() {
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				
				return "Test";
			}
			
		};
	}

}
