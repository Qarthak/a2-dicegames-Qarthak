package androidsamples.java.dicegames;

import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class WalletActivity extends AppCompatActivity {

  private static final String TAG = "WalletActivity";
  public static final String KEY_BALANCE = "KEY_BALANCE";
  public static final String KEY_DIE_VALUE = "KEY_DIE_VALUE";
  public static final int TWOMORE_REQUEST_CODE = 2;

  private WalletViewModel mWalletVM;
  private TextView mTxtBalance;
  private Button mBtnDie;
  private Button mBtnTwoOrMore;


  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
//    Log.d(TAG, "onSaveInstanceState");
    outState.putInt(KEY_BALANCE, mWalletVM.balance());
    outState.putInt(KEY_DIE_VALUE, mWalletVM.dieValue());

//    Log.d(TAG, "Saved: balance = " + mWalletVM.balance() + ", die reads " + mWalletVM.dieValue());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    Log.d(TAG,"onCreate");
    setContentView(R.layout.activity_wallet);

    mTxtBalance=findViewById(R.id.txt_balance);
    mBtnDie=findViewById(R.id.btn_die);
    mBtnTwoOrMore=findViewById(R.id.btn_launch_twoormore);

    mWalletVM=new ViewModelProvider(this).get(WalletViewModel.class);
//    Log.d(TAG,"Die value is "+mWalletVM.dieValue());

    if(savedInstanceState!=null && mWalletVM.dieValue()==0 ){
      int balance=savedInstanceState.getInt(KEY_BALANCE,-1);
      Die6 die=new Die6(savedInstanceState.getInt(KEY_DIE_VALUE,-1));
      mWalletVM.setBalance(balance);
      mWalletVM.setDie(die);
//      Log.d(TAG,"Restoring ViewModel. Balance is "+balance+" die reads "+die.value());
    }

    updateUI();

    mBtnTwoOrMore.setOnClickListener(
      v -> {
        Intent intent = new Intent(this, TwoOrMoreActivity.class);
        intent.putExtra(KEY_BALANCE, mWalletVM.balance());
        startActivityForResult(intent,TWOMORE_REQUEST_CODE);
//       startActivity(intent);
      }
    );

    mBtnDie.setOnClickListener(
      v -> {
        mWalletVM.setDie(new Die6(1));
        mWalletVM.rollDie();
        updateUI();
      }
    );
  }

  /**
  *Updates the Die Throw Button and the Text field showing balance
   */
  private void updateUI(){
    mBtnDie.setText(Integer.toString(mWalletVM.dieValue()));
    mTxtBalance.setText(Integer.toString(mWalletVM.balance()));
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
//    Log.d(TAG,"I have returned! "+requestCode+" "+resultCode );
    if((requestCode==TWOMORE_REQUEST_CODE) && (resultCode==RESULT_OK || resultCode==RESULT_CANCELED)){
      if(data!=null){
        int newBalance=data.getIntExtra(TwoOrMoreActivity.KEY_BALANCE_RETURN,0);
//        Log.d(TAG,"I now have coins = "+newBalance);
        mWalletVM.setBalance(newBalance);
        updateUI();
      }
    }
  }
}