package interface_adapter.validate_card_answer;

/**
 * The State information representing the card validation.
 */
public class ValidateCardState {
    private String message;
    private boolean solved;

    public ValidateCardState() {
        this.message = "";
        this.solved = false;
    }


    public ValidateCardState(ValidateCardState validateCardState) {
        this.message = validateCardState.getMessage();
        this.solved = validateCardState.isSolved();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved() {
        this.solved = true;
    }

}
