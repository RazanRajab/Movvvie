package android.example.homecinema.mainui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String option;
    private Bundle bundle;
    public static int selected_tab =0;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //find the sort method
    @Override
    public Fragment getItem(int position) {
        TabsFragment tf = new TabsFragment();
        position++;
        selected_tab= position;
        bundle= new Bundle();
        bundle.putInt("choice", selected_tab);
        tf.setArguments(bundle);
        return tf;
    }

    //no. of tabs
    @Override
    public int getCount() {
        return 3;
    }

    //label the tabs
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        position++;

        switch(position) {
            case 1:
                option ="Most Popular";
                break;

            case 2:
                option ="Top Rated";
                break;
            case 3:
                option ="Favorites";
                break;
        }
        return option;
    }

}//end class
