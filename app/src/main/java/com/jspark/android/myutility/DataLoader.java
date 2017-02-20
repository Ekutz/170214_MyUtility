package com.jspark.android.myutility;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsPark on 2017. 2. 13..
 */

public class DataLoader {

    public static List<String> data = new ArrayList<>();

    public static List<String> load(Context context) {
        if(data==null || data.size()==0)
        getData(context);

        return data;
    }

    private static void getData(Context context) {
        ContentResolver r = context.getContentResolver();
        Uri target = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String projections[] = {
                MediaStore.Images.ImageColumns.DATA
        };
        Cursor c = r.query(target, projections, null, null, null);

        if(c.moveToFirst()) {
            while(c.moveToNext()) {
                String uriStr = c.getString(c.getColumnIndex(projections[0]));
                data.add(uriStr);
            }
        }
        c.close();
    }

    public static void addData(String str, Context context) {
        getData(context);
    }
}
