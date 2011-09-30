package org.openflexo.view.controller;

import java.beans.PropertyChangeSupport;

import org.openflexo.toolbox.HasPropertyChangeSupport;

public class PPMWebServiceClientconfiguration implements HasPropertyChangeSupport {

	public static enum ServerInstance {
		FREE("https://flexoservertest.bluepimento.eu/Flexo/WebObjects/FlexoServer.woa/ws/PPMWebService"), TEST(
				"https://flexoservertest.bluepimento.eu/Flexo/WebObjects/FlexoServer.woa/ws/PPMWebService"), MAIN(
						"https://www.flexobpmserver.com/Flexo/WebObjects/FlexoServer.woa/ws/PPMWebService"), OTHER(null);
		private String url;

		private ServerInstance(String url) {
			this.url = url;
		}

		public String getUrl() {
			return url;
		}
	}

	private PropertyChangeSupport pc;

	private String login;
	private String password;
	private ServerInstance instance;
	private String url;

	public PPMWebServiceClientconfiguration() {
		pc = new PropertyChangeSupport(this);
	}

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return pc;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
		pc.firePropertyChange("login", null, login);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		pc.firePropertyChange("password", null, password);
	}

	public ServerInstance getInstance() {
		return instance;
	}

	public void setInstance(ServerInstance instance) {
		this.instance = instance;
		pc.firePropertyChange("instance", null, instance);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		pc.firePropertyChange("url", null, url);
	}

}
