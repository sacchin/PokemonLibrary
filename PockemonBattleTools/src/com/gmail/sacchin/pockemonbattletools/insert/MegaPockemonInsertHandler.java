package com.gmail.sacchin.pockemonbattletools.insert;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.SQLException;

import com.gmail.sacchin.pockemonbattletools.MainActivity;
import com.gmail.sacchin.pockemonbattletools.PartyDatabaseHelper;

public class MegaPockemonInsertHandler implements Runnable {

	private PartyDatabaseHelper databaseHelper;
	private MainActivity c = null;

	public MegaPockemonInsertHandler(PartyDatabaseHelper databaseHelper, MainActivity c) {
		this.databaseHelper = databaseHelper;
		this.c = c;
	}

	public void initInsert(){
		JSONArray megaPockemonArray = new JSONArray();	
		try {
			JSONObject megaFushigibana = new JSONObject();
			megaFushigibana.put("pockemonNo", "003");
			megaFushigibana.put("h", 80);
			megaFushigibana.put("a", 100);
			megaFushigibana.put("b", 123);
			megaFushigibana.put("c", 122);
			megaFushigibana.put("d", 120);
			megaFushigibana.put("s", 80);
			megaFushigibana.put("characteristic", "あついしぼう");
			megaFushigibana.put("megaType", "x");
			megaPockemonArray.put(megaFushigibana);

			JSONObject megaRizaX = new JSONObject();
			megaRizaX.put("pockemonNo", "006");
			megaRizaX.put("h", 78);
			megaRizaX.put("a", 130);
			megaRizaX.put("b", 111);
			megaRizaX.put("c", 130);
			megaRizaX.put("d", 85);
			megaRizaX.put("s", 100);
			megaRizaX.put("characteristic", "かたいツメ");
			megaRizaX.put("megaType", "x");
			megaPockemonArray.put(megaRizaX);
			
			JSONObject megaRizaY = new JSONObject();
			megaRizaY.put("pockemonNo", "006");
			megaRizaY.put("h", 78);
			megaRizaY.put("a", 104);
			megaRizaY.put("b", 78);
			megaRizaY.put("c", 159);
			megaRizaY.put("d", 115);
			megaRizaY.put("s", 100);
			megaRizaY.put("characteristic", "ひでり");
			megaRizaY.put("megaType", "y");
			megaPockemonArray.put(megaRizaY);
			
			JSONObject megaKame = new JSONObject();
			megaKame.put("pockemonNo", "009");
			megaKame.put("h", 79);
			megaKame.put("a", 103);
			megaKame.put("b", 120);
			megaKame.put("c", 135);
			megaKame.put("d", 115);
			megaKame.put("s", 78);
			megaKame.put("characteristic", "メガランチャー");
			megaKame.put("megaType", "x");
			megaPockemonArray.put(megaKame);
			
			JSONObject megaBangi = new JSONObject();
			megaBangi.put("pockemonNo", "248");
			megaBangi.put("h", 100);
			megaBangi.put("a", 164);
			megaBangi.put("b", 150);
			megaBangi.put("c", 95);
			megaBangi.put("d", 120);
			megaBangi.put("s", 71);
			megaBangi.put("characteristic", "すなおこし");
			megaBangi.put("megaType", "x");
			megaPockemonArray.put(megaBangi);
			
			JSONObject megaHell = new JSONObject();
			megaHell.put("pockemonNo", "229");
			megaHell.put("h", 75);
			megaHell.put("a", 90);
			megaHell.put("b", 90);
			megaHell.put("c", 140);
			megaHell.put("d", 90);
			megaHell.put("s", 115);
			megaHell.put("characteristic", "サンパワー");
			megaHell.put("megaType", "x");
			megaPockemonArray.put(megaHell);
			
			JSONObject megaKairos = new JSONObject();
			megaKairos.put("pockemonNo", "127");
			megaKairos.put("h", 65);
			megaKairos.put("a", 155);
			megaKairos.put("b", 120);
			megaKairos.put("c", 65);
			megaKairos.put("d", 90);
			megaKairos.put("s", 105);
			megaKairos.put("characteristic", "スカイスキン");
			megaKairos.put("megaType", "x");
			megaPockemonArray.put(megaKairos);
			
			JSONObject megaBoss = new JSONObject();
			megaBoss.put("pockemonNo", "306");
			megaBoss.put("h", 70);
			megaBoss.put("a", 140);
			megaBoss.put("b", 230);
			megaBoss.put("c", 60);
			megaBoss.put("d", 80);
			megaBoss.put("s", 50);
			megaBoss.put("characteristic", "フィルター");
			megaBoss.put("megaType", "x");
			megaPockemonArray.put(megaBoss);
			
			JSONObject megaRaibo = new JSONObject();
			megaRaibo.put("pockemonNo", "310");
			megaRaibo.put("h", 70);
			megaRaibo.put("a", 75);
			megaRaibo.put("b", 80);
			megaRaibo.put("c", 135);
			megaRaibo.put("d", 80);
			megaRaibo.put("s", 135);
			megaRaibo.put("characteristic", "いかく");
			megaRaibo.put("megaType", "x");
			megaPockemonArray.put(megaRaibo);
			
			JSONObject megaHerakuros = new JSONObject();
			megaHerakuros.put("pockemonNo", "214");
			megaHerakuros.put("h", 80);
			megaHerakuros.put("a", 185);
			megaHerakuros.put("b", 115);
			megaHerakuros.put("c", 40);
			megaHerakuros.put("d", 105);
			megaHerakuros.put("s", 75);
			megaHerakuros.put("characteristic", "スキルリンク");
			megaHerakuros.put("megaType", "x");
			megaPockemonArray.put(megaHerakuros);
			
			JSONObject megaGenga = new JSONObject();
			megaGenga.put("pockemonNo", "094");
			megaGenga.put("h", 60);
			megaGenga.put("a", 65);
			megaGenga.put("b", 80);
			megaGenga.put("c", 170);
			megaGenga.put("d", 95);
			megaGenga.put("s", 130);
			megaGenga.put("characteristic", "かげふみ");
			megaGenga.put("megaType", "x");
			megaPockemonArray.put(megaGenga);

			JSONObject megaGyara = new JSONObject();
			megaGyara.put("pockemonNo", "130");
			megaGyara.put("h", 95);
			megaGyara.put("a", 155);
			megaGyara.put("b", 109);
			megaGyara.put("c", 70);
			megaGyara.put("d", 130);
			megaGyara.put("s", 81);
			megaGyara.put("characteristic", "かたやぶり");
			megaGyara.put("megaType", "x");
			megaPockemonArray.put(megaGyara);
			
			JSONObject megaGalra = new JSONObject();
			megaGalra.put("pockemonNo", "115");
			megaGalra.put("h", 105);
			megaGalra.put("a", 125);
			megaGalra.put("b", 100);
			megaGalra.put("c", 60);
			megaGalra.put("d", 100);
			megaGalra.put("s", 100);
			megaGalra.put("characteristic", "おやこあい");
			megaGalra.put("megaType", "x");
			megaPockemonArray.put(megaGalra);
			
			JSONObject megaPutera = new JSONObject();
			megaPutera.put("pockemonNo", "142");
			megaPutera.put("h", 80);
			megaPutera.put("a", 135);
			megaPutera.put("b", 85);
			megaPutera.put("c", 70);
			megaPutera.put("d", 95);
			megaPutera.put("s", 150);
			megaPutera.put("characteristic", "かたいツメ");
			megaPutera.put("megaType", "x");
			megaPockemonArray.put(megaPutera);

			JSONObject megaMyutuX = new JSONObject();
			megaMyutuX.put("pockemonNo", "150");
			megaMyutuX.put("h", 106);
			megaMyutuX.put("a", 190);
			megaMyutuX.put("b", 100);
			megaMyutuX.put("c", 154);
			megaMyutuX.put("d", 100);
			megaMyutuX.put("s", 130);
			megaMyutuX.put("characteristic", "ふくつのこころ");
			megaMyutuX.put("megaType", "x");
			megaPockemonArray.put(megaMyutuX);

			JSONObject megaMyutuY = new JSONObject();
			megaMyutuY.put("pockemonNo", "150");
			megaMyutuY.put("h", 106);
			megaMyutuY.put("a", 150);
			megaMyutuY.put("b", 70);
			megaMyutuY.put("c", 194);
			megaMyutuY.put("d", 120);
			megaMyutuY.put("s", 140);
			megaMyutuY.put("characteristic", "ふみん");
			megaMyutuY.put("megaType", "y");
			megaPockemonArray.put(megaMyutuY);

			JSONObject megaDenryu = new JSONObject();
			megaDenryu.put("pockemonNo", "181");
			megaDenryu.put("h", 90);
			megaDenryu.put("a", 95);
			megaDenryu.put("b", 105);
			megaDenryu.put("c", 165);
			megaDenryu.put("d", 110);
			megaDenryu.put("s", 45);
			megaDenryu.put("characteristic", "かたやぶり");
			megaDenryu.put("megaType", "x");
			megaPockemonArray.put(megaDenryu);

			JSONObject megaBasyamo = new JSONObject();
			megaBasyamo.put("pockemonNo", "257");
			megaBasyamo.put("h", 80);
			megaBasyamo.put("a", 160);
			megaBasyamo.put("b", 80);
			megaBasyamo.put("c", 130);
			megaBasyamo.put("d", 80);
			megaBasyamo.put("s", 100);
			megaBasyamo.put("characteristic", "かそく");
			megaBasyamo.put("megaType", "x");
			megaPockemonArray.put(megaBasyamo);

			JSONObject megaSanaito = new JSONObject();
			megaSanaito.put("pockemonNo", "282");
			megaSanaito.put("h", 68);
			megaSanaito.put("a", 85);
			megaSanaito.put("b", 65);
			megaSanaito.put("c", 165);
			megaSanaito.put("d", 135);
			megaSanaito.put("s", 100);
			megaSanaito.put("characteristic", "フェアリースキン");
			megaSanaito.put("megaType", "x");
			megaPockemonArray.put(megaSanaito);

			JSONObject megaKucheat = new JSONObject();
			megaKucheat.put("pockemonNo", "303");
			megaKucheat.put("h", 50);
			megaKucheat.put("a", 105);
			megaKucheat.put("b", 125);
			megaKucheat.put("c", 65);
			megaKucheat.put("d", 95);
			megaKucheat.put("s", 50);
			megaKucheat.put("characteristic", "ちからもち");
			megaKucheat.put("megaType", "x");
			megaPockemonArray.put(megaKucheat);

			JSONObject megaJupeta = new JSONObject();
			megaJupeta.put("pockemonNo", "354");
			megaJupeta.put("h", 64);
			megaJupeta.put("a", 165);
			megaJupeta.put("b", 75);
			megaJupeta.put("c", 93);
			megaJupeta.put("d", 83);
			megaJupeta.put("s", 75);
			megaJupeta.put("characteristic", "いたずらごころ");
			megaJupeta.put("megaType", "x");
			megaPockemonArray.put(megaJupeta);

			JSONObject megaAbsol = new JSONObject();
			megaAbsol.put("pockemonNo", "359");
			megaAbsol.put("h", 65);
			megaAbsol.put("a", 150);
			megaAbsol.put("b", 60);
			megaAbsol.put("c", 115);
			megaAbsol.put("d", 60);
			megaAbsol.put("s", 115);
			megaAbsol.put("characteristic", "マジックミラー");
			megaAbsol.put("megaType", "x");
			megaPockemonArray.put(megaAbsol);

			JSONObject megaGabli = new JSONObject();
			megaGabli.put("pockemonNo", "445");
			megaGabli.put("h", 108);
			megaGabli.put("a", 170);
			megaGabli.put("b", 115);
			megaGabli.put("c", 120);
			megaGabli.put("d", 95);
			megaGabli.put("s", 92);
			megaGabli.put("characteristic", "すなのちから");
			megaGabli.put("megaType", "x");
			megaPockemonArray.put(megaGabli);

			JSONObject megaRukario = new JSONObject();
			megaRukario.put("pockemonNo", "448");
			megaRukario.put("h", 70);
			megaRukario.put("a", 145);
			megaRukario.put("b", 88);
			megaRukario.put("c", 140);
			megaRukario.put("d", 70);
			megaRukario.put("s", 112);
			megaRukario.put("characteristic", "てきおうりょく");
			megaRukario.put("megaType", "x");
			megaPockemonArray.put(megaRukario);

			JSONObject megaYukinoo = new JSONObject();
			megaYukinoo.put("pockemonNo", "460");
			megaYukinoo.put("h", 90);
			megaYukinoo.put("a", 132);
			megaYukinoo.put("b", 105);
			megaYukinoo.put("c", 132);
			megaYukinoo.put("d", 105);
			megaYukinoo.put("s", 30);
			megaYukinoo.put("characteristic", "ゆきふらし");
			megaYukinoo.put("megaType", "x");
			megaPockemonArray.put(megaYukinoo);

			JSONObject megaFudhin = new JSONObject();
			megaFudhin.put("pockemonNo", "065");
			megaFudhin.put("h", 55);
			megaFudhin.put("a", 50);
			megaFudhin.put("b", 65);
			megaFudhin.put("c", 175);
			megaFudhin.put("d", 95);
			megaFudhin.put("s", 150);
			megaFudhin.put("characteristic", "トレース");
			megaFudhin.put("megaType", "x");
			megaPockemonArray.put(megaFudhin);

			JSONObject megaHassamu = new JSONObject();
			megaHassamu.put("pockemonNo", "212");
			megaHassamu.put("h", 70);
			megaHassamu.put("a", 150);
			megaHassamu.put("b", 140);
			megaHassamu.put("c", 65);
			megaHassamu.put("d", 100);
			megaHassamu.put("s", 75);
			megaHassamu.put("characteristic", "テクニシャン");
			megaHassamu.put("megaType", "x");
			megaPockemonArray.put(megaHassamu);

			JSONObject megaCharemu = new JSONObject();
			megaCharemu.put("pockemonNo", "308");
			megaCharemu.put("h", 60);
			megaCharemu.put("a", 100);
			megaCharemu.put("b", 85);
			megaCharemu.put("c", 80);
			megaCharemu.put("d", 85);
			megaCharemu.put("s", 100);
			megaCharemu.put("characteristic", "ヨガパワー");
			megaCharemu.put("megaType", "x");
			megaPockemonArray.put(megaCharemu);

			for(int i = 0 ; i < megaPockemonArray.length() ; i++){
				databaseHelper.insertMegaPockemonData(megaPockemonArray.getJSONObject(i));
			}
			c.makeToast("メガ進化ポケモンデータOK!");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		initInsert();
	}
}
