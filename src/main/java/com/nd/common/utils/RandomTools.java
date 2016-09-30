package com.nd.common.utils;

import com.nd.common.enums.ResourceEnum;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
public class RandomTools {
    public static Set<Integer> randomPush(Integer max, Integer min) {
        Random random = new Random();
        Set<Integer> resultSet = new HashSet<>();
        while (resultSet.size() < ResourceEnum.Three.getResourceNum()) {
            Integer result = random.nextInt(max) % (max - min + 1) + min;
            resultSet.add(result);
        }
        return resultSet;
    }
}
