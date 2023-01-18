package uac.imsp.clockingapp.View.activity.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

import uac.imsp.clockingapp.R;

public class SaveFingerprint extends AppCompatActivity {
    BiometricManager biometricManager ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savefingerprint);

        // calling the action bar
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.save_fingerprint);


         biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager
                .Authenticators.BIOMETRIC_WEAK | BiometricManager.Authenticators
                .DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("MY_APP_TAG", "No biometric features available on this device.");
                //b.biometricCardView.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                //b.biometricCardView.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                /*final Intent enrollIntent = new Intent
                        (Settings.ACTION_BIOMETRIC_ENROLL);

                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.BIOMETRIC_WEAK |
                                BiometricManager.Authenticators.DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, IntentIntegrator.REQUEST_CODE);*/
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);

        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor,
                new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence
                    errString) {
                super.onAuthenticationError(errorCode, errString);
                //viewEditAndSave();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.
                    AuthenticationResult result) {

                //authComplete = true;
                //viewEditAndSave();
                super.onAuthenticationSucceeded(result);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

       /* BIOMETRIC_STRONG] Authentication allows fingerprint sensor only.
[BIOMETRIC_WEAK] Authentication allows all biometrics i.e. fingerprint, face, and iris.
[DEVICE_CREDENTIAL] Authentication using a screen lock credential – the user's PIN, pattern, or password.*/

        BiometricPrompt.PromptInfo promptInfo = new
                BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.place_your_finger))
                .setSubtitle(getString(R.string.place_your_finger))
                .setDescription(getString(R.string.fingerprint_description))
                .setAllowedAuthenticators(BiometricManager.Authenticators.
                        BIOMETRIC_STRONG
                )
                .setNegativeButtonText(getString(R.string.cancel))
                .setConfirmationRequired(false)
                .build();
        biometricPrompt.authenticate(promptInfo);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    }