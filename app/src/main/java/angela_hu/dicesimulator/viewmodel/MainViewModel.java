package angela_hu.dicesimulator.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import java.util.List;

import angela_hu.dicesimulator.model.Dice;
import angela_hu.dicesimulator.model.RealNumberRepository;

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
    private MutableLiveData<Boolean> mIsProgressing = new MutableLiveData<>();

    private RealNumberRepository mNumberRepo = new RealNumberRepository();


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

    public LiveData<Boolean> isProgressing() {
        return mIsProgressing;
    }


    public void rollDice() {
        mIsProgressing.setValue(true);
        mDices.addSource(mNumberRepo.getRealNumberDices(mNumberOfDice, mNumberOfSides), new Observer<List<Dice>>() {
            @Override
            public void onChanged(@Nullable List<Dice> dices) {
                mDices.setValue(dices);
                calculateTotalPoint();
                mIsProgressing.setValue(false);
            }
        });
    }


    private void calculateTotalPoint() {
        List<Dice> dices = mDices.getValue();
        if (dices == null) {
            mTotalPoint.setValue(0);
            return;
        }

        int totalPoint = 0;
        for (Dice dice : dices) {
            totalPoint += dice.getCurrentPoint();
        }

        mTotalPoint.setValue(totalPoint);
    }
}
