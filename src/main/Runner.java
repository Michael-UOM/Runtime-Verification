package main;

import java.io.IOException;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;

public class Runner {
	
	final Random random = new Random();
	final OkHttpClient httpClient = new OkHttpClient();
	
	public void uploadCorrectAlert() {
		System.out.println("Alert uploaded!");
	}
	
	public void uploadIncorrectAlert() {
		System.out.println("Alert uploaded (incorrect)!");
	}
	
	public void purgeAlerts() {
		System.out.println("Alerts purged!");
	}
	
	public void attemptUploadCorrectAlert() throws IOException {
		// Generate a random alert to upload
		int max = 5;
		int min = 0;
		int randomNumber = random.nextInt((max - min) + 1) + min;
		
		Alert alert = new Alert();
		
		switch (randomNumber) {
			case 0:
				Alert alertCar = new Alert(
			            1,
			            "Lot 14 Metal DieCast model car",
			            "Lot 14 Metal DieCast model car",
			            "https://www.maltapark.com/item/details/9514895",
			            "https://www.maltapark.com/asset/itemphotos/9514895/9514895_1.jpg?_ts=1",
			            400
				    );
				System.out.println("Creating an alert of type car!");
				alert = alertCar;
				break;
			case 1:
				Alert alertBoat = new Alert(
			            2,
			            "Trimarchi 57 S. - Tohatsu 115 hp",
			            "Trimarchi 57 S (5.70 mt.) Year 2021 ° with Tohatsu 115 hp engine year 2022 ° with only 30 hours of motion",
			            "https://www.maltapark.com/item/details/9516448",
			            "https://www.maltapark.com/asset/itemphotos/9516448/9516448_1.jpg?_ts=4",
			            2400000
				    );
				System.out.println("Creating an alert of type boat!");
				alert = alertBoat;
				break;
			case 2:
				Alert alertRent = new Alert(
			            3,
			            "Property For Rent - SLIEMA ONE BEDROOM BARGAIN",
			            "ONE BEDROOM/ ONE BATHROOM/ PRIME LOCATION",
			            "https://www.maltapark.com/item/details/9514948",
			            "https://www.maltapark.com/asset/itemphotos/9514948/9514948_1.jpg?_ts=1",
			            85000
				    );
				System.out.println("Creating an alert of type property-rent!");
				alert = alertRent;
				break;
			case 3:
				Alert alertSale = new Alert(
				        4,
				        "Townhouse",
				        "FOR SALE",
				        "https://www.maltapark.com/item/details/9514599",
				        "https://www.maltapark.com/asset/itemphotos/9514599/9514599_9.jpg/?x=TWF4Vz01NjMmTWF4SD00MjI=&_ts=9",
				        26500000
				    );
				System.out.println("Creating an alert of type property-sale!");
				alert = alertSale;
				break;
			case 4:
				Alert alertToy = new Alert(
			            5,
			            "Trolls Soft Toy",
			            "Trolls Soft Toy - New",
			            "https://www.maltapark.com/item/details/9516601",
			            "https://www.maltapark.com/asset/itemphotos/9516601/9516601_1.jpg?_ts=1",
			            700
				    );
				System.out.println("Creating an alert of type toy!");
				alert = alertToy;
				break;
			case 5:
				Alert alertElectronic = new Alert(
			            6,
			            "Laptop Power Adapters",
			            "Laptop Power Adapters",
			            "https://www.maltapark.com/item/details/9509385",
			            "https://www.maltapark.com/asset/itemphotos/9509385/9509385_4.jpg/?x=TWF4Vz01NjMmTWF4SD00MjI=&_ts=4",
			            1500
				    );
				System.out.println("Creating an alert of type electornics!");
				alert = alertElectronic;
				break;
		}
		
		// Upload alert
		String json = new Gson().toJson(alert);
        
        RequestBody body = RequestBody.create(
    		json,
    		MediaType.parse("application/json; charset=utf-8")
		);

        Request request = new Request.Builder()
                .url("https://api.marketalertum.com/Alert")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } 
		
	}
	
	public void attemptPurgeAlerts() throws IOException {
		Request request = new Request.Builder().url("https://api.marketalertum.com/Alert?userId=c55bc56a-232c-46a4-9778-7f0d41690aa2").delete().build();

        try (Response response = httpClient.newCall(request).execute()) {}
	}
	
	public void attemptUploadIncorrectAlert() throws IOException {
		Alert incorrectAlert = new Alert(
	            -1,
	            "",
	            "",
	            "",
	            "",
	            0
		    );
		
		// Upload alert
		String json = new Gson().toJson(incorrectAlert);
        
        RequestBody body = RequestBody.create(
    		json,
    		MediaType.parse("application/json; charset=utf-8")
		);

        Request request = new Request.Builder()
                .url("https://api.marketalertum.com/Alert")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        }
	}
	
	public void run(final API api) throws IOException {
		final Random rand = new Random();
		
		// Assuming that every time we start a run no alerts are already present and that the number of alerts is 0
		this.attemptPurgeAlerts();
		api.setApiAttributesFromGetRequest();
		int numberOfAlerts = api.numberOfAlerts;
		System.out.println("Initial number of alerts: " + numberOfAlerts);
		
		while(true){
			// Giving more change to upload a correct alert than purging or uploading an incorrect one
			final int randomNumber = rand.nextInt(6);
					
			switch(randomNumber){
			case 1:
				this.attemptUploadIncorrectAlert();
				api.setApiAttributesFromGetRequest();
				if (api.eventLogType == 0 && api.numberOfAlerts == (numberOfAlerts + 1)){
					// Should never enter here but as we treat the system as a black box this had to be tested
					numberOfAlerts = api.numberOfAlerts;
					this.uploadIncorrectAlert();
				}
				break;
			case 2:
				this.attemptPurgeAlerts();
				api.setApiAttributesFromGetRequest();
				if (api.eventLogType == 1 && api.numberOfAlerts == 0){
					numberOfAlerts = api.numberOfAlerts;
					this.purgeAlerts();
				}
				break;
			default:
				this.attemptUploadCorrectAlert();
				api.setApiAttributesFromGetRequest();
				if (api.eventLogType == 0 && api.numberOfAlerts == (numberOfAlerts + 1)){
					numberOfAlerts = api.numberOfAlerts;
					this.uploadCorrectAlert();
				}
				break;
			}
			
			// Small pause
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		final Runner r = new Runner();
		final API api = r.new API();
		r.run(api);
	}
	
	public class API {
		private int numberOfAlerts;
		private int eventLogType;
		
		public API() {}
		

		public void setApiAttributesFromGetRequest() throws IOException {
			Request request = new Request.Builder()
            .url("https://api.marketalertum.com/EventsLog/c55bc56a-232c-46a4-9778-7f0d41690aa2")
            .addHeader("Content-Type", "application/json")
            .build();

		    try (Response response = httpClient.newCall(request).execute()) {
		
		        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		        // Get response body
		        String jsonString = response.body().string();
		
		        GetResponse[] responseArray = new Gson().fromJson(jsonString, GetResponse[].class);
		        
		        this.numberOfAlerts = responseArray[0].systemState.alerts.size();
		        this.eventLogType = responseArray[0].eventLogType;
		    }
			
		}
	}
	
}