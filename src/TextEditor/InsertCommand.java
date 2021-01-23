package TextEditor;

import java.util.Scanner;

public class InsertCommand extends Command {
    private String preChangeDoc;
    private IDocument postChangeDoc;
    private int insertionIndex;
    private String sequenceInput;

    public InsertCommand(IDocument _document, int insertionIndex, String sequenceInput) {
        this.preChangeDoc = _document.sequence().toString();
        this.postChangeDoc = _document;
        this.insertionIndex = insertionIndex;
        this.sequenceInput = sequenceInput;
    }

    @Override
    public void execute() {
        postChangeDoc.insert(insertionIndex, sequenceInput);
    }

    @Override
    public void undo() {
        this.postChangeDoc.clear();
        this.postChangeDoc.insert(0, preChangeDoc);
    }
}
