package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.deck.*;
import seedu.duke.commands.system.ExitProgrammeCommand;
import seedu.duke.commands.system.HelpCommand;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.flashcard.Deck;
import seedu.duke.ui.CardLiUi;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InnerParser {

    private static final Logger logger = Logger.getLogger(InnerParser.class.getName());

    private Deck currDeck;

    public InnerParser(Deck currDeck) {
        this.currDeck = currDeck;
    }

    public InnerParser() {
        this.currDeck = null;
    }

    public Command parseCommand(String input) {
        logger.setLevel(Level.WARNING);
        String commandType = getCommandType(input);
        logger.log(Level.INFO, "new user input detected");

        Command command;
        String arguments;

        switch (commandType) { // TODO: add testing-related commands
        case "add":
            arguments = getCommandArguments(commandType, input);
            command = new AddCardCommand(arguments, this.currDeck);
            logger.log(Level.INFO, "add (card) command parsed and executed");
            break;
        case "edit": //edit /card <card index> /side <side> /input <input>
            arguments = getCommandArguments(commandType, input);
            command = new EditCardCommand(arguments, this.currDeck);
            logger.log(Level.INFO, "edit (card) command parsed and executed");
            break;
        case "delete":
            arguments = getCommandArguments(commandType, input);
            command = new DeleteCardCommand(arguments, this.currDeck);
            logger.log(Level.INFO, "delete (card) command parsed and executed");
            break;
        case "view":
            command = new ViewCardsCommand(this.currDeck);
            logger.log(Level.INFO, "view command parsed and executed");
            break;
        case "help":
            command = new HelpCommand();
            logger.log(Level.INFO, "help command parsed and executed");
            break;
        case "exit":
            command = new ExitDeckCommand();
            logger.log(Level.INFO, "exit command parsed and executed");
            break;
        default:
            command = new InvalidCommand();
            logger.log(Level.INFO, "command was unrecognised and could not be parsed");
        }
        return command;
    }

    public void setCurrDeck(Deck currDeck) {
        this.currDeck = currDeck;
    }

    // TODO: clean up code repeated in OuterParser, possibly using inheritance
    /**
     * Returns the command type of the user's input.
     * @param input
     */
    private String getCommandType(String input) {
        return input.trim().split(" ")[0].toLowerCase();
    }

    /**
     * Returns the String containing the arguments to the command.
     */
    private String getCommandArguments(String commandType, String input) { // TODO: throws FieldEmptyException
        assert input.length() > 0 : "input string should not be empty, at least have command word";
        return input.substring(commandType.length()).trim();
    }

    /**
     * Checks if the given input is an integer or not.
     *
     * @param input input given by user
     * @return true if input is an integer, false otherwise
     */
    public boolean isInteger(String input) {
        for (int i = 0; i < input.length(); i += 1) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
