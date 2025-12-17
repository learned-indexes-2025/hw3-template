package me.index.config;

import java.util.Properties;

public record Config(Keyset keyset, Workload workload, WorkloadFactor workloadFactor, DataSize dataSize, int maxErr) {
    public static Config read(Properties p) {
        try {
            Keyset k = Keyset.valueOf(p.getProperty("keyset"));
            Workload w = Workload.valueOf(p.getProperty("workload.distribution"));
            WorkloadFactor wf = WorkloadFactor.valueOf(p.getProperty("workload.factor"));
            DataSize ds = DataSize.valueOf(p.getProperty("data.size"));
            int err = Integer.parseInt(p.getProperty("max.err"));
            return new Config(k, w, wf, ds, err);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
