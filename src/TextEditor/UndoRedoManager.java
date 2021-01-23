package TextEditor;

import java.util.Stack;

public class UndoRedoManager extends Command {

    private Stack<Command> undoStack;
    private Stack<Command> redoStack;

    public UndoRedoManager() {
        undoStack = new Stack<Command>();
        redoStack = new Stack<Command>();
    }

    public void undo() {
        Command command = undoStack.pop();
        redoStack.push(command);
        command.undo();
    }

    public void redo() {
        Command command = redoStack.pop();
        undoStack.push(command);
        command.execute();
    }

    public void execute(Command command) {
        undoStack.push(command);
        command.execute();
    }

}
