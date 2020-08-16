package io.github.slflfl12.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.github.slflfl12.local.database.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
abstract class LocalDataBase {

    lateinit var db: AppDatabase

    @Before
    fun initDB() {
        this.db =
            Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()

    }

    @After
    fun closeDB() {
        this.db.close()
    }
}