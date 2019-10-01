import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class DirectoryWatcherService {
	@SuppressWarnings({ "rawtypes", "unused" })
	public DirectoryWatcherService(Scheduler s, String dir, String jiraffe_dir, String dateFile, String jiraffeDateFile, String ip, int port, String dbName, String jiraffeDBName, String batFileForScriptsLoc) {
		Path path = Paths.get("C:\\Testing");
		try {
			WatchService watcher = path.getFileSystem().newWatchService();
			path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
			System.out.println("Monitoring directory for changes...");
			s.setServiceStatus(true);
			
			WatchKey watchKey = watcher.take();
			List<WatchEvent<?>> events = watchKey.pollEvents();
			for (WatchEvent event : events) {
				if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					System.out.println("Created: " + event.context().toString());
					// Update MongoDB with latest file
					startProgram(dir, jiraffe_dir, dateFile, jiraffeDateFile, ip, port, dbName, jiraffeDBName);
					// Run Scripts to calculate metrics and store them in MongoDB
					RunScripts rs = new RunScripts(batFileForScriptsLoc);
					// Done with this create event so ready for new service
					s.setServiceStatus(false);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })	
	public static void startProgram(String dir, String jiraffe_dir, String dateFile, String jiraffeDateFile, String ip, int port, String dbName, String jiraffeDBName) {
		FilePaths fps = new FilePaths(dir, dateFile);
		JiraffeFilePaths jfps = new JiraffeFilePaths(jiraffe_dir, jiraffeDateFile);

		// filePaths -> Holds paths to all json files that need to be pulled into
		// MongoDB

		ArrayList<String> filePaths = fps.getFilePaths();
		ArrayList<String> jiraffe_filePaths = jfps.getFilePaths();

		//ONE_JIRA DATA:
		try {
			Mongo mongo = new Mongo(ip, port);
			DB db = mongo.getDB(dbName);

			for (String filePath : filePaths) {
				String[] pathContents = filePath.split("\\\\");
				String projectID = pathContents[9];
				String fileName = pathContents[10];
				
				if(fileName.split("-")[2].equals("userstory")) {
					projectID+="Userstory";
				}

				System.out.println(filePath);
				
				DBCollection collection = db.getCollection(projectID);

				try {
					collection.insert((DBObject) JSON.parse(readFile(filePath)));
				} catch (Exception e) {
					System.out.println("Bad File Encountered -> " + filePath);
				}
			}

		} catch (Exception e) {
			System.out.println("Cannot insert into db");
			e.printStackTrace();
		}
		
		//JIRAFFE DATA:
		try {
			Mongo mongo = new Mongo(ip, port);
			DB jdb = mongo.getDB(jiraffeDBName);
			HashMap<String, ArrayList<JSONObject>> collections = new HashMap<>();

			for (String jiraffeFilePath : jiraffe_filePaths) {
				try {
					String date = jiraffeFilePath.split("\\\\")[8];
					JSONParser parser = new JSONParser();
					JSONObject json = (JSONObject) parser.parse(readFile(jiraffeFilePath));
					JSONArray teams = (JSONArray) json.get("TeamList");
					Iterator<JSONObject> iterator = teams.iterator();
					
					while(iterator.hasNext()) {
						JSONObject team = iterator.next();
						String teamName = "Prjct" + team.get("TeamID").toString();
						
						team.put("Date", date);
						
						JSONObject newDocument = new JSONObject();
						ArrayList<JSONObject> documents = new ArrayList<>();
						
						if(collections.containsKey(teamName)) {
							documents = collections.get(teamName);
							collections.remove(teamName);
						}
						
						newDocument.put("Team", team);
						documents.add(newDocument);
						
						collections.put(teamName, documents);
					}
				} catch (Exception e) {
					System.out.println("Bad File Encountered -> " + jiraffeFilePath);
					System.out.println(e);
				}
			}

			Set<String> teamCollections = collections.keySet();
			for(String collectionName: teamCollections) {
				DBCollection collection = jdb.getCollection(collectionName);
				ArrayList<JSONObject> documents = collections.get(collectionName);
				for(JSONObject obj: documents) {
					collection.insert((DBObject) JSON.parse(obj.toString()));
				}
			}
			
		} catch (Exception e) {
			System.out.println("Cannot insert into db");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static String readFile(String filepath) {
		InputStream is;
		try {
			is = new FileInputStream(filepath);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();
			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}
			String fileAsString = sb.toString();
			return fileAsString;
		} catch (FileNotFoundException e) {
			System.out.println("No file to read.");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("Nothing in file to read.");
			e.printStackTrace();
			return null;
		}
	}
}

