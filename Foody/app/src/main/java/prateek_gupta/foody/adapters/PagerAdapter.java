package prateek_gupta.foody.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    Bundle resultBundle;
    List<Fragment> fragments;
    List<String> titles;
    FragmentManager fragmentManager;

    public PagerAdapter(Bundle resultBundle, List<Fragment> fragments, List<String> titles,
                        FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.resultBundle = resultBundle;
        this.fragments = fragments;
        this.titles = titles;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        fragments.get(position).setArguments(resultBundle);
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
