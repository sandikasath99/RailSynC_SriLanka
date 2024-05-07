package uk.ac.wlv.railsyncsrilanka.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;
import uk.ac.wlv.railsyncsrilanka.model.TicketPriceModel;
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
}
