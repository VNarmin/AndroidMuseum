package com.example.joy.dependency_injection

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.joy.api.ApiKeyInterceptor
import com.example.joy.api.Service
import com.example.joy.local.SozDAO
import com.example.joy.local.SozDataBase
import com.example.joy.utilities.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    @Singleton
    fun provideChucker(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        apiKeyInterceptor: ApiKeyInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(apiKeyInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor).addInterceptor(chuckerInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    @Provides
    @Singleton
    fun createRoom(@ApplicationContext context: Context): SozDataBase {
        return Room.databaseBuilder(context, SozDataBase::class.java, "local_db").build()
    }

    @Provides
    @Singleton
    fun provideDao(sozDataBase: SozDataBase):SozDAO{
        return sozDataBase.getDao()
    }
}