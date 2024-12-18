//
// Generated by JTB 1.2.2++
//

package com.mod_snmp.smiparser.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * identifier -> PredefinedMacros() 
 *               |
 *               TypeIdentifier()
 * -> &lt;MACRO_T&gt;
 * -> &lt;ASSIGN_T&gt;
 * -> &lt;BEGIN_T&gt;
 * macro -> MacroBody()
 * -> &lt;END_T&gt;
 * </PRE>
 */
public class AssignmentMacro implements Assignment {
    private Node parent;
    public Identifier identifier;
    public MacroBody macro;

    public AssignmentMacro(Identifier n0,
                           MacroBody n1) {
        identifier = n0;
        if ( identifier != null ) identifier.setParent(this);
        macro = n1;
        if ( macro != null ) macro.setParent(this);
    }
    public int line() {
        return identifier.line();
    }   

    public boolean IsPredefined() {
        return identifier.getKind() == Identifier.KEYWORD;
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

