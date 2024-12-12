package com.mod_snmp.smiparser.errorhandler;
/* Copyright 2000-2013 Harrie Hazewinkel. All rights reserved.*/
/**
 * Message
 */
import com.mod_snmp.smiparser.grammar.Token;
import com.mod_snmp.smiparser.syntaxtree.Node;

public class Message {
    /**
     * The level of the message.
     */
    public static final int EMERG   = 0;
    public static final int ALERT   = 1;
    public static final int CRIT    = 2;
    public static final int ERROR   = 3;
    public static final int WARNING = 4;
    public static final int NOTICE  = 5;
    public static final int INFO    = 6;
    public static final int DEBUG   = 7;
    /**
     * Message parameters.
     */
    protected int level;
    protected int line;
    protected String msg;

    public Message(int linenr, String message) {
        this.level = ERROR;
        this.line = linenr;
        this.msg = message;
    }
    public Message(int level, int linenr, String message) {
        this.level = level;
        this.line = linenr;
        this.msg = message;
    }
    static public Message emerg(Node item, String message) {
        Message m = emerg(item.line(), "'" + item + "' " + message);
    	return m;
    }
    static public Message emerg(Token t, String item, String message) {
        Message m = emerg(t.beginLine, "'" + item + "' " + message);
    	return m;
    }
    static public Message emerg(int linenr, String message) {
    	Message m = new Message(EMERG, linenr, message);
    	MessageList.list.add(m);
    	return m;
    }
    static public Message alert(Node item, String message) {
        Message m = alert(item.line(), "'" + item + "' " + message);
    	return m;
    }
    static public Message alert(Token t, String item, String message) {
        Message m = alert(t.beginLine, "'" + item + "' " + message);
    	return m;
    }
    static public Message alert(int linenr, String message) {
        Message m = new Message(ALERT, linenr, message);
    	MessageList.list.add(m);
    	return m;
    }
    static public Message critical(Node item, String message) {
        Message m = critical(item.line(), "'" + item + "' " + message); 
    	return m;
    }
    static public Message critical(Token t, String item, String message) {
        Message m = critical(t.beginLine, "'" + item + "' " + message);
    	return m;
    }
    static public Message critical(int linenr, String message) {
        Message m = new Message(CRIT, linenr, message);
    	MessageList.list.add(m);
    	return m;
    }
    static public Message error(Node item, String message) {
        Message m = error(item.line(), "'" + item + "' " + message);
    	return m;
    }
    static public Message error(Token t, String item, String message) {
        Message m = error(t.beginLine, "'" + item + "' " + message);
    	return m;
    }
    static public Message error(int linenr, String message) {
        Message m = new Message(ERROR, linenr, message);
        MessageList.list.add(m);
    	return m;
    }
    static public Message warning(Node item, String message) {
        Message m = warning(item.line(), "'" + item + "' " + message); 
    	return m;
    }
    static public Message warning(Token t, String item, String message) {
        Message m = warning(t.beginLine, "'" + item + "' " + message);
    	return m;
    }
    static public Message warning(int linenr, String message) {
        Message m = new Message(WARNING, linenr, message);
        MessageList.list.add(m);
    	return m;
    }
    static public Message notice(Node item, String message) {
        Message m = notice(item.line(), "'" + item + "' " + message); 
    	return m;
    }
    static public Message notice(Token t, String item, String message) {
        Message m = notice(t.beginLine, "'" + item + "' " + message); 
    	return m;
    }
    static public Message notice(int linenr, String message) {
        Message m = new Message(NOTICE, linenr, message);
        MessageList.list.add(m);
    	return m;
    }
    static public Message debug(Node item, String message) {
        Message m = debug(item.line(), "'" + item + "' " + message);
    	return m;
    }
    static public Message debug(Token t, String item, String message) {
        Message m = debug(t.beginLine, "'" + item + "' " + message); 
    	return m;
    }
    static public Message debug(int linenr, String message) {
        Message m = new Message(DEBUG, linenr, message);
        MessageList.list.add(m);
    	return m;
    }

    public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
    public String toString(){
    	StringBuilder b = new StringBuilder();
    	switch(level){
    	case EMERG:
    		b.append("Emergency ");
    		break;
    	case ALERT:
    		b.append("Alert ");
    		break;
    	case CRIT:
    		b.append("Critical ");
    		break;
    	case ERROR:
    		b.append("Error ");
    		break;
    	case WARNING:
    		b.append("Warning: ");
    		break;
    	case NOTICE:
    		b.append("Notice ");
    		break;
    	case INFO:
    		b.append("Info ");
    		break;
    	case DEBUG:
    		b.append("Debug ");
    		break;
    	}
    	
    	b.append("at line " + line + " --> ");
    	b.append(msg);
    	
    	return b.toString();
    }
    
    public void print() {
        System.err.println(level +" : line " + line + ": " + msg);
    }
}
