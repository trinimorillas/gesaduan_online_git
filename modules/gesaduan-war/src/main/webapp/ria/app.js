Ext.Loader.setConfig({
	enabled : true,
	paths : {
		App : 'app',
		M : null
	},
	disableCaching : false
});
M.application({
	title : 'TemplateApp',
	useMaps : false,
	
	controllers : [ 'BusinessOperations', 'MenuPrincipal', 'PantallaInicio'],
	models : [],
	stores : [],

	init : function() {
		M.log('app init');
	},

	launch : function() {
		M.log('app launch');
	},

	onLaunch : function() {
		M.log('app onLaunch');
	}
});
