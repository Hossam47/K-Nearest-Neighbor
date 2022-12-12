package org.hossam.utils;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hossam.model.FlowerModel;
import org.hossam.model.FlowerType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private Map<FlowerModel, FlowerType> getDataFromFile(String fileName) throws IOException {

        File csvData = new File(fileName);

        CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);

        Map<FlowerModel, FlowerType> data = new HashMap<>();

        for (CSVRecord csvRecord : parser) {

            FlowerModel flowerModel = new FlowerModel();

            flowerModel.setSepalLength(Double.parseDouble(csvRecord.get(0)));
            flowerModel.setSepalWidth(Double.parseDouble(csvRecord.get(1)));
            flowerModel.setPetalLength(Double.parseDouble(csvRecord.get(2)));
            flowerModel.setPetalWidth(Double.parseDouble(csvRecord.get(3)));

            data.put(flowerModel, FlowerType.flowerChecker(csvRecord.get(4)));
        }
        return data;
    }

    public Map<FlowerModel, FlowerType> getTrainingData() throws IOException {
        return getDataFromFile("training.data");
    }

    public Map<FlowerModel, FlowerType> getTestingData() throws IOException {
        return getDataFromFile("test.data");
    }
}
