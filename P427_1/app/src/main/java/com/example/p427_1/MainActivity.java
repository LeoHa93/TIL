package com.example.p427_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Person> persons;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        linearLayout = findViewById(R.id.linearLayout2);

        //lilstview area 클릭시, dialog pop up
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //dialog에 사진 올리기 위한 작업
                LayoutInflater layoutInflater = getLayoutInflater();
                View dview = layoutInflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dlayout));
                ImageView dimg = dview.findViewById(R.id.imageView2);
                dimg.setImageResource(persons.get(position).getImg());
                builder.setView(dview);
                //끝

                builder.setTitle("hi");
                builder.setMessage("NAME : " + persons.get(position).getName());

                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }


    class PersonAdapter extends BaseAdapter{
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
            ImageView im = view.findViewById(R.id.imageView);
            TextView tx_id = view.findViewById(R.id.tx_id);
            TextView tx_name = view.findViewById(R.id.tx_name);
            TextView tx_age = view.findViewById(R.id.tx_age);
            Person p = datas.get(position);

            im.setImageResource(p.getImg());
            tx_id.setText(p.getId());
            tx_name.setText(p.getName());
            tx_age.setText(p.getAge()+"");


            return view;
        }
    }       //End of PERSONADAPTER

    public void getData(){
        persons = new ArrayList<>();
        persons.add(new Person(R.drawable.p1, "id01", "Kim Malsook", 10));
        persons.add(new Person(R.drawable.p2, "id02", "Lee Malsook", 20));
        persons.add(new Person(R.drawable.p3, "id03", "Park Malsook", 30));
        persons.add(new Person(R.drawable.p4, "id04", "Choi Malsook", 40));
        persons.add(new Person(R.drawable.p5, "id05", "Kang Malsook", 50));
        persons.add(new Person(R.drawable.p6, "id06", "Cho Malsook", 60));
        persons.add(new Person(R.drawable.p7, "id07", "Lim Malsook", 70));
        persons.add(new Person(R.drawable.p8, "id08", "Ahn Malsook", 80));
        persons.add(new Person(R.drawable.p9, "id09", "Ha Malsook", 90));

        setList(persons);
    }

    private void setList(ArrayList<Person> persons) {
        PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);
    }

    public void ckbt(View v){
        getData();
    }
}