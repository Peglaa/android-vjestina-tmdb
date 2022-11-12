package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

private val movieDetailsViewState: MovieDetailsViewState =
    movieDetailsMapper.toMovieDetailsViewState(
        MoviesMock.getMovieDetails()
    )

