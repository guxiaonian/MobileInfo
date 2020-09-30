package com.mobile.mobilehardware;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * @author guxiaonian
 */
public class MobInitializer extends ContentProvider {

    public static final String TAG = MobInitializer.class.getSimpleName();

    @Override
    public boolean onCreate() {
        Context context = getContext();
        if (context == null) {
            Log.e(TAG, "Context is null,Please init this!");
        }
        MobileHardWareHelper.init(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
