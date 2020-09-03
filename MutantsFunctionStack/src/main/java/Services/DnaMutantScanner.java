package Services;

import Business.DTOs.IsMutantDTOResponse;
import Business.Interfaces.ScannableNucleicAcid;
import Business.Models.NitrogenousBase;
import Business.Models.NitrogenousBaseSpacialLocation;
import Repositories.CacheRepository;

import java.util.ArrayList;

public class DnaMutantScanner implements ScannableNucleicAcid {

    private CacheRepository cacheRepository;

    public DnaMutantScanner() {
        this.cacheRepository = new CacheRepository();
    }

    @Override
    public boolean isMutantHorizontal(ArrayList<String> dna) {
        int repetitions;
        for (int row = 0; row < dna.size(); row++ ){
            repetitions = 0;
            for (int column = 0; column< dna.get(row).length() - 1; column ++){
                NitrogenousBaseSpacialLocation spacialLocation = new NitrogenousBaseSpacialLocation(row, column);
                NitrogenousBase currentNitrogenousBase = new NitrogenousBase(dna.get(row).charAt(column),spacialLocation);
                NitrogenousBase nextNitrogenousBase = new NitrogenousBase(dna.get(row).charAt(column+1),
                        spacialLocation.MoveHorizontal());
                if (currentNitrogenousBase.getAbbreviation() == nextNitrogenousBase.getAbbreviation()){
                    repetitions++;
                    if(repetitions == 3){
                        return true;
                    }
                }
                else {
                    repetitions = 0;
                }

            }
        }
        return false;
    }

    @Override
    public boolean isMutantVertical(ArrayList<String> dna) {
        int repetitions;
        int row = 0;
        int column = 0;
        repetitions = 0;
        while (true){
            NitrogenousBaseSpacialLocation spacialLocation = new NitrogenousBaseSpacialLocation(row, column);
            NitrogenousBase currentNitrogenousBase = new NitrogenousBase(dna.get(row).charAt(column),spacialLocation);
            NitrogenousBase nextNitrogenousBase = new NitrogenousBase(dna.get(row+1).charAt(column),
                    spacialLocation.MoveVertical());
            if (currentNitrogenousBase.getAbbreviation() == nextNitrogenousBase.getAbbreviation()){
                repetitions++;
                if(repetitions == 3){
                    return true;
                }
            }
            else {
                repetitions = 0;
            }
            row = nextNitrogenousBase.getSpacialLocation().getRow();
            column = nextNitrogenousBase.getSpacialLocation().getColumn();
            if(row >= dna.size()-1){
                repetitions = 0;
                row = 0;
                column++;
            }
            if(column == dna.get(row).length()){
                break;
            }

        }
        return false;
    }

    @Override
    public boolean isMutantOblique(ArrayList<String> dna) {
        int repetitions = 0;
        int row = dna.size()-3;
        int column = 0;
        int columnCounter = 0;
        int rowCounter = row;
        boolean flag = true;
        while (true){
            NitrogenousBaseSpacialLocation spacialLocation = new NitrogenousBaseSpacialLocation(row, column);
            NitrogenousBase currentNitrogenousBase = new NitrogenousBase(dna.get(row).charAt(column),spacialLocation);
            NitrogenousBase nextNitrogenousBase = new NitrogenousBase(dna.get(row+1).charAt(column+1),
                    spacialLocation.MoveOblique(flag));
            if (currentNitrogenousBase.getAbbreviation() == nextNitrogenousBase.getAbbreviation()){
                repetitions++;
                if(repetitions == 3){
                    return true;
                }
            }
            else {
                repetitions = 0;
            }
            if(currentNitrogenousBase.getSpacialLocation().getRow() == 0 &&
                    currentNitrogenousBase.getSpacialLocation().getColumn() == 0){
                flag=false;
            }
            row = nextNitrogenousBase.getSpacialLocation().getRow();
            column = nextNitrogenousBase.getSpacialLocation().getColumn();
            if(flag && row >= dna.size()-1){
                repetitions = 0;
                rowCounter = rowCounter - 1;
                column = 0;
                row = rowCounter;
            }
            if(!flag && column >= dna.get(row).length()-1){
                repetitions = 0;
                row = 0;
                column = columnCounter;
                columnCounter++;
            }
            if(columnCounter == dna.get(row).length() - 3){
                break;
            }

        }
        return false;
    }

    public boolean completeScanner(ArrayList<String> dna, String dnaHashCode){
        if (cacheRepository.get("mutant_" + dnaHashCode) != "0"){
            return true;
        }
        if (cacheRepository.get("human_" + dnaHashCode) != "0"){
            return false;
        }
        return  this.isMutantOblique(dna) ||
                this.isMutantVertical(dna) ||
                this.isMutantHorizontal(dna);
    }

    public boolean checkDna(ArrayList<String> dna){
        int dnaRealSize = dna.size();
        for (int row = 0; row < dna.size(); row++ ){
            if ((dna.get(row).length() != dnaRealSize) || !dna.get(row).matches("^[ACGT_]+$")){
                return false;
            }
        }
        return true;
    }

    public IsMutantDTOResponse scanDna(ArrayList<String> dna){
        cacheRepository.deleteAllKeys();
        IsMutantDTOResponse response = new IsMutantDTOResponse();
        if(!checkDna(dna)){
            response.setState(false);
            response.setStatusCode(400);
            return  response;
        }
        String dnaHashCode = String.valueOf(dna.hashCode());
        boolean checkDna = this.completeScanner(dna, dnaHashCode);
        if (checkDna){
            response.setState(true);
            response.setStatusCode(200);
            setMutantStats(true,dnaHashCode);
        }
        else {
            response.setState(false);
            response.setStatusCode(403);
            setMutantStats(false,dnaHashCode);
        }
        return  response;
    }

    public void setMutantStats(boolean mutant, String hashCode){
        if(mutant && cacheRepository.get("mutant_" + hashCode) == "0"){
            cacheRepository.set("mutant_" + hashCode, hashCode);
            cacheRepository.incr("count_mutant_dna");
        }
        if(!mutant && cacheRepository.get("human_" + hashCode) == "0") {
            cacheRepository.set("human_" + hashCode, hashCode);
            cacheRepository.incr("count_human_dna");
        }
    }

}