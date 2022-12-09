package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.navigation.MOVIE_ID_KEY
import agency.five.codebase.android.movieapp.navigation.MovieDetailsDestination
import agency.five.codebase.android.movieapp.navigation.NavigationItem
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesRoute
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewModel
import agency.five.codebase.android.movieapp.ui.home.*
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsRoute
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewModel
import agency.five.codebase.android.movieapp.ui.theme.Blue
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == NavigationItem.HomeDestination.route ||
                    navBackStackEntry?.destination?.route == NavigationItem.FavoritesDestination.route
        }
    }
    val showBackIcon = !showBottomBar
    val homeViewModel = getViewModel<HomeViewModel>()
    val favoritesViewModel = getViewModel<FavoritesViewModel>()

    BackHandler(
        enabled = navBackStackEntry?.destination?.route == MovieDetailsDestination.route,
        onBack = navController::navigateUp
    )

    Scaffold(
        topBar = {
            TopBar(
                navigationIcon = {
                    if (showBackIcon) BackIcon(
                        onBackClick = navController::navigateUp
                    )
                },
                logoImage = { LogoImage() }
            )
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.FavoritesDestination
                    ),
                    onNavigateToDestination = {
                        navController.navigate(it.route) {
                            popUpTo(it.route) {
                                inclusive = true
                            }
                        }
                    },
                    currentDestination = navBackStackEntry?.destination
                )
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeRoute(
                        onNavigateToMovieDetails = {
                            navController.navigate(
                                MovieDetailsDestination.createNavigationRoute(it)
                            )
                        },
                        viewModel = homeViewModel
                    )
                }
                composable(NavigationItem.FavoritesDestination.route) {
                    FavoritesRoute(
                        onNavigateToMovieDetails = {
                            navController.navigate(
                                MovieDetailsDestination.createNavigationRoute(it)
                            )
                        },
                        viewModel = favoritesViewModel
                    )
                }
                composable(
                    route = MovieDetailsDestination.route,
                    arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType }),
                ) {
                    val movieId = it.arguments!!.getInt(MOVIE_ID_KEY)
                    val viewModel =
                        getViewModel<MovieDetailsViewModel>(parameters = { parametersOf(movieId) })
                    MovieDetailsRoute(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navigationIcon: @Composable (() -> Unit)? = null,
    logoImage: @Composable (() -> Unit)
) {
    if (navigationIcon != null) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Blue
            ),
            title = logoImage,
            navigationIcon = navigationIcon
        )
    } else
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Blue
            ),
            title = logoImage
        )
}

@Composable
private fun BackIcon(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        modifier = modifier
            .padding(start = 10.dp)
            .clickable { onBackClick() },
        contentDescription = stringResource(id = R.string.back_button),
        tint = Color.White
    )
}

@Composable
private fun LogoImage() {
    Image(
        modifier = Modifier.fillMaxHeight(0.02F),
        contentScale = ContentScale.FillHeight,
        painter = painterResource(
            id = R.drawable.tmdb_logo,
        ),
        contentDescription = stringResource(id = R.string.tmdb_logo),
    )
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?
) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background,
    ) {
        destinations.forEach { destination ->
            AddItem(
                destination = destination,
                onNavigateToDestination = onNavigateToDestination,
                currentDestination = currentDestination
            )
        }
    }
}

@Composable
private fun RowScope.AddItem(
    destination: NavigationItem,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigationItem(
        modifier = Modifier,
        label = {
            Text(
                text = stringResource(id = destination.labelId),
                fontSize = 10.sp
            )
        },
        icon = {
            Image(
                modifier = Modifier.fillMaxHeight(0.25F),
                painter = painterResource(
                    id =
                    if (currentDestination?.route == destination.route)
                        destination.selectedIconId
                    else
                        destination.unselectedIconId
                ),
                contentDescription = destination.labelId.toString(),
                contentScale = ContentScale.FillHeight
            )
        },
        onClick = { onNavigateToDestination(destination) },
        selected = currentDestination?.route == destination.route
    )
}
