package com.lambdaschool.veganmichianaguide.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.lambdaschool.veganmichianaguide.R;
import com.lambdaschool.veganmichianaguide.apiaccess.PostDao;
import com.lambdaschool.veganmichianaguide.models.ApiObject;

import org.json.JSONException;
import org.json.JSONObject;


public class AddItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "category";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int category = 1;

//    private OnFragmentInteractionListener mListener;

    public static final String ARG_CATEGORY = "category";

    EditText name;
    EditText description;
    EditText address;
    EditText phone;
    EditText link;
    CheckBox checkbox;
    Button addButton;

    public AddItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment AddItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddItemFragment newInstance(int category) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM2, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt(ARG_PARAM2);
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Restaurant", String.valueOf(category));
        return inflater.inflate(R.layout.fragment_add_item, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        final int category = getActivity().getIntent().getExtras().getInt("category");


        name =  view.findViewById(R.id.name_input);
        address =  view.findViewById(R.id.address_input);
        phone = view.findViewById(R.id.phone_input);
        description = view.findViewById(R.id.description);
        checkbox = view.findViewById(R.id.checkbox);
        link = view.findViewById(R.id.link_input);

        addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final JSONObject object = new JSONObject();

                switch (category) {
                    case ListFragment.CATEGORY_RESTAURANT:

                        try {
                            object.put("restaurantname", name.getText().toString());
                            object.put("description", description.getText().toString());
                            object.put("location", address.getText().toString());
                            object.put("phone", phone.getText().toString());
                            object.put("link", link.getText().toString());
                            object.put("allvegan", checkbox.isChecked());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.i("Restaurant", object.toString());

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                PostDao.addRestaurant(object);
                                if (getFragmentManager().getBackStackEntryCount() != 0) {
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }).start();
                        break;

                    case ListFragment.CATEGORY_MENU_ITEM:
                        try {
                            object.put("menuitemname", name.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                PostDao.addMenuItem(object);
                                if (getFragmentManager().getBackStackEntryCount() != 0) {
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }).start();
                        break;

                    case ListFragment.CATEGORY_PRODUCT:
                        try {
                            object.put("productname", name.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.i("Restaurant", object.toString());

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                PostDao.addProduct(object);
                                if (getFragmentManager().getBackStackEntryCount() != 0) {
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }).start();
                        break;

                    case ListFragment.CATEGORY_STORE:
                        try {
                            object.put("storename", name.getText().toString());
                            object.put("location", address.getText().toString());
                            object.put("phone", phone.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                PostDao.addStore(object);
                                if (getFragmentManager().getBackStackEntryCount() != 0) {
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }).start();
                        break;

                }
                Toast.makeText(getContext(), "Item Created", Toast.LENGTH_SHORT).show();

            }
        });
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
