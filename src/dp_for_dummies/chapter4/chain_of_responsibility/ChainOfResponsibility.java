package dp_for_dummies.chapter4.chain_of_responsibility;

public class ChainOfResponsibility {

    public static void main(String[] args) {
        Application app = new Application();
        IntermediateLayer intermediateLayer = new IntermediateLayer(app);
        FrontEnd frontEnd = new FrontEnd(intermediateLayer);
        frontEnd.getHelp(HelpEnum.FRONT_END_HELP);
        frontEnd.getHelp(HelpEnum.INTERMEDIATE_LAYER_HELP);
        frontEnd.getHelp(HelpEnum.APPLICATION_LAYER_HELP);
    }
}
