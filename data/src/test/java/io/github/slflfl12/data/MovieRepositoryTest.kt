package io.github.slflfl12.data

import android.content.Context
import io.github.slflfl12.data.local.MovieLocalDataSource
import io.github.slflfl12.data.remote.MovieRemoteDataSource
import io.github.slflfl12.data.repository.MovieRepositoryImpl
import io.github.slflfl12.domain.repository.MovieRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException
import kotlin.jvm.Throws


@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    private lateinit var movieRepository: MovieRepository


}