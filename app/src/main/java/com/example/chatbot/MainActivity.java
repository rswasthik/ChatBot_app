package com.example.chatbot;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import ChatsModel.ChatsModal;

public class MainActivity<ChatRVAdapter> extends AppCompatActivity {

    private RecyclerView chatsRV;
    private EditText userMesEdt;
    private FloatingActionButton sendMsgFAB;
    private final   String BOT_KEY = "bot";
    private final   String USER_KEY = "USER";
    private ArrayList<ChatsModal>chatsModalArrayList;
    private ChatRVAdapter chatRVAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatsRV = findViewById(R.id.idRVchats);
        userMesEdt = findViewById(R.id.idEdtMessage);
        sendMsgFAB = findViewById(R.id.idFABSend);
        chatsModalArrayList = new ArrayList<>();
        chatRVAdapter = new ChatRVAdapter(chatsModalArrayList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter((RecyclerView.Adapter) chatRVAdapter);

        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(userMesEdt.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter your message", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    getResponse(userMesEdt.getText().toString());
                }
                userMesEdt.setText("");

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            private void getResponse(String message) {
            chatsModalArrayList.add(new ChatsModal(message,USER_KEY));
            ((RecyclerView.Adapter<?>) chatRVAdapter).notifyDataSetChanged();
            String url ="http://api.brainshop.ai/get?bid=161216&key=OQ7mooVl2FwKP93E&uid=[uid]&msg="+message;
            String BASE_URL = "http://api.brainshop.ai/";
            Retrofit retrofit = new  Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GSonconverterFactory.create())
                    .build();
            RetrofitAPI retrofitAPI =  retrofit.create(RetrofitAPI.class);
            Call call = null ;
                retrofitAPI.getMessage(url);
                final boolean please_revert_your_question = Objects.equals(call, new Call<MsgModal>() {
                    @Override
                    public <response> void onResponce(Call call, response) {
                        Object responce = null;
                        if (responce.isSuccessful()) {
                            MsgModal modal = responce.body();
                            chatsModalArrayList.add(new ChatsModal(modal.getCnt(), BOT_KEY));
                            ((RecyclerView.Adapter<?>) chatRVAdapter).notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        chatsModalArrayList.add(new chatmodal("please revert your question", BOT_KEY));
                        ChatRVAdapter.notifyDataSetChanged();


                    }


                });


            }
        });
    }
}