package bbc.forge.music.monitoring;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource
public class DatabaseMonitoring {

	private int databaseAvailable = 1;
	
	public synchronized void setDatabaseAvailable() {
		databaseAvailable = 1;
	}
	
	public synchronized void setDatabaseUnavailable() {
		databaseAvailable = 0;
	}
	
	@ManagedAttribute(description = "Did the last request to the database succeed or fail")
	public int getDatabaseAvailable() {
		return databaseAvailable;
	}
	
}
