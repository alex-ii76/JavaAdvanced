package by.a1;

import java.util.ArrayList;
import java.util.List;

public class Execution {
 @Override
	public String toString() {
		return "Execution [id=" + id + ", phase=" + phase + ", goals=" + goals + "]";
	}

private String id;
 private String phase;
 private List<String> goals; 
 
public Execution() {
	goals = new ArrayList<String>();
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}

public String getPhase() {
	return phase;
}

public void setPhase(String phase) {
	this.phase = phase;
}

public List<String> getGoals() {
	return goals;
}

public void addGoal(String goal) {
	this.goals.add(goal);
}
 

}
