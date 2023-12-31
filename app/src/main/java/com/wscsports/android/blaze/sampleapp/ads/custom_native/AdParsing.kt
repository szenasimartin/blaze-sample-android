package com.wscsports.android.blaze.sampleapp.ads.custom_native

import com.blaze.blazesdk.features.ads.custom_native.models.BlazeGoogleCustomNativeAdModel
import com.blaze.blazesdk.features.ads.custom_native.models.BlazeTrackingPixel
import com.google.android.gms.ads.nativead.NativeCustomFormatAd

fun NativeCustomFormatAd?.toAdModel(): BlazeGoogleCustomNativeAdModel? = this?.run {
    val advertiserName = getText(AdConstants.ADVERTISER_NAME)?.toString()
    val creativeType = getText(AdConstants.CREATIVE_TYPE)?.toString()
    val image = getImage(AdConstants.IMAGE)?.uri?.toString()
    val video = getText(AdConstants.VIDEO_URL)?.toString()
    val clickType = getText(AdConstants.CLICK_TYPE)?.toString()
    val clickThroughUrl = getText(AdConstants.CLICK_URL)?.toString()
    val clickThroughCTA = getText(AdConstants.CLICK_CTA)?.toString()

    val trackingPixels = mutableSetOf<BlazeTrackingPixel>()
    getText(AdConstants.TRACKING_URL)?.toString()?.let { trackingUrl ->
        trackingPixels.add(
            BlazeTrackingPixel(
                eventType = BlazeTrackingPixel.PixelAdsEvents.OPENED_AD,
                url = trackingUrl
            )
        )
    }

    val ctaType: BlazeGoogleCustomNativeAdModel.CtaModel.CTAType? = when (clickType) {
        AdConstants.WEB -> BlazeGoogleCustomNativeAdModel.CtaModel.CTAType.WEB
        AdConstants.IN_APP -> BlazeGoogleCustomNativeAdModel.CtaModel.CTAType.DEEPLINK
        else -> null
    }
    val cta = if (ctaType != null &&
        !clickThroughUrl.isNullOrEmpty() &&
        !clickThroughCTA.isNullOrEmpty()) {
        BlazeGoogleCustomNativeAdModel.CtaModel(
            type = ctaType,
            text = clickThroughCTA,
            url = clickThroughUrl
        )
    } else
        null

    val content = if (creativeType == AdConstants.DISPLAY && image != null) {
        BlazeGoogleCustomNativeAdModel.Content.Image(urlString = image, duration = 5000.0)
    } else if (creativeType == AdConstants.VIDEO && video != null) {
        val previewImageUrl = getText(AdConstants.VIDEO_PREVIEW_IMAGE_URL)?.toString()
        BlazeGoogleCustomNativeAdModel.Content.Video(urlString = video, loadingImageUrl = previewImageUrl)
    } else
        null

    // Content must be provided.
    content ?: return null

    return BlazeGoogleCustomNativeAdModel(
        content = content,
        title = advertiserName,
        cta = cta,
        trackingPixelAdList = trackingPixels,
        customAdditionalData = CustomAdData(nativeAd = this),
        analyticsData = BlazeGoogleCustomNativeAdModel.AnalyticsData(
            advertiserId = "some advertiserId",
            advertiserName = "some advertiserName",
            campaignId = "some campaignId",
            campaignName = "some campaignName",
            adServer = "some adServer"
        )
    )
}