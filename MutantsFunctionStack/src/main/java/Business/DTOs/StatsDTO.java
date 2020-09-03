package Business.DTOs;

public class StatsDTO {
    public int count_mutant_dna;
    public int count_human_dna;
    public float ratio;

    public void setCount_mutant_dna(int count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public void setCount_human_dna(int count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
