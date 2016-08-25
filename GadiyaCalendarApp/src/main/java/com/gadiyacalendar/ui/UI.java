/**
 * 
 */
package com.gadiyacalendar.ui;

import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author SashankAlladi
 *
 */
public class UI extends JFrame {

	public UI() {
		setLayout(null);
		JLabel eventNameLabel = new JLabel();
		eventNameLabel.setText("Event Name:");
		eventNameLabel.setBounds(10, 10, 100, 20);
		add(eventNameLabel);
		JTextField eventNameField = new JTextField();
		eventNameField.setBounds(110, 10, 150, 20);
		add(eventNameField);
		JLabel eventLocationLabel = new JLabel();
		eventLocationLabel.setText("Location:");
		eventLocationLabel.setBounds(10, 40, 100, 20);
		add(eventLocationLabel);
		JTextField eventLocationField = new JTextField();
		eventLocationField.setBounds(110, 40, 150, 20);
		add(eventLocationField);
		JLabel eventDescriptionLabel = new JLabel();
		eventDescriptionLabel.setText("Description:");
		eventDescriptionLabel.setBounds(10, 70, 100, 20);
		add(eventDescriptionLabel);
		JTextField eventDescriptionField = new JTextField();
		eventDescriptionField.setBounds(110, 70, 150, 20);
		add(eventDescriptionField);
		JLabel eventStartdateLabel = new JLabel();
		eventStartdateLabel.setText("Start Date:");
		eventStartdateLabel.setBounds(10, 100, 100, 20);
		add(eventStartdateLabel);
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateFormatter());
		datePicker.setBounds(110, 100, 150, 20);
		add(datePicker);
		Vector<String> hours = this.getStringNumbers(12);
		JComboBox hoursBox = new JComboBox(hours);
		hoursBox.setBounds(260,100,60,20);
		hoursBox.setSelectedIndex(0);
		add(hoursBox);
		JComboBox minutesBox = new JComboBox(this.getStringNumbers(60));
		minutesBox.setBounds(320,100,60,20);
		minutesBox.setSelectedIndex(0);
		add(minutesBox);
		String[] meridians={"--","AM","PM"};
		JComboBox meridianBox=new JComboBox(meridians);
		meridianBox.setBounds(380,100,60,20);
		add(meridianBox);
		JLabel eventGadiyasLabel = new JLabel();
		eventGadiyasLabel.setText("Gadiyas:");
		eventGadiyasLabel.setBounds(10, 130, 100, 20);
		add(eventGadiyasLabel);
		JTextField eventGadiyasField = new JTextField();
		eventGadiyasField.setBounds(110, 130, 150, 20);
		add(eventGadiyasField);
		JButton addButton = new JButton("Add");
		JButton clearButton = new JButton("Clear");
		JButton cancelButton = new JButton("Cancel");
		addButton.setBounds(40, 180, 50, 20);
		clearButton.setBounds(100, 180, 50, 20);
		cancelButton.setBounds(160, 180, 50, 20);
		add(addButton);
		add(clearButton);
		add(cancelButton);

	}

	public Vector<String> getStringNumbers(int limit) {
		Vector<String> data = new Vector<String>();
		data.add("--");
		for (int i = 1; i <= limit; i++) {
			data.add(i < 10 ? "0" + String.valueOf(i) : String.valueOf(i));
		}
		return data;
	}

}
