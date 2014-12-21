package com.gmail.sacchin.pokemonbattleanalyzer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gmail.sacchin.pokemonbattleanalyzer.insert.MegaPokemonInsertHandler;
import com.gmail.sacchin.pokemonbattleanalyzer.insert.PokemonInsertHandler;
import com.gmail.sacchin.pokemonbattleanalyzer.insert.SkillInsertHandler;
import com.gmail.sacchin.pokemonbattleanalyzer.insert.ItemInsertHandler;

public class MainActivity extends Activity {

    protected ExecutorService executorService = Executors.newCachedThreadPool();

    private PartyDatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();
        // FragmentTransaction を開始
        FragmentTransaction transaction = manager.beginTransaction();

        // FragmentContainer のレイアウトに、MyFragment を割当てる
        transaction.add(R.id.FragmentContainer, MainFragment.newInstance(0));

        // 変更を確定して FragmentTransaction を終える
        transaction.commit();

        firstLaunch();
    }

    private void firstLaunch() {
        SharedPreferences serviceStatePreferences = getSharedPreferences("pokemon", MODE_PRIVATE);
        if(serviceStatePreferences.getBoolean("isFirst", true)){
            executorService.execute(
                    new PokemonInsertHandler(databaseHelper, this));
            executorService.execute(
                    new ItemInsertHandler(databaseHelper, this));
            executorService.execute(
                    new SkillInsertHandler(databaseHelper, this));
//                executorService.execute(new PartyInsertHandler(databaseHelper, null, true, this));
//                executorService.execute(new IndividualPokemonInsertHandler(databaseHelper, this));
            executorService.execute(
                    new MegaPokemonInsertHandler(databaseHelper, this));

            Editor editor = serviceStatePreferences.edit();
            editor.putBoolean("isFirst", false);
            editor.apply();
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

    public void startToolActivity() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
