package com.force.click.cfaadmobsample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.clickforce.ad.Listener.AdViewListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;

/**
 * Created by YaoChang on 2017/7/20.
 */



public class AdMobBannerCustomAD implements CustomEventBanner {
    private com.clickforce.ad.AdView ad;



    /** The event is being destroyed. Perform any necessary cleanup here. */
    @Override
    public void onDestroy() {

    }

    /**
     * The app is being paused. This call is only forwarded to the adapter if the developer
     * notifies AdMob Mediation that the app is being paused.
     */
    @Override
    public void onPause() {
        // The sample ad network doesn't have an onPause method, so it does nothing.
    }

    /**
     * The app is being resumed. This call is only forwarded to the
     * adapter if the developer notifies AdMob Mediation that the app is
     * being resumed.
     */
    @Override
    public void onResume() {
        // The sample ad network doesn't have an onResume method, so it does nothing.
    }

    @Override
    public void requestBannerAd(Context context,
                                CustomEventBannerListener listener,
                                String serverParameter,
                                AdSize size,
                                MediationAdRequest mediationAdRequest,
                                Bundle customEventExtras) {

//        Log.d("Parameter", serverParameter);

        ad = new com.clickforce.ad.AdView(context);
        ad.getAd(Integer.parseInt(serverParameter), com.clickforce.ad.AdSize.MA320X50);

        listener.onAdLoaded(ad);
        ad.setOnAdViewLoaded(new AdViewListener() {
            @Override
            public void OnAdViewLoadSuccess() {
                ad.show();
            }

            @Override
            public void OnAdViewLoadFail() {

            }

            @Override
            public void OnAdViewClickToAd() {

            }
        });

    }


}
