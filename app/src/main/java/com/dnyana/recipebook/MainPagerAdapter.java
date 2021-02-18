package com.dnyana.recipebook;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final int TAB_COUNT = 5;
    private String[] tabTitles = {"Street Food", "Chinese", "South Indian Breakfast", "Bakery Snacks", "Fast Food"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position) {
            case 0:
                frag = IndianStreetFoodFragment.newInstance(tabTitles[position]);
                break;
            case 1:
                frag = ChineseFragment.newInstance(tabTitles[position]);
                break;
            case 2:
                frag = SouthIndianFragment.newInstance(tabTitles[position]);
                break;
            case 3:
                frag = BakerySnacksFragment.newInstance(tabTitles[position]);
                break;
            case 4:
                frag = FastFoodFragment.newInstance(tabTitles[position]);
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
