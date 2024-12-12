//
// Generated by JTB 1.2.2++
//

package com.mod_snmp.smiparser.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * identifier -> ValueIdentifier()
 * info -> ObjectInfo() -- For specific type --
 * assign_token -> &lt;ASSIGN_T&gt;
 * assignedValue -> AssignedValue()
 * </PRE>
 */
public class AssignmentObject implements Assignment {
    private Node parent;
    public Identifier identifier;
    public ObjectInfo info;
    public AssignedValue assignedValue;

    public AssignmentObject(Identifier n0, ObjectInfo n1, AssignedValue n2) {
        identifier = n0;
        if ( identifier != null ) identifier.setParent(this);
        info = n1;
        if ( info != null ) info.setParent(this);
        assignedValue = n2;
        if ( assignedValue != null ) assignedValue.setParent(this);
    }
    public AssignmentObject(Identifier n0, ObjectInfo n1, NumericValue n2) {
        identifier = n0;
        if ( identifier != null ) identifier.setParent(this);
        info = n1;
        if ( info != null ) info.setParent(this);
        assignedValue = new AssignedValue(new NodeList(new OidValue(n2)));
        if ( assignedValue != null ) assignedValue.setParent(this);
    }

    public int line() {
        return identifier.line();
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
