package com.example.handwrite;
import java.util.ArrayList;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity implements OnGesturePerformedListener
        {
         GestureLibrary mLibrary;
         GestureOverlayView gesturesView;
         TextView txt;
         @Override
 public void onCreate(Bundle savedInstanceState)
         {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         gesturesView=(GestureOverlayView)findViewById(R.id.gestures);
         gesturesView.addOnGesturePerformedListener(this);
         txt = (TextView)findViewById(R.id.textView1);
         mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
         if(!mLibrary.load())
         {
         finish();
         }
         }
         /* 根据在GestureOverlayView上画的手势来识别是否匹配手势库里的手势 */
         @Override
 public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture)
         {
         ArrayList predictions=mLibrary.recognize(gesture);
         if(predictions.size()>0)
         {
         Prediction prediction = (Prediction)predictions.get(0);
         if(prediction.score > 1.0)
         {
         Toast.makeText(this,prediction.name,Toast.LENGTH_SHORT).show();
         txt.append(prediction.name);
         }
         }
         }
        }