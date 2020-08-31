package io.github.slflfl12.remote.servicetest

import io.github.slflfl12.remote.TestClient
import io.github.slflfl12.remote.api.MovieApiService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.jvm.Throws


@RunWith(JUnit4::class)
class MovieServiceTest {

    private lateinit var movieService: MovieApiService

    @Before
    fun setUp() {
        movieService = TestClient.createService()
    }

    @Throws(IOException::class)
    @Test
    fun `get Movie Keywords`() {
        movieService.getKeywords(33).test()
            .assertValue { response ->
                response.id == 33
            }
            .awaitDone(5, TimeUnit.SECONDS)
            .assertNoErrors()
            .dispose()
    }

    @Throws(IOException::class)
    @Test
    fun `get Movie Videos`() {
        movieService.getVideos(33).test()
            .assertValue { response ->
                response.id == 33
            }
            .awaitDone(5, TimeUnit.SECONDS)
            .assertNoErrors()
            .dispose()
    }

    @Throws(IOException::class)
    @Test
    fun `get Movie Reviews`() {
        movieService.getReviews(33).test()
            .assertValue { response ->
                response.id == 33
            }
            .awaitDone(5, TimeUnit.SECONDS)
            .assertNoErrors()
            .dispose()
    }

}