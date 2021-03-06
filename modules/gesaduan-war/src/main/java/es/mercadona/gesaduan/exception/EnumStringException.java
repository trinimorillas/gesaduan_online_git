package es.mercadona.gesaduan.exception;



public enum EnumStringException {
	GENERAL("GENERAL"),
	PLAN_EMBARQUE("PLAN EMBARQUE"),	
	EQUIPO_TRANSPORTE("EQUIPO TRANSPORTE"),	
	CARGAS("CARGAS"),
	EXPEDICION("EXPEDICION"),
	CARGAR_EXPEDICION("CARGAR EXPEDICION"),
	MOVER_CARGAS("MOVER CARGAS"),
	CAMBIAR_ESTADO("CAMBIAR ESTADO"),
	PROVEEDORES("PROVEEDORES"),
	PUERTO_AGENCIA("PUERTO_AGENCIA"),
	DECLARACION_VALOR("DECLARACION_VALOR");

    private String descripcion;

    EnumStringException(String descripcion) {
        this.descripcion = descripcion;
    }

    public String descripcion() {
        return descripcion;
    }

	public String getDescripcion() {
		return descripcion;
	}
	
	

}