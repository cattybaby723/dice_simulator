package angela_hu.dicesimulator.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import angela_hu.dicesimulator.model.Dice;
import angela_hu.dicesimulator.model.RandomDiceAsyncTask;

/**
 * @author anrou_hu
 */

public class MainViewModel extends ViewModel {
    private final static int DEFAULT_NUMBER_OF_SIDES = 6;
    private final static int DEFAULT_NUMBER_OF_DICE = 3;

    private int mNumberOfSides = DEFAULT_NUMBER_OF_SIDES;
    private int mNumberOfDice = DEFAULT_NUMBER_OF_DICE;

    private MediatorLiveData<List<Dice>> mDices = new MediatorLiveData<>();
    private MutableLiveData<Integer> mTotalPoint = new MutableLiveData<>();


    public void setNumberOfSides(int numberOfSides) {
        mNumberOfSides = numberOfSides;
    }

    public void setNumberOfDice(int numberOfDice) {
        mNumberOfDice = numberOfDice;
    }

    public LiveData<List<Dice>> getDices() {
        return mDices;
    }

    public LiveData<Integer> getTotalPoint() {
        return mTotalPoint;
    }


    public int calculateTotalPoint() {
        List<Dice> dices = mDices.getValue();
        if (dices == null) return 0;

        int totalPoint = 0;
        for (Dice dice : dices) {
            totalPoint += dice.getCurrentPoint();
        }

        return totalPoint;
    }


    public void rollDice() {
        RandomDiceAsyncTask task = new RandomDiceAsyncTask(mNumberOfSides, mNumberOfDice);
        task.execute();
    }
}
