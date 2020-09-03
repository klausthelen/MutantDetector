package Services;

import Business.DTOs.StatsDTO;
import Repositories.CacheRepository;

public class DnaMutantStats {

    private CacheRepository cacheRepository;

    public DnaMutantStats() {
        this.cacheRepository = new CacheRepository();
    }

    public StatsDTO getMutantStats(){
        StatsDTO mutantStats = new StatsDTO();
        mutantStats.setCount_mutant_dna(Integer.parseInt(cacheRepository.get("count_mutant_dna")));
        mutantStats.setCount_human_dna(Integer.parseInt(cacheRepository.get("count_human_dna")));
        mutantStats.setRatio(Float.parseFloat(cacheRepository.get("ratio")));
        return  mutantStats;
    }
}
