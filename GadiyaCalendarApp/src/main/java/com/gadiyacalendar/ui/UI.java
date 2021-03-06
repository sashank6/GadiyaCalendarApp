/**
 * 
 */
package com.gadiyacalendar.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.gadiyacalendar.core.Service;
import com.google.api.services.calendar.model.Event;

/**
 * @author SashankAlladi
 *
 */
public class UI extends JFrame implements ActionListener {

	JLabel eventNameLabel;
	JTextField eventNameField;
	JLabel eventLocationLabel;
	JTextField eventLocationField;
	JLabel eventDescriptionLabel;
	JTextField eventDescriptionField, eventGadiyasField;
	JLabel eventStartdateLabel, eventGadiyasLabel, resultLabel;
	JDatePickerImpl datePicker;
	JComboBox minutesBox, meridianBox, hoursBox;
	JButton addButton, clearButton, cancelButton;

	//Builds the UI
	public UI() {
		setLayout(null);
		eventNameLabel = new JLabel();
		eventNameLabel.setText("Event Name:");
		eventNameLabel.setBounds(10, 10, 100, 20);
		add(eventNameLabel);
		eventNameField = new JTextField();
		eventNameField.setBounds(110, 10, 150, 20);
		add(eventNameField);
		eventLocationLabel = new JLabel();
		eventLocationLabel.setText("Location:");
		eventLocationLabel.setBounds(10, 40, 100, 20);
		add(eventLocationLabel);
		eventLocationField = new JTextField();
		eventLocationField.setBounds(110, 40, 150, 20);
		add(eventLocationField);
		eventDescriptionLabel = new JLabel();
		eventDescriptionLabel.setText("Description:");
		eventDescriptionLabel.setBounds(10, 70, 100, 20);
		add(eventDescriptionLabel);
		eventDescriptionField = new JTextField();
		eventDescriptionField.setBounds(110, 70, 150, 20);
		add(eventDescriptionField);
		eventStartdateLabel = new JLabel();
		eventStartdateLabel.setText("Start Date:");
		eventStartdateLabel.setBounds(10, 100, 100, 20);
		add(eventStartdateLabel);
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateFormatter());
		datePicker.setBounds(110, 100, 150, 20);
		add(datePicker);
		Vector<String> hours = this.getStringNumbers(12, 1);
		hoursBox = new JComboBox(hours);
		hoursBox.setBounds(260, 100, 70, 20);
		hoursBox.setSelectedIndex(0);
		add(hoursBox);
		minutesBox = new JComboBox(this.getStringNumbers(60, 0));
		minutesBox.setBounds(330, 100, 70, 20);
		minutesBox.setSelectedIndex(0);
		add(minutesBox);
		String[] meridians = { "--", "AM", "PM" };
		meridianBox = new JComboBox(meridians);
		meridianBox.setBounds(400, 100, 70, 20);
		add(meridianBox);
		eventGadiyasLabel = new JLabel();
		eventGadiyasLabel.setText("Gadiyas:");
		eventGadiyasLabel.setBounds(10, 130, 100, 20);
		add(eventGadiyasLabel);
		eventGadiyasField = new JTextField();
		eventGadiyasField.setBounds(110, 130, 150, 20);
		add(eventGadiyasField);
		addButton = new JButton("Add");
		clearButton = new JButton("Clear");
		cancelButton = new JButton("Cancel");
		addButton.setBounds(40, 180, 50, 20);
		clearButton.setBounds(100, 180, 50, 20);
		cancelButton.setBounds(160, 180, 50, 20);
		add(addButton);
		add(clearButton);
		add(cancelButton);
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
		cancelButton.addActionListener(this);
		resultLabel = new JLabel();
		resultLabel.setBounds(40, 220, 400, 20);
		add(resultLabel);
	}

	public Vector<String> getStringNumbers(int limit, int start) {
		Vector<String> data = new Vector<String>();
		data.add("--");
		for (int i = start; i <= limit; i++) {
			data.add(i < 10 ? "0" + String.valueOf(i) : String.valueOf(i));
		}
		return data;
	}
	
	//Event handler for add button
	@Override
	public void actionPerformed(ActionEvent e) {
		resultLabel.setText("Processing...");
		boolean isValid = true;
		if (e.getSource() == addButton && isValid) {
			String eventName = eventNameField.getText();
			String eventLocation = eventLocationField.getText();
			String eventDescription = eventDescriptionField.getText();
			long gadiyas = Long.parseLong(eventGadiyasField.getText());
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date selectedDate = null;
			try {
				selectedDate = dateformat.parse(datePicker.getJFormattedTextField().getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			System.out.println(selectedDate.getTime());
			long start_time = selectedDate.getTime() + this.getTime();
			Service service = new Service();
			com.google.api.services.calendar.Calendar calservice;
			Event calEvent;
			try {
				calservice = service.getCalendarService();
				calEvent = Service.addEvent(eventName, eventLocation, eventDescription, start_time, gadiyas);
				resultLabel.setText("Event Added Successfully at " + calEvent.getHtmlLink());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public long getTime() {
		long time = (meridianBox.getSelectedIndex() == 2) ? 12 * 60 * 60 * 1000 : 0;
		time += (minutesBox.getSelectedIndex() - 1) * 60 * 1000;
		time += (hoursBox.getSelectedIndex() == 12) ? 0 : hoursBox.getSelectedIndex() * 60 * 60 * 1000;
		return time;
	}

}
