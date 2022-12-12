package org.hossam;

import com.github.habernal.confusionmatrix.ConfusionMatrix;
import org.hossam.utils.DistanceType;
import org.hossam.algorithm.KNearestNeighborAlgorithm;
import org.hossam.algorithm.PredictionResult;
import org.hossam.model.FlowerModel;
import org.hossam.model.FlowerType;
import org.hossam.utils.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    private static final int K = 2;

    private static final DistanceType distanceType = DistanceType.MANHATTAN;

    private static int goodPredictions = 0;

    public static void main(String[] args) throws IOException {

        KNearestNeighborAlgorithm algorithm = new KNearestNeighborAlgorithm(K);

        DataManager dataManager = new DataManager();

        Map<FlowerModel, FlowerType> trainingData = dataManager.getTrainingData();
        Map<FlowerModel, FlowerType> testingData = dataManager.getTestingData();

        algorithm.setTrainingData(trainingData);

        List<PredictionResult> predictionsResults = new ArrayList<>();

        for (Map.Entry<FlowerModel, FlowerType> entry : testingData.entrySet()) {

            FlowerType flowerPrediction = algorithm.runAlgorithm(entry.getKey(), distanceType);

            PredictionResult result = new PredictionResult(entry.getValue(), flowerPrediction);

            predictionsResults.add(result);

            if (flowerPrediction == entry.getValue()) {
                goodPredictions++;
            }
        }


        displayAlgorithmResult(testingData.size());
        displayPredictionsResult(predictionsResults);
    }

    private static void displayAlgorithmResult(int dataSize) {
        System.out.println("Start Algorithm with:  " + K + "-Nearest Neighbors algorithm...");
        System.out.println("---------------------------------------------------------------");
        System.out.println("For K=" + K + " and DistanceType " + distanceType + " results are:");
        System.out.println("     - Total predictions = " + dataSize + " | Good = " + goodPredictions + " | Bad = " + (dataSize - goodPredictions));
        double accuracy = goodPredictions * 1.0 / dataSize;
        System.out.println("     - Accuracy = " + accuracy * 100 + "%");
    }

    private static void displayPredictionsResult(List<PredictionResult> results) {
        ConfusionMatrix cm = new ConfusionMatrix();
        for (PredictionResult result : results) {
            cm.increaseValue(result.getExpectedType().name(), result.getPredictedType().name(), 1);
        }
        System.out.println("\n" + cm);
    }

}