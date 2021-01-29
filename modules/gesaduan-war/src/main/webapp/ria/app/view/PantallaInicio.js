/*
 * Plantilla: Pantalla de inicio de la aplicacion.
 *
 * */
Ext.define('App.view.PantallaInicio', {
	extend : 'M.panel.MPanel',
	alias : 'widget.pantallainicio',
	
	// style
	layout : 'fit',
	border : false,
	bodyStyle : 'font-size:2em;padding:2em;text-align:center;vertical-align:middle;',
	
	// content
	title : i18n.pantallainicio.title,
	html : '<h1>' + i18n.pantallainicio.html + '</h1>'
});
