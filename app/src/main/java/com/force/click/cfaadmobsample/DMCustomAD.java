package com.force.click.cfaadmobsample;

import android.app.Activity;
import android.util.Log;

import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.customevent.CustomEventBanner;
import com.google.ads.mediation.customevent.CustomEventBannerListener;

/**
 * Created by YaoChang on 2017/7/20.
 */



public class DMCustomAD  implements CustomEventBanner {
    private com.clickforce.ad.AdView ad;

    @Override
    public void destroy() {

        if (ad != null)
            ad.releaseAd();
    }

    @Override
    public void requestBannerAd(final CustomEventBannerListener listener,
                                final Activity activity, String label, String serverParameter,
                                AdSize adSize, MediationAdRequest request, Object customEventExtra) {

        Log.d("label", label);
        Log.d("Parameter", serverParameter);


        ad = new com.clickforce.ad.AdView(activity);
        int weg = (int) ((activity.getResources().getDisplayMetrics().widthPixels) * 0.6);
        int hig = (int) (weg * 0.15625);

        ad.getAd(Integer.parseInt(serverParameter),  weg, hig,320,50);
        listener.onReceivedAd(ad);

    }
}
