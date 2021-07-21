package com.example.androidroom;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.example.androidroom.Room.Student;
import com.example.androidroom.Room.StudentDatabase;

import java.util.List;

public class StudentRepository {

    private String DB_NAME = "student_database";
    private StudentDatabase studentDatabase;
    private Context context;

    public StudentRepository(Context context) {
        this.context = context;
        studentDatabase = Room.databaseBuilder(context, StudentDatabase.class, DB_NAME).build();
        //Toast.makeText(context,"Database is created.", Toast.LENGTH_SHORT).show();
    }

//    Insert Task start

     public void insertTast(Student student){

        new AsyncTask<Void, Void, Void>(){


            @Override
            protected Void doInBackground(Void... voids) {
                studentDatabase.studentDao().insertTask(student);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Toast.makeText(context, student.student_name+ " is inserted ", Toast.LENGTH_LONG).show();

            }
        }.execute();

     }

//    Insert Task end


//    get data task start

    public List<Student> getStudent(){


        List<Student> studentList = studentDatabase.studentDao().getAll();

        return studentList;
    }

//    get data task end


//    ======================== update task start ====================

    public void updateTask(final Student student){

        new AsyncTask<Void, Void, Void>(){


            @Override
            protected Void doInBackground(Void... voids) {
                studentDatabase.studentDao().updateTask(student);
                return null;
            }
        }.execute();

    }

//    ======================== update task end ====================


    //    ======================== delete task start ====================

    public void deleteTask(final Student student){

        new AsyncTask<Void, Void, Void>(){


            @Override
            protected Void doInBackground(Void... voids) {
                studentDatabase.studentDao().deleteTask(student);
                return null;
            }
        }.execute();

    }

//    ======================== delete task end ====================

}
