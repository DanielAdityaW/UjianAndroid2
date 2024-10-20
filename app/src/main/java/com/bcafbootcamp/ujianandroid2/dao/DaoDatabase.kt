package com.bcafbootcamp.ujianandroid2.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bcafbootcamp.ujianandroid2.Model.ModelItem

@Database(entities = [ModelItem::class], version = 1, exportSchema = false)
abstract class DaoDatabase : RoomDatabase() {

    abstract fun item(): DaoItem

    companion object{
        @Volatile
        private var INSTANCE : DaoDatabase?=null

        fun getDatabase(context:Context) : DaoDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,DaoDatabase::class.java,"dao_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}