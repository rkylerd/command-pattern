package TextEditor;

public class DeleteCommand extends Command {

    private String preChangeDoc;
    private IDocument postChangeDoc;
    private int deletionDistance;
    private int deletionIndex;

    public DeleteCommand(IDocument _document, int deletionDistance, int deletionIndex) {
        this.preChangeDoc = _document.sequence().toString();
        this.postChangeDoc = _document;
        this.deletionDistance = deletionDistance;
        this.deletionIndex = deletionIndex;
    }

    @Override
    public void execute() {
        if (postChangeDoc.delete(deletionIndex, deletionDistance) == null) {
            System.out.println("Deletion unsuccessful");
        }
    }

    @Override
    public void undo() {
        this.postChangeDoc.clear();
        this.postChangeDoc.insert(0, preChangeDoc);
    }
}
