package com.mobile.mobileinfo.util;

import android.support.annotation.NonNull;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import fairy.easy.httpmodel.load.HttpException;
import fairy.easy.httpmodel.model.HttpModel;
import fairy.easy.httpmodel.model.ModelLoader;
import fairy.easy.httpmodel.model.RequestMethod;
import fairy.easy.httpmodel.util.HttpLog;
import fairy.easy.httpmodel.util.Preconditions;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author gunaonian
 * OkHttp加载类
 */
public class OkHttpUrlLoader implements ModelLoader<String> {

    private HttpModel httpModel = null;
    private int timeout = 5 * 1000;
    private ResponseBody responseBody;
    private volatile Call call;
    private Call.Factory client;

    public OkHttpUrlLoader() {
    }

    @Override
    public void loadData(@NonNull DataCallback<? super String> callback) {
        if (httpModel == null || timeout < 1) {
            HttpLog.e("Failed to load data");
            callback.onLoadFailed(new IllegalArgumentException("Failed to load data"));
        }
        client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .build();
        Request.Builder requestBuilder = new Request.Builder().url(httpModel.toStringUrl());
        for (Map.Entry<String, String> headerEntry : httpModel.getHeaders().entrySet()) {
            String key = headerEntry.getKey();
            requestBuilder.addHeader(key, headerEntry.getValue());
        }
        if (RequestMethod.POST == httpModel.getRequestMethod() && Preconditions.checkNotEmptyBoolean(httpModel.getPostParam().getStringParam())) {
            requestBuilder.post(RequestBody.create(MediaType.parse("application/octet-stream"), httpModel.getPostParam().getStringParam().getBytes()));
        }
        Request request = requestBuilder.build();
        call = client.newCall(request);
        try {
            Response response = call.execute();
            responseBody = response.body();
            if (response.isSuccessful()) {
                callback.onDataReady(responseBody.string());
            } else {
                callback.onLoadFailed(new HttpException(response.message(), response.code()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onLoadFailed(e);
        }
        cleanup();
    }


    @Override
    public void cleanup() {
        if (responseBody != null) {
            responseBody.close();
        }
    }

    @Override
    public void cancel() {
        Call local = call;
        if (local != null) {
            local.cancel();
        }
    }

    @Override
    public ModelLoader<String> setHttpModel(HttpModel httpModel) {
        this.httpModel = httpModel;
        return this;
    }

    @Override
    public ModelLoader<String> setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    @NonNull
    @Override
    public Class<String> getDataClass() {
        return String.class;
    }

}