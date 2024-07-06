package com.nnaroju.movies.data.repository

import com.nnaroju.movies.data.remote.RemoteDataSource
import com.nnaroju.movies.data.util.toMovie
import com.nnaroju.movies.domain.model.Movie
import com.nnaroju.movies.domain.repository.MovieRepository

internal class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getMovies(page: Int): List<Movie> {
        return remoteDataSource.getMovies(page).results.map {
            it.toMovie()
        }
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return remoteDataSource.getMovie(movieId).toMovie()
    }
}