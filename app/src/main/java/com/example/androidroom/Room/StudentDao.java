package com.example.androidroom.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    Long insertTask(Student student);

    @Update
    void updateTask(Student student);

    @Delete
    void deleteTask(Student student);

    @Query("select * from student order by rollno asc")
    List<Student> getAll();

}
