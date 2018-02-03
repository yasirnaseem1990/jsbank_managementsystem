package jsbankagent.management.application.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import jsbankagent.management.application.R;
import jsbankagent.management.application.model.AddAgents;

/**
 * Created by Administrator on 1/7/2018.
 */

public class ArchiveAgentAdapter extends RecyclerView.Adapter<ArchiveAgentAdapter.MyViewHolder>{

    private ArrayList<AddAgents> agentsArrayList;
    Context context;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.archive_agent_adapter, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AddAgents agents = agentsArrayList.get(position);

        String name = agents.getAgentName();
        String address = agents.getAgentAddress();
        String picturePath = agents.getImagePath();
        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);


        holder.tv_agentname.setText(name);
        holder.tv_agentaddress.setText(address);
        holder.imageviewAgent.setImageBitmap(bitmap);

    }

    public ArchiveAgentAdapter(ArrayList<AddAgents> arrayList) {
        this.agentsArrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return agentsArrayList.size();
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
