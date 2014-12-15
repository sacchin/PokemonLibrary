package com.gmail.sacchin.pokemonbattleanalyzer;

import java.util.HashMap;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.Pockemon;


public class MainActivity extends Activity {
    private ScrollView scrollView;
    private LinearLayout partyLayout = null;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firstLaunch();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        partyLayout = (LinearLayout) findViewById(R.id.party);
        scrollView = (ScrollView)findViewById(R.id.scrollView);





//        FragmentTabHost mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
//        mTabHost.setup(this,getSupportFragmentManager(),R.id.content);
//
//        mTabHost.addTab(setTab( タグ,タブ名),なんとか.class,null);
//        mTabHost.addTab(setTab( タグ,タブ名),なんとか.class,null);
//
//        //TabHostオブジェクト取得
//        TabHost tabhost = getl
//        //Tab1設定
//        TabSpec tab1 = tabhost.newTabSpec(TAB[0]);
//        tab1.setIndicator(this.getResources().getString(R.string.tab1));
//        tab1.setContent(R.id.tab1);
//        tabhost.addTab(tab1);
//        //Tab2設定
//        TabSpec tab2 = tabhost.newTabSpec(TAB[1]);
//        tab2.setIndicator(this.getResources().getString(R.string.tab2));
//        tab2.setContent(R.id.tab2);
//        tabhost.addTab(tab2);
//        //Tab3設定
//        TabSpec tab3 = tabhost.newTabSpec(TAB[2]);
//        tab3.setIndicator(this.getResources().getString(R.string.tab3));
//        tab3.setContent(R.id.tab3);
//        tabhost.addTab(tab3);
//        //初期表示するタブ
//        tabhost.setCurrentTab(0);

    }

    private void firstLaunch() {
        SharedPreferences serviceStatePreferences = getSharedPreferences("pokemon", MODE_PRIVATE);
        if(serviceStatePreferences.getBoolean("isFirst", true)){
            //データベースの初期化処理を行う
            Editor editor = serviceStatePreferences.edit();
            editor.putBoolean("isFirst", false);
            editor.commit();
            Log.i("This is First Time", "create table!");
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
