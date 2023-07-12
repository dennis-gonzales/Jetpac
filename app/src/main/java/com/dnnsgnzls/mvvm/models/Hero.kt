package com.dnnsgnzls.mvvm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnnsgnzls.mvvm.models.enums.AttackType
import com.dnnsgnzls.mvvm.models.enums.ImageType
import com.dnnsgnzls.mvvm.models.enums.PrimaryAttribute
import com.dnnsgnzls.mvvm.models.enums.Role
import com.google.gson.annotations.SerializedName

@Entity(tableName = "hero")
data class Hero(
    @PrimaryKey
    val id: Int,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("localized_name")
    @SerializedName("localized_name")
    val localizedName: String,

    @ColumnInfo("primary_attr")
    @SerializedName("primary_attr")
    val primaryAttribute: PrimaryAttribute,

    @ColumnInfo("attack_type")
    @SerializedName("attack_type")
    val attackType: AttackType,

    @ColumnInfo("roles")
    val roles: List<Role>,

    @ColumnInfo("legs")
    val legs: Int
)  {
    private fun getImageUrl(type: ImageType): String {
        val typeName = when (type) {
            ImageType.ICON -> "icon"
            ImageType.FULL -> "full"
        }

        // Move the api string to constants
        return "https://api.opendota.com/apps/dota2/images/heroes/${name.removePrefix("npc_dota_hero_")}_${typeName}.png"
    }

    val fullImageUrl: String
        get() = getImageUrl(ImageType.FULL)

    val iconUrl: String
        get() = getImageUrl(ImageType.ICON)
}

