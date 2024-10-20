package com.bcafbootcamp.ujianandroid2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bcafbootcamp.ujianandroid2.Model.ModelItem
import com.bcafbootcamp.ujianandroid2.dao.DaoDatabase
import kotlinx.coroutines.launch

class ViewModelItem (application: Application) : AndroidViewModel(application) {
    private val itemDao = DaoDatabase.getDatabase(application).item()
    val allItem : LiveData<List<ModelItem>> = itemDao.getAll()

    fun insert(item : ModelItem) = viewModelScope.launch {
        itemDao.insert(item)
    }
    fun delete(itemId: Int) = viewModelScope.launch {
        itemDao.deleteById(itemId)
    }
    fun update(item: ModelItem) = viewModelScope.launch {
        itemDao.update(item)
    }
}