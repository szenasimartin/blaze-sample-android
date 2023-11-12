package com.wscsports.android.blaze.sampleapp.ads

import com.blaze.blazesdk.features.ads.models.BlazeAdModel
import com.blaze.blazesdk.features.ads.models.BlazeTrackingPixel
import com.google.android.gms.ads.nativead.NativeCustomFormatAd

fun NativeCustomFormatAd?.toAdModel(): BlazeAdModel? = this?.run {
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

    val ctaType: BlazeAdModel.CtaModel.CTAType? = when (clickType) {
        AdConstants.WEB -> BlazeAdModel.CtaModel.CTAType.WEB
        AdConstants.IN_APP -> BlazeAdModel.CtaModel.CTAType.DEEPLINK
        else -> null
    }
    val cta = if (ctaType != null &&
        !clickThroughUrl.isNullOrEmpty() &&
        !clickThroughCTA.isNullOrEmpty()) {
        BlazeAdModel.CtaModel(
            type = ctaType,
            text = clickThroughCTA,
            url = clickThroughUrl
        )
    } else
        null

    val content = if (creativeType == AdConstants.DISPLAY && image != null) {
        BlazeAdModel.Content.Image(urlString = image, duration = 5000.0)
    } else if (creativeType == AdConstants.VIDEO && video != null) {
        val previewImageUrl = getText(AdConstants.VIDEO_PREVIEW_IMAGE_URL)?.toString()
        BlazeAdModel.Content.Video(urlString = video, loadingImageUrl = previewImageUrl)
    } else
        null

    // Content must be provided.
    content ?: return null

    return BlazeAdModel(
        content = content,
        title = advertiserName,
        cta = cta,
        trackingPixelAdList = trackingPixels,
        customAdditionalData = CustomAdData(nativeAd = this),
        analyticsData = BlazeAdModel.AnalyticsData(
            advertiserId = "some advertiserId",
            advertiserName = "some advertiserName",
            campaignId = "some campaignId",
            campaignName = "some campaignName",
            adServer = "some adServer"
        )
    )
}