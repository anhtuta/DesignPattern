package dp_refactoring_guru.factory_method.ui_elements;

// 3. Base Creator
public abstract class Dialog {

    public void renderWindow() {
        // ... other code ...

        Button okButton = createButton();
        okButton.render();
    }

    /**
     * Factory method: Subclasses will override this method in order to
     * create specific button objects.
     */
    public abstract Button createButton();
}

// 4. Concrete Creator
class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}

// 4. Concrete Creator
class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
