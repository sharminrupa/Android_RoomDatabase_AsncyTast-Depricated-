package com.example.androidroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.androidroom.Room.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Student> studentArrayList;
    ArrayList<Student> studentArrayList_search;
    CustomAdater customAdater;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

//        ******** this is for search statrt *******
        searchText = findViewById(R.id.searchText);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s , int start , int count , int after) {

            }

            @Override
            public void onTextChanged(CharSequence s , int start , int before , int count) {

                String text = s.toString();
                Filter(text);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        ******** this is for search end *******

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new LoadDataTask().execute();

    }

    public class LoadDataTask extends AsyncTask<Void, Void, Void>{

        StudentRepository studentRepository;
        List<Student> studentList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            studentRepository = new StudentRepository(getApplicationContext());
        }

        @Override
        protected Void doInBackground(Void... voids) {

            studentList = studentRepository.getStudent();
            studentArrayList = new ArrayList<>();
            studentArrayList_search = new ArrayList<>();

            for (int i = 0; i<studentList.size(); i++){
                    studentArrayList.add(studentList.get(i));
                    studentArrayList_search.add(studentList.get(i));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            customAdater = new  CustomAdater(ViewActivity.this , studentArrayList);

            recyclerView.setAdapter(customAdater);

        }

      // 01977 450 991

    }


    //        ******** this is for search statrt *******

    public  void  Filter(String charText){

        charText = charText.toLowerCase(Locale.getDefault());

        Log.d("filter", charText+"");
        studentArrayList.clear();

        if (charText.length() == 0){

            studentArrayList.addAll(studentArrayList_search);
            Log.d("lead data ", "All");
        }else {
            Log.d("lead data", "Filtered");
            for (Student student: studentArrayList_search){

                if (student.student_name.toLowerCase(Locale.getDefault()).contains(charText)
                  || student.contacatno.toLowerCase(Locale.getDefault()).contains(charText)
                ){
                    studentArrayList.add(student);
                }

            }

        }

        customAdater.notifyDataSetChanged();
    }

    //        ******** this is for search end *******

}