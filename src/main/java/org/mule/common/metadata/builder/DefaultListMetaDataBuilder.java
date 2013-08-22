/**
 *
 */
package org.mule.common.metadata.builder;

import org.mule.common.metadata.DefaultListMetaDataModel;
import org.mule.common.metadata.ListMetaDataModel;

public class DefaultListMetaDataBuilder<P extends MetaDataBuilder> implements ListMetaDataBuilder<P>
{

    private MetaDataBuilder innerBuilder;
    private P parentBuilder;


    DefaultListMetaDataBuilder(P parentBuilder)
    {
        this.parentBuilder = parentBuilder;
    }

    @Override
    public PojoMetaDataBuilder<ListMetaDataBuilder<P>> ofPojo(Class<?> pojoClass)
    {
        innerBuilder = new DefaultPojoMetaDataBuilder<ListMetaDataBuilder<?>>(pojoClass,this);
        return (PojoMetaDataBuilder) innerBuilder;
    }

    @Override
    public DynamicObjectBuilder<ListMetaDataBuilder<P>> ofDynamicObject(String name)
    {
        innerBuilder = new DefaultDynamicObjectBuilder<ListMetaDataBuilder<?>>(name,this);
        return (DynamicObjectBuilder) innerBuilder;
    }

    @Override
    public ListMetaDataBuilder<ListMetaDataBuilder<P>> ofList()
    {
        innerBuilder = new DefaultListMetaDataBuilder<ListMetaDataBuilder<?>>(this);
        return (ListMetaDataBuilder) innerBuilder;
    }

    @Override
    public P endList()
    {
        return parentBuilder;
    }

    @Override
    public ListMetaDataModel build()
    {
        return new DefaultListMetaDataModel(innerBuilder.build());
    }
}
