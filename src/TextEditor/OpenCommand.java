package TextEditor;

import java.util.Scanner;

public class OpenCommand extends Command {

    private String preChangeDoc;
    private IDocument postChangeDoc;
    private String openFileName;

    public OpenCommand(IDocument _document, String openFileName) {
        this.preChangeDoc = _document.sequence().toString();
        this.postChangeDoc = _document;
        this.openFileName = openFileName;
    }

    @Override
    public void execute() {
        postChangeDoc.clear();
        postChangeDoc.open(openFileName);
    }

    @Override
    public void undo() {
        this.postChangeDoc.clear();
        this.postChangeDoc.insert(0, preChangeDoc);
    }
}
