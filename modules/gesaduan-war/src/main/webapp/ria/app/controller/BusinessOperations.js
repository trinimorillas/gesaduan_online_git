/*
 * Template: BusinessOperations
 * declaracion de las operaciones de negocio a las que tiene acceso la aplicacion
 */
Ext.define('App.controller.BusinessOperations', {
	extend:'M.Controller',

	init:function (app) {
		M.bo.define({
				"demoria.bo.test.parametros" : {
					schema : {
					type : "object",
					additionalProperties : false,
					properties : {
						parametroDeTest : {  type : "string", optional : false}
					}
				}
				}
		});
	}

});
