package com.williamzabot.queens.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.williamzabot.queens.data.db.dao.FavoriteDAO
import com.williamzabot.queens.data.db.entity.QueenEntity

@Database(entities = [QueenEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val favoriteDAO : FavoriteDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    )
                        .build()
                }
                return instance
            }
        }
    }
}
