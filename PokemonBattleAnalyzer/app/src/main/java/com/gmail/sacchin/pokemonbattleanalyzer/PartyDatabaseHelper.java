package com.gmail.sacchin.pokemonbattleanalyzer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl.RankingPokemonIn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class PartyDatabaseHelper extends SQLiteOpenHelper {
	protected static final String DB_FILE = "cache4pbuf.db";

	public static final String POKEMON_MASTER_TABLE_NAME = "pokemon";
	public static final String PARTY_TABLE_NAME = "party";
	public static final String SKILL_MASTER_TABLE_NAME = "skill";
	public static final String ITEM_MASTER_TABLE_NAME = "item";
	public static final String POKEMON_INDIVIDUAL_TABLE_NAME = "pokemon_skill";
	public static final String POKEMON_ITEM_TABLE_NAME = "pokemon_item";
	public static final String MEGA_POKEMON_TABLE_NAME = "mega_pokemon";
    private final String[] DATABASE_TABLE_NAMES;

	private Util util = new Util();

	private static final String[] DATABASE_DEFINITIONS = {
			(POKEMON_MASTER_TABLE_NAME + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY, " +
					"no TEXT, " +
					"jname TEXT, " +
					"ename TEXT, " +
					"h INTEGER, " +
					"a INTEGER, " +
					"b INTEGER, " +
					"c INTEGER, " +
					"d INTEGER, " +
					"s INTEGER, " +
					"ability1 TEXT, " +
					"ability2 TEXT, " +
					"abilityd TEXT, " +
					"type1 INTEGER, " +
					"type2 INTEGER, " +
					"weight REAL, " +
					"count INTEGER)"),
			(SKILL_MASTER_TABLE_NAME + "(" + BaseColumns._ID +
					" INTEGER PRIMARY KEY, " +
					"skillName TEXT, " +
					"type INTEGER, " +
					"power INTEGER, " +
					"accuracy INTEGER, " +
					"category INTEGER, " +
					"pp INTEGER)"),
			(ITEM_MASTER_TABLE_NAME + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY, itemName TEXT)"),
			(PARTY_TABLE_NAME + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY, " +
					"time INTEGER, " +
					"userName TEXT, " +
					"member1 INTEGER, " +
					"member2 INTEGER, " +
					"member3 INTEGER, " +
					"member4 INTEGER, " +
					"member5 INTEGER, " +
					"member6 INTEGER, " +
					"memo text)"),
			(POKEMON_INDIVIDUAL_TABLE_NAME + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY, " +
					"time INTEGER, " +
					"item TEXT, " +
					"characteristic TEXT, " +
					"skillNo1 TEXT, " +
					"skillNo2 TEXT, " +
					"skillNo3 TEXT, " +
					"skillNo4 TEXT, " +
					"evh INTEGER, " +
					"eva INTEGER, " +
					"evb INTEGER, " +
					"evc INTEGER, " +
					"evd INTEGER, " +
					"evs INTEGER, " +
					"pokemonNo String)"),
			(MEGA_POKEMON_TABLE_NAME + "(" + BaseColumns._ID + "_mega INTEGER PRIMARY KEY, " +
					"pokemonNo INTEGER, " +
					"h INTEGER, " +
					"a INTEGER, " +
					"b INTEGER, " +
					"c INTEGER, " +
					"d INTEGER, " +
					"s INTEGER, " +
					"characteristic String, " +
					"megaType String)")
	};

	public PartyDatabaseHelper(Context context) {
		super(context, DB_FILE, null, 1);

		ArrayList<String> list = new ArrayList<>();
		Field[] fields = PartyDatabaseHelper.class.getDeclaredFields();
		for(Field f : fields){
			if(f.getName().endsWith("_TABLE_NAME") &&
					String.class.equals(f.getType())){
				try {
					list.add(String.valueOf(f.get(null)));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					Log.e(getClass().getSimpleName(), "This is a bug.", e);
					System.exit(-1);
				}
			}
		}
		DATABASE_TABLE_NAMES = list.toArray(new String[list.size()]);
	}

	@Override
	synchronized public void onCreate(SQLiteDatabase sqlitedatabase) {
		createTablesIfNotExist(sqlitedatabase);
	}

	private void createTablesIfNotExist(SQLiteDatabase sqlitedatabase) {
		try {
			dropTablesIfExist(sqlitedatabase);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(String sql : DATABASE_DEFINITIONS){
			try {
				Log.v("createTablesIfNotExist", "CREATE TABLE IF NOT EXISTS " + sql);
				sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS " + sql);
			}catch (IllegalStateException e) {
				Log.w(getClass().getSimpleName(), 
						"perhaps, service was restarted or un/reinstalled.", e);
			}
		}
	}

	protected void dropTablesIfExist(SQLiteDatabase db) throws IOException {
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		for(String sql : DATABASE_TABLE_NAMES){
			try {
				Log.w("dropTablesIfExist", sql);
				db.execSQL("DROP TABLE IF EXISTS " + sql);
			}catch (IllegalStateException e) {
				Log.w(getClass().getSimpleName(),
						"perhaps, service was restarted or un/reinstalled.", e);
			}
		}
	}

	synchronized public void insertPBAPokemonData(int no, String ｊname, String ename, int h, int a, int b, int c, int d, int s,
			String ability1, String ability2, String abilityd, int type1, int type2, double weight) throws IOException, SQLException {
		insertPBAPokemonData(String.valueOf(no), ｊname, ename, h, a, b, c, d, s, ability1, ability2, abilityd, type1, type2, weight);
	}

	synchronized public void insertPBAPokemonData(String no, String ｊname, String ename, int h, int a, int b, int c, int d, int s,
			String ability1, String ability2, String abilityd, int type1, int type2, double weight) throws IOException, SQLException {
		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("no", no);
			values.put("jname", ｊname);
			values.put("ename", ename);
			values.put("h", h);
			values.put("a", a);
			values.put("b", b);
			values.put("c", c);
			values.put("d", d);
			values.put("s", s);
			values.put("ability1", ability1);
			values.put("ability2", ability2);
			values.put("abilityd", abilityd);
			values.put("type1", type1);
			values.put("type2", type2);
			values.put("weight", weight);
			values.put("count", 1000);

			db.insert(POKEMON_MASTER_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public long insertIndividualPBAPokemonData(IndividualPBAPokemon p, Timestamp time) throws IOException, SQLException {
		SQLiteDatabase db = getWritableDatabase();
		if(p == null || time == null){
			throw new NullPointerException();
		}
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("time", time.getTime());
			values.put("item", p.getItem());
			values.put("characteristic", p.getCharacteristic());
			values.put("skillNo1", p.getSkillNo1());
			values.put("skillNo2", p.getSkillNo2());
			values.put("skillNo3", p.getSkillNo3());
			values.put("skillNo4", p.getSkillNo4());
			values.put("evh", 0);
			values.put("eva", 0);
			values.put("evb", 0);
			values.put("evc", 0);
			values.put("evd", 0);
			values.put("evs", 0);
			values.put("pokemonNo", p.getRowId());

			return db.insert(POKEMON_INDIVIDUAL_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
		return -1;
	}

	synchronized public void insertSkillData(String name, int type, int power, int accuracy, int category, int pp, boolean direcr, boolean isMamoru) throws IOException, SQLException {
		if (name == null){
			throw new NullPointerException("argument is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("skillName", name);
			values.put("type", type);
			values.put("power", power);
			values.put("accuracy", accuracy);
			values.put("category", category);
			values.put("pp", pp);
			db.insert(SKILL_MASTER_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public void insertItemData(String name) throws IOException, SQLException {
		if (name == null){
			throw new NullPointerException("argument is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("itemName", name);
			db.insert(ITEM_MASTER_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public ArrayList<PBAPokemon> selectAllPBAPokemon() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.query(POKEMON_MASTER_TABLE_NAME, null, null, null, null, null, "count desc, " + BaseColumns._ID + " desc", null);

		ArrayList<PBAPokemon> list = new ArrayList<>();
		try {
			while(cur.moveToNext()) {
				PBAPokemon p = createPBAPokemon(cur);
				p.setRowId(cur.getInt(0));

				Integer id = util.pokemonImageResource.get(String.valueOf(p.getNo()));
				if(id == null){
					continue;
				}
				p.setResourceId(id);
				list.add(p);
			}
		} catch (SQLiteException e) {
			Log.e("selectAllPBAPokemon", "not found");
		}
		return list;
	}

    synchronized public PBAPokemon selectPBAPokemon(String pokemonNo) throws IOException {
        SQLiteDatabase db = getReadableDatabase();
        String[] values = {pokemonNo};
        Cursor cur = db.query(POKEMON_MASTER_TABLE_NAME, null, "no = ?", values, null, null, null, null);
        try {
            if(cur.moveToNext()) {
                PBAPokemon p = createPBAPokemon(cur);
                p.setRowId(cur.getInt(0));

                Integer id = util.pokemonImageResource.get(String.valueOf(p.getNo()));
                if(id != null){
                    p.setResourceId(id);
                }
                return p;
            }
        } catch (SQLiteException e) {
            Log.e("selectPBAPokemon", "not found");
        }
        return null;
    }

	synchronized public ArrayList<String> selectAllSkill() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.query(SKILL_MASTER_TABLE_NAME, new String[]{"skillName"}, null, null, null, null, BaseColumns._ID + " asc", null);
		ArrayList<String> list = new ArrayList<>();
		try {
			while(cur.moveToNext()) {
				list.add(cur.getString(0));
			}
		} catch (SQLiteException e) {
			Log.e("selectAllSkill", "not found");
		}
		cur.close();
		return list;
	}

	synchronized public ArrayList<String> selectAllItem() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.query(ITEM_MASTER_TABLE_NAME, new String[]{"itemName"}, null, null, null, null, BaseColumns._ID + " asc", null);
		ArrayList<String> list = new ArrayList<>();
		try {
			while(cur.moveToNext()) {
				list.add(cur.getString(0));
			}
		} catch (SQLiteException e) {
			Log.e("selectAllItem", "not found");
		}
		cur.close();
		return list;
	}

	synchronized public IndividualPBAPokemon selectIndividualPBAPokemonByID(long rowId) throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor individualCur = db.query(POKEMON_INDIVIDUAL_TABLE_NAME, null, BaseColumns._ID + " = ?", new String[]{String.valueOf(rowId)}, null, null, null, null);
		try {
			if(individualCur.moveToNext()) {
				long id = individualCur.getLong(0);
				Timestamp time = new Timestamp(individualCur.getLong(1));
				String item = individualCur.getString(2);
//				String ability = individualCur.getString(3);
				String characteristic = individualCur.getString(3);
				String skillNo1 = individualCur.getString(4);
				String skillNo2 = individualCur.getString(5);
				String skillNo3 = individualCur.getString(6);
				String skillNo4 = individualCur.getString(7);
				int evh = individualCur.getInt(8);
				int eva = individualCur.getInt(9);
				int evb = individualCur.getInt(10);
				int evc = individualCur.getInt(11);
				int evd = individualCur.getInt(12);
				int evs = individualCur.getInt(13);
				String rowNo = individualCur.getString(14);
				individualCur.close();

				PBAPokemon p = createPBAPokemon(rowNo);
				IndividualPBAPokemon result = new IndividualPBAPokemon(p, id, time, item,
						"", characteristic, skillNo1, skillNo2, skillNo3, skillNo4);
				result.setRowId(Integer.parseInt(rowNo));
				result.setHpEffortValue(evh);
				result.setAttackEffortValue(eva);
				result.setDeffenceEffortValue(evb);
				result.setSpecialAttackEffortValue(evc);
				result.setSpecialDeffenceEffortValue(evd);
				result.setSpeedEffortValue(evs);

				Integer resourceId = util.pokemonImageResource.get(result.getNo());
				if(resourceId != null){
					result.setResourceId(resourceId);
				}
				Log.v("selectIndividualPBAPok", result.toString());

				return result;
			}else{
				Log.e("selectPBAPokemonByNo", "not found");
			}
		} catch (SQLiteException e) {
			Log.e("selectPBAPokemonByNo", "not found");
		}
		return null;
	}

	private PBAPokemon createPBAPokemon(String pokemonNo){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.rawQuery("select * from " + POKEMON_MASTER_TABLE_NAME + " left outer join " + MEGA_POKEMON_TABLE_NAME + " on " +
				POKEMON_MASTER_TABLE_NAME + "." + BaseColumns._ID + " = " +
				MEGA_POKEMON_TABLE_NAME + ".pokemonNo where " + BaseColumns._ID + " = ?", new String[] {String.valueOf(pokemonNo)});

		PBAPokemon result = null;
		while(cur.moveToNext()) {
			if(result == null){
				String no = cur.getString(1);
				String jname = cur.getString(2);
				String ename = cur.getString(3);
				int h = cur.getInt(4);
				int a = cur.getInt(5);
				int b = cur.getInt(6);
				int c = cur.getInt(7);
				int d = cur.getInt(8);
				int s = cur.getInt(9);
				String ability1 = cur.getString(10);
				String ability2 = cur.getString(11);
				String abilityd = cur.getString(12);

				result = new PBAPokemon(no, jname, ename, h, a, b, c, d, s,
						ability1, ability2, abilityd, 0, 0, 0, 0);
			}
			if(0 < cur.getInt(17)){
				PBAPokemon mega = new PBAPokemon(result.getNo(), "メガ" + result.getJname(), "Mega " + result.getEname(),
						cur.getInt(19), cur.getInt(20), cur.getInt(21), cur.getInt(22), cur.getInt(23), cur.getInt(24), cur.getString(25),
						"", "", 0, 0, 0, 0);

				Integer resouceId = util.pokemonImageResource.get(result.getNo() + "m" + cur.getString(26));
				if(resouceId != null){
					mega.setResourceId(resouceId);
				}
				result.addMega(mega);
			}
		}
		cur.close();

		return result;
	}

	synchronized public void insertPartyData(Party party) throws IOException, SQLException {
		if (party == null){
			throw new NullPointerException("argument is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();

			values.put("time", System.currentTimeMillis());
			values.put("userName", party.getUserName());
			values.put("memo", "");

			for (int i = 0; i < party.getMember().size(); i++) {
				long id = this.insertIndividualPBAPokemonData(party.getMember().get(i), party.getTime());
				values.put("member" + (i + 1), id);
			}

			db.insert(PARTY_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public void updatePBAPokemonRanking(List<RankingPokemonIn> rankingList) throws IOException, SQLException {
		if (rankingList == null){
			throw new NullPointerException("argument is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
            for (RankingPokemonIn temp : rankingList){
                ContentValues values = new ContentValues();
                int rank = temp.getRanking() != 0 ? temp.getRanking() : 1000;
                values.put("count", rank);

                String pn = temp.getPokemonNo().split("-")[0];
                int intPokemonNo = Integer.parseInt(pn);
                if(intPokemonNo < 10){
                    pn = "00" + pn;
                }else if(9 < intPokemonNo && intPokemonNo < 100) {
                    pn = "0" + pn;
                }else if(intPokemonNo == 479 || intPokemonNo == 641 || intPokemonNo == 642 ||intPokemonNo == 645){
                    pn = temp.getPokemonNo();
                }

                db.update(POKEMON_MASTER_TABLE_NAME, values, "no = ?", new String[] {pn});
            }
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public void updateIndividualPBAPokemonData(
			long id, String item, String characteristic,
			String skill1, String skill2, String skill3, String skill4,
			int h, int a, int b, int c, int d, int s) throws IOException, SQLException {

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("item", item);
			values.put("characteristic", characteristic);
			values.put("skillNo1", skill1);
			values.put("skillNo2", skill2);
			values.put("skillNo3", skill3);
			values.put("skillNo4", skill4);
			values.put("evh", h);
			values.put("eva", a);
			values.put("evb", b);
			values.put("evc", c);
			values.put("evd", d);
			values.put("evs", s);

			db.update(POKEMON_INDIVIDUAL_TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] {String.valueOf(id)});

			Log.v("updateIndividual", values.toString());
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public Party selectOpponentParty() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		String[] selectArg = { "opponent" };
		Cursor cur = db.query(PARTY_TABLE_NAME, new String[] { "time", "userName",
				"member1", "member2", "member3", "member4", "member5", "member6", "memo"},
				"userName=?", selectArg, null, null, "time desc", null);
		try {
			if(cur.moveToNext()) {
				IndividualPBAPokemon member1 = selectIndividualPBAPokemonByID(cur.getLong(2));
				IndividualPBAPokemon member2 = selectIndividualPBAPokemonByID(cur.getLong(3));
				IndividualPBAPokemon member3 = selectIndividualPBAPokemonByID(cur.getLong(4));
				IndividualPBAPokemon member4 = selectIndividualPBAPokemonByID(cur.getLong(5));
				IndividualPBAPokemon member5 = selectIndividualPBAPokemonByID(cur.getLong(6));
				IndividualPBAPokemon member6 = selectIndividualPBAPokemonByID(cur.getLong(7));

				return new Party(new Timestamp(cur.getLong(0)), 
						member1, member2, member3, member4, member5, member6, 
						cur.getString(8), cur.getString(1));

			}
		} catch (SQLiteException e) {
			Log.e("selectPBAPokemonByNo", "not found");
		}
		cur.close();
		return null;
	}

	synchronized public Party selectMyParty() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		String[] selectArg = { "mine" };
		Cursor cur = db.query(PARTY_TABLE_NAME, new String[] {"time", "userName",
						"member1", "member2", "member3", "member4", "member5", "member6", "memo"},
				"userName=?", selectArg, null, null, "time desc", null);
		try {
			if(cur.moveToNext()) {
				IndividualPBAPokemon member1 = selectIndividualPBAPokemonByID(cur.getLong(2));
				IndividualPBAPokemon member2 = selectIndividualPBAPokemonByID(cur.getLong(3));
				IndividualPBAPokemon member3 = selectIndividualPBAPokemonByID(cur.getLong(4));
				IndividualPBAPokemon member4 = selectIndividualPBAPokemonByID(cur.getLong(5));
				IndividualPBAPokemon member5 = selectIndividualPBAPokemonByID(cur.getLong(6));
				IndividualPBAPokemon member6 = selectIndividualPBAPokemonByID(cur.getLong(7));

				return new Party(new Timestamp(cur.getLong(0)),
						member1, member2, member3, member4, member5, member6,
						cur.getString(8), cur.getString(1));
			}else{
				Log.e("selectPBAPokemonByNo", "not found");
			}
		} catch (SQLiteException e) {
			Log.e("selectPBAPokemonByNo", "not found");
		}
		cur.close();
		return null;
	}

	synchronized public Party selectMyChoicedParty() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		String[] selectArg = { "choiced" };
		Cursor cur = db.query(PARTY_TABLE_NAME, new String[] { "time", "userName",
						"member1", "member2", "member3", "memo"},
				"userName=?", selectArg, null, null, "time desc", null);
		try {
			if(cur.moveToNext()) {
				IndividualPBAPokemon member1 = selectIndividualPBAPokemonByID(cur.getLong(2));
				IndividualPBAPokemon member2 = selectIndividualPBAPokemonByID(cur.getLong(3));
				IndividualPBAPokemon member3 = selectIndividualPBAPokemonByID(cur.getLong(4));

				return new Party(new Timestamp(cur.getLong(0)),
						member1, member2, member3, null, null, null,
						"", cur.getString(1));

			}
		} catch (SQLiteException e) {
			Log.e("selectPBAPokemonByNo", "not found");
		}
		cur.close();
		return null;
	}

	synchronized public long insertIndividualPBAPokemonData(JSONObject individualPBAPokemon) throws IOException, SQLException {
		SQLiteDatabase db = getWritableDatabase();
		if(individualPBAPokemon == null){
			throw new NullPointerException();
		}
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put(BaseColumns._ID, individualPBAPokemon.getInt(BaseColumns._ID));
			values.put("time", individualPBAPokemon.getLong("time"));
			values.put("item", individualPBAPokemon.getString("item"));
			values.put("characteristic", individualPBAPokemon.getString("characteristic"));
			values.put("skillNo1", individualPBAPokemon.getString("skillNo1"));
			values.put("skillNo2", individualPBAPokemon.getString("skillNo2"));
			values.put("skillNo3", individualPBAPokemon.getString("skillNo3"));
			values.put("skillNo4", individualPBAPokemon.getString("skillNo4"));
			values.put("pokemonNo", individualPBAPokemon.getString("pokemonNo"));

			return db.insert(POKEMON_INDIVIDUAL_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}

	synchronized public void insertPartyData(JSONObject party) throws IOException, SQLException {
		if (party == null){
			throw new NullPointerException("argument is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put(BaseColumns._ID, party.getInt(BaseColumns._ID));
			values.put("time", party.getLong("time"));
			values.put("userName", party.getString("userName"));
			values.put("member1", party.getLong("member1"));
			values.put("member2", party.getLong("member2"));
			values.put("member3", party.getLong("member3"));
			values.put("member4", party.getLong("member4"));
			values.put("member5", party.getLong("member5"));
			values.put("member6", party.getLong("member6"));
			values.put("memo", party.getString("memo"));

			db.insert(PARTY_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void insertMegaPBAPokemonData(JSONObject megaPBAPokemon) throws IOException, SQLException {
		if (megaPBAPokemon == null){
			throw new NullPointerException("argument is null.");
		}

        String t = megaPBAPokemon.toString();
		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("pokemonNo", megaPBAPokemon.getString("pokemonNo"));
			values.put("h", megaPBAPokemon.getInt("h"));
			values.put("a", megaPBAPokemon.getInt("a"));
			values.put("b", megaPBAPokemon.getInt("b"));
			values.put("c", megaPBAPokemon.getInt("c"));
			values.put("d", megaPBAPokemon.getInt("d"));
			values.put("s", megaPBAPokemon.getInt("s"));
			values.put("characteristic", megaPBAPokemon.getString("characteristic"));
			values.put("megaType", megaPBAPokemon.getString("megaType"));

			db.insert(MEGA_POKEMON_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		} catch (JSONException e) {
            Log.w(getClass().getSimpleName(), t, e);
			e.printStackTrace();
		}
	}
	public PBAPokemon createPBAPokemon(Cursor cur){
		return new PBAPokemon(cur.getString(1), cur.getString(2), cur.getString(3), cur.getInt(4), cur.getInt(5), cur.getInt(6), cur.getInt(7), cur.getInt(8),
				cur.getInt(9), cur.getString(10), cur.getString(11), cur.getString(12), cur.getInt(13), cur.getInt(14), cur.getFloat(15), cur.getInt(16));
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}
}