package com.lambdaschool.veganmichianaguide.apiaccess;

import android.util.Base64;
import android.util.Log;

import com.lambdaschool.veganmichianaguide.models.MenuItem;
import com.lambdaschool.veganmichianaguide.models.Product;
import com.lambdaschool.veganmichianaguide.models.Restaurant;
import com.lambdaschool.veganmichianaguide.models.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class VeganMichianaDao {

    final static String GET = "GET";
    final static String POST = "POST";
    final static String PUT = "PUT";
    final static String DELETE = "DELETE";

    public static Map<String, String> headerProperties;

    public static Map<String, String> logIn(String username, String password) {
        String token = "";
        String auth = Base64.encodeToString("lambda-client:lambda-secret".getBytes(), Base64.DEFAULT);

        headerProperties = new HashMap<>();
        headerProperties.put("Authorization", "Basic " + auth);

                /*String tokenRequest = NetworkAdapter.httpRequest(
                        "http://192.168.1.14:2019/oauth/token?grant_type=password&username=sally&password=password&scope=",
                        "POST", null, headerProperties);*/


        String tokenRequest = null;
        try {
            tokenRequest = NetworkAdapter.httpRequest(
                    "http://192.168.1.14:2019/oauth/token?grant_type=password&username="
                            + username + "&password="
                            + password + "&scope=",
                    "POST", null, headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("TokenRequest", tokenRequest);
        try {
            token = new JSONObject(tokenRequest).getString("access_token");

            headerProperties.clear();
            headerProperties.put("Authorization", "Bearer " + token);


            String result = null;
            try {
                result = NetworkAdapter.httpRequest("http://192.168.1.14:2019/restaurants/all", "GET", null, headerProperties);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Log.i("Authentication", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Log.i("HeaderProperties", headerProperties.toString());
        return headerProperties;
    }


    public static ArrayList<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        String page = null;
        try {
            page = NetworkAdapter.httpRequest("http://192.168.1.14:2019/restaurants/all", GET, null, headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray resultsArray = new JSONArray(page);
            for (int i = 0; i < resultsArray.length(); ++i) {
                try {
                    restaurants.add(new Restaurant(resultsArray.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public static ArrayList<MenuItem> getAllMenuItems() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        String page = null;
        try {
            page = NetworkAdapter.httpRequest("http://192.168.1.14:2019/menuitems/all", GET, null, headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray resultsArray = new JSONArray(page);
            for (int i = 0; i < resultsArray.length(); ++i) {
                try {
                    menuItems.add(new MenuItem(resultsArray.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public static ArrayList<Store> getAllStores() {
        ArrayList<Store> stores = new ArrayList<>();
        String page = null;
        try {
            page = NetworkAdapter.httpRequest("http://192.168.1.14:2019/stores/all", GET, null, headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray resultsArray = new JSONArray(page);
            for (int i = 0; i < resultsArray.length(); ++i) {
                try {
                    stores.add(new Store(resultsArray.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stores;
    }

    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        String page = null;
        try {
            page = NetworkAdapter.httpRequest("http://192.168.1.14:2019/products/all", GET, null, headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray resultsArray = new JSONArray(page);
            for (int i = 0; i < resultsArray.length(); ++i) {
                try {
                    products.add(new Product(resultsArray.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }
}

//    public static ArrayList<Restaurant> getAllRestaurants(final ObjectCallback<Restaurant> objectCallback) {
//        final ArrayList<Restaurant> restaurants = new ArrayList<>();
//        try {
//            NetworkAdapter.httpRequest("http://192.168.1.14:2019/restaurants/all", GET, null, headerProperties);
//            for (int i = 0; i <)
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/restaurants/all", new NetworkAdapter.NetworkCallback() {
//            @Override
//            public void returnResult(Boolean success, String page) {
//                // process page of data
//                String nextUrl;
//                try {
//                    JSONArray resultsArray = new JSONArray(page);
//                    for (int i = 0; i < resultsArray.length(); ++i) {
//                        try {
//                            lock.acquire();
//                            restaurants.add(new Restaurant(resultsArray.getJSONObject(i)));
//                            objectCallback.returnObject(restaurants.get(restaurants.size() - 1));
//                            lock.release();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });
//    }


