package com.pcf.training.weatherapp.client;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class WeatherClientAppApplication {

	private RestTemplate restTemplate;

	private String temperatureServiceURL = "http://" + Env.serviceHost + "/mediator";

	public static void main(String[] args) {
		SpringApplication.run(WeatherClientAppApplication.class, args);

	}

	@RequestMapping(value = "/runTests", produces = "application/json", method = RequestMethod.GET)
	public void runTests() {

		restTemplate = new RestTemplate();

		testGetDummyObjectMethod(restTemplate);
		testAddTemperatureMethod(restTemplate);
		testGetAllDataMethod(restTemplate);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testGetDummyObjectMethod(RestTemplate restTemplate) {

		String url = temperatureServiceURL + "/getDummyObject";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);

		System.out.println(response.getBody());
	}

	public void testAddTemperatureMethod(RestTemplate restTemplate) {

		String url = temperatureServiceURL + "/add";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> urlVars = getCityPayload();

		HttpEntity<String> request = new HttpEntity<String>(new JSONObject(urlVars).toString(), headers);

		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);

		System.out.println(response.getBody());

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testGetAllDataMethod(RestTemplate restTemplate) {

		String url = temperatureServiceURL + "/getAll";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);

		System.out.println(response.getBody());

	}

	public Map<String, Object> getCityPayload() {

		Map<String, Object> urlVars = new HashMap<String, Object>();

		urlVars.put("date", new Date(System.currentTimeMillis()));
		urlVars.put("city", "City_" + System.currentTimeMillis());

		Map<String, Object> timelyTemperatureMap = new HashMap<String, Object>();
		timelyTemperatureMap.put("time", new Time(System.currentTimeMillis()));
		timelyTemperatureMap.put("temperature", 10);

		List<Map<String, Object>> timelytempList = new ArrayList<Map<String, Object>>();
		timelytempList.add(timelyTemperatureMap);

		urlVars.put("timelyTemperatures", timelytempList);

		return urlVars;
	}
}
