//
// Generated by JTB 1.2.2++
//

package com.mod_snmp.smiparser.syntaxtree;

/**
 * Represents an grammar optional node, e.g. ( A )? or [ A ]
 */
public class NodeOptional implements Node {
    public NodeOptional() {
        node = null;
    }

    public NodeOptional(Node n) {
        addNode(n);
    }

    public void addNode(Node n)  {
        if ( node != null)                // Oh oh!
            throw new Error("Attempt to set optional node twice");

        node = n;
        n.setParent(this);
    }
    public String toString() {
        if (present()) {
            return node.toString();
        }
        return "";
    }
    public int line() {
        if (present()) {
            return node.line();
        }
        return 0;
    }
    public void accept(com.mod_snmp.smiparser.visitor.Visitor v) {
        v.visit(this);
    }
    public Object accept(com.mod_snmp.smiparser.visitor.ObjectVisitor v, Object argu) {
        return v.visit(this,argu);
    }
    public boolean present()   { return node != null; }

    public void setParent(Node n) { parent = n; }
    public Node getParent()       { return parent; }

    private Node parent;
    public Node node;
}

