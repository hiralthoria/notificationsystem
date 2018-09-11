package sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

/**
 * For testing, simple simulation to run multiple producer threads on both the channels
 * @author Hiral Salvi
 *
 */
public class Simulation {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		// five threads for sending notification on email and slack service
		ThreadPoolExecutor executorEmail = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		ThreadPoolExecutor executorSlack = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		executorEmail.execute(new EmailRequest());
		executorSlack.execute(new SlackRequest());
	}
}

/**
 * Multiple email requests
 * @author Hiral Salvi
 *
 */
class EmailRequest implements Runnable {
	
	public void run() {
		for(int i=0;i<50;i++) {
	    try {
	      HttpPost post = new HttpPost("http://localhost:8080/notification?channel=email");
		  JSONObject json = new JSONObject();
		  json.put("from", "FE"  + i);
		  json.put("to", "TE"  + i);
		  json.put("subject", "SE"  + i);
		  json.put("message", "ME"  + i);
		  StringEntity se = new StringEntity( json.toString());
		  se.setContentType("application/json");
		  post.setEntity(se);
		 	
			  HttpResponse response = new DefaultHttpClient().execute(post);
			  BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			  String line = "";
			  while ((line = rd.readLine()) != null) {
				  System.out.println(line);
			    }

			  } catch (Exception e) {
				  e.printStackTrace();
			  }
		 }
	}
}

/**
 * Multiple slack requests
 * @author Hiral Salvi
 *
 */
class SlackRequest implements Runnable {
	
	public void run() {
		
		for(int i=0;i<50;i++) {
	    try {
		
	    	HttpPost post = new HttpPost("http://localhost:8080/notification?channel=slack");
		JSONObject json = new JSONObject();
		  json.put("from", "FS"  + i);
		  json.put("to", "TS"  + i);
		  json.put("subject", "SS"  + i);
		  json.put("message", "MS"  + i);
		  StringEntity se = new StringEntity( json.toString());
		  se.setContentType("application/json");
		  post.setEntity(se);
		 	
			  HttpResponse response = new DefaultHttpClient().execute(post);
			  BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			  String line = "";
			  while ((line = rd.readLine()) != null) {
				  System.out.println(line);
			    }

			  } catch (Exception e) {
				  e.printStackTrace();
			  }
		 }
	}

}