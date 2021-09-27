package com.example.wordguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wordguide.ApiModals.MeaningModal;
import com.example.wordguide.CustomModal.WordMeaningModal;
import com.example.wordguide.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAdapter adapter;
                List<WordMeaningModal> meaningsList=meaningAdd(retrofit,binding);

                binding.recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
                adapter=new CustomAdapter(MainActivity.this,meaningsList);
                binding.recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }
    public  interface  callMeaningInterface {
        @GET("{word}")
        Call<List<MeaningModal>> callMeanings(@Path("word") String input);
    }

    public List<WordMeaningModal> meaningAdd(Retrofit retrofit, ActivityMainBinding binding)
    {

        callMeaningInterface callMeaningApi=retrofit.create(callMeaningInterface.class);
        List<WordMeaningModal> meaningList=new ArrayList<>();

        String userInput=binding.editText.getText().toString();
        Call<List<MeaningModal>> call=callMeaningApi.callMeanings(userInput);
        try {
            call.enqueue(new Callback<List<MeaningModal>>() {
                @Override
                public void onResponse(@NonNull Call<List<MeaningModal>> call, @NonNull Response<List<MeaningModal>> response) {
                    if(!response.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    try {
                        List<MeaningModal> wordMeaning=response.body();
                        int size=wordMeaning.get(0).getMeanings().size();
                        Toast.makeText(MainActivity.this, "Size is: "+size, Toast.LENGTH_SHORT).show();

                        for(int i=0;i<size;i++)
                        {
                            try {
                            /*binding.textview.setText(wordMeaning.get(0).getMeanings().get(0).getDefinitions()
                                    .get(0).getDefinition());*/
                                String wm=wordMeaning.get(0).getMeanings().get(i).getDefinitions()
                                        .get(0).getDefinition();
                                String ty=wordMeaning.get(0).getMeanings().get(i).getPartOfSpeech();
                                int synonymCount=wordMeaning.get(0).getMeanings().get(i).getDefinitions()
                                        .get(0).getSynonyms().size();
                                int antonymCount=wordMeaning.get(0).getMeanings().get(i).getDefinitions()
                                        .get(0).getAntonyms().size();
                                String sy="",an="";
                                for(int j=0;j<synonymCount;j++)
                                {
                                    try {
                                        sy=sy+wordMeaning.get(0).getMeanings().get(i).getDefinitions()
                                                .get(0).getSynonyms().get(j)+" , ";
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                                for(int j=0;j<antonymCount;j++)
                                {
                                    try {
                                        an=an+wordMeaning.get(0).getMeanings().get(i).getDefinitions()
                                                .get(0).getAntonyms().get(j)+" , ";
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }

                                if(!wm.isEmpty()){
                                    //list.add(temp);
                                    meaningList.add(new WordMeaningModal(wm,ty,sy,an));

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this, "Meaning not found! Sorry:(", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Word meaning not found :(", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(@NonNull Call<List<MeaningModal>> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

      return meaningList;

    }
}