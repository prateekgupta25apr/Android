package prateek_gupta.foody.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {

    Bundle resultBundle;
    List<Fragment> fragments;
    FragmentActivity fragmentActivity;

    public PagerAdapter(Bundle resultBundle, List<Fragment> fragments,
                        FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.resultBundle = resultBundle;
        this.fragments = fragments;
        this.fragmentActivity=fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        fragments.get(position).setArguments(resultBundle);
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
