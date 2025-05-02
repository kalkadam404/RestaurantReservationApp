package com.android.data.model.banner

data class BannerResponse (
    val id: Int,
    val title: String,
    val subtitle: String?, // nullable in Django
    val content: String?,  // nullable in Django
    val image: String?,    // could be null if not uploaded yet
    val url: String?,      // nullable in Django
    val button_text: String?, // nullable in Django
    val position: String?,     // has default but still could be null/missing
    val color_scheme: String?, // has default but still best to mark nullable
    val start_date: String,    // DateTime in Django; use String for now
    val end_date: String?,     // nullable in Django
    val is_active: Boolean,
    val priority: Int?,        // has default but should be nullable for safety
    val impressions: Int?,     // auto-filled; can default to 0 but still nullable
    val clicks: Int?,          // same as above
    val ctr: Double?
)

