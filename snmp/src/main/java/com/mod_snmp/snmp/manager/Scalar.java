package com.mod_snmp.snmp.manager;
/* Copyright 2000-2013 Harrie Hazewinkel. All rights reserved.*/

import com.mod_snmp.snmp.protocol.SnmpMessageException;
import com.mod_snmp.snmp.protocol.SnmpPdu;
import com.mod_snmp.snmp.values.SnmpValueException;
import com.mod_snmp.snmp.values.Varbind;
import com.mod_snmp.snmp.values.VarbindList;

/**
 * An Application Programmer Interface class for the SnmpManager that is
 * provided as a base for management applications generated by a MIB compiler.
 * In perticular the MIB compiler that will be included in the SnmpToolBox.
 * The class provides generic methods to access scalar type instance
 * at a remote SNMP agent.
 * The 'core-' invoke the SNMP requests and check whether the received
 * PDUs from the remote agent contains errors.
 */
public class Scalar {
    /**
     * The SNMP manager contains all info of the remote SNMP agent.
     */
    private SnmpManager manager;
    /**
     * The managed instance varbind.
     */
    private Varbind requested;
    public Varbind result;

    /**
     * Default constructor for the management application
     * for a specific SnmpManager.
     * @param mgr The SNMP manager to be used for the communication
     * with the remote SNMP agent.
     * @param inst The managed instance.
     */
    public Scalar(SnmpManager mgr, ManagedInstance inst)
                                                throws SnmpValueException {
        manager = mgr;
        requested = inst.createVarbind();
    }

    /**
     * This function will retrieve the next instance.
     * @throws SnmpValueException the object identifier cannot be created
     * for the varbind.
     * @throws SnmpManagerException 
     */
    private Varbind coreGetNext(Varbind vb) throws
                        SnmpManagerException {
        try {
            VarbindList vbl = new VarbindList();
            vbl.addVarbind(vb);
            SnmpPdu pdu = manager.invokeSnmpGetNext(vbl);
            vbl = pdu.getVarbindList();
            if (!pdu.isErrorStatusSet()) {
                return vbl.varbindAt(0);
            }
            throw new SnmpManagerException(pdu);
        } catch (SnmpMessageException e) {
            throw new SnmpManagerException(e.getMessage());
        }
    }
    /**
     * This function will retrieve the next instance.
     * @throws SnmpValueException the object identifier cannot be created
     * for the varbind.
     * @throws SnmpManagerException
     */
    public ManagedInstance getNext() throws
                        SnmpManagerException {
       	return new ManagedInstance(coreGetNext(requested));
    }
    /**
     * This function will retrieve the instance.
     */
    public Varbind coreGet(Varbind vb) throws
                        SnmpManagerException {
        try {
            VarbindList vbl = new VarbindList();
            vbl.addVarbind(vb);
            SnmpPdu pdu = manager.invokeSnmpGet(vbl);
            vbl = pdu.getVarbindList();
            if (pdu.isErrorStatusSet()) {
                throw new SnmpManagerException(
                                 "Error status :" + pdu.getErrorStatus(""));
            }
            return vbl.varbindAt(0);
        } catch (SnmpMessageException e) {
            throw new SnmpManagerException(e.getMessage());
        }
    }
    /**
     * This function will retrieve the instance.
     */
    public ManagedInstance get() throws
                        SnmpValueException, SnmpManagerException {
        return new ManagedInstance(coreGet(requested));
    }
    /**
     * This function will change (set) the instance.
     * @param instance The value to which the instance is set.
     */
    public void coreSet(ManagedInstance instance) {
    }


    public static void main(String args[]) {
        final String sysLocation = new String("1.3.6.1.2.1.1.6");
        try {
            Arguments a = new Arguments("java Snmp.Manager.Scalar");
            a.parse(args);
            Scalar managedObject = new Scalar(a.generateManager(),
                                        new ManagedInstance(sysLocation));
            System.out.println("-------- SNMP-GET --------------------------");            try {
                ManagedInstance instance = managedObject.get();
                System.out.println("Scalar " + instance);
            } catch (SnmpManagerException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("-------- SNMP-GET-NEXT ---------------------");
            try {
                ManagedInstance instance = managedObject.getNext();
                System.out.println("Scalar " + instance);
            } catch (SnmpManagerException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
