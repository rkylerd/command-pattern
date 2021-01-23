package TextEditor;

import java.util.Scanner;

class TextEditor {

    private IDocument _document;
    private UndoRedoManager manager;

    TextEditor(IDocument document) {
        manager = new UndoRedoManager();
        _document = document;
    }

    void run() {
        while (true) {
            printOptions();

            Scanner scanner = new Scanner(System.in);
            String optionInput = scanner.next();
            int option = validateNumberInput(optionInput);

            if (option != -1) {
                switch (option) {
                    case 1:
                        insert();
                        break;
                    case 2:
                        delete();
                        break;
                    case 3:
                        replace();
                        break;
                    case 4:
                        _document.display();
                        break;
                    case 5:
                        save();
                        break;
                    case 6:
                        open();
                        break;
                    case 7:
                        _document.clear();
                        break;
                    case 8:
                        System.out.println("Undo");
                        manager.undo();
                        break;
                    case 9:
                        System.out.println("Redo");
                        manager.redo();
                        break;
                    case 10:
                        return;
                }
            }

            System.out.println();
        }
    }

    private void printOptions() {
        System.out.println("SELECT AN OPTION (1 - 10):");
        System.out.println("1. Insert a string at a specified index in the document");
        System.out.println("2. Delete a sequence of characters at a specified index");
        System.out.println("3. Replace a sequence of characters at a specified index with a new string");
        System.out.println("4. Display the current contents of the document");
        System.out.println("5. Save the document to a file");
        System.out.println("6. Open a document from a file");
        System.out.println("7. Start a new, empty document");
        System.out.println("8. Undo");
        System.out.println("9. Redo");
        System.out.println("10. Quit");

        System.out.println();
        System.out.print("Your selection: ");
    }

    private void insert() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Start index: ");
        String insertionIndexInput = scanner.next();
        int insertionIndex = validateNumberInput(insertionIndexInput);
        if (insertionIndex != -1) {
            System.out.print("Sequence to insert: ");
            String sequenceInput = scanner.next();
            manager.execute(new InsertCommand(_document, insertionIndex, sequenceInput));
        }
    }

    private void delete() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Start index: ");
        String deletionIndexInput = scanner.next();
        int deletionIndex = validateNumberInput(deletionIndexInput);
        if (deletionIndex != -1) {
            System.out.print("Number of characters to delete: ");
            String deletionDistanceInput = scanner.next();
            int deletionDistance = validateNumberInput(deletionDistanceInput);
            if (deletionDistance != -1) {
                manager.execute(new DeleteCommand(_document, deletionDistance, deletionIndex));
            }
        }
    }

    private void replace() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Start index: ");
        String replaceIndexInput = scanner.next();
        int replaceIndex = validateNumberInput(replaceIndexInput);
        if (replaceIndex != -1) {
            System.out.print("Number of characters to replace: ");
            String replaceDistanceInput = scanner.next();
            int replaceDistance = validateNumberInput(replaceDistanceInput);
            if (replaceDistance != -1) {
                System.out.print("Replacement string: ");
                String replacementString = scanner.next();
                manager.execute(new ReplaceCommand(_document, replaceIndex, replaceDistance, replacementString));

            }
        }
    }

    private void save() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name of file: ");
        String saveFileName = scanner.next();
        _document.save(saveFileName);
    }

    private void open() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name of file to open: ");
        String openFileName = scanner.next();
        manager.execute(new OpenCommand(_document, openFileName));
    }

    private int validateNumberInput(String input) {
        int selection = -1;
        try {
            selection = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }

        return selection;
    }
}
