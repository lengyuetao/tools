package com.tao.tools.algorithm;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BitsetUtils {
    /**
     * 获取bitset长度
     * @param bitset
     * @return
     */
    private  int getBitSetSize(BitSet bitset){
        List<Integer> indexs = new ArrayList<Integer>();
        if (bitset != null) {
            int i = bitset.nextSetBit(0);
            if (i != -1) {
                indexs.add(i);
                for (i = bitset.nextSetBit(i + 1); i >= 0; i = bitset.nextSetBit(i + 1)) {
                    int endOfRun = bitset.nextClearBit(i);
                    do {
                        indexs.add(i);
                    } while (++i < endOfRun);
                }
            }
        }
        return indexs.size();
    }
}
