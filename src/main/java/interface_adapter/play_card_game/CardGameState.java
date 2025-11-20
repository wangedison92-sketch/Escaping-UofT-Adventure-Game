package interface_adapter.play_card_game;
import entity.CardPuzzle;

/**
 * The State information representing the Card Game.
 */
public class CardGameState {
    private CardPuzzle cardPuzzle;
    private String hint;
    private String answerFeedback;
    private boolean solved;
    private String errorMessage = "";

    public CardGameState () {
        this.cardPuzzle = null;
        this.hint = "";
        this.answerFeedback = "";
        this.solved = false;
        this.errorMessage = "";
    }

    public CardGameState (CardGameState other) {
        this.cardPuzzle = other.getcardPuzzle();
        this.hint = other.getHint();
        this.answerFeedback = other.getAnswerFeedback();
        this.solved = other.solved;
        this.errorMessage = other.errorMessage;
    }

    public CardPuzzle getcardPuzzle() {
        return this.cardPuzzle;
    }

    public void setCardPuzzle(CardPuzzle cardPuzzle) {
        this.cardPuzzle =  cardPuzzle;
    }

    public String getHint() {
        return this.hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getAnswerFeedback() {
        return this.answerFeedback;
    }
    public void setAnswerFeedback(String answerFeedback) {
        this.answerFeedback = answerFeedback;
    }

    public boolean isSolved() {
        return this.solved;
    }

    public void setSolved() {
        this.solved = true;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}