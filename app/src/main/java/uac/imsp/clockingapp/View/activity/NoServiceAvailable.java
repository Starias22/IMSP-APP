package uac.imsp.clockingapp.View.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import uac.imsp.clockingapp.R;

public class NoServiceAvailable extends AppCompatActivity
 {
     boolean dark;
     private void retrieveSharedPreferences() {
         String PREFS_NAME="MyPrefsFile";
         SharedPreferences preferences= getSharedPreferences(PREFS_NAME,
                 Context.MODE_PRIVATE);
         dark=preferences.getBoolean("dark",false);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        retrieveSharedPreferences();
        if(dark)
            setTheme(R.style.DarkTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_service_available);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
// showing the back button in action bar
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.no_service_available);
    }
    public void initView(){
        /*TextView errorMesssage = findViewById(R.id.no_service_message);
        Button button = findViewById(R.id.no_service_add);
        button.setOnClickListener(this);*/

    }

   /* @Override
    public void onClick(View v) {
if(v.getId()==R.id.no_service_add){
    //start add service activity
    int a=2;
}*/

    }
