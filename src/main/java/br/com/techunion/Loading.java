package br.com.techunion;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class Loading {

	public static void Carrega() throws InterruptedException {
				
		JFrame f = new JFrame("Aguarde");
		
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = f.getContentPane();
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		Border border = BorderFactory.createTitledBorder("Atualizando Registros...");
		progressBar.setBorder(border);
		content.add(progressBar, BorderLayout.NORTH);
		
		
		f.setSize(300, 100);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		
		for (int i = 0; i <= 100; i++) 
		{
			Thread.sleep(100);
			progressBar.setValue(i);
		}
		
		f.setVisible(false);
		//System.exit(0);
		
		
	}

}
