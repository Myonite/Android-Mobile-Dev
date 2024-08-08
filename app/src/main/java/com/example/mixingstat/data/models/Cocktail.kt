package com.example.mixingstat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Represents a Cocktail entity in the database.
 *
 * @property idDrink The unique ID of the cocktail.
 * @property strDrink The name of the cocktail.
 * @property strDrinkAlternate The alternate name of the cocktail.
 * @property strTags The tags associated with the cocktail.
 * @property strVideo The URL of the video showing how to make the cocktail.
 * @property strCategory The category of the cocktail.
 * @property strIBA The International Bartenders Association (IBA) rating of the cocktail.
 * @property strAlcoholic Whether the cocktail is alcoholic or not.
 * @property strGlass The type of glass used for the cocktail.
 * @property strInstructions The instructions to make the cocktail.
 * @property strInstructionsES The instructions to make the cocktail in Spanish.
 * @property strInstructionsDE The instructions to make the cocktail in German.
 * @property strInstructionsFR The instructions to make the cocktail in French.
 * @property strInstructionsIT The instructions to make the cocktail in Italian.
 * @property strInstructionsZH_HANS The instructions to make the cocktail in Simplified Chinese.
 * @property strInstructionsZH_HANT The instructions to make the cocktail in Traditional Chinese.
 * @property strDrinkThumb The URL of the thumbnail image of the cocktail.
 * @property strIngredient1 The first ingredient of the cocktail.
 * @property strIngredient2 The second ingredient of the cocktail.
 * @property strIngredient3 The third ingredient of the cocktail.
 * @property strIngredient4 The fourth ingredient of the cocktail.
 * @property strIngredient5 The fifth ingredient of the cocktail.
 * @property strIngredient6 The sixth ingredient of the cocktail.
 * @property strIngredient7 The seventh ingredient of the cocktail.
 * @property strIngredient8 The eighth ingredient of the cocktail.
 * @property strIngredient9 The ninth ingredient of the cocktail.
 * @property strIngredient10 The tenth ingredient of the cocktail.
 * @property strIngredient11 The eleventh ingredient of the cocktail.
 * @property strIngredient12 The twelfth ingredient of the cocktail.
 * @property strIngredient13 The thirteenth ingredient of the cocktail.
 * @property strIngredient14 The fourteenth ingredient of the cocktail.
 * @property strIngredient15 The fifteenth ingredient of the cocktail.
 * @property strMeasure1 The measure for the first ingredient of the cocktail.
 * @property strMeasure2 The measure for the second ingredient of the cocktail.
 * @property strMeasure3 The measure for the third ingredient of the cocktail.
 * @property strMeasure4 The measure for the fourth ingredient of the cocktail.
 * @property strMeasure5 The measure for the fifth ingredient of the cocktail.
 * @property strMeasure6 The measure for the sixth ingredient of the cocktail.
 * @property strMeasure7 The measure for the seventh ingredient of the cocktail.
 * @property strMeasure8 The measure for the eighth ingredient of the cocktail.
 * @property strMeasure9 The measure for the ninth ingredient of the cocktail.
 * @property strMeasure10 The measure for the tenth ingredient of the cocktail.
 * @property strMeasure11 The measure for the eleventh ingredient of the cocktail.
 * @property strMeasure12 The measure for the twelfth ingredient of the cocktail.
 * @property strMeasure13 The measure for the thirteenth ingredient of the cocktail.
 * @property strMeasure14 The measure for the fourteenth ingredient of the cocktail.
 * @property strMeasure15 The measure for the fifteenth ingredient of the cocktail.
 * @property strImageSource The source of the image of the cocktail.
 * @property strImageAttribution The attribution for the image of the cocktail.
 * @property strCreativeCommonsConfirmed Whether the image of the cocktail is licensed under Creative Commons.
 * @property dateModified The date when the cocktail was last modified.
 * @property isPopular Whether the cocktail is popular or not.
 * @property isLatest Whether the cocktail is the latest or not.
 */
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
    val dateModified: String? = null,
    @ColumnInfo(defaultValue = "0") var isPopular: Boolean = false,
    @ColumnInfo(defaultValue = "0") var isLatest: Boolean = false

){

    /**
     * Returns a list of pairs of ingredients and their measures for the cocktail.
     *
     * @param cocktail The cocktail for which to get the ingredients and measures.
     * @return A list of pairs of ingredients and their measures.
     */
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