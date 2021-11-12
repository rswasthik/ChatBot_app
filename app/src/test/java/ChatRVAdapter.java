import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.chatbot.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ChatsModel.ChatsModal;

public class ChatRVAdapter extends RecycleView.Adpater {
    private ArrayList <ChatsModal> chatsModalArrayList;
    private Context context;

    public ChatRVAdapter(ArrayList<ChatsModal> chatsModalArrayList, Context context) {
        this.chatsModalArrayList = chatsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Recyclerview.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType)
    {
        View view;
        switch (viewType)
        {
            case 0;
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item, parent,attachToRoot:false)
                 return new UserViewHolder(view);
            case 1;
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_rv_item, parent,attachToRoot:false)
                return new BotViewHolder(view)

        }
        return null;


    }

    @Override
            public int getItemCount()
    {
        return 0;

    }
    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userTV;
        public UserViewHolder(@NonNull View itemViem)
        {
            super(itemViem);
            userTV = itemViem.findViewById(R.id.idTVUser);


        }
    }
    public static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView botMsgTV;
        public BotViewHolder(@NonNull View itemView)
        {
            super(itemView);
            botMsgTV = itemView.findViewById(R.id.idTVBot);

        }

    }
}
