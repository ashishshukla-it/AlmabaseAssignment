package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.AlmabaseModel;
import com.example.model.Commiters;
import com.example.utilities.UtillityClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PreDestroy;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Service
public class AlmabaseService {

	@PreDestroy
	public void preDestroy() throws IOException {
		httpClient.close();
	}

	public final static CloseableHttpClient httpClient = HttpClients.createDefault();

	public List<AlmabaseModel> getResponse(String jsonString, int topN, String organization, int commiters)
			throws ParseException, org.apache.http.ParseException, IOException {
		UtillityClass utility = new UtillityClass();
		List<AlmabaseModel> modelList = new ArrayList<AlmabaseModel>();
		Map<String, Long> repoMap = new HashMap<String, Long>();
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
		for (Object jsonObject : jsonArray) {
			JSONObject js = (JSONObject) jsonObject;
			String name = (String) js.get("name");
			long fork = (long) js.get("forks_count");
			System.out.println(name + " :: " + fork);
			repoMap.put(name, fork);
		}

		Map<String, Long> sortedRepoMap = utility.sortByValue(repoMap);
		for (Entry<String, Long> en : sortedRepoMap.entrySet()) {
			System.out.println("Key = " + en.getKey() + ", Value = " + en.getValue());
			AlmabaseModel model = new AlmabaseModel();
			List<Commiters> commitersList = new ArrayList<Commiters>();
			model.setForks(en.getValue());
			model.setRepoName(en.getKey());
			commitersList = getCommitersInfo(organization, commiters, en.getKey());
			model.setTopCommiters(commitersList);
			modelList.add(model);
		}

		modelList = modelList.subList(0, topN);
		return modelList;
	}

	private List<Commiters> getCommitersInfo(String organization, int commiters, String repoName)
			throws org.apache.http.ParseException, IOException, ParseException {
		UtillityClass uc = new UtillityClass();
		List<Commiters> commitersList = new ArrayList<Commiters>();
		List<String> commiterNameList = new ArrayList<String>();
		HttpGet request = new HttpGet("https://api.github.com/repos/" + organization + "/" + repoName + "/commits");
		HashMap<String, Integer> commitCountMap = new HashMap<String, Integer>();
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		String commitersJsonString = null;
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				commitersJsonString = EntityUtils.toString(entity);
			}
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(commitersJsonString);
			for (Object jsonObject : jsonArray) {
				JSONObject js = (JSONObject) jsonObject;
				JSONObject commit = (JSONObject) js.get("commit");
				JSONObject commiter = (JSONObject) commit.get("committer");
				String commiterName = (String) commiter.get("name");
				System.out.println("@@@ " + commiterName);
				if (commitCountMap.containsKey(commiterName)) {
					commitCountMap.put(commiterName, commitCountMap.get(commiterName) + 1);
				} else
					commitCountMap.put(commiterName, 1);

			}
			Map<String, Integer> sortedCommiterMap = uc.sortByValue2(commitCountMap);
			for (Entry<String, Integer> en : sortedCommiterMap.entrySet()) {
				System.out.println("Key = " + en.getKey() + ", Value = " + en.getValue());
				Commiters commiter = new Commiters();
				Commiters commitersInfo = new Commiters();

				commiter.setCommitCount(en.getValue());
				commiter.setCommiterName(en.getKey());
				commitersList.add(commiter);
			}
			commitersList = commitersList.subList(0, commiters);
		} catch (ClassCastException e) {
			System.out.println("classCastException Found");
		}
		catch(IndexOutOfBoundsException e2) {
			System.out.println("Index out of bound exception occured");
			return commitersList;
		}
		return commitersList;
	}

}
