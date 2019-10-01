import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;

public class Main {

	public static void main(String args[]) {

		if (args.length != 1) {
			System.out.println("Incorrect arguments passed. Please pass path to properties file as the only argument.");
			return;
		}
		File file = new File(args[0]);
		String st;
		String dir = "";
		String jiraffe_dir = "";
		String dateFile = "";
		String jiraffeDateFile = "";
		String ip = "";
		int port = 27017;
		String dbName = "";
		String jiraffeDBName = "";
		String batFileForScriptsLoc = "";
			
		int i = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			System.out.println("Properties Passed: ");
			while ((st = br.readLine()) != null) {
				if (st.contains("OneJira_Dir")) {
					dir = st.split(": ")[1];
					System.out.println("	Path to OneJira Directory: " + dir);
					i++;
					continue;
				} else if (st.contains("Jiraffe_Dir")) {
					jiraffe_dir = st.split(": ")[1];
					System.out.println("	Path to Jiraffe Directory: " + jiraffe_dir);
					i++;
					continue;
				} else if (st.contains("OneJira_LastDate_FilePath")) {
					dateFile = st.split(": ")[1];
					System.out.println("	Path to the file containing last read date for OneJira: " + dateFile);
					i++;
					continue;
				} else if (st.contains("Jiraffe_LastDate_FilePath")) {
					jiraffeDateFile = st.split(": ")[1];
					System.out.println("	Path to the file containing last read date for Jiraffe: " + jiraffeDateFile);
					i++;
					continue;
				} else if (st.contains("MongoDB_IP")) {
					ip = st.split(": ")[1];
					System.out.println("	IP address of MongoDB host: " + ip);
					i++;
					continue;
				} else if (st.contains("MongoDB_Port")) {
					port = Integer.parseInt(st.split(": ")[1]);
					System.out.println("	Port number of MongoDB host: " + port);
					i++;
					continue;
				} else if (st.contains("OneJira_DB_Name")) {
					dbName = st.split(": ")[1];
					System.out.println("	OneJira database name: " + dbName);
					i++;
					continue;
				} else if (st.contains("Jiraffe_DB_Name")) {
					jiraffeDBName = st.split(": ")[1];
					System.out.println("	Jiraffe database name: " + jiraffeDBName);
					i++;
					continue;
				} else if (st.contains("BatFile_FilePath")) {
					batFileForScriptsLoc = st.split(": ")[1];
					System.out.println("	Location to .bat file for running JavaScripts: " + batFileForScriptsLoc);
					i++;
					continue;
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("ERROR >>>>> CANNOT READ PROPERTIES FILE");
			e.printStackTrace();
		}
		
		if (i != 9) {
			System.out.println("\nIncorrect Number of Properties Passed in Properties file. ");
			return;
		} 
		
		Timer timer = new Timer();
		Scheduler sc = new Scheduler();
		sc.setFields(dir, jiraffe_dir, dateFile, jiraffeDateFile, ip, port, dbName, jiraffeDBName, batFileForScriptsLoc);
		timer.scheduleAtFixedRate(sc, 0, 5000);
	}
}

