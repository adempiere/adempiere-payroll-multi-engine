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
package org.spin.eca59.rule;

import java.lang.reflect.Constructor;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MRule;
import org.compiere.util.CLogger;
import org.spin.util.RuleEngineUtil;

/**
 * Get Class from rule
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * @return
 * @return Class<?>
 */
public class RuleRunnerFactory {
	
	/** Logger */
	private static CLogger log = CLogger.getCLogger(RuleRunnerFactory.class);
	
	private static Class<?> getRuleRunnerClass(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			if(RuleRunner.class.isAssignableFrom(clazz)) {
                return clazz;
            }
			//	Make sure that it is a PO class
			Class<?> superClazz = clazz.getSuperclass();
			//	Validate super class
			while (superClazz != null) {
				if (superClazz == RuleRunner.class) {
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
	 * Get Rule Runner Instance
	 * @return
	 */
	public static RuleRunner getRuleRunnerInstance(MRule rule) throws Exception {
		if(rule == null) {
			throw new AdempiereException("@AD_Rule_ID@ @IsMandatory@");
		}
		String className = RuleEngineUtil.getCompleteClassName(rule);
		//	Load it
		Class<?> clazz = getRuleRunnerClass(className);
		//	Not yet implemented
		if (clazz != null) {
			Constructor<?> constructor = clazz.getDeclaredConstructor();
			return (RuleRunner) constructor.newInstance();
		}
		//	new instance
		return null;
	}
}
