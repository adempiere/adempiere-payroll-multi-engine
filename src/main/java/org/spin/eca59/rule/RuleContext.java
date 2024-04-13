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

import java.util.Properties;

import org.spin.eca59.concept.PayrollConcept;
import org.spin.eca59.employee.PayrollEmployee;
import org.spin.eca59.engine.EngineHelper;
import org.spin.eca59.engine.PayrollEngine;
import org.spin.eca59.payroll_process.PayrollProcess;

/**
 * 	Contract for rule conext, use this to send data to runner
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public interface RuleContext {
	
	/**
	 * Get Payroll Process Engine
	 * @return
	 */
	public PayrollEngine getEngine();
	
	/**
	 * Get Current Process Running
	 * @return
	 */
	public PayrollProcess getCurrentProcess();
	
	/**
	 * Get Current Employee
	 * @return
	 */
	public PayrollEmployee getCurrentEmployee();
	
	/**
	 * Get Current Concept
	 * @return
	 */
	public PayrollConcept getCurrentConcept();
	
	/**
	 * Get Current Engine Helper (This is a helper with current process, employee and concept)
	 * @return
	 */
	public EngineHelper getEngineHelper();
	
	/**
	 * Get context method
	 * @return
	 */
	public Properties getContext();
	
	/**
	 * Get transaction Name
	 * @return
	 */
	public String getTransactionName();
	
	/**
	 * Break Running for employee
	 * @param businessPartnerId
	 */
	public void breakEmployeeRunning();
	
	/**
	 * Breack Running for concept
	 * @param businessPartnerId
	 * @param conceptId
	 */
	public void breakConceptRunning();
	
	/**
	 * Breack Running for process
	 */
	public void breakProcessRunning();
}
