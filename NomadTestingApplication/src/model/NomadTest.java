package model;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.junit.Test;
import org.testng.junit.JUnit3TestClass;
import org.testng.junit.JUnit4TestRunner;

import testscript.TestingScript;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;


import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.border.CompoundBorder;

public class NomadTest extends JFrame {
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	String strMessage = "";
	DefaultListModel dlm = new DefaultListModel();
	private JTextField txtAccountEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NomadTest frame = new NomadTest();
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public NomadTest() {
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Nomad Test Suite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 562);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		//ceniuk@aim.com is the testing e-mail
		txtUsername.setText("ceniuk@aim.com");
		txtUsername.setBounds(475, 12, 217, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(475, 43, 217, 20);
		//NomadUAT!1 is the testing password
		contentPane.add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(417, 14, 68, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(417, 46, 68, 14);
		contentPane.add(lblPassword);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 102));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(457, 84, 217, 216);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnHome = new JButton("Run Home Page Test");
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(0, 153, 255));
		btnHome.setBounds(10, 11, 188, 23);
		panel.add(btnHome);
		

		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResults.setForeground(Color.WHITE);
		lblResults.setBounds(10, 71, 75, 14);
		contentPane.add(lblResults);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 84, 437, 216);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 417, 196);
		panel_1.add(scrollPane_1);
		
		JList lstResults = new JList();
		scrollPane_1.setViewportView(lstResults);
		lstResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lstResults.setForeground(Color.WHITE);
		lstResults.setBackground(new Color(0, 153, 204));
		lstResults.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		/********************************************************************************
		 * TEST HOME BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				System.out.println("HOME");
				//Creating TestingScript Object
				TestingScript homeTest = new TestingScript();
				try {
					strMessage = "Running Home Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					//Setting Username and Password to test object
					homeTest.setStrLoginName(txtUsername.getText().toString());
					homeTest.setStrPassword(txtPassword.getText().toString());
					//Runs Login script
					homeTest.setUp();
					//Run Home page test
					homeTest.homePageTest();
					strMessage = "Home Page Test Success!";
					dlm.addElement(strMessage);
					//When successful closes window
					homeTest.tearDown();
				} catch (Exception e1) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/HomePageTest.csv");
						fileWriter.append(homeTest.getStrReport() + " ,FAILURE," + " \n " + "Home Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.out.println("Home Test Failed");
					strMessage = "Home Page Test Failure.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
			}
		});
		/*Run all the main tests
		NOTICE ALL TESTS ARE IN INDIVIDUAL TRY CATCHES
		THAT IS SO THEY CAN STILL ALL RUN IF ONLY ONE 
		BREAKS!*/
		/********************************************************************************
		 * RUN ALL TESTS BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnRunAllTests = new JButton("Run All Tests");
		btnRunAllTests.setForeground(Color.WHITE);
		btnRunAllTests.setBackground(new Color(0, 153, 255));
		btnRunAllTests.setBounds(10, 145, 188, 23);
		btnRunAllTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestingScript RunAllTest = new TestingScript();
				//Home Page Test
				try {
					strMessage = "Running Home Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					RunAllTest.setStrLoginName(txtUsername.getText().toString());
					RunAllTest.setStrPassword(txtPassword.getText().toString());
					RunAllTest.setUp();
					RunAllTest.homePageTest();
					strMessage = "Home Page Test Success!";
					dlm.addElement(strMessage);
					RunAllTest.tearDown();
				
				} catch (Exception e1) {
					System.out.println("TEST FAIL: HOME PAGE FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					strMessage = "TEST FAILURE, HOME PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
			
				    
				}
				//Employee Page Test
				try {
					strMessage = "Running Create Employees Test.....";
					dlm.addElement(strMessage);
					
					lstResults.setModel(dlm);
					RunAllTest.setStrLoginName(txtUsername.getText().toString());
					RunAllTest.setStrPassword(txtPassword.getText().toString());
					RunAllTest.setUp();
					//Creates a new Employee
					RunAllTest.testEmployees();
				} catch (Exception e1) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/CreateEmployeesTest.csv");
						fileWriter.append(RunAllTest.getStrReport() + "FAILURE," + " \n " + "Create Employee Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.out.println("TEST FAIL: Create Employees FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					
					strMessage = "TEST FAILURE, EMPLOYEES PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
				try {
					strMessage = "Running Edit Beneficiaries Test.....";
					dlm.addElement(strMessage);
					
					lstResults.setModel(dlm);
					RunAllTest.setStrLoginName(txtUsername.getText().toString());
					RunAllTest.setStrPassword(txtPassword.getText().toString());
					RunAllTest.setUp();
					//Edits Beneficiaries
					RunAllTest.testEmployees2();
				} catch(Exception e2) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/EditBeneficiariesTest.csv");
						fileWriter.append(RunAllTest.getStrReport() + " FAILURE," + " \n " + "Edit Beneficiaries Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					System.out.println("TEST FAIL: Edit Beneficiaries FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					
					strMessage = "TEST FAILURE, EMPLOYEES PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
				try {
					strMessage = "Running Edit Dependents Test.....";
					dlm.addElement(strMessage);
					
					lstResults.setModel(dlm);
					RunAllTest.setStrLoginName(txtUsername.getText().toString());
					RunAllTest.setStrPassword(txtPassword.getText().toString());
					RunAllTest.setUp();
					//Edits Dependents 
					RunAllTest.testEmployees3();
				} catch(Exception e2) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/EditDependentsTest.csv");
						fileWriter.append(RunAllTest.getStrReport() + " FAILURE," + " \n " + "Edit Dependents Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					System.out.println("TEST FAIL: Edit Dependents PAGE FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					
					strMessage = "TEST FAILURE, EMPLOYEES PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
				try {
					strMessage = "Running Edit/Terminate Employee Test.....";
					dlm.addElement(strMessage);
					
					lstResults.setModel(dlm);
					RunAllTest.setStrLoginName(txtUsername.getText().toString());
					RunAllTest.setStrPassword(txtPassword.getText().toString());
					RunAllTest.setUp();
					//Edit/Terminate Employee
					RunAllTest.testEmployees4();
					
					strMessage = "Employees Page Test Success!";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				
					dlm.addElement("All Employees Tests Finished");
					lstResults.setModel(dlm);
					RunAllTest.tearDown();
				} catch(Exception e2) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/EditTerminateEmployeeTest.csv");
						fileWriter.append(RunAllTest.getStrReport() + " FAILURE," + " \n " + "Edit/Terminate Employee Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					System.out.println("TEST FAIL: Edit/Terminate Employee PAGE FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					
					strMessage = "TEST FAILURE, EMPLOYEES PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
				//Benefits Page Tests
				try {
					/*********************************************************************************************
					 * For some reason can crash randomly on the benefits page. Fix is to maximize the screen when
					 * browser is launched.
					 *********************************************************************************************/
					strMessage = "Running Benefits Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					RunAllTest.setStrLoginName(txtUsername.getText().toString());
					RunAllTest.setStrPassword(txtPassword.getText().toString());
					RunAllTest.setUp();
					//Views Benefits Details
					RunAllTest.testBenefitsDetails();
					//Views Benefits Learn More
					RunAllTest.testBenefitsLearnMore();
					strMessage = "Benefits Page Test Success!";
					dlm.addElement(strMessage);
					RunAllTest.tearDown();
				} catch (Exception e1) {
					System.out.println("TEST FAIL: BENEFITS PAGE FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					
					strMessage = "TEST FAILURE, BENEFITS PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					strMessage = "TEST FAILURE, TRY RESIZING THE FORM TO BE HALF";
					dlm.addElement(strMessage);
					strMessage = "TEST FAILURE, THE PAGE. KNOWN ISSUE!";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					
					
				}
				//Run Resources Test
				try {
					strMessage = "Running Resource Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					RunAllTest.setStrLoginName(txtUsername.getText().toString());
					RunAllTest.setStrPassword(txtPassword.getText().toString());
					RunAllTest.setUp();
					//Testing Resources Page
					RunAllTest.testResources();
					strMessage = "Resources Page Test Success!";
					dlm.addElement(strMessage);
					RunAllTest.tearDown();
				} catch (Exception e1) {
					try {
						FileWriter fileWriter = new FileWriter("Reports/ResourcesTest.csv");
						fileWriter.append(RunAllTest.getStrReport() + "FAILURE," + " \n " + "Resources Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					System.out.println("Resources Test Failed");
					strMessage = "Resources Page Test Failure.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);	
				}
			}
		});
		panel.add(btnRunAllTests);
		/********************************************************************************
		 * BENEFITS PAGE BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnRunBenefitsPage = new JButton("Run Benefits Page Test");
		btnRunBenefitsPage.setForeground(Color.WHITE);
		btnRunBenefitsPage.setBackground(new Color(0, 153, 255));
		btnRunBenefitsPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestingScript benefitsTest = new TestingScript();
				try {
					/*********************************************************************************************
					 * For some reason can crash randomly on the benefits page. Fix is to maximize the screen when
					 * browser is launched.
					 *********************************************************************************************/
					strMessage = "Running Benefits Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					benefitsTest.setStrLoginName(txtUsername.getText().toString());
					benefitsTest.setStrPassword(txtPassword.getText().toString());
					benefitsTest.setUp();
					//Views Benefits Details
					benefitsTest.testBenefitsDetails();
					strMessage = "Benefits Details Test Success!";
					dlm.addElement(strMessage);
					benefitsTest.tearDown();
				} catch (Exception e1) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/BenefitDetailsTest.csv");
						fileWriter.append(benefitsTest.getStrReport() + " FAILURE," + " \n " + "Benefit Details Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					System.out.println("Benefits Test Failed");
					strMessage = "TEST FAILURE, BENEFITS PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					strMessage = "TEST FAILURE, TRY RESIZING THE FORM TO BE HALF";
					dlm.addElement(strMessage);
					strMessage = "TEST FAILURE, THE PAGE. KNOWN ISSUE!";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);	
				}
				try {
					/*********************************************************************************************
					 * For some reason can crash randomly on the benefits page. Fix is to maximize the screen when
					 * browser is launched.
					 *********************************************************************************************/
					strMessage = "Running Benefits Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					benefitsTest.setStrLoginName(txtUsername.getText().toString());
					benefitsTest.setStrPassword(txtPassword.getText().toString());
					benefitsTest.setUp();
					//Views Learn More
					benefitsTest.testBenefitsLearnMore();
					strMessage = "Benefits Learn More Test Success!";
					dlm.addElement(strMessage);
					benefitsTest.tearDown();
				} catch (Exception e2){
					try {
						FileWriter fileWriter = new FileWriter("Reports/BenefitsLearnMoreTest.csv");
						fileWriter.append(benefitsTest.getStrReport() + " FAILURE," + " \n " + "Benefit Learn More Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
				}
			}
		});
		btnRunBenefitsPage.setBounds(10, 79, 188, 23);
		panel.add(btnRunBenefitsPage);
		
		/********************************************************************************
		 * EMPLOYEE PAGE BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnRunEmployeeTest = new JButton("Run Employee Page Test");
		btnRunEmployeeTest.setForeground(Color.WHITE);
		btnRunEmployeeTest.setBackground(new Color(0, 153, 255));
		btnRunEmployeeTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestingScript EmployeeTest = new TestingScript();
				//Employee Page Test
				try {
					strMessage = "Running Create Employees Test.....";
					dlm.addElement(strMessage);
					
					lstResults.setModel(dlm);
					EmployeeTest.setStrLoginName(txtUsername.getText().toString());
					EmployeeTest.setStrPassword(txtPassword.getText().toString());
					EmployeeTest.setUp();
					//Creates a new Employee
					EmployeeTest.testEmployees();
				} catch (Exception e1) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/CreateEmployeesTest.csv");
						fileWriter.append(EmployeeTest.getStrReport() + "FAILURE," + " \n " + "Create Employee Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.out.println("TEST FAIL: Create Employees FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					
					strMessage = "TEST FAILURE, EMPLOYEES PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
				try {
					strMessage = "Running Edit Beneficiaries Test.....";
					dlm.addElement(strMessage);
					
					lstResults.setModel(dlm);
					EmployeeTest.setStrLoginName(txtUsername.getText().toString());
					EmployeeTest.setStrPassword(txtPassword.getText().toString());
					EmployeeTest.setUp();
					//Edits Beneficiaries
					EmployeeTest.testEmployees2();
				} catch(Exception e2) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/EditBeneficiariesTest.csv");
						fileWriter.append(EmployeeTest.getStrReport() + " FAILURE," + " \n " + "Edit Beneficiaries Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					System.out.println("TEST FAIL: Edit Beneficiaries FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					
					strMessage = "TEST FAILURE, EMPLOYEES PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
				try {
					strMessage = "Running Edit Dependents Test.....";
					dlm.addElement(strMessage);
					
					lstResults.setModel(dlm);
					EmployeeTest.setStrLoginName(txtUsername.getText().toString());
					EmployeeTest.setStrPassword(txtPassword.getText().toString());
					EmployeeTest.setUp();
					//Edits Dependents 
					EmployeeTest.testEmployees3();
				} catch(Exception e2) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/EditDependentsTest.csv");
						fileWriter.append(EmployeeTest.getStrReport() + " FAILURE," + " \n " + "Edit Dependents Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					System.out.println("TEST FAIL: Edit Dependents PAGE FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					
					strMessage = "TEST FAILURE, EMPLOYEES PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
				try {
					strMessage = "Running Edit/Terminate Employee Test.....";
					dlm.addElement(strMessage);
					
					lstResults.setModel(dlm);
					EmployeeTest.setStrLoginName(txtUsername.getText().toString());
					EmployeeTest.setStrPassword(txtPassword.getText().toString());
					EmployeeTest.setUp();
					//Edit/Terminate Employee
					EmployeeTest.testEmployees4();
					
					strMessage = "Employees Page Test Success!";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				
					dlm.addElement("All Employees Tests Finished");
					lstResults.setModel(dlm);
					EmployeeTest.tearDown();
				} catch(Exception e2) {
					// When test fails displays these results
					try {
						FileWriter fileWriter = new FileWriter("Reports/EditTerminateEmployeeTest.csv");
						fileWriter.append(EmployeeTest.getStrReport() + " FAILURE," + " \n " + "Edit/Terminate Employee Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					System.out.println("TEST FAIL: Edit/Terminate Employee PAGE FAILURE");
					System.out.println("OR CONTACT DEVELOPER MITCHELL UNGAR");
					
					strMessage = "TEST FAILURE, EMPLOYEES PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
			}
		});
		btnRunEmployeeTest.setBounds(10, 45, 188, 23);
		panel.add(btnRunEmployeeTest);
		
		/********************************************************************************
		 * RESOURCES PAGE BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnRunResources = new JButton("Run Resources Page Test");
		btnRunResources.setForeground(Color.WHITE);
		btnRunResources.setBackground(new Color(0, 153, 255));
		btnRunResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestingScript ResourcesTest = new TestingScript();
				try {
					strMessage = "Running Resource Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					ResourcesTest.setStrLoginName(txtUsername.getText().toString());
					ResourcesTest.setStrPassword(txtPassword.getText().toString());
					ResourcesTest.setUp();
					//Views all the resources. 
					ResourcesTest.testResources();
					strMessage = "Resources Page Test Success!";
					dlm.addElement(strMessage);
					ResourcesTest.tearDown();
				} catch (Exception e1) {
					try {
						FileWriter fileWriter = new FileWriter("Reports/ResourcesTest.csv");
						fileWriter.append(ResourcesTest.getStrReport() + "FAILURE," + " \n " + "Resources Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Resources Test Failed");
					strMessage = "Resources Page Test Failure.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
			}
		});
		btnRunResources.setBounds(10, 113, 188, 23);
		panel.add(btnRunResources);
		
		JLabel lblNomadTestSuite = new JLabel("Nomad Test Suite");
		lblNomadTestSuite.setForeground(Color.WHITE);
		lblNomadTestSuite.setBackground(Color.WHITE);
		lblNomadTestSuite.setFont(new Font("Segoe UI", Font.PLAIN, 41));
		lblNomadTestSuite.setBounds(10, 11, 327, 52);
		contentPane.add(lblNomadTestSuite);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBorder(new CompoundBorder());
		tabbedPane.setBounds(10, 329, 437, 182);
		contentPane.add(tabbedPane);
		
		JPanel pnlBenefits = new JPanel();
		pnlBenefits.setBackground(new Color(0, 153, 102));
		tabbedPane.addTab("Benefits Tests", null, pnlBenefits, null);
		pnlBenefits.setLayout(null);
		/********************************************************************************
		 * BENEFIT DETAILS BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnBDetails = new JButton("Benefit Details Test");
		btnBDetails.setForeground(Color.WHITE);
		btnBDetails.setBackground(new Color(0, 153, 255));
		btnBDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestingScript benefitsTest = new TestingScript();
				try {
					strMessage = "Running Benefits Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					benefitsTest.setStrLoginName(txtUsername.getText().toString());
					benefitsTest.setStrPassword(txtPassword.getText().toString());
					benefitsTest.setUp();
					//Benefit Details Test
					benefitsTest.testBenefitsDetails();
					strMessage = "Benefits Page Test Success!";
					dlm.addElement(strMessage);
					benefitsTest.tearDown();
				} catch (Exception e1) {
					try {
						FileWriter fileWriter = new FileWriter("Reports/BenefitDetailsTest.csv");
						fileWriter.append(benefitsTest.getStrReport() + " FAILURE," + " \n " + "Benefit Details Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					
					System.out.println("Benefits Test Failed");
					strMessage = "TEST FAILURE, BENEFITS PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					strMessage = "TEST FAILURE, TRY RESIZING THE FORM TO BE HALF";
					dlm.addElement(strMessage);
					strMessage = "TEST FAILURE, THE PAGE. KNOWN ISSUE!";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);	
				}
			}
		});
		btnBDetails.setBounds(60, 11, 188, 23);
		pnlBenefits.add(btnBDetails);
		/********************************************************************************
		 * BENEFIT LEARN MORE BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnBLearnMore = new JButton("Benefit Learn More Test");
		btnBLearnMore.setForeground(Color.WHITE);
		btnBLearnMore.setBackground(new Color(0, 153, 255));
		btnBLearnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestingScript benefitsTest = new TestingScript();
				try {
					strMessage = "Running Benefits Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					benefitsTest.setStrLoginName(txtUsername.getText().toString());
					benefitsTest.setStrPassword(txtPassword.getText().toString());
					benefitsTest.setUp();
					//Benefit Learn More Test
					benefitsTest.testBenefitsLearnMore();
					strMessage = "Benefits Page Test Success!";
					dlm.addElement(strMessage);
					benefitsTest.tearDown();
				} catch (Exception e1) {
					try {
						FileWriter fileWriter = new FileWriter("Reports/BenefitsLearnMoreTest.csv");
						fileWriter.append(benefitsTest.getStrReport() + "FAILURE," + " \n " + "Learn More Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.out.println("Benefits Test Failed");
					strMessage = "TEST FAILURE, BENEFITS PAGE SOURCE OF FAILURE";
					dlm.addElement(strMessage);
					strMessage = "TEST FAILURE, TRY RESIZING THE FORM TO BE HALF";
					dlm.addElement(strMessage);
					strMessage = "TEST FAILURE, THE PAGE. KNOWN ISSUE!";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					e1.fillInStackTrace();
					System.out.println(e1.getMessage());
					
				}
			}
		});
		btnBLearnMore.setBounds(60, 45, 188, 23);
		pnlBenefits.add(btnBLearnMore);
		JPanel pnlEmployee = new JPanel();
		pnlEmployee.setBackground(new Color(0, 153, 102));
		tabbedPane.addTab("Employees Tests", null, pnlEmployee, null);
		pnlEmployee.setLayout(null);
		
		/********************************************************************************
		 * EMPLOYEE EDIT DEPENDENT BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnEEditDep = new JButton("Edit Dependents Test");
		btnEEditDep.setForeground(Color.WHITE);
		btnEEditDep.setBackground(new Color(0, 153, 255));
		btnEEditDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestingScript EmployeeTest = new TestingScript();
				try {
					strMessage = "Running Edit Employee Dependent Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					EmployeeTest.setStrLoginName(txtUsername.getText().toString());
					EmployeeTest.setStrPassword(txtPassword.getText().toString());
					EmployeeTest.setUp();
					//Employee Edit Dependent Test
					EmployeeTest.testEmployees3();
					strMessage = "Edit Employee Dependent Test Success!";
					dlm.addElement(strMessage);
					EmployeeTest.tearDown();
				} catch (Exception e1) {
					try {
						FileWriter fileWriter = new FileWriter("Reports/EditDependentsTest.csv");
						fileWriter.append(EmployeeTest.getStrReport() + "FAILURE," + " \n " + "Edit Dependents Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.out.println("Edit Employee Dependent Test Failed");
					strMessage = "Edit Employee Dependent Test Failure.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);	
				}
			}
		});
		btnEEditDep.setBounds(62, 82, 188, 23);
		pnlEmployee.add(btnEEditDep);
		/********************************************************************************
		 * EMPLOYEE EDIT BENEFICIARY BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnEEditBene = new JButton("Edit Beneficiaries Test");
		btnEEditBene.setForeground(Color.WHITE);
		btnEEditBene.setBackground(new Color(0, 153, 255));
		btnEEditBene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestingScript EmployeeTest = new TestingScript();
				try {
					strMessage = "Running Edit Employee Beneficiaries Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					EmployeeTest.setStrLoginName(txtUsername.getText().toString());
					EmployeeTest.setStrPassword(txtPassword.getText().toString());
					EmployeeTest.setUp();
					//Employee edit beneficiary test
					EmployeeTest.testEmployees2();
					strMessage = "Edit Employee Beneficiaries Test Success!";
					dlm.addElement(strMessage);
					EmployeeTest.tearDown();
				} catch (Exception e1) {
					try {
						FileWriter fileWriter = new FileWriter("Reports/EditBeneficiariesTest.csv");
						fileWriter.append(EmployeeTest.getStrReport() + "FAILURE," + " \n " + "Edit Beneficiary Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.out.println("Edit Employee Beneficiaries Test Failed");
					strMessage = "Edit Employee Beneficiaries Test Failure.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
				}
			}
		});
		btnEEditBene.setBounds(62, 45, 188, 23);
		pnlEmployee.add(btnEEditBene);
		/********************************************************************************
		 * CREATE EMPLOYEE BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnENewEmployee = new JButton("Create Employee Test");
		btnENewEmployee.setForeground(Color.WHITE);
		btnENewEmployee.setBackground(new Color(0, 153, 255));
		btnENewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestingScript EmployeeTest = new TestingScript();
				try {
					strMessage = "Running Create Employee Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					EmployeeTest.setStrLoginName(txtUsername.getText().toString());
					EmployeeTest.setStrPassword(txtPassword.getText().toString());
					EmployeeTest.setUp();
					//Creates a new employee test
					EmployeeTest.testEmployees();
					strMessage = "Create Employee Test Success!";
					dlm.addElement(strMessage);
					EmployeeTest.tearDown();
				} catch (Exception e1) {
					try {
						FileWriter fileWriter = new FileWriter("Reports/CreateEmployeesTest.csv");
						fileWriter.append(EmployeeTest.getStrReport() + "FAILURE," + " \n " + "Create Employee Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.out.println("Create Employee Test Failed");
					strMessage = "Create Employee Test Failure.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);	
				}
			}
		});
		btnENewEmployee.setBounds(62, 11, 188, 23);
		pnlEmployee.add(btnENewEmployee);
		/********************************************************************************
		 * EMPLOYEE EDIT/TERMINATE EMPLOYEE BUTTON
		 * @param
		 * - User name
		 * - Password
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnEEditTerminate = new JButton("Edit/Terminate Employee Test");
		btnEEditTerminate.setForeground(Color.WHITE);
		btnEEditTerminate.setBackground(new Color(0, 153, 255));
		btnEEditTerminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestingScript EmployeeTest = new TestingScript();
				try {
					strMessage = "Running Edit/Terminate Employee Page Test.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);
					EmployeeTest.setStrLoginName(txtUsername.getText().toString());
					EmployeeTest.setStrPassword(txtPassword.getText().toString());
					EmployeeTest.setUp();
					//Employee edit/terminate test
					EmployeeTest.testEmployees4();
					strMessage = "Employee Edit/Terminate Page Test Success!";
					dlm.addElement(strMessage);
					EmployeeTest.tearDown();
				} catch (Exception e1) {
					try {
						FileWriter fileWriter = new FileWriter("Reports/EditTerminateEmployeeTest.csv");
						fileWriter.append(EmployeeTest.getStrReport() + "FAILURE," + " \n " + "Edit/Terminate Employee Test Failure");
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					
					System.out.println("Employee Edit/Terminate Test Failed");
					strMessage = "Employee Edit/Terminate Page Test Failure.....";
					dlm.addElement(strMessage);
					lstResults.setModel(dlm);	
				}
			}
		});
		btnEEditTerminate.setBounds(62, 116, 188, 23);
		pnlEmployee.add(btnEEditTerminate);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 153, 102));
		tabbedPane.addTab("My Account", null, panel_2, null);
		panel_2.setLayout(null);
		/********************************************************************************
		 * MY ACCOUNT TEST BUTTON
		 * @param
		 * - User name
		 * - Password
		 * - AccountEmail
		 * @Class
		 * TestingScript
		 *******************************************************************************/
		JButton btnRunAccount = new JButton("Run My Account Page Test");
		btnRunAccount.setForeground(Color.WHITE);
		btnRunAccount.setBackground(new Color(0, 153, 255));
		btnRunAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestingScript myAccountTest = new TestingScript();
				if(txtAccountEmail.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "You must type in a new test email - ex. testassist(num)@test.com");
				} else {
					try {
						strMessage = "Running My Account Test.....";
						dlm.addElement(strMessage);
						lstResults.setModel(dlm);
						myAccountTest.setStrLoginName(txtUsername.getText().toString());
						myAccountTest.setStrPassword(txtPassword.getText().toString());
						//Sets up the account e-mail for new user
						myAccountTest.setStrAccountEmail(txtAccountEmail.getText().toString());
						myAccountTest.setUp();
						//Creates a new account user
						myAccountTest.myAccountPageTest();
						strMessage = "My Account Page Test Success!";
						dlm.addElement(strMessage);
						myAccountTest.tearDown();
					} catch (Exception e1) {
						System.out.println("My Account Test Failed");
						strMessage = "My Account Test Failed.....";
						dlm.addElement(strMessage);
						lstResults.setModel(dlm);
					}
				}
			}
		});
		btnRunAccount.setBounds(100, 70, 207, 23);
		panel_2.add(btnRunAccount);
		
		txtAccountEmail = new JTextField();
		txtAccountEmail.setBounds(100, 39, 207, 20);
		txtAccountEmail.setColumns(10);
		panel_2.add(txtAccountEmail);
		
		JLabel label = new JLabel("Account E-mail");
		label.setBounds(0, 42, 90, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("The Account E-mail needs to be different every time!");
		label_1.setBounds(0, 14, 297, 14);
		panel_2.add(label_1);
		
		JLabel lblMainTests = new JLabel("Main Tests:");
		lblMainTests.setForeground(Color.WHITE);
		lblMainTests.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMainTests.setBounds(457, 71, 86, 14);
		contentPane.add(lblMainTests);
		
		JLabel lblSubTests = new JLabel("Sub Tests:");
		lblSubTests.setForeground(Color.WHITE);
		lblSubTests.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubTests.setBounds(10, 311, 86, 14);
		contentPane.add(lblSubTests);
		
		JLabel lblNewLabel = new JLabel("Welcome to the Nomad Test Suite!\r\n");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(457, 337, 203, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblBeSureTo = new JLabel("Be sure to add a username and password you");
		lblBeSureTo.setForeground(Color.WHITE);
		lblBeSureTo.setBounds(457, 354, 291, 34);
		contentPane.add(lblBeSureTo);
		
		JLabel lblWantToSign = new JLabel("want to sign in with.");
		lblWantToSign.setForeground(Color.WHITE);
		lblWantToSign.setBounds(457, 370, 291, 34);
		contentPane.add(lblWantToSign);
		
		JLabel lblAlsoWhenUsing = new JLabel("Also when using the My Account have a different");
		lblAlsoWhenUsing.setForeground(Color.WHITE);
		lblAlsoWhenUsing.setBounds(457, 389, 291, 34);
		contentPane.add(lblAlsoWhenUsing);
		
		JLabel lblTestEmailEverytime = new JLabel("test email everytime.");
		lblTestEmailEverytime.setForeground(Color.WHITE);
		lblTestEmailEverytime.setBounds(457, 404, 291, 34);
		contentPane.add(lblTestEmailEverytime);
		
		JLabel lblTryOutA = new JLabel("Try out a Test! Click the home page test button!");
		lblTryOutA.setForeground(Color.WHITE);
		lblTryOutA.setBounds(457, 431, 291, 34);
		contentPane.add(lblTryOutA);
	}
}
