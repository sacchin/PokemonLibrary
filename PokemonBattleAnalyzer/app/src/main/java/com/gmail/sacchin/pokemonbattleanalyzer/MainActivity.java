package com.gmail.sacchin.pokemonbattleanalyzer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.fragment.MainFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.http.PokemonRankingDownloader;
import com.gmail.sacchin.pokemonbattleanalyzer.insert.MegaPokemonInsertHandler;
import com.gmail.sacchin.pokemonbattleanalyzer.insert.PokemonInsertHandler;
import com.gmail.sacchin.pokemonbattleanalyzer.insert.SkillInsertHandler;
import com.gmail.sacchin.pokemonbattleanalyzer.insert.ItemInsertHandler;

public class MainActivity extends AppCompatActivity {
    private static final int TOOL_ACTIVITY_CODE = 0;
    private static final int AFFINITY_ACTIVITY_CODE = 1;

    protected ExecutorService executorService = Executors.newCachedThreadPool();

    private PartyDatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.FragmentContainer, MainFragment.newInstance(0));
        transaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Pokemon Battle Tool");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        databaseHelper = new PartyDatabaseHelper(this);
        executorService.execute(
                new PokemonRankingDownloader(databaseHelper));
        firstLaunch();
    }

    private void firstLaunch() {
        SharedPreferences serviceStatePreferences = getSharedPreferences("pokemon", MODE_PRIVATE);
        if(serviceStatePreferences.getBoolean("isFirst", true)){
            executorService.execute(
                    new PokemonInsertHandler(databaseHelper));
            executorService.execute(
                    new ItemInsertHandler(databaseHelper));
            executorService.execute(
                    new SkillInsertHandler(databaseHelper));
//                executorService.execute(new PartyInsertHandler(databaseHelper, null, true, this));
//                executorService.execute(new IndividualPokemonInsertHandler(databaseHelper, this));
            executorService.execute(
                    new MegaPokemonInsertHandler(databaseHelper));

            Editor editor = serviceStatePreferences.edit();
            editor.putBoolean("isFirst", false);
            editor.apply();
            Log.i("This is First Time", "create table!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        boolean result = true;
        switch (id) {
            case R.id.action_settings:
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

    public void startToolActivity() {
        Intent intent = new Intent(MainActivity.this, SelectActivity.class);
        startActivityForResult(intent, TOOL_ACTIVITY_CODE);
    }

    public void startAffinityActivity(PBAPokemon selected) {
        if(selected == null){
            Toast.makeText(this, "エラーが発生しました。", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, AffinityComplementActivity.class);
        intent.putExtra("selected",selected.getNo());
        startActivityForResult(intent, AFFINITY_ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TOOL_ACTIVITY_CODE) {

//        }else if (requestCode == AFFINITY_ACTIVITY_CODE) {
//            partyLayout.removeAllViews();
//            party.clear();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
