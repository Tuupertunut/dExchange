import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class FileUtility{
	public void removeLineFromFile(String file, String lineToRemove){
		Logger log = Logger.getLogger("Minecraft");
		try{
			File inFile = new File(file);
			
			if (!inFile.isFile()) {
				log.severe("[dExchange] Parameter is not an existing file");
				return;
			}

			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			while ((line = br.readLine()) != null){
				if (line.trim().equals(lineToRemove.trim())){
					continue;
				}
				pw.println(line);
				pw.flush();
			}

			pw.close();
			br.close();

			if (!inFile.delete()) {
				log.severe("[dExchange] Could not delete file"+file);
				return;
			}
	
			if (!tempFile.renameTo(inFile)){
				log.severe("[dExchange] Could not rename file "+tempFile.getAbsolutePath()+" to "+inFile.getAbsolutePath());
			}
		}catch (FileNotFoundException ex){
			log.severe("[dExchange] Could not find file ChestTrade.txt");
		}catch (IOException ex) {
			log.severe("[dExchange] Failed to edit file ChestTrade.txt");
		}
	}
}