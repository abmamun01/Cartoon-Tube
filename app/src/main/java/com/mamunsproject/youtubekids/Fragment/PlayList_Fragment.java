package com.mamunsproject.youtubekids.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mamunsproject.youtubekids.Activity.BanglaCartoon;
import com.mamunsproject.youtubekids.Activity.Ben10;
import com.mamunsproject.youtubekids.Activity.Doraemon;
import com.mamunsproject.youtubekids.Activity.Hindi_Cartoon;
import com.mamunsproject.youtubekids.Activity.MotuPatlu;
import com.mamunsproject.youtubekids.Activity.MrBeanCartoon;
import com.mamunsproject.youtubekids.Activity.Oggy_and_coakroaches;
import com.mamunsproject.youtubekids.Activity.TomJerry;
import com.mamunsproject.youtubekids.R;

public class PlayList_Fragment extends Fragment {

    public CardView tomJerryCard,motuPatluCard,oggyandCoackroachesCard,doraemonCard,ben10,banglaCartoon,mrBeanCard,hindiCartoon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_list_, container, false);


        tomJerryCard=view.findViewById(R.id.tomJerryCard);
        motuPatluCard=view.findViewById(R.id.motuPatluCard);

        oggyandCoackroachesCard=view.findViewById(R.id.oggy_and_coakroaches_Card);
        doraemonCard=view.findViewById(R.id.doraemonCard);
        ben10=view.findViewById(R.id.benTenCard);
        banglaCartoon=view.findViewById(R.id.banglaCartoon);
        mrBeanCard=view.findViewById(R.id.mrBeanCard);
        hindiCartoon=view.findViewById(R.id.hindiCartoonCard);


        tomJerryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TomJerry.class));

            }
        });


        ben10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Ben10.class));

            }
        });




        motuPatluCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MotuPatlu.class));

            }
        });








        oggyandCoackroachesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Oggy_and_coakroaches.class));

            }
        });




        doraemonCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Doraemon.class));

            }
        });


       banglaCartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BanglaCartoon.class));

            }
        });


       mrBeanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MrBeanCartoon.class));

            }
        });


       hindiCartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Hindi_Cartoon.class));

            }
        });




        return view;
    }
}