package com.movie.controller;

import com.movie.dao.MoviesDao;
import com.movie.model.MovieRating;
import com.movie.model.Movies;
import com.movie.service.MovieService;
import com.movie.service.MovieTrailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class Home {

    private final MovieService movieService;
    @Autowired
    MovieTrailer movieTrailer;
    @Autowired
    private MoviesDao moviesDao;

    @Autowired
    public Home(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/")
    public String getAllVideo() {

        return "home";
    }

    @GetMapping
    public ResponseEntity<String> getMovieRatingForm(@RequestParam(name = "movieName", required = false) String movieName) throws UnsupportedEncodingException {
        if (movieName != null) {
            String movieRating = movieService.getMovieRating(movieName);
            if (movieRating != null) {
                return ResponseEntity.ok(movieRating);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/genre")
    public ResponseEntity<String> getMovieGenreForm(@RequestParam(name = "genre", required = false) String genre) throws UnsupportedEncodingException {
        if (genre != null) {
            String movieRating = movieService.getMovieGenre(genre);
            if (movieRating != null) {
                return ResponseEntity.ok(movieRating);
            }
        }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping
//    public String showMovies(Model model) {
//        model.addAttribute("movies", movieRepository.findAll());
//        return "movies";
//    }

    @PostMapping("/add")
    public String addMovie(@RequestParam String name, @RequestParam double rating) {
        Movies movie = new Movies();
        movie.setMovieName(name);
        movie.setMovieRating(rating);
        moviesDao.save(movie);
        return "home";
    }

    @GetMapping("/display")
    public String showMovies(Model model) {
        List<Movies> moviesList = moviesDao.findAll();
        model.addAttribute("moviesList", moviesList);
        return "movies";
    }
    @GetMapping("/trailer")
    public ModelAndView getMovieTrailer(@RequestParam(name = "movieNamee", required = false) String movieNamee) throws UnsupportedEncodingException {
        if (movieNamee != null) {
            String movieRating = movieTrailer.getMovieTrailer(movieNamee);
            if (movieRating != null) {
                return new ModelAndView(new RedirectView(movieRating));
            }
        }
        return new ModelAndView(new RedirectView("/error"));
    }


    public void surajmethod(){
        System.out.println("for checking purpose");
    }
}



















//
//   @GetMapping("/")
//   public String getAllVideo() {
//
//       return "home";
//   }

//    @GetMapping("/movies/{movieName}")
//    public ResponseEntity<String> getMovieRating(@PathVariable String movieName) throws UnsupportedEncodingException {
//        String movieRating = movieService.getMovieRating(movieName);
//        if (movieRating != null) {
//            return ResponseEntity.ok(movieRating);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/genre/{genre}")
//    public ResponseEntity<String> getMovieGenre(@PathVariable String genre) throws UnsupportedEncodingException {
//        String movieRating = movieService.getMovieGenre(genre);
//        if (movieRating != null) {
//            return ResponseEntity.ok(movieRating);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
