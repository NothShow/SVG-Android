package com.github.megatronking.svg.generator.vector.parser;


import com.github.megatronking.svg.generator.vector.model.Group;
import com.github.megatronking.svg.generator.vector.model.Path;
import com.github.megatronking.svg.generator.vector.model.Vector;
import com.github.megatronking.svg.generator.vector.model.VectorConstants;
import com.github.megatronking.svg.generator.xml.ChildrenElementParser;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * Build vector's groups and paths values from the children of the element.
 *
 * @author Megatron King
 * @since 2016/9/1 11:00
 */

public class VectorElementParser extends ChildrenElementParser<Vector> {

    public VectorElementParser() {
        super(VectorParserImpl.VECTOR_ATTRIBUTE_PARSER);
    }

    @Override
    protected void parseChild(Element childElement, Vector vector) throws DocumentException {
        Group rootGroup = new Group(null);
        if (VectorConstants.TAG_GROUP.equals(childElement.getName())) {
            Group childGroup = new Group(rootGroup);
            vector.children.add(childGroup);
            VectorParserImpl.GROUP_ELEMENT_PARSER.parse(childElement, childGroup);
        }
        if (VectorConstants.TAG_PATH.equals(childElement.getName())) {
            Path childPath = new Path(rootGroup);
            vector.children.add(childPath);
            VectorParserImpl.PATH_ATTRIBUTE_PARSER.parse(childElement, childPath);
        }
        if (VectorConstants.TAG_CLIP_PATH.equals(childElement.getName())) {
            Path childPath = new Path(rootGroup);
            vector.children.add(childPath);
            VectorParserImpl.CLIP_PATH_ATTRIBUTE_PARSER.parse(childElement, childPath);
        }
    }
}
