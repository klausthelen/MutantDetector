package Business.Models;

import Business.Enums.NitrogenousBaseNamesEnum;

public class NitrogenousBase {
    private String name;
    private char abbreviation;
    private NitrogenousBaseSpacialLocation spacialLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(char abbreviation) {
        this.abbreviation = abbreviation;
    }

    public NitrogenousBaseSpacialLocation getSpacialLocation() {
        return spacialLocation;
    }

    public void setSpacialLocation(NitrogenousBaseSpacialLocation spacialLocation) {
        this.spacialLocation = spacialLocation;
    }

    public NitrogenousBase(char abbreviation, NitrogenousBaseSpacialLocation spacialLocation) {
        this.abbreviation = abbreviation;
        this.spacialLocation = spacialLocation;
        switch (abbreviation){
            case 'A':
                this.name = NitrogenousBaseNamesEnum.A.getCompleteName();
                break;
            case 'C':
                this.name = NitrogenousBaseNamesEnum.C.getCompleteName();
                break;
            case 'G':
                this.name = NitrogenousBaseNamesEnum.G.getCompleteName();
                break;
            case 'T':
                this.name = NitrogenousBaseNamesEnum.T.getCompleteName();
                break;
        }
    }
}
