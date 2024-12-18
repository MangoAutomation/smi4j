package com.mod_snmp.smiparser.syntaxtree;
/* Copyright 2000-2013 Harrie Hazewinkel. All rights reserved.*/


/**
 * Grammar production:
 * <PRE>
 * statusPart -> StatusPart()
 * descriptionPart -> DescriptionPart()
 * referencePart -> ReferencePart()
 * moduleCompliancePart -> ( &lt;MODULE_T&gt; [ ModuleIdentifier() ]
 *                           [ MandatoryPart() ]
 *                           ( CompliancePart() )*
 *                          )+ 
 * moduleCompliancePart -> ModuleCompliancePart()
 * </PRE>
 */
public class ObjectInfoModuleCompliance implements ObjectInfo {
    private Node parent;
    private int level = 0;
    public StatusPart statusPart;
    public DescriptionPart descriptionPart;
    public ReferencePart referencePart;
    public NodeList moduleCompliancePart;

    public ObjectInfoModuleCompliance(StatusPart n1,
                                                DescriptionPart n2,
                                                ReferencePart n3,
                                                NodeList n4) {
        statusPart = n1;
        if ( statusPart != null ) statusPart.setParent(this);
        descriptionPart = n2;
        if ( descriptionPart != null ) descriptionPart.setParent(this);
        referencePart = n3;
        if ( referencePart != null ) referencePart.setParent(this);
        moduleCompliancePart = n4;
        if ( moduleCompliancePart != null ) moduleCompliancePart.setParent(this);
    }
    public int line() {
        return statusPart.line();
    }
    public boolean isConceptualRow() {
        return false;
    }
    public void setIndexLevel(int i) {
    }
    public int getIndexLevel() {
        return 0;
    }
    public void accept(com.mod_snmp.smiparser.visitor.Visitor v) {
        v.visit(this);
    }
    public Object accept(com.mod_snmp.smiparser.visitor.ObjectVisitor v, Object argu) {
        return v.visit(this,argu);
    }
    public void setParent(Node n) { parent = n; }
    public Node getParent()       { return parent; }
}

