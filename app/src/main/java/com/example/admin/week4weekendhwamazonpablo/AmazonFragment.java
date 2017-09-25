package com.example.admin.week4weekendhwamazonpablo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.week4weekendhwamazonpablo.model.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AmazonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AmazonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AmazonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_RESPONSE = "response";
    //private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "AamazonFragment";
    RecyclerView rvCarList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    static List<Response> responseList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AmazonFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static AmazonFragment newInstance(List<Response> param1) {
        AmazonFragment fragment = new AmazonFragment();
        Bundle args = new Bundle();
      /*  try {
            args.putSerializable(ARG_RESPONSE, (ArrayList<? extends Serializable>) param1);
            Log.d(TAG, "newInstance: "+ "parcelable work!!");
        }
        catch (Exception e){
            Log.d(TAG, "newInstance: "+ e.toString());
        }
*/
      args.putSerializable(ARG_RESPONSE, (Serializable) param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
            //          mParam2 = getArguments().getString(ARG_PARAM2);
            responseList = (List<Response>) getArguments().getSerializable(ARG_RESPONSE);
        }
    }

/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amazon, container, false);
    }
*/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCarList = view.findViewById(R.id.rvCarList);
        //  toggleButton = view.findViewById(R.id.toggle_button_id);

        // famousRecyclerView = view.findViewById(R.id.recycler_view_fragment_left);
        //I need to receive by paremeter the layout manager then a switch to select the correct one




    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        RecyclerViewAdapterAmazon adapter = new RecyclerViewAdapterAmazon(responseList);
      //  adapter.setListener(AmazonFragment.this);
        rvCarList.setAdapter(adapter);




        layoutManager = new LinearLayoutManager(getContext());
        rvCarList.setLayoutManager(layoutManager);
        rvCarList.setLayoutManager(layoutManager);

        itemAnimator = new DefaultItemAnimator();
        rvCarList.setLayoutManager(layoutManager);
        rvCarList.setItemAnimator(itemAnimator);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amazon, container, false);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }





    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
