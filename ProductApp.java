package za.co.mattress.app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;

/** Swing GUI for processing mattress sales. */
public class ProductApp extends JFrame {
    private final JTextField customerField = new JTextField(22);
    private final JComboBox<String> productCombo = new JComboBox<>(new String[]{
        "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    });
    private final JTextField priceField = new JTextField(22);
    private final SaleFileWriter fileWriter = new SaleFileWriter();

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 330);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 8, 10, 8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        addRow(form, c, 0, "Customer Name:", customerField);
        addRow(form, c, 1, "Select Product:", productCombo);
        addRow(form, c, 2, "Enter Price:", priceField);

        JButton processButton = new JButton("PROCESS");
        processButton.addActionListener(e -> processSale());
        c.gridx = 1; c.gridy = 3; c.weightx = 1;
        form.add(processButton, c);
        add(form, BorderLayout.CENTER);
        setJMenuBar(createMenuBar());
    }

    private void addRow(JPanel panel, GridBagConstraints c, int row, String label, Component field) {
        c.gridy = row; c.gridx = 0; c.weightx = 0;
        panel.add(new JLabel(label), c);
        c.gridx = 1; c.weightx = 1;
        panel.add(field, c);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        file.add(exit);

        JMenu tools = new JMenu("Tools");
        JMenuItem process = new JMenuItem("Process");
        process.addActionListener(e -> processSale());
        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(e -> clearForm());
        tools.add(process);
        tools.add(clear);
        bar.add(file);
        bar.add(tools);
        return bar;
    }

    private void processSale() {
        String customer = customerField.getText().trim();
        String priceText = priceField.getText().trim();
        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name.", "Input required",
                JOptionPane.WARNING_MESSAGE);
            customerField.requestFocus();
            return;
        }
        if (priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the product price.", "Input required",
                JOptionPane.WARNING_MESSAGE);
            priceField.requestFocus();
            return;
        }

        final double price;
        try {
            price = Double.parseDouble(priceText);
            if (!Double.isFinite(price) || price < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid price of zero or greater.", "Invalid price",
                JOptionPane.ERROR_MESSAGE);
            priceField.requestFocus();
            return;
        }

        String product = (String) productCombo.getSelectedItem();
        ProductCalculations calculations = new ProductCalculations(price);
        try {
            fileWriter.appendSale(customer, product, calculations);
            DecimalFormat money = new DecimalFormat("0.00");
            String message = "Customer Name: " + customer
                + "\nProduct: " + product
                + "\nVAT: R " + money.format(calculations.GetVat())
                + "\nCOMMISSION: R " + money.format(calculations.GetCommission())
                + "\nTOTAL: R " + money.format(calculations.GetTotal());
            JOptionPane.showMessageDialog(this, message, "Sale processed", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save data.txt: " + ex.getMessage(),
                "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        customerField.setText("");
        priceField.setText("");
        productCombo.setSelectedIndex(0);
        customerField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
