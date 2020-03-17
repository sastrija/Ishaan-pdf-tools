/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyothis.pdftools;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Jyothis Sasidharan
 */
public class SecurePdf extends javax.swing.JDialog {

	private final JFileChooser fileChooser = new JFileChooser();
	private final JFileChooser outputFolderChooser = new JFileChooser();
	
	DefaultListModel listModel = null;

	/**
	 * Creates new form SecurePdf
	 * 
	 * @param parent
	 * @param modal
	 */
	public SecurePdf(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();

		txtOutputDirectory.setText(System.getProperty("user.home") + "/Desktop");

		// Init file chooser
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "PDF Documents (*.jpg)";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					String filename = f.getName().toLowerCase();
					return filename.endsWith(".pdf");
				}
			}
		});
		// Init file list model
		listModel = new DefaultListModel();
		lstFiles.setModel(listModel);
	}


	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		btnChooseFiles = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		lstFiles = new javax.swing.JList<>();
		btnRemoveSelectedFile = new javax.swing.JButton();
		btnMoveUp = new javax.swing.JButton();
		btnMoveDown = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		chkAllowPrinting = new javax.swing.JCheckBox();
		chkAllowContent = new javax.swing.JCheckBox();
		chkAllowFillIn = new javax.swing.JCheckBox();
		chkAllowModify = new javax.swing.JCheckBox();
		jLabel1 = new javax.swing.JLabel();
		txtFileNameSuffix = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		pwdUser = new javax.swing.JPasswordField();
		jLabel3 = new javax.swing.JLabel();
		pwdOwner = new javax.swing.JPasswordField();
		jLabel4 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		btnSelectOutputDirectory = new javax.swing.JButton();
		txtOutputDirectory = new javax.swing.JTextField();
		btnSecurePdf = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("PDF File(s) to secure");
		setLocationByPlatform(true);
		setResizable(false);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("PDF File(s) to Secure"));
		jPanel1.setToolTipText("");

		btnChooseFiles.setText("Choose Files");
		btnChooseFiles.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChooseFilesActionPerformed(evt);
			}
		});

		jScrollPane1.setViewportView(lstFiles);

		btnRemoveSelectedFile.setText("Remove Selected");
		btnRemoveSelectedFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRemoveSelectedFileActionPerformed(evt);
			}
		});

		btnMoveUp.setText("^");
		btnMoveUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMoveUpActionPerformed(evt);
			}
		});

		btnMoveDown.setText("V");
		btnMoveDown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMoveDownActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addComponent(btnChooseFiles)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRemoveSelectedFile).addContainerGap())
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(btnMoveUp, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMoveDown, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnChooseFiles).addComponent(btnRemoveSelectedFile))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(btnMoveUp)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btnMoveDown)))
						.addGap(0, 8, Short.MAX_VALUE)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose Options"));

		chkAllowPrinting.setText("Printing");

		chkAllowContent.setText("Copy Content");

		chkAllowFillIn.setText("Fill In");

		chkAllowModify.setText("Modify");

		jLabel1.setText("File Name Suffix");

		txtFileNameSuffix.setText("_secured");

		jLabel2.setText("Password to Open");

		jLabel3.setText("Password to Secure PDF");

		jLabel4.setText("Allow:");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel4)
										.addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(txtFileNameSuffix))))
						.addGroup(
								jPanel2Layout.createSequentialGroup().addGap(51, 51, 51).addComponent(chkAllowPrinting)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(chkAllowContent)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(chkAllowFillIn)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(chkAllowModify))
						.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jLabel2)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(pwdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel3)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(pwdOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 135,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(chkAllowPrinting).addComponent(chkAllowContent)
								.addComponent(chkAllowFillIn).addComponent(chkAllowModify).addComponent(jLabel4))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1).addComponent(txtFileNameSuffix,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2)
								.addComponent(pwdUser, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3).addComponent(pwdOwner, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))));

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Secure PDF"));

		btnSelectOutputDirectory.setText("Select");
		btnSelectOutputDirectory.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSelectOutputDirectoryActionPerformed(evt);
			}
		});

		txtOutputDirectory.setEditable(false);
		txtOutputDirectory.setText("--- Select an Output directory ---");

		btnSecurePdf.setText("Secure PDF");
		btnSecurePdf.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSecurePdfActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addComponent(txtOutputDirectory)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnSelectOutputDirectory))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel3Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSecurePdf).addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup()
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnSelectOutputDirectory).addComponent(txtOutputDirectory,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSecurePdf,
								javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		pack();
	}

	private void btnChooseFilesActionPerformed(java.awt.event.ActionEvent evt) {
		int returnVal = fileChooser.showOpenDialog(SecurePdf.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File[] files = fileChooser.getSelectedFiles();
			for (File file : files) {
				listModel.addElement(file.getAbsolutePath());
			}
		} else {
			System.out.println("Open command cancelled by user.");
		}
	}

	private void btnRemoveSelectedFileActionPerformed(java.awt.event.ActionEvent evt) {
		if (lstFiles.getSelectedIndex() >= 0) {
			listModel.remove(lstFiles.getSelectedIndex());
		}
	}

	private void btnMoveDownActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		int moveMe = lstFiles.getSelectedIndex();
		if (moveMe != listModel.getSize() - 1) {
			// not already at bottom
			swap(moveMe, moveMe + 1);
			lstFiles.setSelectedIndex(moveMe + 1);
			lstFiles.ensureIndexIsVisible(moveMe + 1);
		}
	}

	private void btnMoveUpActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		int moveMe = lstFiles.getSelectedIndex();
		if (moveMe >= 0) {
			// not already at top
			swap(moveMe, moveMe - 1);
			lstFiles.setSelectedIndex(moveMe - 1);
			lstFiles.ensureIndexIsVisible(moveMe - 1);
		}
	}

	private void btnSecurePdfActionPerformed(java.awt.event.ActionEvent evt) {
		for (Object inputFilePath : listModel.toArray()) {
			FileInputStream inputFileFis = null;
			try {
				File file = new File((String) inputFilePath);
				inputFileFis = new FileInputStream(file);
				PdfReader inputPdf = new PdfReader(inputFileFis);
				Document doc = new Document();
				StringBuilder sb = new StringBuilder();
				sb.append(txtOutputDirectory.getText()).append(File.separatorChar)
						.append(FilenameUtils.removeExtension(file.getName())).append(txtFileNameSuffix.getText())
						.append(".pdf");
				System.out.println(">>" + sb.toString());
				OutputStream out = new FileOutputStream(new File(sb.toString()));
				PdfWriter writer = PdfWriter.getInstance(doc, out);
				String userPwd = pwdUser.getPassword().length > 0 ? Arrays.toString(pwdUser.getPassword()) : null;
				String ownerPwd = pwdOwner.getPassword().length > 0 ? Arrays.toString(pwdOwner.getPassword()) : null;
				int permissions = (chkAllowPrinting.isSelected() ? PdfWriter.ALLOW_PRINTING : 0)
						| (chkAllowContent.isSelected() ? PdfWriter.ALLOW_COPY : 0)
						| (chkAllowFillIn.isSelected() ? PdfWriter.ALLOW_FILL_IN : 0)
						| (chkAllowModify.isSelected() ? PdfWriter.ALLOW_MODIFY_CONTENTS : 0);
				writer.setEncryption(userPwd == null ? null : userPwd.getBytes(),
						ownerPwd == null ? null : ownerPwd.getBytes(), permissions, PdfWriter.STANDARD_ENCRYPTION_128);
				writer.createXmpMetadata();
				doc.open();
				PdfContentByte cb = writer.getDirectContent();
				for (int i = 1; i <= inputPdf.getNumberOfPages(); i++) {
					doc.newPage();
					// import the page from source pdf
					PdfImportedPage page = writer.getImportedPage(inputPdf, i);
					// add the page to the destination pdf
					cb.addTemplate(page, 0, 0);
				}
				out.flush();
				doc.close();
				out.close();
			} catch (IOException | DocumentException ex) {
				Logger.getLogger(SecurePdf.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					inputFileFis.close();
				} catch (IOException ex) {
					Logger.getLogger(SecurePdf.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	private void btnSelectOutputDirectoryActionPerformed(java.awt.event.ActionEvent evt) {
		outputFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = outputFolderChooser.showOpenDialog(SecurePdf.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = outputFolderChooser.getSelectedFile();
			txtOutputDirectory.setText(file.getAbsolutePath());
		} else {
			System.out.println("Open command cancelled by user.");
		}
	}

	private void swap(int a, int b) {
		Object aObject = listModel.getElementAt(a);
		Object bObject = listModel.getElementAt(b);
		listModel.set(a, bObject);
		listModel.set(b, aObject);
	}


	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SecurePdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SecurePdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SecurePdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SecurePdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(() -> {
			SecurePdf dialog = new SecurePdf(new javax.swing.JFrame(), true);
			dialog.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
				}
			});

			// Center align window
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			dialog.setLocation(dim.width / 2 - dialog.getSize().width / 2,
					dim.height / 2 - dialog.getSize().height / 2);
			dialog.setVisible(true);
		});
	}

	private javax.swing.JButton btnChooseFiles;
	private javax.swing.JButton btnMoveDown;
	private javax.swing.JButton btnMoveUp;
	private javax.swing.JButton btnRemoveSelectedFile;
	private javax.swing.JButton btnSecurePdf;
	private javax.swing.JButton btnSelectOutputDirectory;
	private javax.swing.JCheckBox chkAllowContent;
	private javax.swing.JCheckBox chkAllowFillIn;
	private javax.swing.JCheckBox chkAllowModify;
	private javax.swing.JCheckBox chkAllowPrinting;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JList<String> lstFiles;
	private javax.swing.JPasswordField pwdOwner;
	private javax.swing.JPasswordField pwdUser;
	private javax.swing.JTextField txtFileNameSuffix;
	private javax.swing.JTextField txtOutputDirectory;
}
