package fr.training.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Developer {

	@Autowired
	@Qualifier("programmingTask")
	private Task task;

	public Developer() {
//		task = new ProgrammingTask();
	}

	public void doTask() {
		task.execute();
	}

}
