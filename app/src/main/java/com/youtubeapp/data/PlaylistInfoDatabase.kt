package com.youtubeapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.youtubeapp.ui.model.PlaylistInfo


@Database(entities = [PlaylistInfo::class], version = 1, exportSchema = false)
abstract class PlaylistInfoDatabase : RoomDatabase() {

    abstract fun playlistInfoDao(): PlaylistInfoDao

    companion object {
        @Volatile
        private var INSTANCE: PlaylistInfoDatabase? = null

        fun getDatabase(context: Context): PlaylistInfoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaylistInfoDatabase::class.java,
                    "playlistInfo_database"
                ).build()
                INSTANCE = instance
                return INSTANCE!!
            }

        }
    }
}