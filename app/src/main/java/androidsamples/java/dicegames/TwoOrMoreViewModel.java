package androidsamples.java.dicegames;

import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.ViewModel;

/**
 * A {@link ViewModel} for the gambling game that allows the user to choose a game type, set a wager, and then play.
 */
public class TwoOrMoreViewModel extends ViewModel {

  private int mBalance;
  private int mWager;
  private Die6[] mDies;
  private List<Integer> dieValues;
  GameType mGameType;
  int mAlike;


  /**
   * No argument constructor.
   */
  public TwoOrMoreViewModel() {
    mBalance=0;
    mWager=-1;
    mGameType=GameType.NONE;
    mAlike=0;

    mDies=new Die6[4];
    for(int i=0;i<4;i++)
      mDies[i]=new Die6(1);

    dieValues=new ArrayList<>(4);
    for(int i=0;i<4;i++)
      dieValues.add(1);
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */
  public int balance() {
    return mBalance;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param balance the given amount
   */
  public void setBalance(int balance) {
    mBalance=balance;
  }

  /**
   * Reports the current game type as one of the values of the {@code enum} {@link GameType}.
   *
   * @return the current game type
   */
  public GameType gameType() {
    // TODO implement method
    return mGameType;
  }

  /**
   * Sets the current game type to the given value.
   *
   * @param gameType the game type to be set
   */
  public void setGameType(GameType gameType) {
    mGameType=gameType;
    if(mGameType==GameType.FOUR_ALIKE){
      mAlike=4;
    }
    if(mGameType==GameType.THREE_ALIKE){
      mAlike=3;
    }
    if(mGameType==GameType.TWO_ALIKE){
      mAlike=2;
    }
    if(mGameType==GameType.NONE){
      mAlike=0;
    }
  }

  /**
   * Reports the wager amount.
   *
   * @return the wager amount
   */
  public int wager() {
    // TODO implement method
    return mWager;
  }

  /**
   * Sets the wager to the given amount.
   *
   * @param wager the amount to be set
   */
  public void setWager(int wager) {
//    if(wager<=0){
//      throw new IllegalStateException("Game Type not set, can't play!");
//    }
//    if(mGameType==GameType.NONE){
//      throw new IllegalStateException("Game Type not set, can't play!");
//    }
    mWager=wager;
  }

  /**
   * Reports whether the wager amount is valid for the given game type and current balance.
   * For {@link GameType#TWO_ALIKE}, the balance must be at least twice as much, for {@link GameType#THREE_ALIKE}, at least thrice as much, and for {@link GameType#FOUR_ALIKE}, at least four times as much.
   * The wager must also be more than 0.
   *
   * @return {@code true} iff the wager set is valid
   */
  public boolean isValidWager() {
    if(mWager<=0){
      return false;
    }
    return (mWager*mAlike<=mBalance);
  }

  /**
   * Returns the current values of all the dice.
   *
   * @return the values of dice
   */
  public List<Integer> diceValues() {
    return dieValues;
  }

  /**
   * Adds the given {@link Die} to the game.
   *
   * @param d the Die to be added
   */
  public void addDie(Die d) {
    // TODO implement method
    mDies=new Die6[mDies.length+1];
    dieValues.add(0);
  }

  /**
   * Simulates playing the game based on the type and the wager and reports the result as one of the values of the {@code enum} {@link GameResult}.
   *
   * @return result of the current game
   * @throws IllegalStateException if the wager or the game type was not set to a proper value.
   */
  public GameResult play() throws IllegalStateException {
    if(mWager==-1){
      throw new IllegalStateException("Wager not set, can't play!");
    }
    if(!isValidWager()){
      throw new IllegalStateException("Wager not valid!");
    }

    int[] mDieHash=new int[6];
    for(int i=0; i<mDies.length; i++){
      mDies[i]=new Die6();
      mDies[i].roll();
      dieValues.set(i,mDies[i].value());
      mDieHash[mDies[i].value()-1]++;
    }
    Arrays.sort(mDieHash);

    if(mDieHash[mDieHash.length-1]>mAlike){
      mBalance+=mWager;
      return GameResult.WIN;
    }
    else {
      mBalance-=mWager;
      return GameResult.LOSS;
    }
  }
}
