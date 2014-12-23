package com.gmail.sacchin.pokemonbattleanalyzer.insert;

import java.io.IOException;

import com.gmail.sacchin.pokemonbattleanalyzer.PartyDatabaseHelper;

import android.database.SQLException;
import android.util.Log;

public class ItemInsertHandler implements Runnable {
	private PartyDatabaseHelper databaseHelper;

	public ItemInsertHandler(PartyDatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	@Override
	public void run() {
		try {
			databaseHelper.insertItemData("いのちのたま");
			databaseHelper.insertItemData("こだわりメガネ");
			databaseHelper.insertItemData("こだわりハチマキ");
			databaseHelper.insertItemData("こだわりスカーフ");
			databaseHelper.insertItemData("ものしりメガネ");
			databaseHelper.insertItemData("ちからのハチマキ");
			databaseHelper.insertItemData("たつじんのおび");
			databaseHelper.insertItemData("メトロノーム");
			databaseHelper.insertItemData("あおぞらプレート");
			databaseHelper.insertItemData("いかずちプレート");
			databaseHelper.insertItemData("がんせきプレート");
			databaseHelper.insertItemData("こうてつプレート");
			databaseHelper.insertItemData("こぶしのプレート");
			databaseHelper.insertItemData("こわもてプレート");
			databaseHelper.insertItemData("しずくプレート");
			databaseHelper.insertItemData("だいちのプレート");
			databaseHelper.insertItemData("たまむしプレート");
			databaseHelper.insertItemData("つららのプレート");
			databaseHelper.insertItemData("ひのたまプレート");
			databaseHelper.insertItemData("ふしぎのプレート");
			databaseHelper.insertItemData("みどりのプレート");
			databaseHelper.insertItemData("もうどくプレート");
			databaseHelper.insertItemData("もののけプレート");
			databaseHelper.insertItemData("りゅうのプレート");
			databaseHelper.insertItemData("あくのジュエル");
			databaseHelper.insertItemData("いわのジュエル");
			databaseHelper.insertItemData("エスパージュエル");
			databaseHelper.insertItemData("かくとうジュエル");
			databaseHelper.insertItemData("くさのジュエル");
			databaseHelper.insertItemData("ゴーストジュエル");
			databaseHelper.insertItemData("こおりのジュエル");
			databaseHelper.insertItemData("じめんのジュエル");
			databaseHelper.insertItemData("でんきのジュエル");
			databaseHelper.insertItemData("どくのジュエル");
			databaseHelper.insertItemData("ドラゴンジュエル");
			databaseHelper.insertItemData("ノーマルジュエル");
			databaseHelper.insertItemData("はがねのジュエル");
			databaseHelper.insertItemData("ひこうのジュエル");
			databaseHelper.insertItemData("ほのおのジュエル");
			databaseHelper.insertItemData("みずのジュエル");
			databaseHelper.insertItemData("むしのジュエル");
			databaseHelper.insertItemData("あついいわ");
			databaseHelper.insertItemData("しめったいわ");
			databaseHelper.insertItemData("さらさらいわ");
			databaseHelper.insertItemData("つめたいいわ");
			databaseHelper.insertItemData("ひかりのねんど");
			databaseHelper.insertItemData("ねばりのかぎづめ");
			databaseHelper.insertItemData("きあいのタスキ");
			databaseHelper.insertItemData("たべのこし");
			databaseHelper.insertItemData("くろいヘドロ");
			databaseHelper.insertItemData("せんせいのツメ");
			databaseHelper.insertItemData("ピントレンズ");
			databaseHelper.insertItemData("するどいツメ");
			databaseHelper.insertItemData("のんきのおこう");
			databaseHelper.insertItemData("ひかりのこな");
			databaseHelper.insertItemData("こうかくレンズ");
			databaseHelper.insertItemData("フォーカスレンズ");
			databaseHelper.insertItemData("きれいなぬけがら");
			databaseHelper.insertItemData("おおきなねっこ");
			databaseHelper.insertItemData("かいがらのすず");
			databaseHelper.insertItemData("するどいキバ");
			databaseHelper.insertItemData("おうじゃのしるし");
			databaseHelper.insertItemData("しろいハーブ");
			databaseHelper.insertItemData("パワフルハーブ");
			databaseHelper.insertItemData("メンタルハーブ");
			databaseHelper.insertItemData("クラボのみ");
			databaseHelper.insertItemData("カゴのみ");
			databaseHelper.insertItemData("モモンのみ");
			databaseHelper.insertItemData("チーゴのみ");
			databaseHelper.insertItemData("ナナシのみ");
			databaseHelper.insertItemData("キーのみ");
			databaseHelper.insertItemData("ラムのみ");
			databaseHelper.insertItemData("オボンのみ");
			databaseHelper.insertItemData("ヒメリのみ");
			databaseHelper.insertItemData("チイラのみ");
			databaseHelper.insertItemData("リュガのみ");
			databaseHelper.insertItemData("カムラのみ");
			databaseHelper.insertItemData("ヤタピのみ");
			databaseHelper.insertItemData("ズアのみ");
			databaseHelper.insertItemData("サンのみ");
			databaseHelper.insertItemData("スターのみ");
			databaseHelper.insertItemData("ミクルのみ");
			databaseHelper.insertItemData("イバンのみ");
			databaseHelper.insertItemData("ナゾのみ");
			databaseHelper.insertItemData("ジャボのみ");
			databaseHelper.insertItemData("レンブのみ");
			databaseHelper.insertItemData("オッカのみ");
			databaseHelper.insertItemData("イトケのみ");
			databaseHelper.insertItemData("ソクノのみ");
			databaseHelper.insertItemData("リンドのみ");
			databaseHelper.insertItemData("ヤチェのみ");
			databaseHelper.insertItemData("ヨプのみ");
			databaseHelper.insertItemData("ビアーのみ");
			databaseHelper.insertItemData("シュカのみ");
			databaseHelper.insertItemData("バコウのみ");
			databaseHelper.insertItemData("ウタンのみ");
			databaseHelper.insertItemData("タンガのみ");
			databaseHelper.insertItemData("ヨロギのみ");
			databaseHelper.insertItemData("カシブのみ");
			databaseHelper.insertItemData("ハバンのみ");
			databaseHelper.insertItemData("ナモのみ");
			databaseHelper.insertItemData("リリバのみ");
			databaseHelper.insertItemData("ホズのみ");
			databaseHelper.insertItemData("かえんだま");
			databaseHelper.insertItemData("どくどくだま");
			databaseHelper.insertItemData("こうこうのしっぽ");
			databaseHelper.insertItemData("くっつきバリ");
			databaseHelper.insertItemData("くろいてっきゅう");
			databaseHelper.insertItemData("でんきだま");
			databaseHelper.insertItemData("ふといホネ");
			databaseHelper.insertItemData("こころのしずく");
			databaseHelper.insertItemData("はっきんだま");
			databaseHelper.insertItemData("しらたま");
			databaseHelper.insertItemData("こんごうだま");
			databaseHelper.insertItemData("しんかいのキバ");
			databaseHelper.insertItemData("しんかいのウロコ");
			databaseHelper.insertItemData("ながねぎ");
			databaseHelper.insertItemData("ラッキーパンチ");
			databaseHelper.insertItemData("スピードパウダー");
			databaseHelper.insertItemData("メタルパウダー");
			databaseHelper.insertItemData("じゃくてんほけん");
			databaseHelper.insertItemData("とつげきチョッキ");
			databaseHelper.insertItemData("ぼうじんゴーグル");
			
			
			
			
			databaseHelper.insertItemData("あかいいと");
			databaseHelper.insertItemData("アップグレード");
			databaseHelper.insertItemData("あやしいおこう");
			databaseHelper.insertItemData("あやしいパッチ");
			databaseHelper.insertItemData("うしおのおこう");
			databaseHelper.insertItemData("エレキブースター");
			databaseHelper.insertItemData("おうじゃのしるし");
			databaseHelper.insertItemData("おおきなねっこ");
			databaseHelper.insertItemData("おはなのおこう");
			databaseHelper.insertItemData("おまもりこばん");
			databaseHelper.insertItemData("かいがらのすず");
			databaseHelper.insertItemData("かるいし");
			databaseHelper.insertItemData("かわらずのいし");
			databaseHelper.insertItemData("がんせきおこう");
			databaseHelper.insertItemData("きあいのハチマキ");
			databaseHelper.insertItemData("きゅうこん");
			databaseHelper.insertItemData("きょうせいギプス");
			databaseHelper.insertItemData("きよめのおこう");
			databaseHelper.insertItemData("きよめのおふだ");
			databaseHelper.insertItemData("きれいなウロコ");
			databaseHelper.insertItemData("けむりだま");
			databaseHelper.insertItemData("こううんのおこう");
			databaseHelper.insertItemData("ゴツゴツメット");
			databaseHelper.insertItemData("さざなみのおこう");
			databaseHelper.insertItemData("しあわせタマゴ");
			databaseHelper.insertItemData("しめつけバンド");
			databaseHelper.insertItemData("じゅうでんち");
			databaseHelper.insertItemData("しんかのきせき");
			databaseHelper.insertItemData("だっしゅつボタン");
			databaseHelper.insertItemData("においぶくろ");
			databaseHelper.insertItemData("ねらいのまと");
			databaseHelper.insertItemData("ひかりごけ");
			databaseHelper.insertItemData("ふうせん");
			databaseHelper.insertItemData("プロテクター");
			databaseHelper.insertItemData("ホイップポップ");
			databaseHelper.insertItemData("マグマブースター");
			databaseHelper.insertItemData("まんぷくおこう");
			databaseHelper.insertItemData("やすらぎのすず");
			databaseHelper.insertItemData("ゆきだま");
			databaseHelper.insertItemData("りゅうのウロコ");
			databaseHelper.insertItemData("れいかいのぬの");
			databaseHelper.insertItemData("レッドカード");
			databaseHelper.insertItemData("アクアカセット");
			databaseHelper.insertItemData("イナズマカセット");
			databaseHelper.insertItemData("こころのしずく");
			databaseHelper.insertItemData("スピードパウダー");
			databaseHelper.insertItemData("フリーズカセット");
			databaseHelper.insertItemData("ブレイズカセット");
			databaseHelper.insertItemData("シルクのスカーフ");
			databaseHelper.insertItemData("もくたん");
			databaseHelper.insertItemData("しんぴのしずく");
			databaseHelper.insertItemData("じしゃく");
			databaseHelper.insertItemData("きせきのタネ");
			databaseHelper.insertItemData("とけないこおり");
			databaseHelper.insertItemData("くろおび");
			databaseHelper.insertItemData("どくバリ");
			databaseHelper.insertItemData("やわらかいすな");
			databaseHelper.insertItemData("するどいくちばし");
			databaseHelper.insertItemData("まがったスプーン");
			databaseHelper.insertItemData("ぎんのこな");
			databaseHelper.insertItemData("かたいいし");
			databaseHelper.insertItemData("のろいのおふだ");
			databaseHelper.insertItemData("りゅうのキバ");
			databaseHelper.insertItemData("くろいメガネ");
			databaseHelper.insertItemData("メタルコート");
			databaseHelper.insertItemData("アブソルナイト");
			databaseHelper.insertItemData("カイロスナイト");
			databaseHelper.insertItemData("ガブリアスナイト");
			databaseHelper.insertItemData("カメックスナイト");
			databaseHelper.insertItemData("ガルーラナイト");
			databaseHelper.insertItemData("ギャラドスナイト");
			databaseHelper.insertItemData("クチートナイト");
			databaseHelper.insertItemData("ゲンガナイト");
			databaseHelper.insertItemData("サーナイトナイト");
			databaseHelper.insertItemData("ジュペッタナイト");
			databaseHelper.insertItemData("チャーレムナイト");
			databaseHelper.insertItemData("デンリュウナイト");
			databaseHelper.insertItemData("バシャーモナイト");
			databaseHelper.insertItemData("ハッサムナイト");
			databaseHelper.insertItemData("バンギラスナイト");
			databaseHelper.insertItemData("フーディナイト");
			databaseHelper.insertItemData("フシギバナイト");
			databaseHelper.insertItemData("プテラナイト");
			databaseHelper.insertItemData("ヘラクロスナイト");
			databaseHelper.insertItemData("ヘルガナイト");
			databaseHelper.insertItemData("ボスゴドラナイト");
			databaseHelper.insertItemData("ミュウツナイトX");
			databaseHelper.insertItemData("ミュウツナイトY");
			databaseHelper.insertItemData("ユキノオナイト");
			databaseHelper.insertItemData("ライボルトナイト");
			databaseHelper.insertItemData("ルカリオナイト");
			databaseHelper.insertItemData("リザードナイトX");
			databaseHelper.insertItemData("リザードナイトY");

            Log.i("ItemInsertHandler", "ポケモンの持ち物データOK!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
