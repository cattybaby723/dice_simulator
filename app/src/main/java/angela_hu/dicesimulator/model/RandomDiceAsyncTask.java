package angela_hu.dicesimulator.model;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author anrou_hu
 */

public class RandomDiceAsyncTask extends AsyncTask<Void, Void, Void> {
    private final static String TAG = RandomDiceAsyncTask.class.getSimpleName();
    private final static String REAL_RANDOM_WEB_HOST = "https://www.random.org";

    private int mNumberOfSides;
    private int mNumberOfDice;


    public RandomDiceAsyncTask(int numberOfSides, int numberOfDice) {
        mNumberOfSides = numberOfSides;
        mNumberOfDice = numberOfDice;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        RealRandomMapBuilder builder = new RealRandomMapBuilder().setDefaultSetting(mNumberOfDice, mNumberOfSides);
        Map<String, String> map = builder.build();
        final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(REAL_RANDOM_WEB_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        RealRandomService service = retrofit.create(RealRandomService.class);
        service.listTextNumber(map).enqueue(new Callback<Number>() {
            @Override
            public void onResponse(Call<Number> call, Response<Number> response) {
                Log.d(TAG, "onResponse response: " + response);
                Log.d(TAG, "onResponse body:" + response.body());
            }

            @Override
            public void onFailure(Call<Number> call, Throwable t) {
                Log.d(TAG, "onResponse" + t.toString());
            }
        });


        return null;
    }


}

