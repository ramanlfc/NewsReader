package newsreader.raman.com.newreader.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import newsreader.raman.com.newreader.R;

public class SettingActivity extends AppCompatActivity {

    public static final String PREFERENCES = "preferences";
    private RadioGroup ddefaultChoiceRadioGroup;
    private SharedPreferences sharedPreferences;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ddefaultChoiceRadioGroup = (RadioGroup) findViewById(R.id.choice_group);

        sharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);

        url = sharedPreferences.getString("url", null);

        if (url != null) {
            setupDefault(url);
        }

        ddefaultChoiceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor edit = sharedPreferences.edit();

                switch (checkedId) {
                    case R.id.choice_india:
                        setUserChoice(edit, MainActivity.NEWS_INDIA_LINK);
                        break;
                    case R.id.choice_business:
                        setUserChoice(edit, MainActivity.BUSINESS_LINK);
                        break;
                    case R.id.choice_sport:
                        setUserChoice(edit, MainActivity.SPORTS_LINK);
                        break;
                    case R.id.choice_world:
                        setUserChoice(edit, MainActivity.WORLD_NEWS_LINK);
                        break;
                }

            }

            private void setUserChoice(SharedPreferences.Editor edit, String url) {
                edit.putString("url", url);
                edit.apply();
            }
        });// end OnCheckedChangeListener


    }

    private void setupDefault(String url) {

        ddefaultChoiceRadioGroup.clearCheck();
        RadioButton radioButton;

        switch (url) {
            case MainActivity.NEWS_INDIA_LINK:
                radioButton = (RadioButton) ddefaultChoiceRadioGroup.findViewById(R.id.choice_india);
                radioButton.setChecked(true);
                break;
            case MainActivity.SPORTS_LINK:
                radioButton = (RadioButton) ddefaultChoiceRadioGroup.findViewById(R.id.choice_sport);
                radioButton.setChecked(true);
                break;
            case MainActivity.BUSINESS_LINK:
                radioButton = (RadioButton) ddefaultChoiceRadioGroup.findViewById(R.id.choice_business);
                radioButton.setChecked(true);
                break;
            case MainActivity.WORLD_NEWS_LINK:
                radioButton = (RadioButton) ddefaultChoiceRadioGroup.findViewById(R.id.choice_world);
                radioButton.setChecked(true);
                break;
        }

    }


}
