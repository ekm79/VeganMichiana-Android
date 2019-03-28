package com.lambdaschool.veganmichianaguide.models;

import android.util.ArraySet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

public class Restaurant extends ApiObject {

    protected String location;
    protected String phone;
    protected String description;
    protected String link;
    protected Boolean allVegan;
    protected ArrayList<MenuItem> menuitems;
    protected ArrayList<String> reviews;

    public Restaurant(long restaurantid, String restaurantname, String description, String location, String phone, String link, Boolean allVegan, ArrayList<MenuItem> menuitems, ArrayList<String> reviews) {
        super.id = restaurantid;
        super.name = restaurantname;
        this.location = location;
        this.phone = phone;
        this.menuitems = menuitems;
        super.category = "restaurants";
        this.reviews = reviews;
        super.content = this.location +  "\n" + this.phone + "\n";
        this.description = description;
        this.link = link;
        this.allVegan = allVegan;
    }

    public Restaurant() {
        super.category = "restaurants";
        super.content = this.location +  "\n" + this.phone + "\n";
    }

    public Restaurant(JSONObject jsonObject) {
        super.category = "restaurants";

        try {
            this.id = jsonObject.getLong("restaurantid");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            super.name = jsonObject.getString("restaurantname");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.location = jsonObject.getString("location");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.phone = jsonObject.getString("phone");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.description = jsonObject.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.link = jsonObject.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.allVegan = jsonObject.getBoolean("allvegan");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.menuitems = new ArrayList<>();


        try {
            JSONArray menuitems = jsonObject.getJSONArray("menuitems");
            this.menuitems = Restaurant.fromJson(menuitems);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.content = this.description + "\n\n" + this.location +  "\n\n" + this.phone + "\n\n" + this.link + "\n\n" + "All Vegan: " + this.allVegan + "\n\n"  + arrayListToString(this.menuitems);
    }

    public long getRestaurantid() {
        return id;
    }

    public void setRestaurantid(long restaurantid) {
        this.id = restaurantid;
    }

    public String getName() {
        return name;
    }

    public String getContent() { return content; }

    public void setRestaurantname(String restaurantname) {
        this.name = restaurantname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<MenuItem> getMenuitems() {
        return menuitems;
    }

    public void setMenuitems(ArrayList<MenuItem> menuitems) {
        this.menuitems = menuitems;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getAllVegan() {
        return allVegan;
    }

    public void setAllVegan(Boolean allVegan) {
        this.allVegan = allVegan;
    }

    public static MenuItem menuItemFromJson(JSONObject jsonObject) {
        MenuItem b = new MenuItem();
        // Deserialize json into object fields
        try {
            b.id = jsonObject.getLong("menuid");
            b.name = jsonObject.getString("menuitemname");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }

    public static ArrayList<MenuItem> fromJson(JSONArray jsonArray) {
        JSONObject productJson;
        ArrayList<MenuItem> menuitems = new ArrayList<MenuItem>(jsonArray.length());
        for (int i =0; i < jsonArray.length(); i++) {
            try {
                productJson = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            MenuItem menuitem = Restaurant.menuItemFromJson(productJson);
            if (menuitem!= null) {
                menuitems.add(menuitem);
            }
        }
        return menuitems;
    }

    public String arrayListToString(ArrayList<MenuItem> array) {
        StringBuilder sb = new StringBuilder("On the Menu: \n");
        for (int i = 0 ; i < array.size(); i++) {
            sb.append(array.get(i).getMenuitemname() + "\n");
        }
        return sb.toString();
    }
}
