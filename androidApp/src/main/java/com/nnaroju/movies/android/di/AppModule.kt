package com.nnaroju.movies.android.di

import com.nnaroju.movies.android.details.DetailsViewModel
import com.nnaroju.movies.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params -> DetailsViewModel(
        getMovieUseCase = get(),
        movieId = params.get()
    ) }
}