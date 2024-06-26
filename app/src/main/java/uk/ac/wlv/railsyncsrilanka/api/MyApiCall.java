package uk.ac.wlv.railsyncsrilanka.api;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import uk.ac.wlv.railsyncsrilanka.model.NewsModel;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;
import uk.ac.wlv.railsyncsrilanka.model.TicketPriceModel;
import uk.ac.wlv.railsyncsrilanka.model.TrainModel;
import uk.ac.wlv.railsyncsrilanka.model.TrainScheduleModel;

public interface MyApiCall {
    @FormUrlEncoded
    @POST("getLineStations.php")
    Call<ArrayList<StationModel>> getStationsByLine(@Field("line") String line);

    @FormUrlEncoded
    @POST("getScheduleTrains.php")
    Call<ArrayList<TrainScheduleModel>> getScheduleTrain(@Field("start") String start, @Field("end") String end,@Field("date") String date);

    @GET("getStations.php")
    Call<ArrayList<StationModel>> getStations();

    @FormUrlEncoded
    @POST("getTicketPrices.php")
    Call<ArrayList<TicketPriceModel>> getTicketPrice(@Field("from") String from, @Field("to") String to);

    @GET("getNews.php")
    Call<ArrayList<NewsModel>> getNews();

    @FormUrlEncoded
    @POST("getLineTrains.php")
    Call<ArrayList<TrainModel>> getTrainsByLines(@Field("line") String line,@Field("station") String station);

    @FormUrlEncoded
    @POST("getStationLocation.php")
    Call<ArrayList<StationModel>> getStationLocation(@Field("station") String station);

    @GET("json")
    Call<JsonObject> getJson(@Query("origin") String origin, @Query("destination") String destination,
                             @Query("alternatives") boolean alternatives, @Query("key") String key);
}
