package mendel.frontend;

import mendel.constructutil.Conditional;
import mendel.dbmanager.Storage;
import mendel.discretetask.Deadline;
import mendel.discretetask.Event;
import mendel.discretetask.Todo;
import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;
import mendel.metacognition.LeaveCommand;
import mendel.metacognition.TaskList;

/**
 * The class processes user input and manages tasks accordingly.
 */
public class Parser {
    private final TaskList taskStorage;
    private final Storage dbContoller;

    /**
     * Constructs a Parser object and initializes task storage and database controller.
     * The database is loaded into the task storage upon initialization.
     */
    public Parser() {
        this.taskStorage = new TaskList();
        this.dbContoller = new Storage("data/dbTaskList.txt");
        dbContoller.loadInto(taskStorage);
    }

    /**
     * Processes the user's input command and performs the corresponding action.
     *
     * @param currAction The user input command.
     * @return The response message after executing the command.
     * @throws MendelException If the command is invalid or if there is an error processing it.
     */
    public String manage(String currAction) throws MendelException {
        return Conditional.ifBlock(() -> currAction.equals("bye"), new LeaveCommand()::speak)
                .elifBlock(() -> currAction.equals("list"), this.taskStorage::speak)
                .elseBlock(() -> this.handleMultiWordCommands(currAction));
    }

    private String handleMultiWordCommands(String currAction) {
        String[] segments = currAction.split(" ");
        String message;
        if (segments[0].equals("mark")) {
            handleSequenceNumberSizeError(segments);
            message = taskStorage.marker(Integer.parseInt(segments[1]) - 1);
            this.dbContoller.update(this.taskStorage);
        } else if (segments[0].equals("unmark")) {
            handleSequenceNumberSizeError(segments);
            message = taskStorage.unMarker(Integer.parseInt(segments[1]) - 1);
            this.dbContoller.update(this.taskStorage);
        } else if (segments[0].equals("delete")) {
            handleSequenceNumberSizeError(segments);
            message = taskStorage.delete(Integer.parseInt(segments[1]) - 1);
            this.dbContoller.update(this.taskStorage);
        } else if (segments[0].equals("todo")) {
            Todo task = Todo.of(currAction);
            message = taskStorage.add(task);
            this.dbContoller.create(task, taskStorage.isFirstTask());
        } else if (segments[0].equals("deadline")) {
            Deadline task = Deadline.of(currAction);
            message = taskStorage.add(task);
            this.dbContoller.create(task, taskStorage.isFirstTask());
        } else if (segments[0].equals("event")) {
            Event task = Event.of(currAction);
            message = taskStorage.add(task);
            this.dbContoller.create(task, taskStorage.isFirstTask());
        } else if (segments[0].equals("remind")) {
            message = taskStorage.find(currAction);
        } else if (segments[0].equals("find")) {
            message = taskStorage.findDescription(currAction);
        } else {
            throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
        }
        return message;
    }

    private void handleSequenceNumberSizeError(String[] segments) {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(segments.length < 2, "OOPS! No serial given.\nSpecify serial.")
                .conditionTriggerException(segments.length > 2, "OOPS! Too much stuff.\nSpecify only serial.");
        try {
            Integer.parseInt(segments[1]);
        } catch (NumberFormatException e) {
            throw new MendelException("OOPS! That is invalid serial\nSpecify integer serial");
        }
    }

}
