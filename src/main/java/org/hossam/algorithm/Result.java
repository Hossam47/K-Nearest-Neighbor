package org.hossam.algorithm;

import org.hossam.model.FlowerType;

public class Result {

    private double distance;
    private FlowerType flowerType;

     Result(double distance, FlowerType flowerType) {
        this.distance = distance;
        this.flowerType = flowerType;
    }

     public double getDistance() {
        return distance;
    }
     FlowerType getFlowerType() {
        return flowerType;
    }
}
