package com.bcafbootcamp.ujianandroid2.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bcafbootcamp.ujianandroid2.Model.ModelItem


@Dao
interface DaoItem {
    @Query("SELECT * FROM item order by id ASC")
    fun getAll(): LiveData<List<ModelItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ModelItem)

    @Query("DELETE FROM item")
    suspend fun deleteAll()

    @Query("DELETE FROM item WHERE id = :itemId")
    suspend  fun deleteById(itemId: Int)

    @Update
    suspend fun update(item: ModelItem)
}