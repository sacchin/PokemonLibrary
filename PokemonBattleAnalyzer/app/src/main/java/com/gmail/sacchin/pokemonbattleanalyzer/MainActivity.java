package com.gmail.sacchin.pokemonbattleanalyzer;

import java.util.ArrayList;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonlibrary.entity.Pokemon;


public class MainActivity extends Activity {
    private LayoutParams LP = new LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    private static ScrollView scrollView;
    private static LinearLayout partyLayout = null;


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

    public void onHogehoge() {
        if(scrollView == null){
            scrollView = (ScrollView)findViewById(R.id.scrollView);
        }
        if(scrollView == null){
            Log.e("onResume", "scrollView is null");
            return;
        }
        if(0 < scrollView.getChildCount()){
            return;
        }

        LinearLayout all = new LinearLayout(this);
        all.setLayoutParams(LP);
        all.setOrientation(LinearLayout.VERTICAL);
        all.setGravity(Gravity.CENTER);
        scrollView.addView(all);

//        try {
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        ArrayList<PBAPokemon> list = new ArrayList<PBAPokemon>();
        PBAPokemon sono2 = new PBAPokemon("003", "フシギバナ", "Venusaur",  80,  82,  83,  100,  100,  80, "しんりょく", " -", "ようりょくそ",4,7,100.0f, 0);
        sono2.setResourceId(R.drawable.n003);
        list.add(sono2);
        PBAPokemon sono3 = new PBAPokemon("006", "リザードン", "Charizard",  78,  84,  78,  109,  85,  100, "もうか", " -", "サンパワー",1,9,100.5f, 0);
        sono3.setResourceId(R.drawable.n006);
        list.add(sono3);
        PBAPokemon sono4 = new PBAPokemon("009", "カメックス", "Blastoise",  79,  83,  100,  85,  105,  78, "げきりゅう", " -", "あめうけざら",2,-1,85.5f, 0);
        sono4.setResourceId(R.drawable.n009);
        list.add(sono4);
        PBAPokemon sono5 = new PBAPokemon("012", "バタフリー", "Butterfree",  60,  45,  50,  90,  80,  70, "ふくがん", " -", "いろめがね",11,9,32.0f, 0);
        sono5.setResourceId(R.drawable.n012);
        list.add(sono5);
        PBAPokemon sono6 = new PBAPokemon("015", "スピアー", "Beedrill",  65,  90,  40,  45,  80,  75, "むしのしらせ", " -", "スナイパー",11,7,129.5f, 0);
        sono6.setResourceId(R.drawable.n015);
        list.add(sono6);

        int count = 0;
        for(;count < list.size();){
            LinearLayout block = new LinearLayout(this);
            block.setLayoutParams(LP);
            block.setGravity(Gravity.CENTER);
            for(int i = 0 ; i < 4 ; i++){
                FrameLayout d = createFrameLayout(list.get(count), countMap);
                block.addView(d);
                count++;
                if(count > list.size() - 1){
                    break;
                }
            }
            all.addView(block);
        }
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
            partyLayout = (LinearLayout) rootView.findViewById(R.id.party);
            scrollView = (ScrollView)rootView.findViewById(R.id.scrollView);

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            try {
                    ( (MainActivity)activity). onHogehoge();
            } catch (ClassCastException e) {
                // Fragment が組み込まれる先の Activity に対して、FragmentCallbacks インタフェースの実装を要求する為
                // キャストに失敗した場合は、実行時例外としてプログラムのミスであることを示す
                throw new IllegalStateException("activity should implement FragmentCallbacks", e);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    public FrameLayout createFrameLayout(PBAPokemon p, HashMap<String, Integer> countMap){
        Resources r = getResources();
        FrameLayout fl = new FrameLayout(this);
        ImageView localView = new ImageView(this);
        Bitmap temp = BitmapFactory.decodeResource(r, p.getResourceId());
        Matrix matrix = new Matrix();
        matrix.postScale(150f / (float)temp.getWidth(), 150f / (float)temp.getHeight());
        temp = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(),temp.getHeight(), matrix, true);
        localView.setImageBitmap(temp);
//        fl.setOnClickListener(new OnClickFromList(this, p));
        fl.addView(localView);
        TextView tv = new TextView(this);

        Integer c = countMap.get(String.valueOf(p.getRowId()));
        if(c != null){
            tv.setText(String.valueOf(c));
        }else{
            tv.setText("0");
        }
        fl.addView(tv);
        return fl;
    }

}
