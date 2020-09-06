package io.github.slflfl12.local.dao

import androidx.room.*
import io.github.slflfl12.local.model.PersonEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: PersonEntity): Completable

    @Update
    fun updatePerson(person: PersonEntity): Completable

    @Query("SELECT * FROM people WHERE id = :id")
    fun getPerson(id: Int): Single<PersonEntity>
}