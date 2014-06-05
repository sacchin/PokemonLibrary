package com.gmail.sacchin.pockemonbattletools;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gmail.sacchin.pockemonbattletools.entity.IndividualPockemon;
import com.gmail.sacchin.pockemonbattletools.entity.Party;
import com.gmail.sacchin.pockemonbattletools.entity.Pockemon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * ProtocolBuffer version DB for AndroidLogger
 * @vori
 * @sacchin
 */
public class PartyDatabaseHelper extends SQLiteOpenHelper {
	protected static final String 	DB_FILE = "cache4pbuf.db";

	public static final String		POCKEMON_MASTER_TABLE_NAME	= "pockemon";
	public static final String		PARTY_TABLE_NAME	= "party";
	public static final String		SKILL_MASTER_TABLE_NAME	= "skill";
	public static final String		ITEM_MASTER_TABLE_NAME		= "item";
	public static final String		POCKEMON_INDIVIDUAL_TABLE_NAME	= "pockemon_skill";
	public static final String		POCKEMON_ITEM_TABLE_NAME		= "pockemon_item";
	public static final String		MEGA_POCKEMON_TABLE_NAME		= "mega_pockemon";
	private Util util = new Util();
	

	private final String[] DATABASE_TABLE_NAMES;

	private static final String[] DATABASE_DEFINITIONS = {
		POCKEMON_MASTER_TABLE_NAME + "(" + 
				BaseColumns._ID + " INTEGER PRIMARY KEY, " + 
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
				"count INTEGER)",

				SKILL_MASTER_TABLE_NAME + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY, skillName TEXT)",
				ITEM_MASTER_TABLE_NAME + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY, itemName TEXT)",

				PARTY_TABLE_NAME + "(" + 
						BaseColumns._ID + " INTEGER PRIMARY KEY, " + 
						"time INTEGER, " +
						"userName TEXT, " +
						"member1 INTEGER, " +
						"member2 INTEGER, " +
						"member3 INTEGER, " + 
						"member4 INTEGER, " +
						"member5 INTEGER, " +
						"member6 INTEGER, " +
						"memo text)",

						POCKEMON_INDIVIDUAL_TABLE_NAME + "(" + 
								BaseColumns._ID + " INTEGER PRIMARY KEY, " +
								"time INTEGER, " +
								"item TEXT, " +
								"characteristic TEXT, " +
								"skillNo1 TEXT, " +
								"skillNo2 TEXT, " +
								"skillNo3 TEXT, " +
								"skillNo4 TEXT, " +
								"pockemonNo String)",
								
								MEGA_POCKEMON_TABLE_NAME + "(" + 
										BaseColumns._ID + "_mega INTEGER PRIMARY KEY, " +
										"pockemonNo INTEGER, " +
										"h INTEGER, " +
										"a INTEGER, " +
										"b INTEGER, " +
										"c INTEGER, " +
										"d INTEGER, " +
										"s INTEGER, " +
										"characteristic String, " +
										"megaType String)"
	};

	/**
	 * This is constructor
	 * @param context
	 */
	public PartyDatabaseHelper(Context context) {
		super(context, DB_FILE, null, 1);

		ArrayList<String> list = new ArrayList<String>();
		Field[] fields = PartyDatabaseHelper.class.getDeclaredFields();
		for(int i = 0 ; i < fields.length ; i++){
			if(fields[i].getName().endsWith("_TABLE_NAME") &&
					String.class.equals(fields[i].getType())){
				try {
					list.add(String.valueOf(fields[i].get(null)));
				} catch (IllegalArgumentException e) {
					Log.e(getClass().getSimpleName(), "This is a bug.", e);
					System.exit(-1);
				} catch (IllegalAccessException e) {
					Log.e(getClass().getSimpleName(), "This is a bug.", e);
					System.exit(-1);
				}
			}
		}
		DATABASE_TABLE_NAMES = list.toArray(new String[0]);
	}

	@Override
	synchronized public void onCreate(SQLiteDatabase sqlitedatabase) {
		createTablesIfNotExist(sqlitedatabase);
	}

	protected void createTablesIfNotExist() throws IOException{
		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		createTablesIfNotExist(db);
	}

	/**
	 * @param sqlitedatabase
	 */
	private void createTablesIfNotExist(SQLiteDatabase sqlitedatabase) {
		try {
			dropTablesIfExist(sqlitedatabase);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(int i = 0 ; i < DATABASE_DEFINITIONS.length ; i++){
			try {
				Log.e("createTablesIfNotExist", "CREATE TABLE IF NOT EXISTS " + DATABASE_DEFINITIONS[i]);
				sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_DEFINITIONS[i]);
			}catch (IllegalStateException e) {
				Log.w(getClass().getSimpleName(), 
						"perhaps, service was restarted or un/reinstalled.", e);
			}
		}
	}

	/**
	 * @param sqlitedatabase
	 * @throws IOException 
	 */
	protected void dropTablesIfExist(SQLiteDatabase db) throws IOException {
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		for (int i = 0 ; i < DATABASE_TABLE_NAMES.length ; i++) {
			try {
				Log.w("dropTablesIfExist", DATABASE_TABLE_NAMES[i]);
				db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAMES[i]);
			}catch (IllegalStateException e) {
				Log.w(getClass().getSimpleName(), 
						"perhaps, service was restarted or un/reinstalled.", e);
			}
		}
	}

	/**
	 * @param sqlitedatabase
	 * @throws IOException 
	 */
	protected void dropTablesIfExist() throws IOException {
		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		for (int i = 0 ; i < DATABASE_TABLE_NAMES.length ; i++ ) {
			try {
				Log.w("dropTablesIfExist", DATABASE_TABLE_NAMES[i]);
				db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAMES[i]);
			}catch (IllegalStateException e) {
				Log.w(getClass().getSimpleName(), 
						"perhaps, service was restarted or un/reinstalled.", e);
			}
		}
	}

	synchronized public void insertPockemonData(int no, String ｊname, String ename, int h, int a, int b, int c, int d, int s, 
			String characteristic1, String characteristic2, String characteristicd, int type1, int type2, double weight) throws IOException, SQLException {
		insertPockemonData(String.valueOf(no), ｊname, ename, h, a, b, c, d, s, characteristic1, characteristic2, characteristicd, type1, type2, weight);
	}

	synchronized public void insertPockemonData(String no, String ｊname, String ename, int h, int a, int b, int c, int d, int s, 
			String characteristic1, String characteristic2, String characteristicd, int type1, int type2, double weight) throws IOException, SQLException {
		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("no", no);
			values.put("ｊname", ｊname);
			values.put("ename", ename);
			values.put("h", h);
			values.put("a", a);
			values.put("b", b);
			values.put("c", c);
			values.put("d", d);
			values.put("s", s);
			values.put("characteristic1", characteristic1);
			values.put("characteristic2", characteristic2);
			values.put("characteristicd", characteristicd);
			values.put("type1", type1);
			values.put("type2", type2);
			values.put("weight", weight);
			values.put("count", 0);

			db.insert(POCKEMON_MASTER_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public long insertIndividualPockemonData(IndividualPockemon p, Timestamp time) throws IOException, SQLException {
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
			values.put("pockemonNo", p.getRowId());

			return db.insert(POCKEMON_INDIVIDUAL_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
		return -1;
	}

	synchronized public void insertSkillData(String name) throws IOException, SQLException {
		if (name == null){
			throw new NullPointerException("argment is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("skillName", name);
			db.insert(SKILL_MASTER_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public void insertItemData(String name) throws IOException, SQLException {
		if (name == null){
			throw new NullPointerException("argment is null.");
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

	synchronized public void insertPockemonItemData(int pockemonNo, int itemNo) throws IOException, SQLException {
		if (pockemonNo < 0 || itemNo < 0){
			throw new NullPointerException("argment is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("time", System.currentTimeMillis());
			values.put("itemNo", itemNo);
			values.put("pockemonNo", pockemonNo);
			db.insert(POCKEMON_ITEM_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public void insertPockemonSkillData(int pockemonNo, int skillNo) throws IOException, SQLException {
		if (pockemonNo < 0 || skillNo < 0){
			throw new NullPointerException("argment is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("time", System.currentTimeMillis());
			values.put("skillNo", skillNo);
			values.put("pockemonNo", pockemonNo);
			db.insert(POCKEMON_INDIVIDUAL_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public ArrayList<Pockemon> selectAllPockemon() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.query(POCKEMON_MASTER_TABLE_NAME, null, null, null, null, null, "count desc, " + BaseColumns._ID + " desc", null);

		ArrayList<Pockemon> list = new ArrayList<Pockemon>();
		try {
			while(cur.moveToNext()) {
				Pockemon p = createPockemon(cur);
				p.setRowId(cur.getInt(0));

				Integer id = util.imageResouse.get(String.valueOf(p.getNo()));
				if(id != null){
					p.setResouceId(id.intValue());
				}else{
					continue;
				}
				list.add(p);
			}
		} catch (SQLiteException e) {
			Log.e("selectAllPockemon", "not found");
		}
		return list;
	}

	synchronized public ArrayList<String> selectAllSkill() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.query(SKILL_MASTER_TABLE_NAME, new String[]{"skillName"}, null, null, null, null, BaseColumns._ID + " asc", null);
		ArrayList<String> list = new ArrayList<String>();
		try {
			while(cur.moveToNext()) {
				list.add(cur.getString(0));
			}
		} catch (SQLiteException e) {
			Log.e("selectAllSkill", "not found");
		}
		return list;
	}

	synchronized public ArrayList<String> selectAllItem() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.query(ITEM_MASTER_TABLE_NAME, new String[]{"itemName"}, null, null, null, null, BaseColumns._ID + " asc", null);
		ArrayList<String> list = new ArrayList<String>();
		try {
			while(cur.moveToNext()) {
				list.add(cur.getString(0));
			}
		} catch (SQLiteException e) {
			Log.e("selectAllItem", "not found");
		}
		return list;
	}

	synchronized public HashMap<String, Integer> selectIndividualPockemonCount() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.rawQuery("select pockemonNo, count(*) from " + POCKEMON_INDIVIDUAL_TABLE_NAME +
				" group by pockemonNo", null);
		try {
			HashMap<String, Integer> result = new HashMap<String, Integer>();
			while(cur.moveToNext()) {
				int no = cur.getInt(0);
				int count = cur.getInt(1);
				result.put(String.valueOf(no), count);
			}
			return result;
		} catch (SQLiteException e) {
			Log.e("selectPockemonByNo", "not found");
		}
		return null;
	}

	synchronized public Map<String, Integer> selectIndividualPockemonItemCount(int rowId) throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.rawQuery("select item, count(*) from " + POCKEMON_INDIVIDUAL_TABLE_NAME +
				" where pockemonNo = " + rowId + " group by item", null);

		try {
			Map<String, Integer> result = new HashMap<String, Integer>();
			while(cur.moveToNext()) {
				String item = cur.getString(0);
				if(item != null && !item.isEmpty()){
					int count = cur.getInt(1);
					result.put(item, count);
				}
			}
			result = new TreeMap<String, Integer>(result);
			return result;
		} catch (SQLiteException e) {
			Log.e("selectPockemonByNo", "not found");
		}
		return null;
	}

	synchronized public Map<String, Integer> selectIndividualPockemonSkillCount(int rowId) throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.rawQuery("select * from " + POCKEMON_INDIVIDUAL_TABLE_NAME +
				" where pockemonNo = " + rowId, null);

		try {
			Map<String, Integer> result = new HashMap<String, Integer>();
			while(cur.moveToNext()) {
				for (int i = 4; i < 8; i++) {
					String skill = cur.getString(i);
					if(skill != null && !skill.isEmpty()){
						Integer count = result.get(skill);
						if(count == null){
							result.put(skill, 1);
						}else{
							result.put(skill, count + 1);
						}
					}
				}
			}
			result = new TreeMap<String, Integer>(result);
			return result;
		} catch (SQLiteException e) {
			Log.e("selectPockemonByNo", "not found");
		}
		return null;
	}

	synchronized public IndividualPockemon selectIndividualPockemonByID(long rowId) throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor individualCur = db.query(POCKEMON_INDIVIDUAL_TABLE_NAME, null, BaseColumns._ID + " = ?", new String[] {String.valueOf(rowId)}, null, null, null, null);
		
		try {
			if(individualCur.moveToNext()) {
				long id = individualCur.getLong(0);
				Timestamp time = new Timestamp(individualCur.getLong(1));
				String item = individualCur.getString(2);
				String characteristic =  individualCur.getString(3);
				String skillNo1 = individualCur.getString(4);
				String skillNo2 = individualCur.getString(5);
				String skillNo3 = individualCur.getString(6);
				String skillNo4 = individualCur.getString(7);
				String rowNo = individualCur.getString(8);
				Cursor cur = db.rawQuery("select * from " + POCKEMON_MASTER_TABLE_NAME + " left outer join " + MEGA_POCKEMON_TABLE_NAME + " on " +
						POCKEMON_MASTER_TABLE_NAME + "." + BaseColumns._ID + " = " + 
						MEGA_POCKEMON_TABLE_NAME + ".pockemonNo where " + BaseColumns._ID + " = ?", new String[] {String.valueOf(rowNo)});

				IndividualPockemon result = null;
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
						result = new IndividualPockemon(no, jname, ename, h, a, b, c, d, s, 
								ability1, ability2, abilityd, id, time, item, 
								characteristic, skillNo1, skillNo2, skillNo3, skillNo4);
						result.setRowId(Integer.parseInt(rowNo));
						Integer resouceId = util.imageResouse.get(result.getNo());
						if(resouceId != null){
							result.setResouceId(resouceId.intValue());
						}
					}
					if(0 < cur.getInt(17)){
						Pockemon mega = new Pockemon(individualCur.getString(1), "メガ" + individualCur.getString(2), "Mega " + individualCur.getString(2), 
								cur.getInt(19), cur.getInt(20), cur.getInt(21), cur.getInt(22), cur.getInt(23), cur.getInt(24), cur.getString(25), "", "", 0, 0, 0, 0);

						Integer resouceId = util.imageResouse.get(result.getNo() + "m" + cur.getString(26));
						if(resouceId != null){
							mega.setResouceId(resouceId.intValue());
						}
						result.addMega(mega);
					}
				}
				return result;
			}
		} catch (SQLiteException e) {
			Log.e("selectPockemonByNo", "not found");
		}
		return null;
	}

	synchronized public void insertPartyData(Party party) throws IOException, SQLException {
		if (party == null){
			throw new NullPointerException("argment is null.");
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
				long id = this.insertIndividualPockemonData(party.getMember().get(i), party.getTime());
				Log.v("member" + (i + 1), "IP = " + party.getMember().get(i).getRowId() + ", id = " + id);
				values.put("member" + (i + 1), id);
			}

			db.insert(PARTY_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public void updatePockemonData(Party party) throws IOException, SQLException {
		if (party == null){
			throw new NullPointerException("argment is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			for(Pockemon p : party.getMember()){
				ContentValues values = new ContentValues();
				values.put("count", 1);
				db.update(POCKEMON_MASTER_TABLE_NAME, values, "no = ?", new String[] {String.valueOf(p.getNo())});
			}
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public void updatePockemonData(HashMap<String, Integer> countMap) throws IOException, SQLException {
		if (countMap == null){
			throw new NullPointerException("argment is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			for(String key : countMap.keySet()){
				values.put("count", countMap.get(key));
				db.update(POCKEMON_MASTER_TABLE_NAME, values, BaseColumns._ID  + " = ?", new String[] {key});
			}
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public void updateIndividualPockemonData(Party party) throws IOException, SQLException {
		if (party == null){
			throw new NullPointerException("argment is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			for(IndividualPockemon p : party.getMember()){
				ContentValues values = new ContentValues();
				values.put("item", p.getItem());
				values.put("skillNo1", p.getSkillNo1());
				values.put("skillNo2", p.getSkillNo2());
				values.put("skillNo3", p.getSkillNo3());
				values.put("skillNo4", p.getSkillNo4());
				db.update(POCKEMON_INDIVIDUAL_TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] {String.valueOf(p.getId())});
			}
			ContentValues values = new ContentValues();
			values.put("userName", party.getUserName());
			values.put("memo", party.getMemo());
			int i = db.update(PARTY_TABLE_NAME, values, "time = ?", new String[] {String.valueOf(party.getTime().getTime())});
			Log.v("test" + i, party.getUserName() + ", t = " + String.valueOf(party.getTime().getTime()));
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		}
	}

	synchronized public Party selectNewestParty() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.query(PARTY_TABLE_NAME, new String[] { "time", "userName", 
				"member1", "member2", "member3", "member4", "member5", "member6", "memo"}, 
				null, null, null, null, "time desc", null);
		try {
			if(cur.moveToNext()) {
				IndividualPockemon member1 = selectIndividualPockemonByID(cur.getLong(2));
				IndividualPockemon member2 = selectIndividualPockemonByID(cur.getLong(3));
				IndividualPockemon member3 = selectIndividualPockemonByID(cur.getLong(4));
				IndividualPockemon member4 = selectIndividualPockemonByID(cur.getLong(5));
				IndividualPockemon member5 = selectIndividualPockemonByID(cur.getLong(6));
				IndividualPockemon member6 = selectIndividualPockemonByID(cur.getLong(7));

				return new Party(new Timestamp(cur.getLong(0)), 
						member1, member2, member3, member4, member5, member6, 
						cur.getString(8), cur.getString(1));

			}
		} catch (SQLiteException e) {
			Log.e("selectPockemonByNo", "not found");
		}
		return null;
	}
	synchronized public ArrayList<Party> selectAllParty(int limit) throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		if(limit <= 0){
			limit = 50;
		}
		Cursor cur = db.query(PARTY_TABLE_NAME, new String[] { "time", "userName", 
				"member1", "member2", "member3", "member4", "member5", "member6", "memo"}, 
				null, null, null, null, "time desc", String.valueOf(limit));

		ArrayList<Party> parties = new ArrayList<Party>();
		try {
			while(cur.moveToNext()) {
				IndividualPockemon member1 = selectIndividualPockemonByID(cur.getLong(2));
				IndividualPockemon member2 = selectIndividualPockemonByID(cur.getLong(3));
				IndividualPockemon member3 = selectIndividualPockemonByID(cur.getLong(4));
				IndividualPockemon member4 = selectIndividualPockemonByID(cur.getLong(5));
				IndividualPockemon member5 = selectIndividualPockemonByID(cur.getLong(6));
				IndividualPockemon member6 = selectIndividualPockemonByID(cur.getLong(7));
				parties.add(
						new Party(new Timestamp(cur.getLong(0)), 
								member1, member2, member3, member4, member5, member6, 
								cur.getString(8), cur.getString(1)));
			}
		} catch (SQLiteException e) {
			Log.e("selectPockemonByNo", "not found");
		}
		return parties;
	}

	synchronized public JSONArray selectAllPartyForJSON() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.query(PARTY_TABLE_NAME, null, 
				null, null, null, null, "time desc", null);

		JSONArray allParty = new JSONArray();
		try {
			while(cur.moveToNext()) {
				JSONObject temp = new JSONObject();
				temp.put(BaseColumns._ID, cur.getInt(0));
				temp.put("time", cur.getLong(1));
				temp.put("userName", cur.getString(2));
				temp.put("member1", cur.getLong(3));
				temp.put("member2", cur.getLong(4));
				temp.put("member3", cur.getLong(5));
				temp.put("member4", cur.getLong(6));
				temp.put("member5", cur.getLong(7));
				temp.put("member6", cur.getLong(8));
				temp.put("memo", cur.getString(9));
				allParty.put(temp);
			}
		} catch (SQLiteException e) {
			Log.e("selectPockemonByNo", "not found");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return allParty;
	}

	synchronized public JSONArray selectAllIndiviualPockemonForJSON() throws IOException {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cur = db.query(POCKEMON_INDIVIDUAL_TABLE_NAME, null, 
				null, null, null, null, "time desc", null);
		

		JSONArray allParty = new JSONArray();
		try {
			while(cur.moveToNext()) {
				JSONObject temp = new JSONObject();
				temp.put(BaseColumns._ID, cur.getInt(0));
				temp.put("time", cur.getLong(1));
				temp.put("item", cur.getString(2));
				temp.put("characteristic", cur.getString(3));
				temp.put("skillNo1", cur.getString(4));
				temp.put("skillNo2", cur.getString(5));
				temp.put("skillNo3", cur.getString(6));
				temp.put("skillNo4", cur.getString(7));
				temp.put("pockemonNo", cur.getString(8));
				allParty.put(temp);
			}
		} catch (SQLiteException e) {
			Log.e("selectPockemonByNo", "not found");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return allParty;
	}

	synchronized public long insertIndividualPockemonData(JSONObject individualPockemon) throws IOException, SQLException {
		SQLiteDatabase db = getWritableDatabase();
		if(individualPockemon == null){
			throw new NullPointerException();
		}
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put(BaseColumns._ID, individualPockemon.getInt(BaseColumns._ID));
			values.put("time", individualPockemon.getLong("time"));
			values.put("item", individualPockemon.getString("item"));
			values.put("characteristic", individualPockemon.getString("characteristic"));
			values.put("skillNo1", individualPockemon.getString("skillNo1"));
			values.put("skillNo2", individualPockemon.getString("skillNo2"));
			values.put("skillNo3", individualPockemon.getString("skillNo3"));
			values.put("skillNo4", individualPockemon.getString("skillNo4"));
			values.put("pockemonNo", individualPockemon.getString("pockemonNo"));

			return db.insert(POCKEMON_INDIVIDUAL_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}

	synchronized public void insertPartyData(JSONObject party) throws IOException, SQLException {
		if (party == null){
			throw new NullPointerException("argment is null.");
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
	
	synchronized public void insertMegaPockemonData(JSONObject megaPockemon) throws IOException, SQLException {
		if (megaPockemon == null){
			throw new NullPointerException("argment is null.");
		}

		SQLiteDatabase db = getWritableDatabase();
		if (db.isReadOnly()){
			throw new IOException("Cannot get writable access to DB.");
		}
		try {
			ContentValues values = new ContentValues();
			values.put("pockemonNo", megaPockemon.getInt("pockemonNo"));
			values.put("h", megaPockemon.getInt("h"));
			values.put("a", megaPockemon.getInt("a"));
			values.put("b", megaPockemon.getInt("b"));
			values.put("c", megaPockemon.getInt("c"));
			values.put("d", megaPockemon.getInt("d"));
			values.put("s", megaPockemon.getInt("s"));
			values.put("characteristic", megaPockemon.getString("characteristic"));
			values.put("megaType", megaPockemon.getString("megaType"));

			db.insert(MEGA_POCKEMON_TABLE_NAME, null, values );
		}catch (IllegalStateException e) {
			Log.w(getClass().getSimpleName(), "perhaps, service was restarted or un/reinstalled.", e);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public Pockemon createPockemon(Cursor cur){
		return new Pockemon(cur.getString(1), cur.getString(2), cur.getString(3), cur.getInt(4), cur.getInt(5), cur.getInt(6), cur.getInt(7), cur.getInt(8),
				cur.getInt(9), cur.getString(10), cur.getString(11), cur.getString(12), cur.getInt(13), cur.getInt(14), cur.getFloat(15), cur.getInt(16));
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}
}