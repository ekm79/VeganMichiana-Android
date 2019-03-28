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

//    public static void logIn() {
//    String auth = Base64.encodeToString("lambda-client:lambda-secret".getBytes(), Base64.DEFAULT);
//
//    Map<String, String> headerProperties = new HashMap<>();
//                headerProperties.put("Authorization", "Basic " + auth);
//
//                /*String tokenRequest = NetworkAdapter.httpRequest(
//                        "http://10.0.2.2:8080/oauth/token?grant_type=password&username=sally&password=password&scope=",
//                        "POST", null, headerProperties);*/
//
//        String tokenRequest = null;
//        try {
//            tokenRequest = NetworkAdapter.httpRequest(
//                    "http://192.168.1.14:2019/oauth/token?grant_type=password&username=erin&password=password&scope=",
//                    "POST", null, headerProperties);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Log.i("Restaurant", tokenRequest);
//                try {
//        String token = new JSONObject(tokenRequest).getString("access_token");
//
//        headerProperties.clear();
//        headerProperties.put("Authorization", "Bearer " + token);
//
//                    String result = null;
//                    try {
//                        result = NetworkAdapter.httpRequest("http://192.168.1.14:2019/admin", "GET", null, headerProperties);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Log.i("Authentication", result);
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }
//    }

    public interface ObjectCallback<T> {
        void returnObject(T object);
    }

    public static void getAllRestaurants(final ObjectCallback<Restaurant> objectCallback) {
        final ArrayList<Restaurant> restaurants = new ArrayList<>();
        final Semaphore            lock       = new Semaphore(1);
        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/restaurants/all", new NetworkAdapter.NetworkCallback() {
            @Override
            public void returnResult(Boolean success, String page) {
                // process page of data
                String nextUrl = "http://localhost:2019/restaurants/all";
                try {
//                    JSONObject pageJson     = new JSONObject(page);
                    JSONArray  resultsArray = new JSONArray(page);
                    for (int i = 0; i < resultsArray.length(); ++i) {
                        try {
                            lock.acquire();
                            restaurants.add(new Restaurant(resultsArray.getJSONObject(i)));
                            objectCallback.returnObject(restaurants.get(restaurants.size() - 1));
                            lock.release();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/restaurants/all", new NetworkAdapter.NetworkCallback() {
            @Override
            public void returnResult(Boolean success, String page) {
                // process page of data
                String nextUrl;
                try {
                    JSONArray resultsArray = new JSONArray(page);
                    for (int i = 0; i < resultsArray.length(); ++i) {
                        try {
                            lock.acquire();
                            restaurants.add(new Restaurant(resultsArray.getJSONObject(i)));
                            objectCallback.returnObject(restaurants.get(restaurants.size() - 1));
                            lock.release();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public static void getAllMenuItems(final AtomicBoolean canceled, final ObjectCallback<MenuItem> objectCallback) {
        final ArrayList<MenuItem> menuItems = new ArrayList<>();
        final Semaphore            lock       = new Semaphore(1);
        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/menuitems/all", new NetworkAdapter.NetworkCallback() {
            @Override
            public void returnResult(Boolean success, String page) {
                // process page of data
                String nextUrl = "http://localhost:2019/menuitems/all";
                try {
//                    JSONObject pageJson     = new JSONObject(page);
                    JSONArray  resultsArray = new JSONArray(page);
                    for (int i = 0; i < resultsArray.length(); ++i) {
                        try {
                            lock.acquire();
                            menuItems.add(new MenuItem(resultsArray.getJSONObject(i)));
                            objectCallback.returnObject(menuItems.get(menuItems.size() - 1));
                            lock.release();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/menuitems/all", new NetworkAdapter.NetworkCallback() {
            @Override
            public void returnResult(Boolean success, String page) {
                // process page of data
                String nextUrl;
                try {
                    JSONArray resultsArray = new JSONArray(page);
                    for (int i = 0; i < resultsArray.length(); ++i) {
                        try {
                            lock.acquire();
                            menuItems.add(new MenuItem(resultsArray.getJSONObject(i)));
                            objectCallback.returnObject(menuItems.get(menuItems.size() - 1));
                            lock.release();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public static void getAllStores(final AtomicBoolean canceled, final ObjectCallback<Store> objectCallback) {
        final ArrayList<Store> stores = new ArrayList<>();
        final Semaphore            lock       = new Semaphore(1);
        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/stores/all", new NetworkAdapter.NetworkCallback() {
            @Override
            public void returnResult(Boolean success, String page) {
                // process page of data
                String nextUrl = "http://localhost:2019/stores/all";
                try {
//                    JSONObject pageJson     = new JSONObject(page);
                    JSONArray  resultsArray = new JSONArray(page);
                    for (int i = 0; i < resultsArray.length(); ++i) {
                        try {
                            lock.acquire();
                            stores.add(new Store(resultsArray.getJSONObject(i)));
                            objectCallback.returnObject(stores.get(stores.size() - 1));
                            lock.release();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/stores/all", new NetworkAdapter.NetworkCallback() {
            @Override
            public void returnResult(Boolean success, String page) {
                // process page of data
                String nextUrl;
                try {
                    JSONArray resultsArray = new JSONArray(page);
                    for (int i = 0; i < resultsArray.length(); ++i) {
                        try {
                            lock.acquire();
                            stores.add(new Store(resultsArray.getJSONObject(i)));
                            objectCallback.returnObject(stores.get(stores.size() - 1));
                            lock.release();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
//    public static void getAllRestaurants(final AtomicBoolean canceled, final ObjectCallback<ArrayList<Restaurant>> objectCallback) {
//        final ArrayList<Restaurant> restaurants = new ArrayList<>();
//        final Semaphore lock    = new Semaphore(1);
//        final NetworkAdapter.NetworkCallback callback = new NetworkAdapter.NetworkCallback() {
//            @Override
//            public void returnResult(Boolean success, String page) {
//                // process page of data
//                if (canceled.get()) {
//                    Log.i("GetRequestCanceled", page);
//                    return;
//                }
//                JSONObject pageJson = null;
//                try {
////                    pageJson = new JSONObject(page);
//                    JSONArray resultsArray = new JSONArray(page);
//
////                    String nextUrl = null;
////                    try {
////                        nextUrl = resultsArray.getString("next");
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                        nextUrl = null;
////                    }
////                    // yay recursion!
////                    if (nextUrl != null) {
////                        NetworkAdapter.httpGetRequest(nextUrl, canceled, this);
////                    }
//
//                    //                        JSONArray resultsArray = pageJson.getJSONArray("results");
////
////                        if (canceled.get()) {
////                            Log.i("GetRequestCanceled", page);
////                            return;
////                        }
//
//                    for (int i = 0; i < resultsArray.length(); ++i) {
//                        try {
//                            lock.acquire();
//                            restaurants.add(new Restaurant(resultsArray.getJSONObject(i)));
//                            lock.release();
//                            for (Restaurant r: restaurants) {
//                                Log.i("Restaurants", r.getName());
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
////                    if (nextUrl == null) {
////                    /*synchronized (planets) {
////                        planets.notify();
////                    }*/
////                        objectCallback.returnObject(restaurants);
////                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    if(page.equals("")) {
//                        objectCallback.returnObject(restaurants);
//                    }
//                }
//
//            }
//        };
//        if (canceled.get()) {
//            Log.i("GetRequestCanceled", "First");
//            return;
//        }
//        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/restaurants/all", canceled, callback);
//    }
//
//
//    public static void getAllMenuItems(final AtomicBoolean canceled, final ObjectCallback<ArrayList<MenuItem>> objectCallback) {
//        final ArrayList<MenuItem> menuItems = new ArrayList<>();
//        final Semaphore lock    = new Semaphore(1);
//        final NetworkAdapter.NetworkCallback callback = new NetworkAdapter.NetworkCallback() {
//            @Override
//            public void returnResult(Boolean success, String page) {
//                // process page of data
//                if (canceled.get()) {
//                    Log.i("GetRequestCanceled", page);
//                    return;
//                }
//                JSONObject pageJson = null;
//                try {
//                    JSONArray resultsArray = new JSONArray(page);
//
//                    for (int i = 0; i < resultsArray.length(); ++i) {
//                        try {
//                            lock.acquire();
//                            menuItems.add(new MenuItem(resultsArray.getJSONObject(i)));
//                            lock.release();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
////                    if (nextUrl == null) {
////                    /*synchronized (planets) {
////                        planets.notify();
////                    }*/
////                        objectCallback.returnObject(menuItems);
////                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    if(page.equals("")) {
//                        objectCallback.returnObject(menuItems);
//                    }
//                }
//
//            }
//        };
//        if (canceled.get()) {
//            Log.i("GetRequestCanceled", "First");
//            return;
//        }
//        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/menuitems/all", canceled, callback);
//    }
//
//
//
//    public static void getAllStores(final AtomicBoolean canceled, final ObjectCallback<ArrayList<Store>> objectCallback) {
//        final ArrayList<Store> stores = new ArrayList<>();
//        final Semaphore lock    = new Semaphore(1);
//        final NetworkAdapter.NetworkCallback callback = new NetworkAdapter.NetworkCallback() {
//            @Override
//            public void returnResult(Boolean success, String page) {
//                // process page of data
//                if (canceled.get()) {
//                    Log.i("GetRequestCanceled", page);
//                    return;
//                }
//                JSONObject pageJson = null;
//                try {
////                    pageJson = new JSONObject(page);
//                    JSONArray resultsArray = new JSONArray(page);
//
//
//                    for (int i = 0; i < resultsArray.length(); ++i) {
//                        try {
//                            lock.acquire();
//                            stores.add(new Store(resultsArray.getJSONObject(i)));
//                            lock.release();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    if(page.equals("")) {
//                        objectCallback.returnObject(stores);
//                    }
//                }
//
//            }
//        };
//        if (canceled.get()) {
//            Log.i("GetRequestCanceled", "First");
//            return;
//        }
//        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/stores/all", canceled, callback);
//    }


    public static void getAllProducts(final AtomicBoolean canceled, final ObjectCallback<Product> objectCallback) {
        final ArrayList<Product> products = new ArrayList<>();
        final Semaphore            lock       = new Semaphore(1);
        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/products/all", new NetworkAdapter.NetworkCallback() {
            @Override
            public void returnResult(Boolean success, String page) {
                // process page of data
                String nextUrl = "http://localhost:2019/products/all";
                try {
//                    JSONObject pageJson     = new JSONObject(page);
                    JSONArray  resultsArray = new JSONArray(page);
                    for (int i = 0; i < resultsArray.length(); ++i) {
                        try {
                            lock.acquire();
                            products.add(new Product(resultsArray.getJSONObject(i)));
                            objectCallback.returnObject(products.get(products.size() - 1));
                            lock.release();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        NetworkAdapter.httpGetRequest("http://192.168.1.14:2019/products/all", new NetworkAdapter.NetworkCallback() {
            @Override
            public void returnResult(Boolean success, String page) {
                // process page of data
                String nextUrl;
                try {
                    JSONArray resultsArray = new JSONArray(page);
                    for (int i = 0; i < resultsArray.length(); ++i) {
                        try {
                            lock.acquire();
                            products.add(new Product(resultsArray.getJSONObject(i)));
                            objectCallback.returnObject(products.get(products.size() - 1));
                            lock.release();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
