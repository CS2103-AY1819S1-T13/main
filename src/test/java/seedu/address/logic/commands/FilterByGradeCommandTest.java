package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;


import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.GradeFilterPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;


public class FilterByGradeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        String first = "20 90";
        String second = " ";


        FilterByGradeCommand filterByGradeFirstCommand = new FilterByGradeCommand(first);
        FilterByGradeCommand filterByGradeSecondCommand = new FilterByGradeCommand(second);

        // same object -> returns true
        assertTrue(filterByGradeFirstCommand.equals(filterByGradeFirstCommand));

        // same values -> returns true
        FilterByGradeCommand filterByEducationFirstCommandCopy = new FilterByGradeCommand(first);
        assertTrue(filterByGradeFirstCommand.equals(filterByEducationFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterByGradeFirstCommand.equals(1));


        // different person -> returns false
        assertFalse(filterByGradeFirstCommand.equals(filterByGradeSecondCommand));
    }

    @Test
    public void executeZeroKeywordsNoPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        GradeFilterPredicate predicate = new GradeFilterPredicate(20, 90);
        FilterByGradeCommand command = new FilterByGradeCommand("20 90");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    public NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
