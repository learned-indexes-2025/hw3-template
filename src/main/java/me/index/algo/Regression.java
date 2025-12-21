package me.index.algo;

import java.util.List;

public class Regression implements Splittable {
    public Regression() {
    }

    @Override
    public void split(List<Long> keys, int maxErr, TConsumer<Integer, Integer, LRM> lambda) {
        int start = 0;
        while (start < keys.size()) {
            int i = 0;
            double[] cf = new double[]{0.0, 0.0};
            while (start + i < keys.size() && can_expand(keys.get(start + i), i, maxErr, cf)) {
                i++;
            }
            int err = 0;
            for (int j = start; j < start + i; j++)
                err = Math.max(err, Math.abs(predict(cf[0], cf[1], keys.get(j)) - (j - start)));
            LRM lrm = new LRM(cf[0], cf[1], err);
            if (lrm.maxErr() > 1000) {
                System.out.println("[WARNING] large error due to the limited precision of doubles in LRM, "
                        + "found: " + lrm.maxErr() + ", expected: " + maxErr);
            }
            lambda.accept(start, start + i, lrm);
            start += i;
        }
    }

    public boolean can_expand(long key, int pos, int maxErr, double[] cf) {
        return true;
    }

    public static int predict(double k, double b, long x) {
        int pos = (int) (k * x + b);
        return Math.max(pos, 0);
    }
}
