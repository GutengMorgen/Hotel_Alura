package models;

import java.time.LocalDate;

public class Reserva {
	private Integer Id;
	private LocalDate dateE;
	private LocalDate dateS;
	private String Valor;
	private String FormaPago;
	
	public Reserva(LocalDate dateE, LocalDate dateS, String valor, String formaPago) {
		super();
		this.dateE = dateE;
		this.dateS = dateS;
		Valor = valor;
		FormaPago = formaPago;
	}

	public Reserva(Integer id, LocalDate dateE, LocalDate dateS, String valor, String formaPago) {
		super();
		Id = id;
		this.dateE = dateE;
		this.dateS = dateS;
		Valor = valor;
		FormaPago = formaPago;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public LocalDate getDateE() {
		return dateE;
	}

	public void setDateE(LocalDate dateE) {
		this.dateE = dateE;
	}

	public LocalDate getDateS() {
		return dateS;
	}

	public void setDateS(LocalDate dateS) {
		this.dateS = dateS;
	}

	public String getValor() {
		return Valor;
	}

	public void setValor(String valor) {
		Valor = valor;
	}

	public String getFormaPago() {
		return FormaPago;
	}

	public void setFormaPago(String formaPago) {
		FormaPago = formaPago;
	}

	@Override
	public String toString() {
		return "Reserva [Id=" + Id + ", dateE=" + dateE + ", dateS=" + dateS + ", Valor=" + Valor + ", FormaPago="
				+ FormaPago + "]";
	}
}
