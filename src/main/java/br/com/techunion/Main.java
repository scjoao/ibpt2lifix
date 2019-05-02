package br.com.techunion;

import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.techunion.service.ConversorService;

public class Main{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {		
		
		LOGGER.trace("Operação Inicializada");

		ConversorService chamadaMain = new ConversorService();
				
		     chamadaMain.atualizarTabelaNCM(
				chamadaMain.abrirArquivoSelecionado(
					chamadaMain.selecionarArquivo()));
		
		LOGGER.trace("Operação Finalizada");
		
		JOptionPane.showMessageDialog(null, "Operação finalizada.");
		System.exit(14);
		
	}
}
