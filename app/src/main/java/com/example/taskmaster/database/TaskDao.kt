package com.example.taskmaster.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskmaster.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : Task)

    @Update
    suspend fun updateTask(task : Task)

    @Delete
    suspend fun deleteTask(task : Task)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE taskTitle LIKE :query OR taskDesc LIKE :query OR taskPriority LIKE :query")
    fun searchTask(query: String?): LiveData<List<Task>>
}