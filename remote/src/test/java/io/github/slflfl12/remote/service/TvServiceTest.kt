package io.github.slflfl12.remote.service

import io.github.slflfl12.remote.MockTestUtil
import io.github.slflfl12.remote.api.TvApiService
import io.github.slflfl12.remote.di.RemoteModule
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import java.io.File
import java.util.concurrent.TimeUnit

class TvServiceTest {

    private lateinit var tvService: TvApiService


    private lateinit var okHttpClient: OkHttpClient


    @Mock
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        okHttpClient = RemoteModule.provideOkHttpClient()
        tvService = MockTestUtil.provideRetrofit(okHttpClient, mockWebServer.url("/").toString())
            .create(TvApiService::class.java)
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }


    @Test
    fun getKeywords() {
        // Given
        val json = getJson("api-response/tmdb_movie_keywords.json")
        val response = MockResponse().setBody(json)
        mockWebServer.enqueue(response)


        // When
        val testObserver = tvService.getKeywords(330).test()

        // Then
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue { response ->
                response.id == 550
            }

/*        tvService.getKeywords(550).test()
            .assertValue { response ->
                response.id == 550
            }
            .awaitDone(5, TimeUnit.SECONDS)
            .dispose()*/
    }

    /*

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
    */
    private fun getJson(path: String): String {
        val file = File("src/test/resources/$path")
        return String(file.readBytes())
    }

}