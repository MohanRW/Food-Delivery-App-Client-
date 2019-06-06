package com.warriorprod.fooddeliveryapp.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {
    public static User currentUser;

    public static String convertCodeToStatus(String status){
        if(status!=null) {
            if (status.equals("0"))
                return "Placed";
            else if (status.equals("1"))
                return "On My Way";
            else
                return "Shipped";
        }else
            return "Order Status is Not Available!";
    }

    public static boolean isConnectedToInternet(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager!=null)
        {
            NetworkInfo[] info =connectivityManager.getAllNetworkInfo();
            if (info!=null)
            {
                for (int i=0;i<info.length;i++)
                {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

    public static final String DELETE = "Delete";

}
