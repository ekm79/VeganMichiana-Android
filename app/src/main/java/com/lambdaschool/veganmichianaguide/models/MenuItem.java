package com.lambdaschool.veganmichianaguide.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItem extends ApiObject {
    protected Restaurant restaurantid;

    public MenuItem(long menuitemid, String menuitemname, Restaurant restaurantid) {
        super.id = menuitemid;
        super.name = menuitemname;
        this.restaurantid = restaurantid;
        super.category = "menuitems";
    }

    public MenuItem() {
        super.category = "menuitems";
    }

    public MenuItem(JSONObject jsonObject) {
        super.category = "menuitems";
        try {
            super.id = jsonObject.getLong("menuid");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.name = jsonObject.getString("menuitemname");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
           JSONObject restaurant = jsonObject.getJSONObject("restaurantid");
            this.restaurantid = MenuItem.restaurantFromJson(restaurant);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getName() {
        return super.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long menuitemid) {
        this.id = menuitemid;
    }

    public String getMenuitemname() {
        return name;
    }

    public void setMenuitemname(String menuitemname) {
        this.name = menuitemname;
    }

    public Restaurant getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Restaurant restaurantid) {
        this.restaurantid = restaurantid;
    }

    public static Restaurant restaurantFromJson(JSONObject jsonObject) {
        Restaurant b = new Restaurant();
        // Deserialize json into object fields
        try {
            b.id = jsonObject.getLong("restaurantid");
            b.name = jsonObject.getString("restaurantname");
            b.location = jsonObject.getString("location");
            b.phone = jsonObject.getString("phone");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }

//    public static ArrayList<MenuItem> fromJson(JSONArray jsonArray) {
//        JSONObject productJson;
//        ArrayList<MenuItem> menuitems = new ArrayList<MenuItem>(jsonArray.length());
//        for (int i =0; i < jsonArray.length(); i++) {
//            try {
//                productJson = jsonArray.getJSONObject(i);
//            } catch (JSONException e) {
//                e.printStackTrace();
//                continue;
//            }
//            MenuItem menuitem = Restaurant.menuItemFromJson(productJson);
//            if (menuitem!= null) {
//                menuitems.add(menuitem);
//            }
//        }
//        return menuitems;
//    }
}
