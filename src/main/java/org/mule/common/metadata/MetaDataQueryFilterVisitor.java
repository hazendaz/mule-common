package org.mule.common.metadata;

import org.mule.common.metadata.datatype.DataType;
import org.mule.common.query.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Visitor used to filter metadata fields
 */
public class MetaDataQueryFilterVisitor implements MetaDataModelVisitor {
    private List<Field> fields;
    private MetaDataModel resultModel;

    public MetaDataQueryFilterVisitor(List<Field> fields) {
        this.fields = fields;
        this.resultModel = new DefaultSimpleMetaDataModel(DataType.VOID);
    }

    @Override
    public void visitPojoModel(PojoMetaDataModel pojoMetaDataModel) {
        resultModel = pojoMetaDataModel;
    }

    @Override
    public void visitListMetaDataModel(ListMetaDataModel listMetaDataModel) {
        MetaDataQueryFilterVisitor child = new MetaDataQueryFilterVisitor(fields);
        listMetaDataModel.getElementModel().accept(child);
        resultModel = new DefaultListMetaDataModel(child.filteringResult().getPayload());
    }

    @Override
    public void visitSimpleMetaDataModel(SimpleMetaDataModel simpleMetaDataModel) {
        resultModel = simpleMetaDataModel;
    }

    @Override
    public void visitStaticMapModel(ParameterizedMapMetaDataModel parameterizedMapMetaDataModel) {
        resultModel = parameterizedMapMetaDataModel;
    }

    @Override
    public void visitXmlMetaDataModel(XmlMetaDataModel xmlMetaDataModel) {
    	resultModel = xmlMetaDataModel;
    }

    @Override
    public void visitUnknownMetaDataModel(UnknownMetaDataModel unknownMetaDataModel)
    {
        resultModel = unknownMetaDataModel;
    }

    @Override
    public void visitDynamicMapModel(DefinedMapMetaDataModel definedMapMetaDataModel) {
        if (fields == null) return;
        Map<String,MetaDataModel> newMapModel = new HashMap<String, MetaDataModel>();
        for(Field f: fields) {
            MetaDataModel fieldModel = definedMapMetaDataModel.getValueMetaDataModel(f.getName());
            if (fieldModel != null) {
                newMapModel.put(f.getName(),fieldModel);
            }
        }
        resultModel = new DefaultQueryResultMetaDataModel(new DefaultDefinedMapMetaDataModel(newMapModel,definedMapMetaDataModel.getName()));
    }

    public MetaData filteringResult() {
        return new DefaultMetaData(resultModel);
    }
}
