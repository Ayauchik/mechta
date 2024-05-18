package kz.petprojects.mechta.data.di

import com.google.gson.GsonBuilder
import kz.petprojects.mechta.Constants
import kz.petprojects.mechta.R
import kz.petprojects.mechta.data.network.api.PlaceholderService
import kz.petprojects.mechta.data.network.response.getSmartphones.Stickers
import kz.petprojects.mechta.data.network.response.getSmartphones.StickersDeserializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory<Converter.Factory> {
        val gson = GsonBuilder()
            .registerTypeAdapter(Stickers::class.java, StickersDeserializer())
            .create()

        GsonConverterFactory
            .create(gson)
    }
    factory { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get(HttpLoggingInterceptor::class))
            .build()
    }

    factory<Retrofit> {// (baseUrl: String) ->
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    factory<PlaceholderService> {
        this.get<Retrofit>().create(PlaceholderService::class.java)
    }
}