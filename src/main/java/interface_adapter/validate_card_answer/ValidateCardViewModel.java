package interface_adapter.validate_card_answer;

import interface_adapter.ViewModel;
import interface_adapter.play_card_game.CardGameViewModel;
import interface_adapter.validate_card_answer.ValidateCardState;

/**
 * The View Model for the Validate Answer Use Case.
 */
public class ValidateCardViewModel extends ViewModel<ValidateCardState> {

    public ValidateCardViewModel(CardGameViewModel cardGameViewModel) {
        super("Validate Card Answer");
        setState(new ValidateCardState());
    }

}
