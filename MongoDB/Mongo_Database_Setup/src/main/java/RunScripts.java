import java.io.IOException;

public class RunScripts {

	@SuppressWarnings("unused")
	public RunScripts(String batFileForScriptsLoc) {
		Runtime runtime = Runtime.getRuntime();
		try {
		    Process p1 = runtime.exec("cmd.exe /c start "+ batFileForScriptsLoc);
		} catch(IOException ioException) {
		    System.out.println(ioException.getMessage() );
		}
	}
}
