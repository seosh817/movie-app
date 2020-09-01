package io.github.slflfl12.remote.datasourcetest

import com.nhaarman.mockitokotlin2.mock
import io.github.slflfl12.data.remote.MovieRemoteDataSource
import io.github.slflfl12.remote.api.MovieApiService
import io.github.slflfl12.remote.source.MovieRemoteDataSourceImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRemoteDataSourceTest {


    private val movieApiService = mock<MovieApiService>()



    private lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieRemoteDataSource = MovieRemoteDataSourceImpl(movieApiService)
    }

/*    @Test
    fun getKeywordsTest() {
        movieRemoteDataSource.getKeywords(33).test()
            .assertValue { list ->
                list[0].id == 33
            }
            .dispose()
    }*/

    @Test
    fun getKeywordService() {
        movieApiService.getKeywords(33).test()
            .assertValue { response ->
                response.id == 33
            }
            .dispose()
    }
}