package model;

import util.Hashable;

public class User implements Hashable<String>{

	private String id;
	
	public User(String id) {
		this.id=id;
	}
	
	@Override
	public String getKey() {
		return id;
	}

}