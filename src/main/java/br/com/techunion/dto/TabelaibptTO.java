package br.com.techunion.dto;

import java.util.Date;

public class TabelaibptTO {

	private String codigo;
	private String descricao;
	private Double aliqNac;
	private Double aliqImp;
	private Double aliquototaEstadual;
	private Double aliquotaMunicipal;
	private Date vigenciainicio;
	private Date vigenciafim;
	private String versao;
	private String fonte;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getAliqNac() {
		return aliqNac;
	}

	public void setAliqNac(Double aliqNac) {
		this.aliqNac = aliqNac;
	}

	public Double getAliqImp() {
		return aliqImp;
	}

	public void setAliqImp(Double aliqImp) {
		this.aliqImp = aliqImp;
	}

	public Double getAliquototaEstadual() {
		return aliquototaEstadual;
	}

	public void setAliquototaEstadual(Double aliquototaEstadual) {
		this.aliquototaEstadual = aliquototaEstadual;
	}

	public Double getAliquotaMunicipal() {
		return aliquotaMunicipal;
	}

	public void setAliquotaMunicipal(Double aliquotaMunicipal) {
		this.aliquotaMunicipal = aliquotaMunicipal;
	}

	public Date getVigenciainicio() {
		return vigenciainicio;
	}

	public void setVigenciainicio(Date vigenciainicio) {
		this.vigenciainicio = vigenciainicio;
	}

	public Date getVigenciafim() {
		return vigenciafim;
	}

	public void setVigenciafim(Date vigenciafim) {
		this.vigenciafim = vigenciafim;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	@Override
	public String toString() {
		return "tabelaibptTO [codigo=" + codigo + ", descricao=" + descricao + ", nacionalfederal=" + aliqNac
				+ ", importadosfederal=" + aliqImp + ", aliquototaEstadual=" + aliquototaEstadual
				+ ",aliquototaMunicipal=" + aliquototaEstadual + " , vigenciainicio=" + vigenciainicio
				+ ", vigenciasim=" + vigenciafim + ", versao=" + versao + ", fonte=" + fonte + "]";
	}

}
