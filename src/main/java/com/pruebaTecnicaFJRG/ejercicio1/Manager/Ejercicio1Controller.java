package com.pruebaTecnicaFJRG.ejercicio1.Manager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;


@RestController
public class Ejercicio1Controller {


	protected static final String URL_TOKEN = "http://localhost:8080/token";

	@GetMapping("/get-token")
	public Map<String, String> getString() {

		String token = getToken();
		String date = getDate();
		
		Map<String, String> rtn = new LinkedHashMap<>();
		rtn.put("auth-vivelibre-token", token);
		rtn.put("date", date );

		return rtn;

	}

	private String getDate() {
		SimpleDateFormat SDFormat= new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
		String date = SDFormat.format(new Date());
		return date;
	}

	private String getToken() {
		String token = ""; 
		Unirest.setTimeouts(0, 0);
		try {
			HttpResponse<JsonNode> response = Unirest.post(URL_TOKEN)
			  .header("Content-Type", "application/json")
			  .body("{\n\"username\":\"auth-vivelibre\",\n\"password\":\"password\"\n}")
			  .asJson();
			
			JSONObject jsonResponse = response.getBody().getObject();
			token = jsonResponse.getString("token");
			
		} catch (UnirestException e) {
			throw new RuntimeException("Error getting Token");
		} catch (Exception e) {
			throw new RuntimeException("Error Parsing the Response");
		}
		
		return token;
	}

}
