package com.mod_snmp.smiparser.syntaxtree;
/* Copyright 2000-2013 Harrie Hazewinkel. All rights reserved.*/

/**
 * Grammar production:
 * <PRE>
 * -> &lt;REVISION&gt;
 * time -> &lt;UCT_TIME_T&gt;
 * descr -> DescriptionPart();
 * </PRE>
 */
public class Revision implements Node {
    private Node parent;
    public NodeToken time;
    public DescriptionPart descr;

    public Revision(NodeToken n0, DescriptionPart n1) {
        time = n0;
        if ( time != null ) time.setParent(this);
        descr = n1;
        if ( descr != null ) descr.setParent(this);
    }

    public int line() {
        return time.beginLine;
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

