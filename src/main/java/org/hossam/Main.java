package org.hossam;

import com.github.habernal.confusionmatrix.ConfusionMatrix;
import org.hossam.algorithm.DistanceType;
import org.hossam.algorithm.KNNAlgorithm;
import org.hossam.algorithm.PredictionResult;
import org.hossam.model.FlowerModel;
import org.hossam.model.FlowerType;
import org.hossam.utils.DataImporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    private static final int K = 2;

    private static final DistanceType distanceType = DistanceType.MANHATTAN;

    public static void main(String[] args) throws IOException {

        KNNAlgorithm algorithm = new KNNAlgorithm(K);

        DataImporter importer = new DataImporter();

        Map<FlowerModel, FlowerType> trainingSet = importer.getTrainingSet();
        Map<FlowerModel, FlowerType> testSet = importer.getTestSet();

        algorithm.setTrainingSet(trainingSet);

        System.out.println("Executing the " + K + "-Nearest Neighbors algorithm...");
        int goodPredictions = 0;

        List<PredictionResult> predictionsResults = new ArrayList<>();

        for (Map.Entry<FlowerModel, FlowerType> entry : testSet.entrySet()) {

            FlowerType prediction = algorithm.predict(entry.getKey(), distanceType);

            PredictionResult result = new PredictionResult(entry.getValue(), prediction);

            predictionsResults.add(result);

            if (prediction == entry.getValue()) {
                goodPredictions++;
            }
        }

        System.out.println("-----");
        System.out.println("For K=" + K + " and DistanceType " + distanceType + " results are:");
        System.out.println("     - Total predictions = " + testSet.size() + " | good = " + goodPredictions + " | bad = " + (testSet.size() - goodPredictions));
        double accuracy = goodPredictions * 1.0 / testSet.size();
        System.out.println("     - Accuracy = " + accuracy * 100 + "%");

        createAndDisplayConfusionMatrix(predictionsResults);
    }

    private static void createAndDisplayConfusionMatrix(List<PredictionResult> results){
        ConfusionMatrix cm = new ConfusionMatrix();
        for (PredictionResult result : results) {
            cm.increaseValue(result.getExpectedType().name(), result.getPredictedType().name(), 1);
        }
        System.out.println("\n" + cm);
    }

}