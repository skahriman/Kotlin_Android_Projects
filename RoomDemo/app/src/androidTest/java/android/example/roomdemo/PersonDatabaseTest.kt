package android.example.roomdemo

import android.example.roomdemo.database.Person
import android.example.roomdemo.database.PersonDatabase
import android.example.roomdemo.database.PersonDatabaseDao
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PersonDatabaseTest {
    private lateinit var personDao: PersonDatabaseDao
    private lateinit var db: PersonDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, PersonDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        personDao = db.personDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetPerson() {
        val personInstance = Person(1L, "John", "Doe")
        personDao.insert(personInstance)
        val person = personDao.get(1)
        Assert.assertEquals(person?.firstName, "John")
    }
}