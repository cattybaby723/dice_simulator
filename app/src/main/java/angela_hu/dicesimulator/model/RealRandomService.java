package angela_hu.dicesimulator.model;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Get real random number in https://www.random.org/
 *
 * @author anrou_hu
 */

public interface RealRandomService {

    @GET("integers")
    Call<Number> listTextNumber(@QueryMap Map<String, String> options);
}
