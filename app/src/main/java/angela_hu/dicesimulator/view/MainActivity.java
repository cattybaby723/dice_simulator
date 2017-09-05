package angela_hu.dicesimulator.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import angela_hu.dicesimulator.R;
import angela_hu.dicesimulator.viewmodel.MainViewModel;

public class MainActivity extends LifecycleActivity {


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
    }


    private void initViews() {
        mNumberOfSides = (EditText) findViewById(R.id.numberOfSides);
        mNumberOfDice = (EditText) findViewById(R.id.numberOfDice);
        mDiceRecyclerView = (RecyclerView) findViewById(R.id.diceRecyclerView);
        mTotalPoint = (TextView) findViewById(R.id.totalPoint);
        mRoll = (Button) findViewById(R.id.roll);
    }


    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }
}
