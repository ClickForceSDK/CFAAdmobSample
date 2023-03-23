package com.force.click.cfaadmobsample;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.clickforce.ad.Listener.AdViewListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationConfiguration;

import androidx.annotation.NonNull;

public class AdMobBannerCustomAD implements MediationBannerAd, AdViewListener {

    public static final String TAG = "ClickForce";

    public static final String SAMPLE_SDK_DOMAIN = "com.google.ads.mediation.sample.sdk";

    private com.clickforce.ad.AdView ad;

    /** Configuration for requesting the banner ad from the third-party network. */
    private final MediationBannerAdConfiguration mediationBannerAdConfiguration;

    /** Callback that fires on loading success or failure. */
    private final MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback>
            mediationAdLoadCallback;

    /** Callback for banner ad events. */
    private MediationBannerAdCallback bannerAdCallback;

    public AdMobBannerCustomAD(
            @NonNull MediationBannerAdConfiguration mediationBannerAdConfiguration,
            @NonNull MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> mediationAdLoadCallback
    ) {
        this.mediationBannerAdConfiguration = mediationBannerAdConfiguration;
        this.mediationAdLoadCallback = mediationAdLoadCallback;
    }

    public void loadAd() {
        Log.i("BannerCustomEvent", "Begin loading banner ad.");
        String serverParameter =
                mediationBannerAdConfiguration.getServerParameters().getString(
                        MediationConfiguration.CUSTOM_EVENT_SERVER_PARAMETER_FIELD);

        Log.d("BannerCustomEvent", "Received server parameter : " + serverParameter);
        Context context = mediationBannerAdConfiguration.getContext();
        ad = new com.clickforce.ad.AdView(context);
        ad.getAd(Integer.parseInt(serverParameter), com.clickforce.ad.AdSize.MA320X50); //todo 請修改對應的合作尺寸
        ad.setOnAdViewLoaded(this);
    }

    @Override
    public void OnAdViewLoadSuccess() {
        ad.show();
        bannerAdCallback = mediationAdLoadCallback.onSuccess(this);
        bannerAdCallback.reportAdImpression();
    }

    @Override
    public void OnAdViewLoadFail() {
        mediationAdLoadCallback.onFailure(createSdkError(2));
    }

    @Override
    public void OnAdViewClickToAd() {
        Log.d(TAG, "The banner ad was clicked.");
        bannerAdCallback.onAdOpened();
        bannerAdCallback.onAdLeftApplication();
        bannerAdCallback.reportAdClicked();
    }

    @NonNull
    @Override
    public View getView() {
        return ad;
    }

    public static AdError createSdkError(int errorCode) {
        return new AdError(errorCode, "No find ad.", SAMPLE_SDK_DOMAIN);
    }
}
