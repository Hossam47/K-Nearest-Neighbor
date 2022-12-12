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

public class DataImporter {

    private Map<FlowerModel, FlowerType> importData(String fileName) throws IOException {
        File csvData = new File(fileName);
        CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);

        Map<FlowerModel, FlowerType> data = new HashMap<>();
        for (CSVRecord csvRecord : parser) {
            FlowerModel flowerModel = new FlowerModel();
            // Warning: the order matter ! See the CSV file description
            flowerModel.setSepalLength(Double.parseDouble(csvRecord.get(0)));
            flowerModel.setSepalWidth(Double.parseDouble(csvRecord.get(1)));
            flowerModel.setPetalLength(Double.parseDouble(csvRecord.get(2)));
            flowerModel.setPetalWidth(Double.parseDouble(csvRecord.get(3)));

            data.put(flowerModel, FlowerType.flowerChecker(csvRecord.get(4)));
        }
        return data;
    }

    public Map<FlowerModel, FlowerType> getTrainingSet() throws IOException {
        return importData("training.data");
    }

    public Map<FlowerModel, FlowerType> getTestSet() throws IOException {
        return importData("test.data");
    }
}
