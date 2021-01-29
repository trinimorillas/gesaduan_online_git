/**
 * Template: Plantilla para el menu principal de la aplicacion
 *
 * copiar a la carpeta de la aplicacion para sobreescribir la funcionalidad
 *
 */
Ext.define('App.view.MenuPrincipal', {
	extend : 'M.view.MMenuPrincipal',
	alias : 'widget.m_menuprincipal',
	
	initComponent : function() {
		this.items = [ {
			text : i18n.menuprincipal.inicio,
			mView : 'pantallainicio'
		}, {
			text : i18n.menuprincipal.userinfo,
			menu : [ {
				text : i18n.userinfo.title,
				mView : 'userinfo'
			} ]
		}, {
			text : i18n.menuprincipal.cambiarIdioma,
			menu : [ {
				text : i18n.cambiarIdioma.es,
				handler : function() {
					var context = location.pathname.split('/')[1];
					Ext.Ajax.request({
						url: '/' + context + '/rest/fwk3/bo/security/changeLocale',
						method: "POST",
						jsonData : {bodata: {locale:"es"}},
						callback : function(options, success, response) {
							var resp = Ext.decode(response.responseText);
							
							if(success && resp.success) {
								M.security.refreshSession();
								document.location='index.html';

							} else {
								M.showError(i18n.error, resp.exceptions[0].message);
							}
						}
					});
				},
				mView : 'pantallainicio'
			}, {
				text : i18n.cambiarIdioma.en,
				handler : function() {
					var context = location.pathname.split('/')[1];
					Ext.Ajax.request({
						url: '/' + context + '/rest/fwk3/bo/security/changeLocale',
						method: "POST",
						jsonData : {bodata: {locale: "es"}},
						callback : function(options, success, response) {
							var resp = Ext.decode(response.responseText);
							
							if(success && resp.success) {
								M.security.refreshSession();
								document.location='index.html';

							} else {
								M.showError(i18n.error, resp.exceptions[0].message);
							}
						}
					});
				},
				mView : 'pantallainicio'
			} ]
		}, {
			text : i18n.menuprincipal.logout,
			handler : function(btn) {
				btn.el.removeCls('x-pressed');
				Ext.Msg.confirm(i18n.dialog.confirm, i18n.dialog.logout, function(yes){
					if (yes=='yes'){
						var context = location.pathname.split('/')[1];
						Ext.Ajax.request({
							url: '/' + context + '/rest/fwk3/bo/security/logout',
							callback: function(){
								delete M.security.userInfo;
								document.location='index.html';
							}
						});
					}
				});
			},
			mView : 'pantallainicio'
		} ];

		this.callParent(arguments);
	}

});
