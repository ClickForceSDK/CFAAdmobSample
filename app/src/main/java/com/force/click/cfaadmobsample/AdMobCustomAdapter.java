package com.force.click.cfaadmobsample;

import android.content.Context;

import com.clickforce.ad.WebServiceDO;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.VersionInfo;

import java.util.List;


public class AdMobCustomAdapter extends Adapter {

    @Override
    public void initialize(
            Context context,
            InitializationCompleteCallback initializationCompleteCallback,
            List<MediationConfiguration> list
    ) {
        initializationCompleteCallback.onInitializationSucceeded();
    }

    @Override
    public VersionInfo getSDKVersionInfo() {
        String[] splits = WebServiceDO.VERSION.split("\\.");

        if (splits.length >= 4) {
            int major = Integer.parseInt(splits[0]);
            int minor = Integer.parseInt(splits[1]);
            int micro = Integer.parseInt(splits[2]);
            return new VersionInfo(major, minor, micro);
        }

        return new VersionInfo(0, 0, 0);
    }

    @Override
    public VersionInfo getVersionInfo() {
        String[] splits = WebServiceDO.VERSION.split("\\.");

        if (splits.length >= 3) {
            int major = Integer.parseInt(splits[0]);
            int minor = Integer.parseInt(splits[1]);
            int micro = Integer.parseInt(String.valueOf((Integer.parseInt(splits[2]) * 100)));
            return new VersionInfo(major, minor, micro);
        }

        return new VersionInfo(0, 0, 0);
    }


    @Override
    public void loadBannerAd(
            MediationBannerAdConfiguration mediationBannerAdConfiguration,
            MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> callback
    ) {
        AdMobBannerCustomAD adMobBannerCustomAD = new AdMobBannerCustomAD(mediationBannerAdConfiguration, callback);
        adMobBannerCustomAD.loadAd();
    }
}
