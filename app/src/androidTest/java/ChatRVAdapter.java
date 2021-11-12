import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.R;

import java.text.BreakIterator;
import java.util.ArrayList;

import ChatsModel.ChatsModal;

public class ChatRVAdapter extends RecyclerView.Adapter {
    private ArrayList<ChatsModal> chatsModalArrayList;
    private Context context;

    public ChatRVAdapter(ArrayList<ChatsModal> chatsModalArrayList, Context context) {
        this.chatsModalArrayList = chatsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view;
        switch (viewType)
        {
            case 0:
                ViewGroup user_msg_rv_item =  null;
                boolean parents = false;
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item,parents,false);
                    return new UserViewHolder(view);


            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_rv_item, parent, false);
                    return new BotViewHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatsModal chatsModal=  chatsModalArrayList.get(position);
        switch(chatsModal.getSender)
        {
            ((UserViewHolder)holder).userTV.setText(chatsModal.getMessage());
            break;
            case "bot":
                ((UserViewHolder)holder).botTV.setText(chatsModal.getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return  chatsModalArrayList.size();
    }
    public static class  UserViewHolder extends RecyclerView.ViewHolder{

        public BreakIterator userTV;
        public BreakIterator botTV;

        public UserViewHolder(@NonNull  View itemView) {
            super();
            TextView userTV;
            super(itemView);
            userTV = itemView.findViewById(R.id.idTVUser);

        }
    }
    public static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView botMsgTV;
        public BotViewHolder(@NonNull View itemView)
        {
            super(itemView);
            botMsgTV =itemView.findViewById(R.id.idTVBot);



        }
    }


}
