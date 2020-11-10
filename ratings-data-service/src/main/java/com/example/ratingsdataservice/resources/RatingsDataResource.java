package com.example.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingsdataservice.models.Rating;
import com.example.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	@GetMapping("/{movieId}")
	public Rating getMovieRating(@PathVariable String movieId) {
		return new Rating(movieId, 9.1);
	}

	@GetMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating("1236", 8.7),
				new Rating("1237", 6.3)
		);
		
		UserRating userRating = new UserRating();
		userRating.setUserRatings(ratings);
		return userRating;
	}
}
