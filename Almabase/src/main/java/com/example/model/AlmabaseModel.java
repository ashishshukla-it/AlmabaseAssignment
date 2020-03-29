package com.example.model;

import java.util.List;

public class AlmabaseModel {
	private String repoName;
	private long forks;
	private List<Commiters> topCommiters;
	
	
	public List<Commiters> getTopCommiters() {
		return topCommiters;
	}
	public void setTopCommiters(List<Commiters> topCommiters) {
		this.topCommiters = topCommiters;
	}
	public String getRepoName() {
		return repoName;
	}
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	public long getForks() {
		return forks;
	}
	public void setForks(long forks) {
		this.forks = forks;
	}
}
