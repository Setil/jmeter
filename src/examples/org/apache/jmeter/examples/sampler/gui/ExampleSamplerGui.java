// $Header$
/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

/*
 * Example Sampler GUI (non-beans version)
 */

package org.apache.jmeter.examples.sampler.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.jmeter.examples.sampler.ExampleSampler;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;

/**
 * Example Sampler (non-Bean version)
 * 
 * This class is responsible for ensuring that the Sampler data is kept in step
 * with the GUI.
 * 
 * The GUI class is not invoked in non-GUI mode, so it should not perform any
 * additional setup that a test would need at run-time
 * 
 * @version $Revision$ $Date$
 */
public class ExampleSamplerGui extends AbstractSamplerGui {

	private JTextField data;

	public ExampleSamplerGui() {
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.jmeter.gui.JMeterGUIComponent#getStaticLabel()
	 */
	public String getLabelResource() {
		return "example_title";
	}

	/*
	 * (non-Javadoc) Copy the data from the test element to the GUI
	 * 
	 * @see org.apache.jmeter.gui.JMeterGUIComponent#configure(org.apache.jmeter.testelement.TestElement)
	 */
	public void configure(TestElement element) {
		data.setText(element.getPropertyAsString(ExampleSampler.DATA));
		super.configure(element);
	}

	/*
	 * (non-Javadoc) Create the corresponding Test Element and set up its data
	 * 
	 * @see org.apache.jmeter.gui.JMeterGUIComponent#createTestElement()
	 */
	public TestElement createTestElement() {
		ExampleSampler sampler = new ExampleSampler();
		modifyTestElement(sampler);
		return sampler;
	}

	/*
	 * (non-Javadoc) Modifies a given TestElement to mirror the data in the gui
	 * components.
	 * 
	 * @see org.apache.jmeter.gui.JMeterGUIComponent#modifyTestElement(TestElement)
	 */
	public void modifyTestElement(TestElement te) {
		te.clear();
		configureTestElement(te);
		te.setProperty(ExampleSampler.DATA, data.getText());
	}

	/*
	 * Helper method to set up the GUI screen
	 */
	private void init() {
		// Standard setup
		setLayout(new BorderLayout(0, 5));
		setBorder(makeBorder());
		add(makeTitlePanel(), BorderLayout.NORTH); // Add the standard title

		// Specific setup
		add(createDataPanel(), BorderLayout.CENTER);
	}

	/*
	 * Create a data input text field
	 * 
	 * @return the panel for entering the data
	 */
	private Component createDataPanel() {
		JLabel label = new JLabel(JMeterUtils.getResString("example_data"));

		data = new JTextField(10);
		data.setName(ExampleSampler.DATA);
		label.setLabelFor(data);

		JPanel dataPanel = new JPanel(new BorderLayout(5, 0));
		dataPanel.add(label, BorderLayout.WEST);
		dataPanel.add(data, BorderLayout.CENTER);

		return dataPanel;
	}
}