package kz.petprojects.mechta

import android.app.Application
import kz.petprojects.mechta.data.di.mapperModule
import kz.petprojects.mechta.data.di.networkModule
import kz.petprojects.mechta.data.di.repositoryModule
import kz.petprojects.mechta.data.di.useCaseModule
import kz.petprojects.mechta.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

class CustomApplication : Application() {
    private val modulesToUse = listOf(
        networkModule,
        repositoryModule,
        useCaseModule,
        viewModelModule,
        mapperModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CustomApplication)
            parametersOf(Constants.BASE_URL)
            modules(modulesToUse)
        }
    }
}