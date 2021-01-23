package TextEditor;

public class StartCommand extends Command {

    private String preChangeDoc;
    private IDocument postChangeDoc;

    public StartCommand(IDocument _document) {
        this.preChangeDoc = _document.sequence().toString();
        this.postChangeDoc = _document;
    }

    @Override
    public void execute() {
        postChangeDoc.clear();
    }

    @Override
    public void undo() {
        this.postChangeDoc.insert(0, preChangeDoc);
    }
}
