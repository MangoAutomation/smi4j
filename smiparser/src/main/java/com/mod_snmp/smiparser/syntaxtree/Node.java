//
// Generated by JTB 1.2.2++
//

package com.mod_snmp.smiparser.syntaxtree;

/**
 * The interface which all syntax tree classes must implement.
 */
public interface Node extends java.io.Serializable {
   public void accept(com.mod_snmp.smiparser.visitor.Visitor v);
   public Object accept(com.mod_snmp.smiparser.visitor.ObjectVisitor v, Object argu);
   public int line();
   // It is the responsibility of each implementing class to call
   // setParent() on each of its child Nodes.
   public void setParent(Node n);
   public Node getParent();
}
