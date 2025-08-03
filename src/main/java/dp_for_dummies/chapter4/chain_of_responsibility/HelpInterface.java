package dp_for_dummies.chapter4.chain_of_responsibility;

public interface HelpInterface {
    public void getHelp(HelpEnum helpEnum);
}


class FrontEnd implements HelpInterface {

    // Biến này chính là observer tiếp theo, nếu observer này ko xử lý được
    // thì sẽ pass request xuống observer đó
    private HelpInterface successor;

    public FrontEnd(HelpInterface successor) {
        this.successor = successor;
    }

    @Override
    public void getHelp(HelpEnum helpEnum) {
        if (helpEnum != HelpEnum.FRONT_END_HELP) {
            successor.getHelp(helpEnum);
        } else {
            System.out.println("This is the front end. Don’t you like it?");
        }
    }
}


class IntermediateLayer implements HelpInterface {

    private HelpInterface successor; // observer tiếp theo

    public IntermediateLayer(HelpInterface successor) {
        this.successor = successor;
    }

    @Override
    public void getHelp(HelpEnum helpEnum) {
        if (helpEnum != HelpEnum.INTERMEDIATE_LAYER_HELP) {
            successor.getHelp(helpEnum);
        } else {
            System.out.println("This is the intermediate layer. Nice, eh?");
        }
    }
}


class Application implements HelpInterface {

    public Application() {}

    @Override
    public void getHelp(HelpEnum helpEnum) {
        System.out.println(
                "This is final help layer, if we cannot help you, try to find help from somewhere else!");
    }
}
