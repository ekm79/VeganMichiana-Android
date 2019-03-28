package com.lambdaschool.veganmichianaguide.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lambdaschool.veganmichianaguide.R;
import com.lambdaschool.veganmichianaguide.apiaccess.VeganMichianaDao;
import com.lambdaschool.veganmichianaguide.models.ApiObject;
import com.lambdaschool.veganmichianaguide.models.MenuItem;
import com.lambdaschool.veganmichianaguide.models.Product;
import com.lambdaschool.veganmichianaguide.models.Restaurant;
import com.lambdaschool.veganmichianaguide.models.Store;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListFragment extends Fragment {

    public static final int CATEGORY_RESTAURANT = 1;
    public static final int CATEGORY_MENU_ITEM = 2;
    public static final int CATEGORY_PRODUCT = 3;
    public static final int CATEGORY_STORE = 4;


    // TODO: Customize parameter argument names
    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String ARG_CATEGORY = "category";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int category = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListFragment newInstance(int columnCount) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            category = getArguments().getInt(ARG_CATEGORY);
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.list);
        Button addButton = (Button) view.findViewById(R.id.add_to_list);
        switch (category){
            case CATEGORY_PRODUCT:
                addButton.setText("Add to Products");
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddItemFragment addItem = AddItemFragment.newInstance(CATEGORY_PRODUCT);
                        replaceFragment(addItem);
                    }
                });
                break;
            case CATEGORY_RESTAURANT:
                addButton.setText("Add to Restaurants");
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddItemFragment addItem = AddItemFragment.newInstance(CATEGORY_RESTAURANT);
                        replaceFragment(addItem);
                    }
                });
                break;
            case CATEGORY_STORE:
                addButton.setText("Add to Stores");
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddItemFragment addItem = AddItemFragment.newInstance(CATEGORY_STORE);
                        replaceFragment(addItem);
                    }
                });
                break;
            case CATEGORY_MENU_ITEM:
                addButton.setText("Add to Menu Items");
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddItemFragment addItem = AddItemFragment.newInstance(CATEGORY_MENU_ITEM);
                        replaceFragment(addItem);
                    }
                });
                break;
        }
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddItemFragment addItem = AddItemFragment.newInstance(category);
//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.replace(R.id.fragment_holder_detail, addItem);
//                fr.commit();
//            }
//        });



        // Set the adapter
        if (recyclerView != null) {
            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyListRecyclerViewAdapter(new ArrayList<ApiObject>(), mListener));

            switch (category) {
                case CATEGORY_RESTAURANT:
                    VeganMichianaDao.getAllRestaurants(new VeganMichianaDao.ObjectCallback<Restaurant>() {
                        @Override
                        public void returnObject(final Restaurant object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                            /*ArrayList<ApiObject> parentObject = new ArrayList<>();
                            parentObject.addAll(object);

                            recyclerView.setAdapter(new MySwApiObjectRecyclerViewAdapter(parentObject, mListener));*/
                                    ((MyListRecyclerViewAdapter) recyclerView.getAdapter()).addItem(object);
                                }
                            });
                        }

                        public void returnObject(final ArrayList<Restaurant> object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    ArrayList<ApiObject> parentObject = new ArrayList<>();
                                    parentObject.addAll(object);

                                    recyclerView.setAdapter(new MyListRecyclerViewAdapter(parentObject, mListener));
                                }
                            });
                        }
                    });
                    break;

                case CATEGORY_MENU_ITEM:
                    VeganMichianaDao.getAllMenuItems(new AtomicBoolean(false), new VeganMichianaDao.ObjectCallback<MenuItem>() {
                        @Override
                        public void returnObject(final MenuItem object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                            /*ArrayList<SwApiObject> parentObject = new ArrayList<>();
                            parentObject.addAll(object);

                            recyclerView.setAdapter(new MySwApiObjectRecyclerViewAdapter(parentObject, mListener));*/
                                    ((MyListRecyclerViewAdapter) recyclerView.getAdapter()).addItem(object);
                                }
                            });
                        }

                        public void returnObject(final ArrayList<MenuItem> object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    ArrayList<ApiObject> parentObject = new ArrayList<>();
                                    parentObject.addAll(object);

                                    recyclerView.setAdapter(new MyListRecyclerViewAdapter(parentObject, mListener));
                                }
                            });
                        }
                    });
                    break;

                case CATEGORY_STORE:
                    VeganMichianaDao.getAllStores(new AtomicBoolean(false), new VeganMichianaDao.ObjectCallback<Store>() {
                        @Override
                        public void returnObject(final Store object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                            /*ArrayList<SwApiObject> parentObject = new ArrayList<>();
                            parentObject.addAll(object);

                            recyclerView.setAdapter(new MySwApiObjectRecyclerViewAdapter(parentObject, mListener));*/
                                    ((MyListRecyclerViewAdapter) recyclerView.getAdapter()).addItem(object);
                                }
                            });
                        }

                        public void returnObject(final ArrayList<Store> object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    ArrayList<ApiObject> parentObject = new ArrayList<>();
                                    parentObject.addAll(object);

                                    recyclerView.setAdapter(new MyListRecyclerViewAdapter(parentObject, mListener));
                                }
                            });
                        }
                    });
                    break;

                case CATEGORY_PRODUCT:
                    VeganMichianaDao.getAllProducts(new AtomicBoolean(false), new VeganMichianaDao.ObjectCallback<Product>() {
                        @Override
                        public void returnObject(final Product object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                            /*ArrayList<SwApiObject> parentObject = new ArrayList<>();
                            parentObject.addAll(object);

                            recyclerView.setAdapter(new MySwApiObjectRecyclerViewAdapter(parentObject, mListener));*/
                                    ((MyListRecyclerViewAdapter) recyclerView.getAdapter()).addItem(object);
                                }
                            });
                        }

                        public void returnObject(final ArrayList<Product> object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    ArrayList<ApiObject> parentObject = new ArrayList<>();
                                    parentObject.addAll(object);

                                    recyclerView.setAdapter(new MyListRecyclerViewAdapter(parentObject, mListener));
                                }
                            });
                        }
                    });
                    break;
            }
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ApiObject item, View sharedView);

//        void onListFragmentInteraction(ApiObject item, View sharedView);
    }

    public void replaceFragment(Fragment frag) {
//        UpdateItemFragment update = UpdateItemFragment.newInstance(frag);
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_holder_detail, frag);
        fr.addToBackStack(null);
        fr.commit();
    }
}
