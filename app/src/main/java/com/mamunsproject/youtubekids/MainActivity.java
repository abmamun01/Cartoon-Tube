package com.mamunsproject.youtubekids;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mamunsproject.youtubekids.Adapter.AdapterViewPager;
import com.mamunsproject.youtubekids.Fragment.Home_Fragment;
import com.mamunsproject.youtubekids.Fragment.PlayList_Fragment;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.ibrahimsn.lib.OnItemReselectedListener;
import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

import static com.facebook.ads.AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;

public class MainActivity extends AppCompatActivity  {

    private final ArrayList<Fragment> fragmentList = new ArrayList<>();
    SmoothBottomBar bottomNavigationView;
    EditText searchId;
    private AdView adView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdSettings.setIntegrationErrorMode(INTEGRATION_ERROR_CRASH_DEBUG_MODE);
        AudienceNetworkAds.initialize(this);

        checkConnection();


//===============================================FB BANNER AD============================================


        adView = new AdView(this, "761984221190315_761984364523634", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();


//===============================================FB BANNER AD============================================



        bottomNavigationView = findViewById(R.id.bottomBar);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerID,new Home_Fragment()).commit();



        bottomNavigationView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (i) {


                    case 0:
                        transaction.replace(R.id.fragmentContainerID, new Home_Fragment());
                        transaction.commit();
                        break;


                    case 1:
                        transaction.replace(R.id.fragmentContainerID, new PlayList_Fragment());
                        transaction.commit();
                        break;


                }

                return false;

            }

        });


    }

    public void checkConnection(){

        ConnectivityManager manager=(ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();

        if (null!=activeNetwork){

            if (activeNetwork.getType()==ConnectivityManager.TYPE_WIFI){

            }
            if (activeNetwork.getType()==ConnectivityManager.TYPE_WIFI){

            }



        }else {


            new AlertDialog.Builder(this)
                    .setTitle("No Internet Connection!")
                    .setMessage("You Have To Turn On Your Mobile Data or Wifi!")
                    .setCancelable(false)

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation

                            checkConnection();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(R.drawable.ic_warn)
                    .show();





        }

    }

}
