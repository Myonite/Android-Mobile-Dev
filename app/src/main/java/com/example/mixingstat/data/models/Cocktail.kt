package com.example.mixingstat.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cocktail")
data class Cocktail(
    @PrimaryKey @SerializedName("idDrink")  val idDrink: String,
    val strDrink: String,
    val strDrinkAlternate: String? = null,
    val strTags: String? = null,
    val strVideo: String? = null,
    val strCategory: String? = null,
    val strIBA: String? = null,
    val strAlcoholic: String? = null,
    val strGlass: String? = null,
    val strInstructions: String? = null,
    val strInstructionsES: String? = null,
    val strInstructionsDE: String? = null,
    val strInstructionsFR: String? = null,
    val strInstructionsIT: String? = null,
    val strInstructionsZH_HANS: String? = null,
    val strInstructionsZH_HANT: String? = null,
    val strDrinkThumb: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,
    val strIngredient7: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null,
    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null,
    val strMeasure4: String? = null,
    val strMeasure5: String? = null,
    val strMeasure6: String? = null,
    val strMeasure7: String? = null,
    val strMeasure8: String? = null,
    val strMeasure9: String? = null,
    val strMeasure10: String? = null,
    val strMeasure11: String? = null,
    val strMeasure12: String? = null,
    val strMeasure13: String? = null,
    val strMeasure14: String? = null,
    val strMeasure15: String? = null,
    val strImageSource: String? = null,
    val strImageAttribution: String? = null,
    val strCreativeCommonsConfirmed: String? = null,
    val dateModified: String? = null

){
    fun getIngredientsWithMeasures(cocktail: Cocktail): List<Pair<String?, String?>> {
        return listOf(
            Pair(cocktail.strIngredient1, cocktail.strMeasure1),
            Pair(cocktail.strIngredient2, cocktail.strMeasure2),
            Pair(cocktail.strIngredient3, cocktail.strMeasure3),
            Pair(cocktail.strIngredient4, cocktail.strMeasure4),
            Pair(cocktail.strIngredient5, cocktail.strMeasure5),
            Pair(cocktail.strIngredient6, cocktail.strMeasure6),
            Pair(cocktail.strIngredient7, cocktail.strMeasure7),
            Pair(cocktail.strIngredient8, cocktail.strMeasure8),
            Pair(cocktail.strIngredient9, cocktail.strMeasure9),
            Pair(cocktail.strIngredient10, cocktail.strMeasure10),
            Pair(cocktail.strIngredient11, cocktail.strMeasure11),
            Pair(cocktail.strIngredient12, cocktail.strMeasure12),
            Pair(cocktail.strIngredient13, cocktail.strMeasure13),
            Pair(cocktail.strIngredient14, cocktail.strMeasure14),
            Pair(cocktail.strIngredient15, cocktail.strMeasure15)
        )
    }
}