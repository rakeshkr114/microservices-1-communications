package com.example.moviecatalogservice.models;

import java.util.List;

public class UserRating {

	List<Rating> userRatings;

	public UserRating() {
		super();
	}

	public UserRating(List<Rating> userRatings) {
		super();
		this.userRatings = userRatings;
	}

	public List<Rating> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(List<Rating> userRatings) {
		this.userRatings = userRatings;
	}
	
}
