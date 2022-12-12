package org.hossam.utils;

import org.hossam.algorithm.Result;
import org.hossam.model.FlowerModel;

import java.util.Comparator;

 public class DistanceCalculator implements Comparator<Result> {

    @Override
    public int compare(Result a, Result b) {
        return Double.compare(a.getDistance(), b.getDistance());
    }

     public static  double calculateDistance(DistanceType type, FlowerModel from, FlowerModel to) {
         switch (type) {
             case EUCLIDEAN:
                 return computeEuclideanDistance(from, to);
             case MANHATTAN:
                 return computeManhattanDistance(from, to);
         }
         return 0.0;
     }

     public static double computeEuclideanDistance(FlowerModel from, FlowerModel to) {
         double distance = 0.0;
         distance += Math.pow(from.getSepalLength() - to.getSepalLength(), 2);
         distance += Math.pow(from.getSepalWidth() - to.getSepalWidth(), 2);
         distance += Math.pow(from.getPetalLength() - to.getPetalLength(), 2);
         distance += Math.pow(from.getPetalWidth() - to.getPetalWidth(), 2);
         return Math.sqrt(distance);
     }

     public static  double computeManhattanDistance(FlowerModel from, FlowerModel to) {
         double distance = 0.0;
         distance += Math.abs(from.getSepalLength() - to.getSepalLength());
         distance += Math.abs(from.getSepalWidth() - to.getSepalWidth());
         distance += Math.abs(from.getPetalLength() - to.getPetalLength());
         distance += Math.abs(from.getPetalWidth() - to.getPetalWidth());
         return distance;
     }

 }
