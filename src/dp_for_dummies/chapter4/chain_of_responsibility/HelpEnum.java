package dp_for_dummies.chapter4.chain_of_responsibility;

public enum HelpEnum {
    FRONT_END_HELP(1), INTERMEDIATE_LAYER_HELP(2), APPLICATION_LAYER_HELP(3);

    private final int value;

    HelpEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
