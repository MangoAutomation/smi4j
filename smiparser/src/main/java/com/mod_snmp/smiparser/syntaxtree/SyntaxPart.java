//
// Generated by JTB 1.2.2++
//

package com.mod_snmp.smiparser.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * -> &lt;SYNTAX_T&gt;
 * type -> Types()
 * </PRE>
 */
public class SyntaxPart implements Node {
    private Node parent;
    public Type type;

    public SyntaxPart(Type n0) {
        type = n0;
        if ( type != null ) type.setParent(this);
    }
    public String toString() {
        return "SYNTAX" + type.toString();
    }
    public int line() {
        return type.line();
    }
    public void setGenericType(Type type) {
        type.setGenericType(type);
    }
    public Type getGenericType() {
        return type.getGenericType();
    }
    public boolean isGenericType() {
        return type.isGenericType();
    }
    public boolean restrictionPresent() {
        return type.restrictionPresent();
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

