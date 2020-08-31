package io.github.slflfl12.remote.servicetest

import io.github.slflfl12.remote.TestClient
import io.github.slflfl12.remote.api.MovieApiService
import io.github.slflfl12.remote.api.TvApiService
import org.junit.Before

class TvServiceTest {

    private lateinit var tvService: TvApiService

    @Before
    fun setUp() {
        tvService = TestClient.createService()
    }



}