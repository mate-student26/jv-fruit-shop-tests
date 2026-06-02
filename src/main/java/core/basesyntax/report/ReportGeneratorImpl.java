package core.basesyntax.report;

import core.basesyntax.storage.Storage;
import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    private Storage storage;

    public ReportGeneratorImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append("fruit,quantity\n");

        for (Map.Entry<String, Integer> entry : storage.getAll().entrySet()) {
            if (entry.getKey() == null || entry.getKey().equals("")) {
                throw new NullPointerException("Null or empty keys are not allowed");
            }

            report.append(entry.getKey())
                    .append(",")
                    .append(entry.getValue())
                    .append("\n");
        }

        return report.toString();
    }
}
