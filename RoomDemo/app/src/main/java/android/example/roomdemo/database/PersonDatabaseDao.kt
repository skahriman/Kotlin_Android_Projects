package android.example.roomdemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDatabaseDao {
    @Insert
    fun insert(person: Person)

    @Update
    fun update(person: Person)

    @Query("SELECT * FROM person_table WHERE id = :key")
    fun get(key: Long): Person

    @Query("SELECT * FROM person_table ORDER BY id DESC LIMIT 1")
    fun getPerson(): Person
}