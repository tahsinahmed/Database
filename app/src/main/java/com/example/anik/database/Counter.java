package com.example.anik.database;

/**
 * Created by stahs on 11/11/2017.
 */
        import android.os.Handler;
        import android.os.SystemClock;
        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;

public class Counter extends Fragment{

    Button btnstart,btnstop,btnpause;
    TextView textView;
    LinearLayout container1;
    Handler customHandler = new Handler();

    long startTime=0L,timeInMilisecond=0L,timeSwapBuff=0L,updateTime=0L;

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilisecond=SystemClock.uptimeMillis()-startTime;
            updateTime=timeSwapBuff+timeInMilisecond;
            int sec = (int)(updateTime/1000);
            int min=sec/60;
            sec%=60;
            int milisec=(int)(updateTime%1000);
            textView.setText(""+min+":"+String.format("%02d",sec)+":"
                                        +String.format("%03d",milisec));
            customHandler.postDelayed(this,0);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.counter, container, false);


        btnstart=(Button)rootView.findViewById(R.id.button8);
        btnpause=(Button)rootView.findViewById(R.id.button9);
        btnstop=(Button)rootView.findViewById(R.id.button10);

        textView=(TextView)rootView.findViewById(R.id.textView3);
        container1=(LinearLayout)rootView.findViewById(R.id.container);


        btnstart.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        startTime= SystemClock.uptimeMillis();

                        customHandler.postDelayed(updateTimerThread,0);
                    }
                }
        );


        btnpause.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        timeSwapBuff+=timeInMilisecond;
                        customHandler.removeCallbacks(updateTimerThread);
                    }
                }
        );

        btnstop.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                       LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
                        View addView=inflater.inflate(R.layout.rows,null);
                        TextView textValue =(TextView)addView.findViewById(R.id.textcontent);
                        textValue.setText(textView.getText());
                        container1.addView(addView);
                    }
                }
        );


        return rootView;
    }
}
