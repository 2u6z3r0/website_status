package siteStatus;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CheckStatus {

	public static final String filename = "/root/Desktop/workspace/websitestatus/urls.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BufferedReader br = null;
		FileReader fr = null;
		String currentLine;
		ExecutorService executor = Executors.newFixedThreadPool(5); //creating a pool of 3 threads
		try{
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			while((currentLine = br.readLine()) != null){
				Runnable worker = new WorkerThread(currentLine);
				executor.execute(worker);
			}
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{	
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			}catch (IOException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
		
	}

}
