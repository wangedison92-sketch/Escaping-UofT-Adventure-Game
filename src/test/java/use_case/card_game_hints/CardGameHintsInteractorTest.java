package use_case.card_game_hints;

import entity.Card;
import entity.CardPuzzle;
import interface_adapter.ViewManagerModel;
import interface_adapter.card_game_hints.CardGameHintsPresenter;
import interface_adapter.play_card_game.CardGamePresenter;
import interface_adapter.play_card_game.CardGameViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

public class CardGameHintsInteractorTest {
    @Test
    void successTest() {

        CardGameViewModel cardGameViewModel = new CardGameViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Create test presenter that captures calls
        TestPresenter presenter = new TestPresenter();

        // Create the interactor we're actually testing
        CardGameHintsInteractor interactor = new CardGameHintsInteractor((CardGameHintsOutputBoundary) presenter);

        // Create input data (you'll need to create a CardPuzzle with test cards)
        List<Card> cards = Arrays.asList(new Card(2), new Card(4), new Card(6), new Card(3));
        CardPuzzle puzzle = new CardPuzzle(cards);
        CardGameHintsInputDataObject input = new CardGameHintsInputDataObject(puzzle);

        interactor.execute(input);
        assertTrue(presenter.successCalled);
        assertFalse(presenter.failCalled);
        assertNotNull(presenter.lastOutputData);
    }

    @Test
    void testGenerateHintWithKnownCards() {
        TestPresenter presenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(presenter);
        List<Card> cards = Arrays.asList(new Card(2), new Card(3), new Card(4), new Card(4));

        String hint = interactor.generateHint(cards);
        assertTrue(hint.startsWith("Maybe try"));
        assertTrue(hint.endsWith("first."));
    }


    @Test
    void getHintsTest() {
        CardGameHintsOutputDataObject output = new CardGameHintsOutputDataObject("Test Hint");
        assertEquals("Test Hint", output.getHint());
    }

    @Test
    void testExtractInnerEmpty() {

        TestPresenter mockPresenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(mockPresenter);
        String result = interactor.extractInner("");
        assertEquals("a simple calculation", result);
    }

    @Test
    void testExtractInnerNull() {
        TestPresenter mockPresenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(mockPresenter);
        String result = interactor.extractInner(null);
        assertEquals("a simple calculation", result);
    }

    @Test
    void testExtractInnerStrange() {
        TestPresenter mockPresenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(mockPresenter);
        String result = interactor.extractInner(")(");
        assertEquals(")(", result);
    }


    @Test
    void testExtractInnerLarge() {
        TestPresenter mockPresenter = new TestPresenter();
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(mockPresenter);
        String result = interactor.extractInner("6+7+8+9+10");
        assertEquals("6+7+8+", result);
    }

    @Test
    void testExceptionWhenGenerateHintFails() {
        CardGameHintsOutputBoundary failurePresenter = new CardGameHintsOutputBoundary() {
            @Override
            public void prepareSuccessView(CardGameHintsOutputDataObject outputData) {
                fail("Should not succeed when generateHint fails");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("Failed to get hint"));
                assertTrue(error.contains("No solutions found"));
            }
        };

        List<Card> cards = Arrays.asList(new Card(1), new Card(1), new Card(1), new Card(1));
        CardPuzzle puzzle = new CardPuzzle(cards);

        CardGameHintsInputDataObject input = new CardGameHintsInputDataObject(puzzle);

        CardGameHintsInteractor interactor = new CardGameHintsInteractor(failurePresenter);
        CardGameHintsInteractor spyInteractor = Mockito.spy(interactor);

        doThrow(new RuntimeException("No solutions found")).when(spyInteractor).generateHint(cards);

        spyInteractor.execute(input);
    }

    @Test
    public void execute() {
        CardGameViewModel cardGameViewModel =  new CardGameViewModel();
        ViewManagerModel viewManagerMode = new ViewManagerModel();
        CardGameHintsPresenter presenter = new CardGameHintsPresenter(cardGameViewModel,viewManagerMode);
        CardGameHintsInteractor interactor = new CardGameHintsInteractor(presenter);
        CardGameHintsInputDataObject inputData = new CardGameHintsInputDataObject(null);
        try {
            Object hint = interactor.generateHint(inputData.getCardPuzzle().getCards());
            if (hint == null || (hint instanceof Collection && ((Collection<?>) hint).isEmpty())) {
                // No hints available - this is a valid success case
                CardGameHintsOutputDataObject outputData = new CardGameHintsOutputDataObject(
                        null// might have a flag indicating no hints
                );
                presenter.prepareSuccessView(outputData);
            } else {
                // Hints found
                CardGameHintsOutputDataObject outputData = new CardGameHintsOutputDataObject((String) hint);
                presenter.prepareSuccessView(outputData);
            }
        } catch (Exception e) {
            presenter.prepareFailView("Failed to get hint: " + e.getMessage());
        }
    }
}


class TestPresenter implements CardGameHintsOutputBoundary {
    public boolean successCalled = false;
    public boolean failCalled = false;
    public CardGameHintsOutputDataObject lastOutputData = null;

    @Override
    public void prepareSuccessView(CardGameHintsOutputDataObject outputData) {
        this.successCalled = true;
        this.lastOutputData = outputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.failCalled = true;
    }
}

