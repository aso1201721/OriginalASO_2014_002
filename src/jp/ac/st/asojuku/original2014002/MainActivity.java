package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		Button btnmente = (Button)findViewById(R.id.btnmente);
		btnmente.setOnClickListener(this);
		Button btntoroku = (Button)findViewById(R.id.btntoroku);
		btntoroku.setOnClickListener(this);
		Button btncheck = (Button)findViewById(R.id.btncheck);
		btncheck.setOnClickListener(this);

		//クラスのフィールド変数がnullなら、データベース全間オープン
		if(sdb == null) {
				helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			//異常終了
			return;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		Intent intent = null;
		switch(v.getId()){
			case R.id.btntoroku:
				EditText etv = (EditText)findViewById(R.id.edit);
				String inputMsg = etv.getText().toString();

				//inputMsgがnullでない、かつ空でない場合のみ、if文内を実行
				if(inputMsg!=null && !inputMsg.isEmpty()){
					// MySQLiteOpenHelperのインサートメソッドを呼び出し
					helper.insertHitokoto(sdb, inputMsg);
				}
				//入力欄クリア
				etv.setText("");
				break;
			case R.id.btnmente: //メンテボタンが押された

				//インテントのインスタンス生成
				intent = new Intent(this, MaintenanceActivity.class);
				//次画面のアクティビティ起動
				startActivity(intent);
				break;
			case R.id.btncheck: //一言チェックボタンが押された

				//MySQLiteOpenHelperのセレクト一言メソッドを呼び出して一言をランダムに取得
				String strHitokoto = helper.selectRandomHitokoto(sdb);

				//インテントのインスタンス生成
				intent = new Intent(this, Hitokoto.class);
				//インテントに一言を混入
				intent.putExtra("hitokoto", strHitokoto);

				//次画面の悪ティビティ起動
				startActivity(intent);
				break;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
