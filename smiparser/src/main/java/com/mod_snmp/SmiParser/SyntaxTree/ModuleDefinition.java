//
// Generated by JTB 1.2.2++
//

package com.mod_snmp.SmiParser.SyntaxTree;

/**
 * Grammar production:
 * <PRE>
 * moduleIdentifier -> ModuleIdentifier()
 * nodeToken -> &lt;DEFINITIONS_T&gt;
 * assign_token -> &lt;ASSIGN_T&gt;
 * nodeToken2 -> &lt;BEGIN_T&gt;
 * exports_list -> [ &lt;EXPORTS_T&gt; ExportSymbols() &lt;SEMI_COLON_T&gt; ]
 * import_module_list -> [ &lt;IMPORTS_T&gt; ( SymbolsFromModule() )* &lt;SEMI_COLON_T&gt; ]
 * assignmentList -> ( Assignment )*
 * nodeToken3 -> &lt;END_T&gt;
 * </PRE>
 */
public class ModuleDefinition implements Node {
    private Node parent;
    public ModuleIdentifier moduleIdentifier;
    public NodeList exports_list;
    public NodeList import_module_list;
    public NodeList assignmentList;

    public ModuleDefinition(ModuleIdentifier n0, NodeList n1, NodeList n2, NodeList n3) {
        moduleIdentifier = n0;
        if ( moduleIdentifier != null ) moduleIdentifier.setParent(this);
        exports_list = n1;
        if ( exports_list != null ) exports_list.setParent(this);
        import_module_list = n2;
        if ( import_module_list != null ) import_module_list.setParent(this);
        assignmentList = n3;
        if ( assignmentList != null ) assignmentList.setParent(this);
    }
    public int line() {
        return moduleIdentifier.line();
    }
    public void accept(com.mod_snmp.SmiParser.Visitor.Visitor v) {
        v.visit(this);
    }
    public Object accept(com.mod_snmp.SmiParser.Visitor.ObjectVisitor v, Object argu) {
        return v.visit(this,argu);
    }
    public void setParent(Node n) { parent = n; }
    public Node getParent()       { return parent; }
}

