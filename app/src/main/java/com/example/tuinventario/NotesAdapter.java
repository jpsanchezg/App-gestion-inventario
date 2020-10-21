package com.example.tuinventario;

import android.content.Context;
import android.text.Html;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Productos> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView note;
        public TextView dot;
        public TextView Cantidad;

        public MyViewHolder(View view) {
            super(view);
            note = view.findViewById(R.id.producto);

            dot = view.findViewById(R.id.dot);
            Cantidad = view.findViewById(R.id.Ncantidad);
        }
    }


    public NotesAdapter(Context context, List<Productos> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Productos note = notesList.get(position);

        holder.note.setText(note.getMaterial());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.Cantidad.setText(note.getCanti());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }


}
