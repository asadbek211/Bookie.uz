package com.bizmiz.bookieuz.di
import com.bizmiz.bookieuz.ui.auth.api.ApiClient
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.auth.sign_in.SignInViewModel
import com.bizmiz.bookieuz.ui.auth.sign_up.SignUpViewModel
import com.bizmiz.bookieuz.ui.main.book_details.BookDetailsViewModel
import com.bizmiz.bookieuz.ui.main.genre.karakalpak_folklor.KarakalpakFalkloreViewModel
import com.bizmiz.bookieuz.ui.main.genre.karakalpak_literature.KarakalpakLiteratureViewModel
import com.bizmiz.bookieuz.ui.main.genre.search.SearchViewModel
import com.bizmiz.bookieuz.ui.main.genre.short_audios.ShortAudiosViewModel
import com.bizmiz.bookieuz.ui.main.genre.uzbek_literature.UzbekLiteratureViewModel
import com.bizmiz.bookieuz.ui.main.genre.view_model.GenreViewModel
import com.bizmiz.bookieuz.ui.main.genre.world_literature.WorldLiteratureViewModel
import com.bizmiz.bookieuz.ui.main.home.latest.LatestViewModel
import com.bizmiz.bookieuz.ui.main.home.views.ViewsBookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { ApiClient.getClient() }
    single { NetworkHelper(get()) }
}
val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { GenreViewModel(get()) }
    viewModel { LatestViewModel(get()) }
    viewModel { ViewsBookViewModel(get()) }
    viewModel { WorldLiteratureViewModel(get()) }
    viewModel { KarakalpakLiteratureViewModel(get()) }
    viewModel { UzbekLiteratureViewModel(get()) }
    viewModel { ShortAudiosViewModel(get()) }
    viewModel { KarakalpakFalkloreViewModel(get()) }
    viewModel { BookDetailsViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}