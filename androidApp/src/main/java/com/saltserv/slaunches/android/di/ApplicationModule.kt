package com.saltserv.slaunches.android.di

import android.content.Context
import com.saltserv.SpaceXSDK
import com.saltserv.slaunches.cache.DatabaseDriverFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun databaseFactory(@ApplicationContext context: Context) = DatabaseDriverFactory(context)

    @Provides
    @Singleton
    fun spacexSDK(@ApplicationContext context: Context) = SpaceXSDK(databaseFactory(context))
}
