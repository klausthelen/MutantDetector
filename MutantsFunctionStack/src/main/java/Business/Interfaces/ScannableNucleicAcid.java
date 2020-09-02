package Business.Interfaces;

import java.util.ArrayList;

public interface ScannableNucleicAcid {
    boolean isMutantHorizontal(ArrayList<String> nucleicAcid);
    boolean isMutantVertical(ArrayList<String> nucleicAcid);
    boolean isMutantOblique(ArrayList<String> nucleicAcid);
}
