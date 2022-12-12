package org.hossam.algorithm;


import org.hossam.model.FlowerModel;
import org.hossam.model.FlowerType;
import org.hossam.utils.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hossam.algorithm.DistanceCalculator.calculDistance;

public class KNNAlgorithm {

    private final int k;
    private Map<FlowerModel, FlowerType> trainingSet;

    public KNNAlgorithm(int k) {
        this.k = k;
    }

    public void setTrainingSet(Map<FlowerModel, FlowerType> trainingSet) {
        this.trainingSet = trainingSet;
    }

    public FlowerType predict(FlowerModel testInstance, DistanceType distanceType) {

        List<Result> result = new ArrayList<>();

        for (Map.Entry<FlowerModel, FlowerType> entry : trainingSet.entrySet()) {
            result.add(new Result(calculDistance(distanceType, entry.getKey(), testInstance), entry.getValue()));
        }

        Collections.sort(result, new DistanceCalculator());

        List<FlowerType> bestNeighbors = new ArrayList<>();

        for (int i = 0; i <= k; i++) {
            bestNeighbors.add(result.get(i).getFlowerType());
        }

        return ListUtils.flowerClassify(bestNeighbors);
    }
}
