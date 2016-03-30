package bubtjobs.com.handeler;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

    }
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
               // updateStatus(); //this function can change value of mInterval.
                Toast.makeText(MainActivity.this,"running....",Toast.LENGTH_SHORT).show();
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    void startRepeatingTask() {
        mStatusChecker.run();
    }
    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopRepeatingTask();
        Toast.makeText(MainActivity.this,"pause....",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startRepeatingTask();
    }
}
