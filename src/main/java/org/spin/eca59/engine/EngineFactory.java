/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2019 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.eca59.engine;

import java.lang.reflect.Constructor;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSysConfig;
import org.compiere.util.CLogger;
import org.compiere.util.Util;
import org.eevolution.hr.model.MHRProcess;

/**
 * Get Class from device type, used for handler
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * @return
 * @return Class<?>
 */
public class EngineFactory {
	
	/**
	 * Payroll Engine Implementation
	 */
	public static final String ECA59_PAYROLL_ENGINE = "ECA59_PAYROLL_ENGINE";
	/** Logger */
	private static CLogger log = CLogger.getCLogger(EngineFactory.class);
	
	/**
	 * Get Class from implementation, used for handler
	 * @return
	 * @return Class<?>
	 */
	private Class<?> getHandlerClass(int clientId) {
		String className = MSysConfig.getValue(ECA59_PAYROLL_ENGINE, DefaultImplementation.class.getName(), clientId);
		//	Validate null values
		if(Util.isEmpty(className)) {
			return null;
		}
		try {
			Class<?> clazz = Class.forName(className);
			//	Make sure that it is a PO class
			Class<?> superClazz = clazz.getSuperclass();
			//	Validate super class
			while (superClazz != null) {
				if (superClazz == AbstractImplementation.class) {
					log.fine("Use: " + className);
					return clazz;
				}
				//	Get Super Class
				superClazz = superClazz.getSuperclass();
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		//	
		log.finest("Not found: " + className);
		return null;
	}	//	getHandlerClass
	
	/**
	 * Get Report export instance
	 * @return
	 */
	public AbstractImplementation getInstance(MHRProcess process) throws Exception {
		if(process == null) {
			throw new AdempiereException("@HR_Process_ID@ @IsMandatory@");
		}
		//	Load it
		//	Get class from parent
		Class<?> clazz = getHandlerClass(process.getAD_Client_ID());
		//	Not yet implemented
		if (clazz == null) {
			log.log(Level.INFO, "Using Default Payroll Implementation");
			return new AbstractImplementation(process);
		}
		//	
		Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{MHRProcess.class});
		//	new instance
		return (AbstractImplementation) constructor.newInstance(new Object[] {process});
	}
}
