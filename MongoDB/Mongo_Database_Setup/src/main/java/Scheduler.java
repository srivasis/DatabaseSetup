import java.util.Date;
import java.util.TimerTask;

public class Scheduler extends TimerTask{

	private Date current;
	private boolean serviceStatus;
	
	private String dir;
	private String jiraffe_dir;
	private String dateFile;
	private String jiraffeDateFile;
	private String ip;
	private int port;
	private String dbName;
	private String jiraffeDBName;
	private String batFileForScriptsLoc;
	
	public void setFields(String dir, String jiraffe_dir, String dateFile, String jiraffeDateFile, String ip, int port, String dbName, String jiraffeDBName, String batFileForScriptsLoc) {
		this.dir = dir;
		this.jiraffe_dir = jiraffe_dir;
		this.dateFile = dateFile;
		this.jiraffeDateFile = jiraffeDateFile;
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
		this.jiraffeDBName = jiraffeDBName;
		this.batFileForScriptsLoc = batFileForScriptsLoc;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void run() {
		this.current = new Date();
		System.out.println("Starting up a new service for monitoring directory .......");
		DirectoryWatcherService service = new DirectoryWatcherService(this, dir, jiraffe_dir, dateFile, jiraffeDateFile, ip, port, dbName, jiraffeDBName, batFileForScriptsLoc);
		System.out.println("Checking monitoring service at " + this.current);
		if(serviceStatus == false) {
			System.out.println("----------Monitoring Service Down----------");
		}
	}
	
	public void setServiceStatus(boolean status) {
		this.serviceStatus = status;
	}

}
