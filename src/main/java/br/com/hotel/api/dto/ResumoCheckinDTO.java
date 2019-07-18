package br.com.hotel.api.dto;

public class ResumoCheckinDTO {
	
	private String nome;
	private String documento;
	private double valor;
	
	public ResumoCheckinDTO(String nome, String documento, double valor) {
		super();
		this.nome = nome;
		this.documento = documento;
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

}
