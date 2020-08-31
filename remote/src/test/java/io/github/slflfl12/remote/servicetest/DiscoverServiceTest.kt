package io.github.slflfl12.remote.servicetest

import io.github.slflfl12.remote.TestClient
import io.github.slflfl12.remote.api.DiscoverApiService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.jvm.Throws

@RunWith(JUnit4::class)
class DiscoverServiceTest {

    private lateinit var discoverService: DiscoverApiService

    @Before
    fun setUp() {
        discoverService = TestClient.createService()
    }

    @Throws(IOException::class)
    @Test
    fun `get Discover Tvs`() {
        discoverService.getDiscoverTv(2).test()
            .assertValue { response ->
                response.page == 2
            }
            .awaitDone(5, TimeUnit.SECONDS)
            .dispose()
    }

    @Throws(IOException::class)
    @Test
    fun `get Discover Movies`() {
        discoverService.getDiscoverMovie(2).test()
            .assertValue { response ->
                response.page == 2
            }
            .awaitDone(5, TimeUnit.SECONDS)
            .dispose()
    }
}