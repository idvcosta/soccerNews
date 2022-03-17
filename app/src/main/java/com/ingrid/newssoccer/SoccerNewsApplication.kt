package com.ingrid.newssoccer

import android.app.Application
import com.ingrid.newssoccer.repositories.NewsRepository
import com.ingrid.newssoccer.ui.news.NewsViewModel
import com.ingrid.newssoccer.usecases.OpenLinkUseCase
import com.ingrid.newssoccer.usecases.ShareUserCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

val soccerNewsModule = module {
    single { NewsRepository(get()) }
    single { OpenLinkUseCase(get()) }
    single { ShareUserCase(get()) }
    viewModel { NewsViewModel(get(), get(), get()) }

}

class SoccerNewsApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@SoccerNewsApplication)
            modules(soccerNewsModule)
        }
    }
}