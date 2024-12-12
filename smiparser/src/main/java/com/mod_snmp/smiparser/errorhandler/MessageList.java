package com.mod_snmp.smiparser.errorhandler;
import java.util.ArrayList;

/**
 * An extension to Vector in which a 'Message' gets stored.
 * The 'Message' is created from SmiExceptions.
 */
public class MessageList extends ArrayList<Message> {

	private static final long serialVersionUID = 1L;
	static public MessageList list = new MessageList();

    /**
     * Print function of the message list.
     */
    public void print() {
        if (size() > 0) {
            for (int i =  size() - 1; i >= 0 ; i--) {
                ((Message)get(i)).print();
            }
            System.err.println("Output may be flawed: " + size()
                                                + " messages");
        }
    }
    /**
     * Add a parsing message in the message list by line number order.
     */
    public boolean add(Message msg) {
        int i = 0;
        while (i < size()) {
            if (msg.line > ((Message)get(i)).line) {
                break;
            }
            i++;
        }
        add(i, msg);
        return true;
    }
    /**
     * Wrapper to see whether messages are in the list.
     */
    public boolean hasErrors() {
        return (0 != size());
    }
}
