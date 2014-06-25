package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Hitokoto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hitokoto);


		//TextView tv = (TextView)findViewById(R.id.txvMsg);
		//tv.setText(inputMsg);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		//画面(Activity)に渡されたインテントを取得
		Intent intent = this.getIntent();
		//intentから、Extraに混入させた値をキーワードで取得
		String strHitokoto = intent.getStringExtra("hitokoto");

		//取得したStringを、TxtViewにセット
		TextView txvHITOKOTO = (TextView)findViewById(R.id.txvMsg);
		txvHITOKOTO.setText(strHitokoto);
	}
}

