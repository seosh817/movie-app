package io.github.slflfl12.local

import androidx.room.PrimaryKey
import io.github.slflfl12.local.model.MovieEntity
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class MovieDaoTest : LocalDataBase() {

    @Test
    fun insertAndReadTest() {
        db.movieDao().insertMovieList(listOf<MovieEntity>(
            MovieEntity(
            page = 1,
                adult = false,
                backdrop_path = "",
                genre_ids = listOf(),
                id=1,
                original_title = "",
                original_language = "",
                overview = "",
                popularity = 0.0,
                poster_path = "",
                release_date = "",
                title = "",
                video = false,
                vote_average = 0.0,
                vote_count = 0,
                favorite = false
        )))
        val loadFromDB = db.movieDao().getMovieList(1).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
            .subscribe({
                assertThat()
            }, {

            }).addTo()
        assertThat(loadFromDB.page, `is`(1))
    }
}