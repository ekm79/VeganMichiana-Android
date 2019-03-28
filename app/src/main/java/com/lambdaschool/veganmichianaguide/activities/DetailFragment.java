package com.lambdaschool.veganmichianaguide.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lambdaschool.veganmichianaguide.R;
import com.lambdaschool.veganmichianaguide.apiaccess.PostDao;
import com.lambdaschool.veganmichianaguide.models.ApiObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private ApiObject mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param object Parameter 1.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(ApiObject object) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, object);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (ApiObject) getArguments().getSerializable(ARG_PARAM1);
        }
        getActivity().supportPostponeEnterTransition();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View view1 = getView();
        final TextView nameView = (TextView) view.findViewById(R.id.detail_text_name);
        final TextView contentView = (TextView) view.findViewById(R.id.detail_text_content);
        final ListView listView = (ListView) view.findViewById(R.id.list_view);
        final FragmentActivity activity = getActivity();
        Button deleteButton = (Button) view.findViewById(R.id.delete_button);
        final Button updateButton = (Button) view.findViewById(R.id.update_button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: change to updatefragment
                UpdateItemFragment update = UpdateItemFragment.newInstance(mParam1);
                replaceFragment(update);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PostDao.deleteProduct(mParam1.getId());
                        if (getFragmentManager().getBackStackEntryCount() != 0) {
                            getFragmentManager().popBackStack();
                        }
                    }
                }).start();
                Toast.makeText(getContext(), "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });

//        nameView.setText(mParam1.getName());
//        contentView.setText(mParam1.getContent());


        new Thread(new Runnable() {
            @Override
            public void run() {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().supportStartPostponedEnterTransition();
                        nameView.setText(mParam1.getName());
                        contentView.setText(mParam1.getContent());

//                        ArrayList<ApiObject> array = new ArrayList<>();
//                        for (int i = 0; i < mParam1.getArrayList().size(); i++) {
//                            array.add(mParam1.getArrayList().get(i));
//                        }
//
//                        ListArrayAdapter adapter = new ListArrayAdapter(getContext(), android.R.layout.simple_list_item_1, array);
//                        listView.setAdapter(adapter);
                        Log.i("Restaurant", mParam1.getName());
                        Log.i("Restaurant", nameView.getText().toString());
                        Log.i("Restaurant", contentView.getText().toString());
                    }
                });
            }
        }).start();
    }

    private class ListArrayAdapter extends ArrayAdapter<ApiObject> {
        ArrayList<ApiObject> arrayList = new ArrayList<>();
        public ListArrayAdapter(Context context, int textViewResourceId, ArrayList<ApiObject> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                arrayList.add(objects.get(i));
            }
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(ApiObject item);
    }

    public void replaceFragment(Fragment frag) {
//        UpdateItemFragment update = UpdateItemFragment.newInstance(frag);
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_holder_detail, frag);
        fr.addToBackStack(null);
        fr.commit();
    }
}
