package com.example.niu.mlpt.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by NIU on 2018/4/25.
 */

public class SharePreferenceUtils {

    private static Context mContext;
    private static final String name ="milepaotui";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void init(Context context){

        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(name,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public static void put(String key,Object object){

        if (object instanceof String){

            editor.putString(key,(String )object);

        }else if (object instanceof Boolean){

           editor.putBoolean(key,(boolean)object);

        }else if (object  instanceof Integer){

            editor.putInt(key,(Integer)object);
        }else if (object instanceof Float){

            editor.putFloat(key,(Float)object);

        }else if (object instanceof Long){

            editor.putLong(key, (Long) object);
        }

        editor.apply();

    }


    public static Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return    (String)sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return (boolean)sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        }
      return null;
    }

    public static void remove(String key){

        editor.remove(key);
        editor.apply();
    }
     public static boolean contains(String key){

        return sharedPreferences.contains(key);

     }

     public static void clearAll(){

         editor.clear();
         editor.apply();

     }

















}
