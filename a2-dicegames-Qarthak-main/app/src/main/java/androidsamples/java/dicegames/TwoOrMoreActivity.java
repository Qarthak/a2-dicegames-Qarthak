package androidsamples.java.dicegames;

import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TwoOrMoreActivity extends AppCompatActivity {
  private static final String TAG = "WalletActivity";
  public static final String KEY_BALANCE_RETURN = "KEY_BALANCE_RETURN";
  public static final String KEY_BALANCE = "KEY_BALANCE";

  private TwoOrMoreViewModel mTwoOrMoreVM;

  private TextView mTxtBalance;
  private TextView mTxtWager;

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
//    Log.d(TAG, "onSaveInstanceState");
    outState.putInt(KEY_BALANCE, mTwoOrMoreVM.balance());

//    Log.d(TAG, "Saved: balance = " + mTwoOrMoreVM.balance());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);

    mTwoOrMoreVM=new ViewModelProvider(this).get(TwoOrMoreViewModel.class);

    mTwoOrMoreVM.setBalance(getIntent().getIntExtra(WalletActivity.KEY_BALANCE,0));

    mTxtBalance=findViewById(R.id.txt_balance_twoormore);
    mTxtWager=findViewById(R.id.edit_wager);

    if(savedInstanceState!=null ){
      int balance=savedInstanceState.getInt(KEY_BALANCE,-1);
      mTwoOrMoreVM.setBalance(balance);
//      Log.d(TAG,"Restoring ViewModel. Balance is "+balance);
    }

    Button mBtnGo = findViewById(R.id.btn_go);
    mBtnGo.setOnClickListener(v->{
      mTwoOrMoreVM.setWager( Integer.parseInt(mTxtWager.getText()+"") );

      mTwoOrMoreVM.play();
      updateUI();
    });

    Button mBackBtn=findViewById(R.id.btn_back);
    mBackBtn.setOnClickListener(v->{
      returnToHomeActivity(RESULT_OK);
      finish();
    });

    Button mInfoBtn=findViewById(R.id.btn_info);
    mInfoBtn.setOnClickListener(v->{
      Intent intent=new Intent(this,InfoEmptyActivity.class);
      startActivity(intent);
    });

//    RadioGroup mRadioGroup=findViewById(R.id.radioGroup);
//    int selectedBut=mRadioGroup.getCheckedRadioButtonId() ;
//    RadioButton mRadioButton=findViewById(selectedBut);
//    GameType gameType=GameType.NONE;
//    if(mRadioButton.getText()=="2 Alike"){
//      gameType=GameType.TWO_ALIKE;
//    }
//    if(mRadioButton.getText()=="3 Alike"){
//      gameType=GameType.THREE_ALIKE;
//    }
//    if(mRadioButton.getText()=="4 Alike"){
//      gameType=GameType.FOUR_ALIKE;
//    }
//    mTwoOrMoreVM.setGameType(gameType);

    updateUI();
  }

  @Override
  public void onBackPressed() {
    returnToHomeActivity(RESULT_CANCELED);
    super.onBackPressed();
  }

  /**
   * returns to home activity keeping the balance and result
   *
   * @param result that is to be set : example : failed/passes
   */
  private void returnToHomeActivity(int result){
    Intent resultIntent=new Intent();
    resultIntent.putExtra(KEY_BALANCE_RETURN,mTwoOrMoreVM.balance());
    setResult(result,resultIntent);
  }

  /**
   * Updates the UI
   *
   */
  private void updateUI(){
    ((TextView)findViewById(R.id.txt_die1)).setText(Integer.toString(mTwoOrMoreVM.diceValues().get(0)));
    ((TextView)findViewById(R.id.txt_die2)).setText(Integer.toString(mTwoOrMoreVM.diceValues().get(1)));
    ((TextView)findViewById(R.id.txt_die3)).setText(Integer.toString(mTwoOrMoreVM.diceValues().get(2)));
    ((TextView)findViewById(R.id.txt_die4)).setText(Integer.toString(mTwoOrMoreVM.diceValues().get(3)));
    mTxtBalance.setText(Integer.toString(mTwoOrMoreVM.balance()));
  }
}