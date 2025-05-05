package com.example.myapplication;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NormalUserAdapter extends RecyclerView.Adapter<NormalUserAdapter.NormalUserViewHolder> {
    private List<User> userList;
    public NormalUserAdapter(List<User> userList) {
        this.userList = userList;
    }
    @NonNull

    @Override
    public NormalUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_row, parent, false);
        return new NormalUserViewHolder(view);}

    }
}
