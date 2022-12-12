package org.hossam.model;

public enum FlowerType {
    SETOSA("Iris-setosa"), VERSICOLOUR("Iris-versicolor"), VIRGINICA("Iris-virginica");
    private String value;

    FlowerType(String value) {
        this.value = value;
    }

    public static FlowerType flowerChecker(String value) {
        for (FlowerType type : FlowerType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }
}