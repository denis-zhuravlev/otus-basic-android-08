package com.otus.otusfragmentlesson;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.otus.otusfragmentlesson.ready.FragmentNewsDetailed;
import com.otus.otusfragmentlesson.ready.FragmentNewsList;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity implements FragmentNewsList.NewsClickListener {
    private static final String LIST_TAG = "fragmentNewsList";
    private static final String DETAILS = "fragmentNewsDetailed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentNewsDetailed fragmentNewsDetailed = (FragmentNewsDetailed) getSupportFragmentManager().findFragmentByTag(DETAILS);
        if (fragmentNewsDetailed != null && !isPortrait()) {
            getSupportFragmentManager().beginTransaction().remove(fragmentNewsDetailed).commit();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentNewsList(), LIST_TAG)
                    .commit();
        } else if (isPortrait() && savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentNewsList(), LIST_TAG)
                    .commit();
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE;
    }

    private boolean isPortrait() {
        return !isLandscape();
    }

    @Override
    public void onResume() {
        super.onResume();

        Toast.makeText(this, "onResume activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

        //Toast.makeText(this, "onAttachFragment " + fragment.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();

        if (fragment instanceof FragmentNewsList) {
            ((FragmentNewsList) fragment).setListener(this);
        }
    }

    @Override
    public void onNewsClick(String newsText) {
        //Toast.makeText(this, newsText, Toast.LENGTH_SHORT).show();
        if (isLandscape()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerDetailed, FragmentNewsDetailed.newInstance(newsText), DETAILS)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, FragmentNewsDetailed.newInstance(newsText), DETAILS)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
