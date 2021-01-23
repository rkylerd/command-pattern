package TextEditor;

import java.util.Scanner;

public class ReplaceCommand extends Command {
    private String preChangeDoc;
    private IDocument postChangeDoc;
    private int replaceIndex;
    private String replacementString;
    private int replaceDistance;

    public ReplaceCommand(IDocument _document, int replaceIndex, int replaceDistance, String replacementString) {
        this.preChangeDoc = _document.sequence().toString();
        this.postChangeDoc = _document;
        this.replaceIndex = replaceIndex;
        this.replaceDistance = replaceDistance;
        this.replacementString = replacementString;
    }

    @Override
    public void execute() {
        postChangeDoc.delete(replaceIndex, replaceDistance);
        postChangeDoc.insert(replaceIndex, replacementString);
    }

    @Override
    public void undo() {
        this.postChangeDoc.clear();
        this.postChangeDoc.insert(0, preChangeDoc);
    }
}
