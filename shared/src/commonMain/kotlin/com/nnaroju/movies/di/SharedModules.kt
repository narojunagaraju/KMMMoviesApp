package com.nnaroju.movies.di

import com.nnaroju.movies.data.remote.MovieService
import com.nnaroju.movies.data.remote.RemoteDataSource
import com.nnaroju.movies.data.repository.MovieRepositoryImpl
import com.nnaroju.movies.domain.repository.MovieRepository
import com.nnaroju.movies.domain.usecase.GetMovieUseCase
import com.nnaroju.movies.domain.usecase.GetMoviesUseCase
import com.nnaroju.movies.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory {
        RemoteDataSource(get(), get())
    }
    factory {
        MovieService()
    }
}

private val utilityModule = module {
    factory {
        provideDispatcher()
    }
}

private val domainModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }
    factory {
        GetMoviesUseCase()
    }
    factory {
        GetMovieUseCase()
    }
}

private val sharedModules = listOf(
    domainModule, dataModule, utilityModule
)

fun getSharedModules() = sharedModules