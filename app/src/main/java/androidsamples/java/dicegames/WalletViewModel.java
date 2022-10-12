package androidsamples.java.dicegames;

//import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

  private static final String TAG = "WalletViewActivity";

  private static int mWinValue = 6;
  private static int mIncrement = 5;

  private int mBalance;
  private Die mDie;

  /**
   * The no argument constructor.
   */
  public WalletViewModel() {
    mBalance=0;
    mDie=new Die6();
  }

  /**
   * Reports the current balance.
   * @return the balance
   */
  public int balance() {
    return mBalance;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param amount the new balance
   */
  public void setBalance(int amount) {
    mBalance=amount;
  }

  /**
   * Rolls the {@link Die} in the wallet.
   */
  public void rollDie() {
    if(mDie.value() == 0)
      throw new IllegalStateException("Not enough die available");
    mDie.roll();
//    Log.d(TAG,"Die Roll = "+mDie.value());
    if(mDie.value()==mWinValue){
      mBalance+=mIncrement;
//      Log.d(TAG,"New Balance = "+mBalance);
    }
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   * @return current value of the die
   */
  public int dieValue() {
    return mDie.value();
  }

  /**
   * Sets the increment value for earning in the wallet.
   *
   * @param increment amount to add to the balance
   */
  public void setIncrement(int increment) {
    mIncrement=increment;
  }

  /**
   * Sets the value which when rolled earns the increment.
   *
   * @param winValue value to be set
   */
  public void setWinValue(int winValue) {
    mWinValue=winValue;
  }

  /**
   * Sets the {@link Die} to be used in this wallet.
   *
   * @param d the Die to use
   */
  public void setDie(Die d) {
    mDie=d;
  }
}
