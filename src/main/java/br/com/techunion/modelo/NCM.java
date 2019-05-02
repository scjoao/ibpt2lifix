package br.com.techunion.modelo;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "NCM.findall", query = "SELECT N FROM NCM N"),
	@NamedQuery(name = "NCM.findByCodNcm", query = "SELECT N FROM NCM N WHERE N.codigoNCM = :valor") })

public class NCM {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idNcm;
	private BigDecimal aliquota;
	private String codigoNCM;
	private String descricaoNCM;
	private Long idCofins;
	private Long idCofinsST;
	private Long idPis;
	private Long idPisST;
	private Long subGrupoNCM_idSubGrupoNCM;
	private BigDecimal aliqImp;
	private BigDecimal aliqNac;
	private BigDecimal aliquotaMunicipal;
	private BigDecimal aliquotaEstadual;
	//private BigDecimal aliquotaFederal;

	public String getDescricaoNCM() {
		return descricaoNCM;
	}

	public void setDescricaoNCM(String descricaoNCM) {
		this.descricaoNCM = descricaoNCM;
	}

	public Long getIdNcm() {
		return idNcm;
	}

	public void setIdNcm(Long idNcm) {
		this.idNcm = idNcm;
	}

	public BigDecimal getAliquota() {
		return aliquota;
	}

	public void setAliquota(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}

	public String getCodigoNCM() {
		return codigoNCM;
	}

	public void setCodigoNCM(String codigoNCM) {
		this.codigoNCM = codigoNCM;
	}

	public Long getIdCofins() {
		return idCofins;
	}

	public void setIdCofins(Long idCofins) {
		this.idCofins = idCofins;
	}

	public Long getIdCofinsST() {
		return idCofinsST;
	}

	public void setIdCofinsST(Long idCofinsST) {
		this.idCofinsST = idCofinsST;
	}

	public Long getIdPis() {
		return idPis;
	}

	public void setIdPis(Long idPis) {
		this.idPis = idPis;
	}

	public Long getIdPisST() {
		return idPisST;
	}

	public void setIdPisST(Long idPisST) {
		this.idPisST = idPisST;
	}

	public Long getSubGrupoNCM_idSubGrupoNCM() {
		return subGrupoNCM_idSubGrupoNCM;
	}

	public void setSubGrupoNCM_idSubGrupoNCM(Long subGrupoNCM_idSubGrupoNCM) {
		this.subGrupoNCM_idSubGrupoNCM = subGrupoNCM_idSubGrupoNCM;
	}

	public BigDecimal getAliqImp() {
		return aliqImp;
	}

	public void setAliqImp(BigDecimal aliqImp) {
		this.aliqImp = aliqImp;
	}

	public BigDecimal getAliqNac() {
		return aliqNac;
	}

	public void setAliqNac(BigDecimal aliqNac) {
		this.aliqNac = aliqNac;
	}

	public BigDecimal getAliquotaMunicipal() {
		return aliquotaMunicipal;
	}

	public void setAliquotaMunicipal(BigDecimal aliquotaMunicipal) {
		this.aliquotaMunicipal = aliquotaMunicipal;
	}

	public BigDecimal getAliquotaEstadual() {
		return aliquotaEstadual;
	}

	public void setAliquotaEstadual(BigDecimal aliquotaEstadual) {
		this.aliquotaEstadual = aliquotaEstadual;
	}

//	public BigDecimal getAliquotaFederal() {
//		return aliquotaFederal;
//	}
//
//	public void setAliquotaFederal(BigDecimal aliquotaFederal) {
//		this.aliquotaFederal = aliquotaFederal;
//	}

	@Override
	public String toString() {
		return "NCM [idNcm=" + idNcm + ", aliquota=" + aliquota + ", codigoNCM=" + codigoNCM + ", descricaoNCM="
				+ descricaoNCM + ", idCofins=" + idCofins + ", idCofinsST=" + idCofinsST + ", idPis=" + idPis
				+ ", idPisST=" + idPisST + ", subGrupoNCM_idSubGrupoNCM=" + subGrupoNCM_idSubGrupoNCM + ", aliqImp="
				+ aliqImp + ", aliqNac=" + aliqNac + ",aliquotaMunicipal = " + aliquotaMunicipal
				+ ",aliquotaEstadual = " + aliquotaMunicipal + ",aliquotaFederal=" /*+ aliquotaFederal*/ +"]";
	}

}
