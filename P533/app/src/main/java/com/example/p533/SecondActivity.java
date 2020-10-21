package com.example.p533;

import android.app.ProgressDialog;
import android.content.Context;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;
    LinearLayout container;
    ArrayList<Movie> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        list = new ArrayList<>();
        getData();
    }

    private void getData() {
        String url = "http://192.168.0.6/myMovie/movie.jsp";
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20181101";
        MovieAsync movieAsync = new ItemAsync();
        movieAsync.execute(url); //호출 시,
    }
    class MovieAsync extends AsyncTask<String, Void, String> {
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
                    int rank = jo.getInt("rank");
                    String movie_name = jo.getString("movieNm");
                    long sales_amount = jo.getLong("sales_amount");
                    String open_date = jo.getString("open_date");
                    String img = jo.getString("img");
                    Movie movie = new Movie(rank, movie_name, sales_amount, open_date, img);
                    list.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MovieAdapter movieAdapter = new MovieAdapter();
            listView.setAdapter(movieAdapter);
        }
    }//END ASYNC TASK

    class MovieAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View movieView = null;
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            movieView = inflater.inflate(R.layout.movie_list, container, true);
            TextView txt_rank = movieView.findViewById(R.id.txt_rank);
            TextView txt_movie_name = movieView.findViewById(R.id.txt_movie_name);
            TextView txt_sales_amount = movieView.findViewById(R.id.txt_sales_amount);
            TextView txt_open_date = movieView.findViewById(R.id.txt_open_date);
            final ImageView imageView = movieView.findViewById(R.id.imageView);

            txt_rank.setText(list.get(position).getRank()+"");
            txt_movie_name.setText(list.get(position).getMovie_name());
            txt_sales_amount.setText(list.get(position).getSales_amount()+"");
            txt_open_date.setText(list.get(position).getOpen_date());

            String img = list.get(position).getImg();
            final String url = "http://192.168.0.6/myMovie/img/";
            GetImg t1 = new GetImg(img, url, imageView);
            t1.start();

            return movieView;
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
            }//END run()
        }//END GetImg
    }//END ADAPTER
}
