package uk.ac.wlv.railsyncsrilanka.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uk.ac.wlv.railsyncsrilanka.R;
import uk.ac.wlv.railsyncsrilanka.model.TrainScheduleModel;

public class SearchTrainAdapter extends RecyclerView.Adapter<SearchTrainAdapter.ViewHolder> {

    private ArrayList<TrainScheduleModel> trains;
    private Context context;


    public SearchTrainAdapter(ArrayList<TrainScheduleModel> trains, Context context) {
        this.trains = trains;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_seach_train, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrainScheduleModel trainScheduleModel= trains.get(position);
        holder.trainName.setText(trainScheduleModel.getTrain_name());
        holder.startStation.setText(trainScheduleModel.getTrain_start_station());
        holder.endStation.setText(trainScheduleModel.getTrain_end_station());
        holder.startTime.setText(trainScheduleModel.getStart_arival_time());
        holder.endTime.setText(trainScheduleModel.getEnd_arival_time());

    }

    @Override
    public int getItemCount() {
        return trains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView trainName;
        TextView startStation;
        TextView endStation;
        TextView startTime;
        TextView endTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trainName = itemView.findViewById(R.id.trainName);
            startStation = itemView.findViewById(R.id.startStation);
            endStation = itemView.findViewById(R.id.endStation);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);

        }
    }
}
