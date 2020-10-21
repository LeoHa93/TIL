package com.example.p500;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;
    LinearLayout container;
    ArrayList<Item> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        list = new ArrayList<>();
        getData();
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String str_result = bundle.getString("str", "");
//        Toast.makeText(this, str_result, Toast.LENGTH_SHORT).show();
    }

    private void getData() {
//        String url = "http://192.168.0.6/android/items.jsp";
//        String url = "http://127.0.0.1/android/items.jsp";
        String url = "http://192.168.0.6/android/item.jsp";
        ItemAsync itemAsync = new ItemAsync();
        itemAsync.execute(url); //호출 시,
    }

    class ItemAsync extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(SecondActivity.this);
            progressDialog.setTitle("GET DATA ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0].toString();
            String result = HttpConnect.getString(url);
            return result;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("test", s);   //로그 찍는 함수
            progressDialog.dismiss();
            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                for(int i = 0; i < ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String name = jo.getString("name");
                    String id = jo.getString("id");
                    int age = jo.getInt("age");
                    String img = jo.getString("img");
                    int rank = jo.getInt("rank");

                    Item item = new Item(id, name, age, img, rank);
                    list.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ItemAdapter itemAdapter = new ItemAdapter();
            listView.setAdapter(itemAdapter);

        }
    }//END ASYNC TASK

    class ItemAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }
//        중요

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
//         중요
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = null;
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item, container, true);
            TextView tx_id = itemView.findViewById(R.id.textView);
            TextView tx_name = itemView.findViewById(R.id.textView2);
            TextView tx_age = itemView.findViewById(R.id.textView3);

            tx_id.setText(list.get(position).getId());
            tx_name.setText(list.get(position).getName());
            tx_age.setText(list.get(position).getAge()+"");

            //            inflater.inflate(R.layout.item, container, true);
            final ImageView imageView = itemView.findViewById(R.id.imageView);
            String img = list.get(position).getImg();
//          final String url = "http://192.168.0.6/android/img/" + img;
//          final String url = "http://127.0.0.1/android/img/" + img;
            final String url = "http://192.168.0.6/android/img/";
            GetImg t1 = new GetImg(img, url, imageView);        //여기서 thread가 돌면서 image 뿌려줌
            t1.start();


            /*랭킹에 따른 이미지 삽입*/
            final ImageView imageView2 = itemView.findViewById(R.id.imageView2);
            int rank = list.get(position).getRank();
            String rimg = null;
            if(rank==1){
                rimg = "gold.jpg";
            }else if(rank==2){
                rimg = "silver.jpg";
            }else if(rank==3){
                rimg = "bronze.jpg";
            }
            GetImg t2 = new GetImg(rimg, url, imageView2);        //여기서 thread가 돌면서 image 뿌려줌
            t2.start();
            return itemView;
        }
        class GetImg extends Thread{
            String img;
            String url;
            ImageView imageView;
            public GetImg(String img, String url, ImageView imageView){
                this.img = img;
                this.url = url;
                this.imageView = imageView;
            }

            @Override
            public void run() {
                URL httpurl = null;
                InputStream is = null;
                try {
                    httpurl = new URL(url+img);
                    is= httpurl.openStream();
                    final Bitmap bm = BitmapFactory.decodeStream(is);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bm);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//END ADAPTER
}