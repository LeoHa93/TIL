package com.example.p440;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Person> persons;
    LinearLayout linearLayout;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        linearLayout = findViewById(R.id.linearLayout5);

        editText = findViewById(R.id.txt_name);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                persons.add(new Person("s.toString()", "s.toString()", "s.toString()"));
                setList(persons);
                System.out.println(persons.size());
                ckbt1();
            }
        });

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
                    linearLayout,            //이 곳으로 뿌린다.
                    true
            );
            TextView txt_member_name = view.findViewById(R.id.txt_member_name);
            TextView txt_member_birth = view.findViewById(R.id.txt_member_birth);
            TextView txt_member_phone = view.findViewById(R.id.txt_member_phone);
            Person p = datas.get(position);

            txt_member_name.setText(p.getName());
            txt_member_birth.setText(p.getBirth());
            txt_member_phone.setText(p.getPhone());


            return view;
        }
    }       //End of PERSONADAPTER
    public void getData(){
        persons = new ArrayList<>();
        persons.add(new Person("김철수", "930415", "010-1234-5678"));
        persons.add(new Person("이철수", "930416", "010-9876-5432"));

        setList(persons);
    }
    private void setList(ArrayList<Person> persons) {
        PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);
    }
    public void ckbt1(){
        setList(persons);
    }

    public void ckbt2(View v){

        getData();
    }
}