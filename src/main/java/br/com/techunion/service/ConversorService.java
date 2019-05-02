package br.com.techunion.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opencsv.CSVReader;
import br.com.techunion.dao.NcmDAO;
import br.com.techunion.dto.TabelaibptTO;
import br.com.techunion.modelo.NCM;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class ConversorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConversorService.class);
	private static final String ENCODE = "Cp1252";

	public static final int codigo = 0;
	public static final int ex = 1;
	public static final int tipo = 2;
	public static final int descricao = 3;
	public static final int aliqNac = 4;
	public static final int aliqImp = 5;
	public static final int aliquotaEstadual = 6;
	public static final int aliquotaMunicipal = 7;
	public static final int vigenciainicio = 8;
	public static final int vigenciafim = 9;
	public static final int chave = 10;
	public static final int versao = 11;
	public static final int fonte = 12;

	public String selecionarArquivo() throws Exception {
		
		try {
			LOGGER.info("=====> Escolha do Arquivo Iniciada !");

			File arquivo = null;
			String pathNomeArquivo = "";

			JFileChooser escolhaDeArquivo = new JFileChooser();
			escolhaDeArquivo.setDialogTitle("Selecione a Tabela de IBPT");
			escolhaDeArquivo.setDialogType(JFileChooser.OPEN_DIALOG);
			escolhaDeArquivo.setApproveButtonText("OK");
			escolhaDeArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
			escolhaDeArquivo.setMultiSelectionEnabled(false);

			int resultado = escolhaDeArquivo.showOpenDialog(escolhaDeArquivo);

			if (resultado == JFileChooser.CANCEL_OPTION) {
				LOGGER.info("Seleção da Tabela Cancelada Pelo Usuario!");
				System.exit(13);
			} else {
				arquivo = escolhaDeArquivo.getSelectedFile();
				pathNomeArquivo = arquivo.getAbsolutePath();
				LOGGER.info("Arquivo Selecionado {}.", arquivo.getAbsolutePath());
			}
			LOGGER.trace("//Escolha de Arquivo Finalizada");

			return pathNomeArquivo;

		}catch (Exception z) {
			LOGGER.info("Erro Durante a Escolha do Arquivo.");
			JOptionPane.showMessageDialog(null, "Erro Durante a Escolha do Arquivo.");
			return null;
		}
	}

	public boolean validarTabelaIbpt(String[] coluna) throws Exception {

//		if (coluna.length != 13) {
//			JOptionPane.showMessageDialog(null, "Quantidade de Colunas da Tabela Invalida", "ERRO",
//					JOptionPane.OK_OPTION);
//			LOGGER.info("Quantidade de Colunas da Tabela Invalida");
//			throw new Exception("Quantidade de Colunas da Tabela Invalida");
//		}

		if (coluna != null && "IBPT".equalsIgnoreCase(coluna[fonte])) {
			if (coluna[codigo].length() < 8 && coluna[codigo].length() > 6) {
				LOGGER.debug("Código Aceito Menor Que 8 Digitos {}", coluna[codigo]);
				coluna[codigo] = StringUtils.leftPad(coluna[codigo], 8, "0");

			} else {
				if (coluna[codigo].length() > 8) {
					return false;
				}
			}
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "A Tabela Carregada Não Está no Padrão IBPT", "ERRO",
					JOptionPane.OK_OPTION);
			LOGGER.info("A Tabela Carregada Não Está no Padrão IBPT");
			throw new Exception("Arquivo Invalido !");
		}
	}

	public List<TabelaibptTO> abrirArquivoSelecionado(String pathCsvFile) throws Exception {

		CSVReader reader = null;

		try {
			LOGGER.debug("Abrindo Arquivo {}", pathCsvFile);
			reader = new CSVReader(new InputStreamReader(new FileInputStream(pathCsvFile), ENCODE), ';', '\'', 1);

			String[] coluna = reader.readNext();
			List<TabelaibptTO> lista = new ArrayList<TabelaibptTO>();

			while ((coluna = reader.readNext()) != null) {

				LOGGER.info("[Codigo= " + coluna[codigo] + ", Ex= " + coluna[ex] + " , Tipo=" + coluna[tipo] + ","
						+ "Descrição= " + coluna[descricao] + ", NacionalFederal= " + coluna[aliqNac]
						+ ", AliquotaFederal= " + coluna[aliqImp] + ", AliquotaEstadual= "
						+ coluna[aliquotaEstadual] + ", AliquotaMunicipal= " + coluna[aliquotaMunicipal]
						+ ", VigenciaInicio= " + coluna[vigenciainicio] + ", VigenciaFim= " + coluna[vigenciafim]
						+ ", Chave= " + coluna[chave] + ", Versão= " + coluna[versao] + ", Fonte= " + coluna[fonte]
						+ "]");

				if (this.validarTabelaIbpt(coluna)) {

					gerarLogDeConclusao("NCM: " + coluna[codigo] + " Foi Validado.");

					TabelaibptTO tabIBPT = new TabelaibptTO();

					SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

					tabIBPT.setCodigo(coluna[codigo]);
					int max = coluna[descricao].length() < 200 ? coluna[descricao].length() : 200;
					tabIBPT.setDescricao(coluna[descricao].substring(0, max));
					tabIBPT.setAliqNac(Double.parseDouble(coluna[aliqNac]));
					tabIBPT.setAliqImp(Double.parseDouble(coluna[aliqImp]));
					tabIBPT.setAliquotaMunicipal(Double.parseDouble(coluna[aliquotaMunicipal]));
					tabIBPT.setAliquototaEstadual(Double.parseDouble(coluna[aliquotaEstadual]));
					tabIBPT.setVigenciainicio(dataFormatada.parse((coluna[vigenciainicio])));
					tabIBPT.setVigenciafim(dataFormatada.parse((coluna[vigenciafim])));
					tabIBPT.setVersao(coluna[versao]);
					tabIBPT.setFonte(coluna[fonte]);

					lista.add(tabIBPT);
				}
			}
			reader.close();
			

			return lista;

		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			throw new Exception("Arquivo Invalido !");
		}

		catch (Exception e1) {
			e1.printStackTrace();
			LOGGER.error(e1.getMessage());
			throw new Exception("Não é uma tabela IBPT");
		}

		finally {
			reader.close();
		}
	}

	public void atualizarTabelaNCM(List<TabelaibptTO> lista) throws InterruptedException {

		LOGGER.trace("//Inicio da Atualização.");

		JFrame f = new JFrame("Aguarde");

		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = f.getContentPane();

		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		Border border = BorderFactory.createTitledBorder("Atualizando Registros");
		progressBar.setBorder(border);
		content.add(progressBar, BorderLayout.NORTH);

		f.setSize(300, 100);

		f.setLocationRelativeTo(null);

		try {

			NcmDAO ncmDAO = new NcmDAO();

			if (!lista.isEmpty()) {

				int validacao = JOptionPane.showConfirmDialog(null,
						"Confirma Atualização " + lista.get(0).getVersao() + " ?", "Escolha uma opção.",
						JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);

				if (validacao == JOptionPane.YES_OPTION) {

					// Necessario para calculo da barra
					double valorCadaCiclo = 100 / lista.size();
					double valorCicloAtual = valorCadaCiclo;

					for (TabelaibptTO auxTO : lista) {

						//Inicio do calculo da barra

						progressBar.setValue((int) valorCicloAtual);
						f.setVisible(true);
						progressBar.repaint();
						valorCicloAtual += valorCadaCiclo;

						// Fim do calculo da barra

						LOGGER.debug("Vou Pesquisar o NCM {}", auxTO.getCodigo());

						NCM auxNcm = ncmDAO.findByCodNcm(auxTO.getCodigo());

						LOGGER.debug("Vou Verificar o NCM Retornado Como {}", auxNcm);

						if (auxNcm != null) {

							LOGGER.info("Iniciando Processo de Atualização de NCM");
							auxNcm.setDescricaoNCM(auxTO.getDescricao());
							auxNcm.setAliqImp(new BigDecimal(auxTO.getAliqImp()));
							auxNcm.setAliqNac(new BigDecimal(auxTO.getAliqNac()));
							auxNcm.setAliquotaEstadual(new BigDecimal(auxTO.getAliquototaEstadual()));
							auxNcm.setAliquotaMunicipal(new BigDecimal(auxTO.getAliquotaMunicipal()));

							LOGGER.debug("{}", auxNcm);
							ncmDAO.update(auxNcm);
							gerarLogDeConclusao("====> NCM: " + auxNcm.getCodigoNCM() + " Atualizado: ");

						} else {

							LOGGER.info("Iniciando Processo de Inclusão de NCM");
							auxNcm = new NCM();
							auxNcm.setCodigoNCM(auxTO.getCodigo());
							auxNcm.setDescricaoNCM(auxTO.getDescricao());
							auxNcm.setAliqImp(new BigDecimal(auxTO.getAliqImp()));
							auxNcm.setAliqNac(new BigDecimal(auxTO.getAliqNac()));
							auxNcm.setAliquotaEstadual(new BigDecimal(auxTO.getAliquototaEstadual()));
							auxNcm.setAliquotaMunicipal(new BigDecimal(auxTO.getAliquotaMunicipal()));

							LOGGER.debug("{}", auxNcm);
							ncmDAO.save(auxNcm);
							gerarLogDeConclusao("=====> NCM Incluído: " + auxNcm.getCodigoNCM());

						}
						LOGGER.debug("Finalizei o TO {}", auxTO);
					}

					// Esconde a barra de progresso
					f.setVisible(false);

				} else if (validacao == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "Atualização Cancelada.");
				}
			}
			ncmDAO.close();

		} catch (Exception e) {
			LOGGER.error("Erro", e);
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}

		LOGGER.trace("=====>Fim da Atualização.");
	}

	public void gerarLogDeConclusao(String dsOperacao) {

		try {
			LOGGER.info(dsOperacao);
		}
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}

	}

}
