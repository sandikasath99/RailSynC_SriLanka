package uk.ac.wlv.railsyncsrilanka.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;

public interface MyApiCall {
    @FormUrlEncoded
    @POST("getLineStations.php")
    Call<ArrayList<StationModel>> getStationsByLine(@Field("line") String line);

}
