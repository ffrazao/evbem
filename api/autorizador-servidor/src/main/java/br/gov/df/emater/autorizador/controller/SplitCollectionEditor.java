package br.gov.df.emater.autorizador.controller;

import java.util.Collection;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

/**
 * Created by ahmed on 21.5.18.
 */
@SuppressWarnings("rawtypes")
public class SplitCollectionEditor extends CustomCollectionEditor {

	private final Class<? extends Collection> collectionType;
	private final String splitRegex;

	public SplitCollectionEditor(final Class<? extends Collection> collectionType, final String splitRegex) {
		super(collectionType, true);
		this.collectionType = collectionType;
		this.splitRegex = splitRegex;
	}

	@Override
	public void setAsText(final String text) throws IllegalArgumentException {
		if ((text == null) || text.isEmpty()) {
			super.setValue(super.createCollection(this.collectionType, 0));
		} else {
			super.setValue(text.split(this.splitRegex));
		}
	}
}
