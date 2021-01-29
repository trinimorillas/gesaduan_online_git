package es.mercadona.gesaduan.exception;

public enum EnumGesaduanException {
	ERROR_GENERICO("Error genérico",null, "GES-00000", "GENERAL", "GENERAL"),
	PARAMETROS_OBLIGATORIOS("Faltan parámetros obligatorios.",null, "GES-00001", "GENERAL", "GENERAL"),
	ESTADO_NO_EN_CURSO("El plan de embarque no está EN CURSO.",null,  "GES-00002", "PLAN EMBARQUE", "CREAR/MODIFICAR"),
    ESTADO_INCORRECTO("El estado del plan de embarque no es correcto.",null,  "GES-00003", "PLAN EMBARQUE", "CAMBIAR ESTADO"),
    PLAN_EMBARQUE_SIN_EQUIPOS("El plan de embarque no tiene equipos.",null,  "GES-00004", "PLAN EMBARQUE", "CAMBIAR ESTADO"),
    EQUIPO_SIN_CARGAS("Algún equipo del plan de embarque no tiene cargas asignadas.",null,  "GES-00005", "PLAN EMBARQUE", "CAMBIAR ESTADO"),
	FECHA_MAYOR_QUE_HOY("La fecha de carga no puede ser anterior a la fecha actual.",null,  "GES-00006", "EQUIPO TRANSPORTE", "CREAR/MODIFICAR"),
	YA_EXISTE_EL_REGISTRO("Ya existe un registro con esos valores.",null,  "GES-00007", "GENERAL", "GENERAL"),
	EQUIPO_CARGADO("El equipo está marcado como cargado.",null,  "GES-00008", "EQUIPO TRANSPORTE", "MODIFICAR EQUIPO"),
	CARGA_NO_PROCESADA("El equipo tiene al menos una carga que no está en estado Pendiente asignar.",null,  "GES-00009", "EQUIPO TRANSPORTE", "CREAR RELACION EQUIPO CARGA"),
	EQUIPO_ORIGEN_CARGADO("El equipo origen está marcado como pendiente de facturar.",null,  "GES-00010", "EQUIPO TRANSPORTE", "MOVER CARGAS"),
	EQUIPO_DESTINO_CARGADO("El equipo destino está marcado como pendiente de facturar.",null,  "GES-00011", "EQUIPO TRANSPORTE", "MOVER CARGAS"),
	CODIGOS_CARGAS_DISTINTOS("Los códigos de carga deben estar: todos vacíos, o todos informados.",null,  "GES-00012", "CARGAS", "CREAR CARGAS"),
	ESTADO_CARGA_NO_PENDIENTE("La carga no se encuentra en estado Pendiente de asignar.",null,  "GES-00013", "CARGAS", "CREAR RELACION CARGA PEDIDO"),	
	CAMBIAR_ESTADO_CARGA("No se puede cambiar el estado de alguna de las cargas porque es incompatible con su estado actual.","No se puede cambiar el estado de la carga porque es incompatible con su estado actual.",  "GES-00014", "CARGAS", "CAMBIAR ESTADO CARGA"),
	PEDIDOS_NO_ASOCIADOS("Alguna de las cargas no tiene pedidos asociados y por ello no se puede cambiar su estado a PENDIENTE ASIGNAR.","Esta carga no tiene pedidos asociados y por ello no se puede cambiar su estado a PENDIENTE ASIGNAR.",  "GES-00015", "CARGAS", "CAMBIAR ESTADO CARGA"),
	PEDIDO_YA_EXISTE("El pedido ya existe para esta carga.",null,  "GES-00016", "CARGAS", "CREAR RELACION CARGA PEDIDO"),
	CARGA_NO_VALIDADA_NO_TIENE_PEDIDOS("Las siguientes cargas no han sido validadas por que no tiene pedidos asociados $CARGAS.",null,  "GES-00017", "CARGAS", "VALIDAR PEDIDOS CARGAS"),
	NUM_ALBARAN_DISTINTO("El número de albarán no coincide con el número de expedición de la pestaña de resultados", null, "GES-00018", "EXPEDICION", "CARGAR EXPEDICION"),
	NUM_PEDIDO_DISTINTO("El número de pedido del Excel no coincide con el número de pedido de la pestaña de resultados", null, "GES-00019", "EXPEDICION", "CARGAR EXPEDICION"),
	VERSION_DISTINTA_EXCEL("La versión del Excel no coincide con la versión indicada en la base de datos", null, "GES-00020", "EXPEDICION", "CARGAR EXPEDICION");

    private String descripcion;
    private String descripcionSingular;    
    private String codigo;
    private String entidad;
    private String operacion;

    EnumGesaduanException(String descripcion, String descripcionSingular, String codigo, String entidad, String operacion) {
        this.descripcion = descripcion;
        if (descripcionSingular != null) {
        	this.descripcionSingular = descripcionSingular;
        }
        else {
        	this.descripcionSingular = this.descripcion;        	
        }
        this.codigo = codigo;
        this.entidad = entidad;
        this.operacion = operacion;
    }

    public String descripcion() {
        return descripcion;
    }

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	
    public String descripcionSingular() {
        return descripcionSingular;
    }

	public String getDescripcionSingular() {
		return descripcionSingular;
	}	

	public void setDescripcionSingular(String descripcionSingular) {
		this.descripcionSingular = descripcionSingular;
	}	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
}