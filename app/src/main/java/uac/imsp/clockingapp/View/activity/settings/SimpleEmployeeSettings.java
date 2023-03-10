package uac.imsp.clockingapp.View.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import uac.imsp.clockingapp.Controller.control.settings.others.SimpleEmployeeSettingsController;
import uac.imsp.clockingapp.Controller.util.ISimpleEmployeeSettingsController;
import uac.imsp.clockingapp.R;
import uac.imsp.clockingapp.View.activity.settings.others.DarkMode;
import uac.imsp.clockingapp.View.activity.settings.others.Help;
import uac.imsp.clockingapp.View.activity.settings.others.Languages;
import uac.imsp.clockingapp.View.activity.settings.others.ReportProblem;
import uac.imsp.clockingapp.View.util.settings.ISimpleEmployeeSettingsView;

public class SimpleEmployeeSettings extends AppCompatActivity
		implements View.OnClickListener, ISimpleEmployeeSettingsView {
	private ISimpleEmployeeSettingsController simpleEmployeeSettingsPresenter;
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
		setContentView(R.layout.activity_simple_employee_settings);
		simpleEmployeeSettingsPresenter=new SimpleEmployeeSettingsController(this);
		ActionBar actionBar = getSupportActionBar();
// showing the back button in action bar
		assert actionBar != null;
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(R.string.settings);
		initView();
		simpleEmployeeSettingsPresenter=new SimpleEmployeeSettingsController(this);
	}
	public void initView(){
		 final TextView dark=findViewById(R.id.setting_dark),
				 lang=findViewById(R.id.setting_languages),
				 problem=findViewById(R.id.setting_problem),
				 help=findViewById(R.id.setting_help),
				 personalInfos=findViewById(R.id.setting_personal_infos),
				accountSettings=findViewById(R.id.setting_my_account),
				 saveFingerprint=findViewById(R.id.setting_save_fingerprint);
		 saveFingerprint.setOnClickListener(this);
		 dark.setOnClickListener(this);
		lang.setOnClickListener(this);
		problem.setOnClickListener(this);
		help.setOnClickListener(this);
		personalInfos.setOnClickListener(this);
		accountSettings.setOnClickListener(this);

	}



	@Override
	public void onClick(@NonNull View v) {
		if(v.getId()==R.id.setting_my_account){
			simpleEmployeeSettingsPresenter.onMyAccount();


		}
		else if (v.getId()==R.id.setting_personal_infos){
			simpleEmployeeSettingsPresenter.onPersonalInfos();


		}
		else if (v.getId()==R.id.setting_docs){
			simpleEmployeeSettingsPresenter.onUserDocs();

		}

		else if (v.getId()==R.id.setting_dark){
			simpleEmployeeSettingsPresenter.onDarkMode();

		}

		else if (v.getId()==R.id.setting_languages){

			simpleEmployeeSettingsPresenter.onLanguage();
		}

		else if (v.getId()==R.id.setting_problem){
			simpleEmployeeSettingsPresenter.onProblem();

		}

		else if (v.getId()==R.id.setting_help){
			simpleEmployeeSettingsPresenter.onHelp();

		}
		else if(v.getId()==R.id.setting_save_fingerprint)
			simpleEmployeeSettingsPresenter.onSaveFingerprint();

	}


	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			finish();
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onAccount() {
		startActivity(new Intent(this,Account.class));

	}

	@Override
	public void onPersonalInfos() {
		startActivity((new Intent(this, PersonalInformations.class)
				).putExtra("CURRENT_USER",
						getIntent().getIntExtra("CURRENT_USER",0))
		);

	}

	@Override
	public void onUserDocs() {

	}

	@Override
	public void onDarkMode() {
		startActivity(new Intent(this, DarkMode.class));
	}

	@Override
	public void onLanguage() {
		startActivity(new Intent(this, Languages.class));
	}

	@Override
	public void onProblem() {
		startActivity(new Intent(this, ReportProblem.class));
	}

	@Override
	public void onHelp() {
		startActivity(new Intent(this, Help.class));
	}

	@Override
	public void onSaveFingerprint() {
		startActivity((new Intent(this, SaveFingerprint.class)).
				putExtra("CURRENT_USER",
				getIntent().getIntExtra("CURRENT_USER",0))
		);
	}
}