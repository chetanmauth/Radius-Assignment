package com.example.radiusassignment.util;

import com.example.radiusassignment.R;

public class Constants {

   final public static long ONE_DAY_IN_MILLISECONS=86400000;

   final public static String BASE_URL="https://my-json-server.typicode.com/";

   public static int getImageRes(String str){
      switch (str){
         case "apartment": return R.drawable.apartment;
         case "condo": return R.drawable.condo;
         case "boat": return R.drawable.boat;
         case "land": return R.drawable.land;
         case "rooms": return R.drawable.rooms;
         case "no-room": return R.drawable.no_room;
         case "swimming": return R.drawable.swimming;
         case "garden": return R.drawable.garden;
         case "garage": return R.drawable.garage;
      }
      return 0;
   }
}
