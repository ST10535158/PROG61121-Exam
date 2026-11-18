package za.co.mattress.app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/** Appends processed sale details to data.txt. */
public class SaleFileWriter {
    private final Path file;

    public SaleFileWriter() { this(Path.of("data.txt")); }
    public SaleFileWriter(Path file) { this.file = file; }

    public void appendSale(String customer, String product, IProductCalculations calculation) throws IOException {
        String record = "DATA LOG\n"
            + "**************************************************\n"
            + "Customer Name: " + customer + "\n"
            + "Product: " + product + "\n"
            + String.format(java.util.Locale.US, "VAT: R %.2f%n", calculation.GetVat())
            + String.format(java.util.Locale.US, "COMMISSION: R %.2f%n", calculation.GetCommission())
            + String.format(java.util.Locale.US, "TOTAL: R %.2f%n", calculation.GetTotal())
            + "**************************************************\n\n";
        Files.writeString(file, record, StandardCharsets.UTF_8,
            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
