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
package org.spin.eca59.engine.parallel;

import java.util.Properties;

import org.spin.eca59.concept.PayrollConcept;
import org.spin.eca59.employee.PayrollEmployee;
import org.spin.eca59.engine.EngineHelper;
import org.spin.eca59.engine.PayrollEngine;
import org.spin.eca59.payroll_process.PayrollProcess;
import org.spin.eca59.rule.RuleContext;

/**
 * 	A context for parallel processing
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class ParallelContext implements RuleContext {

	public ParallelContext(PayrollEngine engine, PayrollProcess process, PayrollEmployee employee, PayrollConcept concept, String transactionName) {
		this.engine = engine;
		this.process = process;
		this.employee = employee;
		this.concept = concept;
		this.transactionName = transactionName;
		this.helper = new ParallelHelper(this);
	}
	
	private PayrollEngine engine;
	private PayrollProcess process;
	private PayrollEmployee employee;
	private PayrollConcept concept;
	private String transactionName;
	private ParallelHelper helper;
	
	@Override
	public PayrollEngine getEngine() {
		return engine;
	}
	
	@Override
	public PayrollProcess getCurrentProcess() {
		return process;
	}

	@Override
	public PayrollEmployee getCurrentEmployee() {
		return employee;
	}

	@Override
	public PayrollConcept getCurrentConcept() {
		return concept;
	}

	@Override
	public EngineHelper getEngineHelper() {
		return helper;
	}

	@Override
	public String getTransactionName() {
		return transactionName;
	}

	@Override
	public Properties getContext() {
		return process.getContext();
	}
}
