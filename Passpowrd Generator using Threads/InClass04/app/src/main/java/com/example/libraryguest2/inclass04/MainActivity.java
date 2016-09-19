package com.example.libraryguest2.inclass04;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressdialog;
    AlertDialog.Builder passwordsprint;
    TextView tvpassworddis;
    ExecutorService threadpool;
    Handler handler;
    ArrayList<String> passarraythread;
    int length=8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvpasscountdis=(TextView) findViewById(R.id.tvpasscountdis);
        final TextView tvpasslengthdis=(TextView) findViewById(R.id.tvpasslengthdis);
        tvpassworddis=(TextView) findViewById(R.id.tvpassworddis);
        final SeekBar seekbarpasscount=(SeekBar) findViewById(R.id.seekbarpasscount);
        final SeekBar seekbarpasslength=(SeekBar) findViewById(R.id.seekbarpasslength);

        Button btnthread=(Button) findViewById(R.id.btnthread);
        Button btnasync=(Button) findViewById(R.id.btnasync);

        passwordsprint=new AlertDialog.Builder(this);





        seekbarpasscount.setProgress(0);
        seekbarpasslength.setProgress(0);


        tvpasscountdis.setText("1");
        tvpasslengthdis.setText("8");

        seekbarpasscount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int count;

                count=(seekbarpasscount.getProgress()+1);

                tvpasscountdis.setText(count+"");
        }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
        });


        seekbarpasslength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int length;

                length=(seekbarpasslength.getProgress()+8);
                tvpasslengthdis.setText(length+"");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnasync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count,length;

                count=(seekbarpasscount.getProgress()+1);
                length=seekbarpasslength.getProgress()+8;

                new AsyncPassword().execute(count,length);

            }
        });

        //ThreadPoolExecutor

        passarraythread= new ArrayList<String>();
        threadpool= Executors.newFixedThreadPool(2);





        btnthread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count;


                count=(seekbarpasscount.getProgress()+1);
                length=seekbarpasslength.getProgress()+8;

                Bundle b= new Bundle();

                b.putInt("Length",length);


                for(int i=0;i<count;i++)
                {
                    threadpool.execute(new threadwork());

                  // progressdialog.setProgress((int)((100/count)*(i+1)));
                }


                final CharSequence[] cs=passarraythread.toArray(new CharSequence[passarraythread.size()]);


                passwordsprint.setTitle("Passwords")
                        .setItems(cs, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvpassworddis.setText(cs[which]);
                            }
                        });

                final AlertDialog alert=passwordsprint.create();
                alert.show();
                alert.setCancelable(false);

            }
        });



    }

    public class threadwork implements Runnable
    {

        @Override
        public void run() {


            String passtemp;


            passtemp=Util.getPassword(length);
            Toast.makeText(getApplicationContext(),passtemp,Toast.LENGTH_LONG).show();
            passarraythread.add(passtemp);
        }
    }


























    public class AsyncPassword extends AsyncTask<Integer, Integer, ArrayList<String>>
    {


        @Override
        protected ArrayList<String> doInBackground(Integer... params)
        {

            ArrayList<String> passwords=new ArrayList<String>();

            for(int i=0;i<params[0];i++) {
                passwords.add(Util.getPassword(params[1]));
                publishProgress((int)((100/params[0])*(i+1)));
            }
            return passwords;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressdialog=new ProgressDialog(MainActivity.this);
            progressdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressdialog.setMax(100);
            progressdialog.setCancelable(false);
            progressdialog.setMessage("Generating Passwords...");
            progressdialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            progressdialog.dismiss();
            //delegate.processFinish();
           final CharSequence[] cs=s.toArray(new CharSequence[s.size()]);


            passwordsprint.setTitle("Passwords")
            .setItems(cs, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tvpassworddis.setText(cs[which]);
                }
            });

            final AlertDialog alert=passwordsprint.create();
            alert.show();
            alert.setCancelable(false);


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressdialog.setProgress(values[0]);

        }
    }



}
