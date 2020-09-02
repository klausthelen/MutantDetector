package Business.Enums;

public enum NitrogenousBaseNamesEnum {
    A('A',"adenine"),
    C('C',"cytosine"),
    G('G',"guanine"),
    T('T',"thymine");

    private final char letter;
    private final String completeName;

    NitrogenousBaseNamesEnum(char letter, String completeName) {
        this.letter = letter;
        this.completeName = completeName;
    }

    public String getCompleteName() {
        return this.completeName;
    }
}