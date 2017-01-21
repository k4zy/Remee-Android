package com.github.kazy1991.remee.dummy;

import android.content.Context;

import com.github.kazy1991.twitterpack.TwitterPack;
import com.github.kazy1991.twitterpack.deserializer.LocalDateTimeDeserializer;
import com.github.kazy1991.twitterpack.entity.Oauth2Response;
import com.github.kazy1991.twitterpack.entity.TweetEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.LocalDateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import kotlin.NotImplementedError;


public class DummyTwitterPack implements TwitterPack {

    private Context context;

    public DummyTwitterPack(Context context) {
        this.context = context;
    }

    @Override
    public Flowable<List<TweetEntity>> fetchFavorite(String s) {
        return Flowable.fromCallable(new Callable<List<TweetEntity>>() {
            @Override
            public List<TweetEntity> call() throws Exception {
                Type listType = new TypeToken<List<TweetEntity>>() {
                }.getType();
                return customGson().fromJson(jsonFromAssets("favorite_response.json"), listType);
            }
        });
    }

    @Override
    public Flowable<Oauth2Response> auth() {
        throw new NotImplementedError("TwitterPack#auth not Implement");
    }

    private Gson customGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();
    }

    private String jsonFromAssets(String path) {
        try {
            StringBuilder buf = new StringBuilder();
            InputStream json = context.getAssets().open(path);
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
            return buf.toString();

        } catch (IOException e) {
            throw new RuntimeException("Can't read dummy json from assets");
        }
    }
}
