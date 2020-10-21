package com.example.p427;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Person> persons;
    ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
    }

    class PersonAdapter extends BaseAdapter {
        ArrayList<Person> datas;

        public PersonAdapter(ArrayList<Person> datas){
            this.datas = datas;
        }


        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(
                    R.layout.person,    //이 화면으로 뿌려라
                    container,            //이 곳으로 뿌린다.
                    true
            );
            ImageView im = view.findViewById(R.id.imageView);
            TextView tx_name = view.findViewById(R.id.tx_name);
            TextView tx_phone = view.findViewById(R.id.tx_phone);
            Person p = datas.get(position);

            im.setImageResource(p.getId());
            tx_name.setText(p.getName());
            tx_phone.setText(p.getPhone());


            return view;
        }
    }

    public void setList(ArrayList<Person> persons){
        PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);
    }
    public void getData(){
        persons= new ArrayList<>();
        persons.add(new Person(R.drawable.p1, "Kim Malsook", "010-1234-5678"));
        persons.add(new Person(R.drawable.p2, "Lee Malsook", "010-9876-5432"));
        persons.add(new Person(R.drawable.p3, "Park Malsook", "010-1234-5678"));
        persons.add(new Person(R.drawable.p4, "Choi Malsook", "010-1234-5678"));
        persons.add(new Person(R.drawable.p5, "Ha Malsook", "010-1234-5678"));
        persons.add(new Person(R.drawable.p6, "Jeong Malsook", "010-1234-5678"));
        persons.add(new Person(R.drawable.p7, "Ahn Malsook", "010-1234-5678"));
        persons.add(new Person(R.drawable.p8, "Kang Malsook", "010-1234-5678"));
        persons.add(new Person(R.drawable.p9, "Cho Malsook", "010-1234-5678"));

        setList(persons);

    }
    public void ckbt(View v){
        getData();
    }
}