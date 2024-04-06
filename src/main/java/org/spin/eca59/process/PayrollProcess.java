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
package org.spin.eca59.process;

import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPeriod;
import org.eevolution.hr.model.MHRPayroll;
import org.eevolution.hr.model.MHRPeriod;
import org.eevolution.hr.model.MHRProcess;

/**
 * 	Contract for payroll process definition values
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class PayrollProcess {
	private int id;
	private int businessPartnerId;
	private Timestamp validFrom;
	private Timestamp validTo;
	private int payrollId;
	private String payrollValue;
	private int departmentId;
	private int jobId;
	private String documentNo;
	private String name;
	
	public static PayrollProcess newInstance(MHRProcess sourceProcess) {
		return new PayrollProcess(sourceProcess);
	}
	
	private PayrollProcess(MHRProcess process) {
		if(process == null) {
			throw new AdempiereException("@HR_Concept_ID@ @IsMandatory@");
		}
		id = process.getHR_Process_ID();
		documentNo = process.getDocumentNo();
		name = process.getName();
		MHRPeriod payrollPeriod;
		if (process.getHR_Period_ID() > 0) {
			payrollPeriod = MHRPeriod.getById(process.getCtx(),  process.getHR_Period_ID(), null);
		} else {
			payrollPeriod = new MHRPeriod(process.getCtx() , 0 , null);
			MPeriod period = MPeriod.get(process.getCtx(),  process.getDateAcct(), process.getAD_Org_ID(), null);
			if(period != null) {
				payrollPeriod.setStartDate(period.getStartDate());
				payrollPeriod.setEndDate(period.getEndDate());
			} else {
				payrollPeriod.setStartDate(process.getDateAcct());
				payrollPeriod.setEndDate(process.getDateAcct());
			}
		}
		//	
		validFrom = payrollPeriod.getStartDate();
		validTo = payrollPeriod.getEndDate();
		payrollId = process.getHR_Payroll_ID();
		if(payrollId > 0) {
			MHRPayroll payroll = MHRPayroll.getById(process.getCtx(), process.getHR_Payroll_ID(), null);
			if(payroll != null) {
				payrollValue = payroll.getValue();
			}
		}
		departmentId = process.getHR_Department_ID();
		jobId = process.getHR_Job_ID();
		businessPartnerId = process.getC_BPartner_ID();
	}

	public int getId() {
		return id;
	}

	public int getBusinessPartnerId() {
		return businessPartnerId;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public int getPayrollId() {
		return payrollId;
	}

	public String getPayrollValue() {
		return payrollValue;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public int getJobId() {
		return jobId;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public String getName() {
		return name;
	}
}