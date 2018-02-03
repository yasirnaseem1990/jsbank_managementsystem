package jsbankagent.management.application.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jsbankagent.management.application.R;
import jsbankagent.management.application.model.AddAgents;
import jsbankagent.management.application.model.PendingUploads;

/**
 * Created by Administrator on 1/8/2018.
 */

public class PendingUploadsAdapter extends RecyclerView.Adapter<PendingUploadsAdapter.MyViewHolder>{


    private ArrayList<PendingUploads> pendingUploads;
    Context context;

    @Override
    public PendingUploadsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_uploads_adapter, parent, false);
        context = parent.getContext();
        return new PendingUploadsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PendingUploadsAdapter.MyViewHolder holder, int position) {
        PendingUploads uploads = pendingUploads.get(position);

        String name = uploads.getAgentName();
        String address = uploads.getAgentAddress();
        String picturePath = uploads.getImagePath();
        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);


        holder.tv_agentname.setText(name);
        holder.tv_agentaddress.setText(address);
        holder.imageviewAgent.setImageBitmap(bitmap);

    }

    public PendingUploadsAdapter(ArrayList<PendingUploads> arrayList) {
        this.pendingUploads = arrayList;
    }

    @Override
    public int getItemCount() {
        return pendingUploads.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_agentname,tv_agentaddress;
        public ImageView imageviewAgent;

        public MyViewHolder(View view) {
            super(view);

            tv_agentname =  view.findViewById(R.id.aNametxt);
            tv_agentaddress = view.findViewById(R.id.aAddresstxt);
            imageviewAgent = view.findViewById(R.id.imageviewagent);

        }
    }
}
