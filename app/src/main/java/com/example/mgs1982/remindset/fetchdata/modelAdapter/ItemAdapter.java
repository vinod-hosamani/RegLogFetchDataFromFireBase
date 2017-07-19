package com.example.mgs1982.remindset.fetchdata.modelAdapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mgs1982.remindset.R;
import com.example.mgs1982.remindset.registration.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MGS1982 on 7/18/2017.
 */

public class ItemAdapter extends  RecyclerView.Adapter<ItemAdapter.TaskViewHolder>
{
    private List<UserModel> userModelList;
    Context context;

  /*  public ItemAdapter() {

    }*/

    public ItemAdapter(Context context)
    {
        this.context=context;
        userModelList = new ArrayList<>();
    }
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_user_data_list, parent, false);
        return new TaskViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ItemAdapter.TaskViewHolder holder, int position)
    {
        final UserModel todoItemModel = userModelList.get(position);
        holder.muserName.setText(todoItemModel.getUserName());
        holder.mUserEmail.setText(todoItemModel.getEmail());
        holder.mUserPass.setText(todoItemModel.getPassword());
    }
    @Override
    public int getItemCount()
    {
        return userModelList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder
    {
        public LinearLayout linearLayoutTodo, linearLayoutBorder;
        public AppCompatTextView muserName,mUserEmail,mUserPass;
        public CardView cardViewTodo;

        public TaskViewHolder(View view)
        {
            super(view);
            linearLayoutTodo = view.findViewById(R.id.linearLayout_todo_background);
            linearLayoutBorder = view.findViewById(R.id.linearLayout_for_border);

            muserName = view.findViewById(R.id.userNameId);
            mUserEmail = view.findViewById(R.id.userEmailID);
            mUserPass = view.findViewById(R.id.userpassID);
            cardViewTodo = view.findViewById(R.id.cardView_todo_note);

        }
    }
    public void setUsersList(List<UserModel> noteList)
    {
        userModelList.clear();
        notifyDataSetChanged();
        userModelList.addAll(noteList);
        notifyDataSetChanged();
    }
}