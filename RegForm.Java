import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame implements ActionListener {

    private JTextField nameField, mobileField, addressField;
    private JRadioButton maleButton, femaleButton;
    private JComboBox<String> dayBox, monthBox, yearBox;
    private JButton submitButton, resetButton;
    private ButtonGroup genderGroup;

    public RegistrationForm() {
        setTitle("Registration Form");

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        // Mobile
        JLabel mobileLabel = new JLabel("Mobile:");
        mobileField = new JTextField(20);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        // DOB
        JLabel dobLabel = new JLabel("DOB:");
        String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] years = {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"};
        dayBox = new JComboBox<>(days);
        monthBox = new JComboBox<>(months);
        yearBox = new JComboBox<>(years);

        // Address
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);

        // Terms and Conditions
        JCheckBox termsCheckBox = new JCheckBox("Accept Terms and Conditions");

        // Buttons
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        // Adding Action Listeners
        submitButton.addActionListener(this);
        resetButton.addActionListener(this);

        // Layout
        setLayout(new GridLayout(8, 2));

        add(nameLabel);
        add(nameField);
        add(mobileLabel);
        add(mobileField);
        add(genderLabel);
        add(maleButton);
        add(femaleButton);
        add(dobLabel);
        add(dayBox);
        add(monthBox);
        add(yearBox);
        add(addressLabel);
        add(addressField);
        add(termsCheckBox);
        add(submitButton);
        add(resetButton);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Handle submit button click
            String name = nameField.getText();
            String mobile = mobileField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = dayBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-" + yearBox.getSelectedItem();
            String address = addressField.getText();

            DatabaseManager.saveFormData(name, mobile, gender, dob, address);

            JOptionPane.showMessageDialog(this, "Data Saved");
        } else if (e.getSource() == resetButton) {
            // Handle reset button click
            nameField.setText("");
            mobileField.setText("");
            genderGroup.clearSelection();
            dayBox.setSelectedIndex(0);
            monthBox.setSelectedIndex(0);
            yearBox.setSelectedIndex(0);
            addressField.setText("");
        }
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
