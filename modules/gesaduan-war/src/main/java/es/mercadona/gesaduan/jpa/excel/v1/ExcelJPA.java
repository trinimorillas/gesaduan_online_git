package es.mercadona.gesaduan.jpa.excel.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "C_VARIABLE")
public class ExcelJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name="COD_V_VARIABLE")
	private String codigoVariable;
	
	@Column(name = "TXT_VALOR")
	private String txtValor;

	public String getCodigoVariable() {
		return codigoVariable;
	}

	public void setCodigoVariable(String codigoVariable) {
		this.codigoVariable = codigoVariable;
	}

	public String getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(String txtValor) {
		this.txtValor = txtValor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoVariable == null) ? 0 : codigoVariable.hashCode());
		result = prime * result + ((txtValor == null) ? 0 : txtValor.hashCode());
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
		ExcelJPA other = (ExcelJPA) obj;
		if (codigoVariable == null) {
			if (other.codigoVariable != null)
				return false;
		} else if (!codigoVariable.equals(other.codigoVariable))
			return false;
		if (txtValor == null) {
			if (other.txtValor != null)
				return false;
		} else if (!txtValor.equals(other.txtValor))
			return false;
		return true;
	}

}
