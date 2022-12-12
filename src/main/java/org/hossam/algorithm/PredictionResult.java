package org.hossam.algorithm;

import org.hossam.model.FlowerType;

public class PredictionResult {

    private FlowerType expectedType;
    private FlowerType predictedType;

    public PredictionResult(FlowerType expectedType, FlowerType predictedType) {
        this.expectedType = expectedType;
        this.predictedType = predictedType;
    }

    public FlowerType getExpectedType() {
        return expectedType;
    }
    public FlowerType getPredictedType() {
        return predictedType;
    }
}
