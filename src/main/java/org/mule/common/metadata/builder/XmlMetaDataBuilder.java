package org.mule.common.metadata.builder;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import org.mule.common.metadata.XmlMetaDataModel;

import javax.xml.namespace.QName;

public interface XmlMetaDataBuilder<P extends MetaDataBuilder<?>> extends MetaDataBuilder<XmlMetaDataModel> {

	DefaultXmlMetaDataBuilder<P> setSourceUri(URL sourceUrl);

	public DefaultXmlMetaDataBuilder<P> addSchemaStringList(String... schemas);
	
	public DefaultXmlMetaDataBuilder<P> addSchemaStreamList(InputStream... schemaStreams);

	public DefaultXmlMetaDataBuilder<P> addSchemaUrlList(URL... schemaStreams);
	
	public DefaultXmlMetaDataBuilder<P> setEncoding(Charset xmlCharset);
	
	public DefaultXmlMetaDataBuilder<P> setExample(String xmlExample);

    public DefaultXmlMetaDataBuilder<P> setLabel(String label);

    public DefaultXmlMetaDataBuilder<P> setDescription(String description);

    public DefaultXmlMetaDataBuilder<P> setType(QName qname);


}
