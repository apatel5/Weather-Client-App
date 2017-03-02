package com.pcf.training.weatherapp.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Env {

	public static String serviceHost;

	static {
		serviceHost = System.getenv("VCAP_SERVICES");
		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
		if (VCAP_SERVICES != null) {
			JSONObject vcap;
			try {
				vcap = new JSONObject(VCAP_SERVICES);

				URL serviceUri = new URL(new JSONObject(
						new JSONObject(new JSONArray(vcap.get("Weather-Service").toString()).get(0).toString())
								.get("credentials").toString()).get("uri").toString());

				serviceHost = serviceUri.getHost();

			} catch (JSONException | MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
