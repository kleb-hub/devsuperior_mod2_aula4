package com.devsuperior.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devsuperior.movieflix.entities.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long>  {

	
	@Query(nativeQuery = true, value = """
			SELECT mov.id, genre_id, title, sub_title, movie_year, img_url, synopsis
			FROM tb_movie mov
			INNER JOIN tb_genre ren
			ON mov.genre_id = ren.id
			WHERE (:genreId = 0 OR genre_id = :genreId)
			ORDER BY title
			""")
	Page<Movie> findMoviesByGenre(Long genreId, Pageable pageable);
}
