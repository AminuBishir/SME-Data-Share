package com.sadarwa.aminubishier.smed_share;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Welcome_screen extends AppCompatActivity {

    //declaring instance variables and objects
    private ProgressBar prg;
    private int progress=0;
    private Intent intent;
    private TextView textView;
    Handler handler= new Handler();
    private DatabaseOpenHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        prg= (ProgressBar) findViewById(R.id.progressBar);
        myDb = new DatabaseOpenHelper(this);
        textView =(TextView) findViewById(R.id.percentage_text);


        //An inner Class to create thread that will run the splash screen
         new Thread(new Runnable(){

                    @Override
                    public void run() {
                        while(progress<100){
                            progress+=10;

                            handler.post(new Runnable() {

                                @Override
                                public void run() {

                                    //show the progress of the progress bar
                                    prg.setProgress(progress);
                                    textView.setText(progress +"%");

                                    //if progress bar is full, load the next screen
                                    if(progress==prg.getMax()){

                                        //if user record exists in the database, load main menu screen
                                        if(myDb.getCompany_name()!=null){
                                             intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        //if no user record exists in the database, load Login screen
                                        else {
                                             intent = new Intent(getApplicationContext(), Login.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                    }
                                }



                            });
                            try

                            {
                                //this will make the splash screen to delay a bit
                                Thread.sleep(100);
                            }
                            catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }

                    }
                }
                //start the thread
         ).start();

    }
}
