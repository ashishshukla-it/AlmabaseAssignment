package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.ws.rs.QueryParam;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AlmabaseModel;
import com.example.service.AlmabaseService;

@RestController
@RequestMapping(path = "/AlmabaseAssignment/orgRepoForkCount")
public class AlmabaseController {
	@PreDestroy
	public void preDestroy() throws IOException {
		httpClient.close();
	}
	public final static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public List<AlmabaseModel> getData(@QueryParam("organization") String organization, @QueryParam("topN") int topN,
			@QueryParam("commiters") int commiters) 
			throws ParseException, ClientProtocolException, IOException {
		List<AlmabaseModel> serviceResponse = null;
		AlmabaseService service = new AlmabaseService();
		HttpGet request = new HttpGet("https://api.github.com/orgs/"+organization+"/repos");
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		String result = null;
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity);
				System.out.println(result);
			}
			 serviceResponse = service.getResponse(result, topN, organization,commiters);
		} finally {
			
		}
		return serviceResponse;
	}
}
