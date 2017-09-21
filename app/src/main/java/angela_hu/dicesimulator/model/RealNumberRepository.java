package angela_hu.dicesimulator.model;

import android.arch.lifecycle.MediatorLiveData;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author anrou_hu
 */

public class RealNumberRepository {

    private final static String TAG = RealNumberRepository.class.getSimpleName();
    private final static String REAL_RANDOM_WEB_HOST = "https://www.random.org";


    public MediatorLiveData<List<Dice>> getRealNumberDices(int numberOfDice, final int numberOfSides) {
        RealRandomMapBuilder builder = new RealRandomMapBuilder().setDefaultSetting(numberOfDice, numberOfSides);
        Map<String, String> map = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(REAL_RANDOM_WEB_HOST)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

        final MediatorLiveData<List<Dice>> dices = new MediatorLiveData<>();
        RealRandomService service = retrofit.create(RealRandomService.class);
        service.listTextNumber(map).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dices.setValue(parsePoints(numberOfSides, response.body()));
                Log.d(TAG, "onResponse body:" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        return dices;
    }

    private List<Dice> parsePoints(int numberOfSides, String responseBody) {
        List<Dice> dices = new ArrayList<>();
        if (TextUtils.isEmpty(responseBody)) return dices;
        String[] values = responseBody.split("\n");
        for (String value : values) {
            Dice dice = new Dice(Integer.valueOf(value), numberOfSides);
            dices.add(dice);
        }

        return dices;
    }


    public MediatorLiveData<List<Dice>> getRandomNumberDices(int numberOfDice, int numberOfSides) {
        final MediatorLiveData<List<Dice>> dices = new MediatorLiveData<>();
        List<Dice> diceList = generateRandomNumber(numberOfDice, numberOfSides);

        dices.setValue(diceList);
        return dices;
    }

    private List<Dice> generateRandomNumber(int numberOfDice, int numberOfSides) {
        List<Dice> dices = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < numberOfDice; i++) {
            int value = random.nextInt(numberOfSides);
            Dice dice = new Dice(value, numberOfSides);
            dices.add(dice);
        }

        return dices;
    }




}
