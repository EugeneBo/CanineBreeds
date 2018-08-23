package eugenebo.com.github.caninebreeds;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import eugenebo.com.github.caninebreeds.view.BreedFragment;
import static eugenebo.com.github.caninebreeds.view.BreedFragment.capitalizeFirstLetter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBreedListFragment();
    }

    public void initBreedListFragment() {
        initFragment(new BreedFragment());
    }

    public void initFragment(final Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    public void setActionBarTitle(final String title) {
        (getSupportActionBar()).
                setTitle(capitalizeFirstLetter(title));
    }
}
