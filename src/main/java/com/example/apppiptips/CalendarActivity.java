package com.example.apppiptips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    private SQLiteHandler dbHandler;
    private Spinner selectedPair;
    private Spinner selectedTime;
    private CalendarView calendarView;
    private String selectedDate;
    private SQLiteDatabase SQLite;
    private TextView textView;
    Button btNotification;
    private final String CHANNEL_ID = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        createNotificationChannel();
        textView = findViewById(R.id.textView);
        calendarView = findViewById(R.id.calendarView);
        selectedPair = findViewById(R.id.pair_spinner);
        selectedTime = findViewById(R.id.time_spinner);
        btNotification = findViewById(R.id.saveDate);

        ImageButton imageButton = findViewById(R.id.btNotification);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, "Reminder set!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CalendarActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(CalendarActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long timeAtButtonClick = System.currentTimeMillis();
                long oneHour = 3600000; // Reminds you in one hour

                alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + oneHour, pendingIntent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year) + month + dayOfMonth;
                readDB(calendarView);
            }
        });

        try {
            dbHandler = new SQLiteHandler(this, "CalendarDatabase",null, 1);
            SQLite = dbHandler.getWritableDatabase();
            SQLite.execSQL("CREATE TABLE CalendarDatabase(Date TEXT, Pair TEXT, Time TEXT)");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification";
            String description = "Include all notifications for Oreo up.";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void insertDB (View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", selectedDate);
        contentValues.put("Pair", selectedPair.getSelectedItem().toString());
        contentValues.put("Time", selectedTime.getSelectedItem().toString());
        SQLite.insert("CalendarDatabase", null, contentValues);
    }

    public void readDB (View view) {
        String query = "SELECT Pair FROM CalendarDatabase WHERE Date =" + selectedDate;
        String pair = selectedPair.getSelectedItem().toString();
        String time = selectedTime.getSelectedItem().toString();
        try {
            Cursor cursor = SQLite.rawQuery(query, null);
            cursor.moveToFirst();
            textView.setText(cursor.getString(0));
        }
        catch (Exception e) {
            e.printStackTrace();
            textView.setText("");
        }
    }
}
