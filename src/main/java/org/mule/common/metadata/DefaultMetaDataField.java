package org.mule.common.metadata;

import org.mule.common.metadata.field.property.DefaultFieldPropertyFactory;
import org.mule.common.metadata.field.property.MetaDataFieldProperty;

import java.util.Arrays;
import java.util.List;

public class DefaultMetaDataField
	implements MetaDataField {

	private String name;
	private MetaDataModel model;
	private FieldAccessType accessType;
    private MetaDataPropertyManager<MetaDataFieldProperty> metaDataFieldPropertyManager;

	public DefaultMetaDataField(final String name, final MetaDataModel model) {
		this(name, model, FieldAccessType.READ_WRITE, (new DefaultFieldPropertyFactory()).getProperties(null, model));
	}

    public DefaultMetaDataField(final String name, final MetaDataModel model, final FieldAccessType accessType) {
        this(name, model, accessType, (new DefaultFieldPropertyFactory()).getProperties(null, model));
    }

    public DefaultMetaDataField(final String name, final MetaDataModel model, List<MetaDataFieldProperty> fieldProperties) {
        this(name, model, FieldAccessType.READ_WRITE, fieldProperties);
    }

    public DefaultMetaDataField(final String name, final MetaDataModel model, MetaDataFieldProperty... fieldProperties) {
        this(name, model, FieldAccessType.READ_WRITE, Arrays.asList(fieldProperties));
    }

	public DefaultMetaDataField(final String name, final MetaDataModel model, final FieldAccessType accessType, List<MetaDataFieldProperty> fieldProperties) {
		this.name = name;
		this.model = model;
		this.accessType = accessType;
        this.metaDataFieldPropertyManager = new MetaDataPropertyManager<MetaDataFieldProperty>(fieldProperties);
	}

	@Override
	public FieldAccessType getAccessType() {
		return accessType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public MetaDataModel getMetaDataModel() {
		return model;
	}

    @Override
    public List<MetaDataFieldProperty> getProperties() {
        return this.metaDataFieldPropertyManager.getProperties();
    }

    @Override
    public boolean addProperty(MetaDataFieldProperty metaDataFieldProperty) {
        return this.metaDataFieldPropertyManager.addProperty(metaDataFieldProperty);
    }

    @Override
    public boolean removeProperty(MetaDataFieldProperty metaDataFieldProperty) {
        return this.metaDataFieldPropertyManager.removeProperty(metaDataFieldProperty);
    }

    @Override
    public boolean hasProperty(Class<? extends MetaDataFieldProperty> metaDataFieldProperty) {
        return this.metaDataFieldPropertyManager.hasProperty(metaDataFieldProperty);
    }

    @Override
    public <T extends MetaDataFieldProperty> T getProperty(Class<T> metaDataFieldProperty) {
        return this.metaDataFieldPropertyManager.getProperty(metaDataFieldProperty);
    }

    @Override
	public String toString() {
		return "DefaultMetaDataField [name=" + name + ", model=" + model.getClass() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultMetaDataField other = (DefaultMetaDataField) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
