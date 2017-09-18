package angela_hu.dicesimulator.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import angela_hu.dicesimulator.R;
import angela_hu.dicesimulator.model.Dice;
import angela_hu.dicesimulator.viewmodel.MainViewModel;

public class MainActivity extends LifecycleActivity implements View.OnClickListener {
    private final static String TAG = MainActivity.class.getSimpleName();

    private EditText mNumberOfSides;
    private EditText mNumberOfDice;
    private RecyclerView mDiceRecyclerView;
    private TextView mTotalPoint;
    private Button mRoll;

    private MainViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initViewModel();
        initObserver();
        initTextWatcher();
    }


    private void initViews() {
        mNumberOfSides = findViewById(R.id.numberOfSides);
        mNumberOfDice = findViewById(R.id.numberOfDice);
        mDiceRecyclerView = findViewById(R.id.diceRecyclerView);
        mTotalPoint = findViewById(R.id.totalPoint);
        mRoll = findViewById(R.id.roll);

        mRoll.setOnClickListener(this);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }


    private void initObserver() {
        mViewModel.getDices().observe(this, new Observer<List<Dice>>() {
            @Override
            public void onChanged(@Nullable List<Dice> dices) {
                refreshDices();
            }
        });

        mViewModel.getTotalPoint().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == null) return;
                mTotalPoint.setText(String.valueOf(integer));
            }
        });
    }

    private void refreshDices() {
        Log.d(TAG, "refreshDices()");
    }


    private void initTextWatcher() {
        mNumberOfSides.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int numberOfSides = Integer.valueOf(editable.toString());
                mViewModel.setNumberOfSides(numberOfSides);
            }
        });

        mNumberOfDice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int numberOfDice = Integer.valueOf(editable.toString());
                mViewModel.setNumberOfDice(numberOfDice);
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roll:
                rollDice();
                break;
        }
    }

    private void rollDice() {
        mViewModel.rollDice();
    }

}
