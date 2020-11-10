package com.example.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		/*get all movie IDs rated by a user (get it from ratings-data-service)*/
		
		//UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
		
		/* Discovering URL with service discovery. Note: Use of @LoadBalanced on getRestTemplate */
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
		
		return ratings.getUserRatings().stream().map(rating -> {
			// For each movie ID, call movie-info-service and get movie details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
			// Put them all together
			return new CatalogItem(movie.getName(), "Great Movie!", 8.8);
		}).collect(Collectors.toList());
		
	}
	
	
	/*
	Alternative WebClient way
	Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
	.retrieve().bodyToMono(Movie.class).block();
	*/
	
	
	// WebClient(Use for asynchronous calls) streaming example (not part of this project...)
	/*
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@GetMapping(path = "/streaming", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamCatalog(){
		final Flux<String> stream = webClientBuilder.build()
					.get()
					.uri("http://emojitrack-gostreamer.herokuapp.com/subscribe/eps") // uri returning chunks of data constantly
					.retrieve()
					.bodyToFlux(String.class);
		
		return stream;
	}
	*/
}
