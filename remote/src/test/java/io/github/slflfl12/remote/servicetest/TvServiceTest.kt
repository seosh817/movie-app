package io.github.slflfl12.remote.servicetest

import com.nhaarman.mockitokotlin2.mock
import io.github.slflfl12.remote.TestClient
import io.github.slflfl12.remote.api.MovieApiService
import io.github.slflfl12.remote.api.TvApiService
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class TvServiceTest {

    private val tvService:TvApiService = mock()

    @Before
    fun setUp() {

    }

    @Test
    fun `get Keywords`() {
        tvService.getKeywords(33).test()
            .assertValue { response ->
            response.id == 33
        }
            .awaitDone(5, TimeUnit.SECONDS)
            .dispose()
    }

    @Test
    fun `get Reviews`() {
        tvService.getReviews(33).test()
            .assertValue { response ->
                response.id == 33
            }
            .awaitDone(5, TimeUnit.SECONDS)
            .dispose()
    }

    @Test
    fun `get Videos`() {
        tvService.getVideos(33).test()
            .assertValue { response ->
                response.id == 33
            }
            .awaitDone(5, TimeUnit.SECONDS)
            .dispose()
    }


}