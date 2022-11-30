package agency.five.codebase.android.movieapp.data.di

import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewModel
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel {
        FavoritesViewModel(
            movieRepository = get(),
            favoritesScreenMapper = get()
        )
    }
    single<FavoritesMapper> { FavoritesMapperImpl() }
}
