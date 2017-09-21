package angela_hu.dicesimulator.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private ContentLoadingProgressBar mProgressBar;
    private Button mRoll;

    private MainViewModel mViewModel;
    private DiceAdapter mDiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        initViews();
        initObserver();
        initTextWatcher();
    }


    private void initViews() {
        mNumberOfSides = findViewById(R.id.numberOfSides);
        mNumberOfDice = findViewById(R.id.numberOfDice);
        mTotalPoint = findViewById(R.id.totalPoint);
        mProgressBar = findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.INVISIBLE);
        mRoll = findViewById(R.id.roll);
        mRoll.setOnClickListener(this);
        mRoll.requestFocus();

        initRecyclerView();
    }

    private void initRecyclerView() {
        mDiceRecyclerView = findViewById(R.id.diceRecyclerView);
        mDiceAdapter = new DiceAdapter(mViewModel.getDices());
        mDiceRecyclerView.setAdapter(mDiceAdapter);
        int spanSize = getResources().getInteger(R.integer.default_span_size);
        mDiceRecyclerView.setLayoutManager(new GridLayoutManager(this, spanSize, GridLayoutManager.VERTICAL, false));
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
            public void onChanged(@Nullable Integer totalPoint) {
                if (totalPoint == null) return;
                mTotalPoint.setText(String.valueOf(totalPoint));
            }
        });

        mViewModel.isProgressing().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isProgressing) {
                if (isProgressing != null && isProgressing) {
                    mProgressBar.show();
                } else {
                    mProgressBar.hide();
                }
            }
        });
    }

    private void refreshDices() {
        mDiceRecyclerView.removeAllViews();
        mDiceAdapter.notifyDataSetChanged();
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
                String value = editable.toString();
                int numberOfSides = TextUtils.isEmpty(value) ? 0 : Integer.valueOf(value);
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
                String value = editable.toString();
                int numberOfDice = TextUtils.isEmpty(value) ? 0 : Integer.valueOf(value);
                mViewModel.setNumberOfDice(numberOfDice);
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roll:
                hideSoftInput();
                rollDice();
                break;
        }
    }

    private void rollDice() {
        mViewModel.rollDice();
        Log.d(TAG, "rollDice");
    }

    private void hideSoftInput() {
        View view = getCurrentFocus();
        if (view == null) return;
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
        Log.d(TAG, "hideSoftKeyboard");
    }

}
