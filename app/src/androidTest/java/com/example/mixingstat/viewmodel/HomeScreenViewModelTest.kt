import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.data.repository.CocktailRepository
import com.example.mixingstat.presentation.viewmodel.home.HomeScreenViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeScreenViewModelTest {

    private lateinit var viewModel: HomeScreenViewModel
    private val repository: CocktailRepository = mockk()

    @Before
    fun setup() {
        coEvery { repository.getAllPopularCocktails() } returns emptyList()
        coEvery { repository.getAllLatestCocktails() } returns emptyList()
        coEvery { repository.getRandomCocktail() } returns null
        viewModel = HomeScreenViewModel(repository)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun when_loadCocktails_is_called_and_repository_returns_empty_state_is_updated_with_empty_lists() =
        runTest {
            coEvery { repository.getAllPopularCocktails() } returns emptyList()
            coEvery { repository.getAllLatestCocktails() } returns emptyList()
            coEvery { repository.getRandomCocktail() } returns null

            viewModel.loadCocktails()

            val state = viewModel.state.firstOrNull()
            assertNotNull(state)
            assertEquals(emptyList<Cocktail>(), state?.popularCocktails)
            assertEquals(emptyList<Cocktail>(), state?.latestCocktails)
            assertEquals(null, state?.suggestionCocktail)
        }

    @Test
    fun when_loadCocktails_is_called_and_repository_returns_cocktails_state_is_updated_with_cocktails() = runTest {
        // Create some mock cocktails
        val popularCocktails = listOf(Cocktail(
            idDrink = "1",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ), Cocktail(
            idDrink = "2",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ))
        val latestCocktails = listOf(Cocktail(
            idDrink = "3",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ), Cocktail(
            idDrink = "4",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ))
        val suggestionCocktail = Cocktail(
            idDrink = "6",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        )

        // Define the behavior of the repository
        coEvery { repository.getAllPopularCocktails() } returns popularCocktails
        coEvery { repository.getAllLatestCocktails() } returns latestCocktails
        coEvery { repository.getRandomCocktail() } returns suggestionCocktail

        // Call the method under test
        viewModel.loadCocktails()

        // Check the state
        val state = viewModel.state.firstOrNull()
        assertNotNull(state)
        assertEquals(popularCocktails, state?.popularCocktails)
        assertEquals(latestCocktails, state?.latestCocktails)
        assertEquals(suggestionCocktail, state?.suggestionCocktail)
    }
}