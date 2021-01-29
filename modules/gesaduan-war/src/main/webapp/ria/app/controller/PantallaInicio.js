/**
 * MTemplate: Plantilla para copiar a la aplicacion 
 */
Ext.define('App.controller.PantallaInicio', {
	extend:'M.Controller',

	views : ['PantallaInicio'],

	init:function (app) {

	},
	
	getPanel : function(){
		return Ext.widget("pantallainicio");
	}
});
