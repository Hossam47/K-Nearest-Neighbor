package org.hossam.algorithm;


import org.hossam.model.FlowerModel;
import org.hossam.model.FlowerType;
import org.hossam.utils.DistanceCalculator;
import org.hossam.utils.DistanceType;

import java.util.*;
import java.util.stream.Collectors;

import static org.hossam.utils.DistanceCalculator.calculateDistance;

public class KNearestNeighborAlgorithm {

    private final int k;
    private Map<FlowerModel, FlowerType> trainingData;

    public KNearestNeighborAlgorithm(int k) {
        this.k = k;
    }

    public void setTrainingData(Map<FlowerModel, FlowerType> trainingData) {
        this.trainingData = trainingData;
    }

    public FlowerType runAlgorithm(FlowerModel flowerModel, DistanceType distanceType) {

        List<Result> result = new ArrayList<>();

        for (Map.Entry<FlowerModel, FlowerType> entry : trainingData.entrySet()) {
            result.add(new Result(calculateDistance(distanceType, entry.getKey(), flowerModel), entry.getValue()));
        }

        Collections.sort(result, new DistanceCalculator());

        List<FlowerType> bestNeighbors = findBestNeighbors(result);

        return flowerClassify(bestNeighbors);
    }

    private List<FlowerType> findBestNeighbors(List<Result> result) {
        List<FlowerType> listNeighbors = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            listNeighbors.add(result.get(i).getFlowerType());
        }
        return listNeighbors;
    }

    private <T> T flowerClassify(List<T> list) {

        Map<T, Integer> numFlowerAppearsList = new HashMap<>();

        for (T t : list) {
            Integer val = numFlowerAppearsList.get(t);
            numFlowerAppearsList.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> mostFlowersAppears = null;

        for (Map.Entry<T, Integer> entry : numFlowerAppearsList.entrySet()) {
            if (mostFlowersAppears == null || entry.getValue() > mostFlowersAppears.getValue())
                mostFlowersAppears = entry;
        }

        return mostFlowersAppears.getKey();
    }

}
