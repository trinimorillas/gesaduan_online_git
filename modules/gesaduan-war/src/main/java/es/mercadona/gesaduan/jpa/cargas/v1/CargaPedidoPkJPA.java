package es.mercadona.gesaduan.jpa.cargas.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CargaPedidoPkJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "COD_V_CARGA")
	private String codigoCarga;
	
	@Column(name="COD_V_ALMACEN_ORIGEN")
	private String codigoCentroOrigen;
	
	@Column(name="COD_V_PEDIDO")
	private String codigoPedido;
	
	@Column(name = "COD_V_DIVISION_PEDIDO")
	private String codigoDivisionPedido;	

	public String getCodigoCarga() {
		return codigoCarga;
	}

	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}

	public String getCodigoCentroOrigen() {
		return codigoCentroOrigen;
	}

	public void setCodigoCentroOrigen(String codigoCentroOrigen) {
		this.codigoCentroOrigen = codigoCentroOrigen;
	}

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public String getCodigoDivisionPedido() {
		return codigoDivisionPedido;
	}

	public void setCodigoDivisionPedido(String codigoDivisionPedido) {
		this.codigoDivisionPedido = codigoDivisionPedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCarga == null) ? 0 : codigoCarga.hashCode());
		result = prime * result + ((codigoCentroOrigen == null) ? 0 : codigoCentroOrigen.hashCode());
		result = prime * result + ((codigoPedido == null) ? 0 : codigoPedido.hashCode());
		result = prime * result + ((codigoDivisionPedido == null) ? 0 : codigoDivisionPedido.hashCode());		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CargaPedidoPkJPA other = (CargaPedidoPkJPA) obj;
		if (codigoCarga == null) {
			if (other.codigoCarga != null)
				return false;
		} else if (!codigoCarga.equals(other.codigoCarga))
			return false;
		if (codigoCentroOrigen == null) {
			if (other.codigoCentroOrigen != null)
				return false;
		} else if (!codigoCentroOrigen.equals(other.codigoCentroOrigen))
			return false;
		if (codigoPedido == null) {
			if (other.codigoPedido != null)
				return false;
		} else if (!codigoPedido.equals(other.codigoPedido))
			return false;
		if (codigoDivisionPedido == null) {
			if (other.codigoDivisionPedido != null)
				return false;
		} else if (!codigoDivisionPedido.equals(other.codigoDivisionPedido))
			return false;		
		return true;
	}
	
}
