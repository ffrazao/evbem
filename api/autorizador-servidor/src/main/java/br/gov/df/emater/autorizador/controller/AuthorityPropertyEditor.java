package br.gov.df.emater.autorizador.controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Created by ahmed on 21.5.18.
 */
public class AuthorityPropertyEditor implements PropertyEditor {

	private GrantedAuthority grantedAuthority;

	@Override
	public void addPropertyChangeListener(final PropertyChangeListener listener) {

	}

	@Override
	public String getAsText() {
		return this.grantedAuthority.getAuthority();
	}

	@Override
	public Component getCustomEditor() {
		return null;
	}

	@Override
	public String getJavaInitializationString() {
		return null;
	}

	@Override
	public String[] getTags() {
		return new String[0];
	}

	@Override
	public Object getValue() {
		return this.grantedAuthority;
	}

	@Override
	public boolean isPaintable() {
		return false;
	}

	@Override
	public void paintValue(final Graphics gfx, final Rectangle box) {

	}

	@Override
	public void removePropertyChangeListener(final PropertyChangeListener listener) {

	}

	@Override
	public void setAsText(final String text) throws IllegalArgumentException {
		if ((text != null) && !text.isEmpty()) {
			this.grantedAuthority = new SimpleGrantedAuthority(text);
		}
	}

	@Override
	public void setValue(final Object value) {
		this.grantedAuthority = (GrantedAuthority) value;
	}

	@Override
	public boolean supportsCustomEditor() {
		return false;
	}
}
