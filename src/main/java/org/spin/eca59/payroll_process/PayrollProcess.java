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
package org.spin.eca59.payroll_process;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_HR_Concept;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPeriod;
import org.compiere.model.Query;
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
	private Timestamp dateAcct;
	private Timestamp validFrom;
	private Timestamp validTo;
	private int payrollId;
	private String payrollValue;
	private int departmentId;
	private int jobId;
	private String documentNo;
	private String name;
	private int periodNo;
	private int periodId;
	private int currencyId;
	private int clientId;
	private int conversionTypeId;
	private int organizationId;
	private MHRProcess process;
	private Properties context;
	private List<Integer> concepts = new ArrayList<Integer>();
	
	public static PayrollProcess newInstance(MHRProcess sourceProcess) {
		return new PayrollProcess(sourceProcess);
	}
	
	private PayrollProcess(MHRProcess process) {
		if(process == null) {
			throw new AdempiereException("@HR_Concept_ID@ @IsMandatory@");
		}
		this.process = process;
		context = process.getCtx();
		id = process.getHR_Process_ID();
		documentNo = process.getDocumentNo();
		currencyId = process.getC_Currency_ID();
		conversionTypeId = process.getC_ConversionType_ID();
		clientId = process.getAD_Client_ID();
		organizationId = process.getAD_Org_ID();
		dateAcct = process.getDateAcct();
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
		periodId = process.getHR_Period_ID();
		periodNo = payrollPeriod.getPeriodNo();
		//	
		validFrom = payrollPeriod.getStartDate();
		validTo = payrollPeriod.getEndDate();
		payrollId = process.getHR_Payroll_ID();
		if(payrollId > 0) {
			MHRPayroll payroll = MHRPayroll.getById(process.getCtx(), process.getHR_Payroll_ID(), null);
			if(payroll != null) {
				payrollValue = payroll.getValue();
				concepts = getConceptsFromPayroll();
			}
		}
		departmentId = process.getHR_Department_ID();
		jobId = process.getHR_Job_ID();
		businessPartnerId = process.getC_BPartner_ID();
	}
	
	private List<Integer> getConceptsFromPayroll() {
		return new Query(getContext(), I_HR_Concept.Table_Name, "EXISTS(SELECT 1 FROM HR_PayrollConcept c "
				+ "WHERE c.HR_Payroll_ID = ? "
				+ "AND c.HR_Concept_ID = HR_Concept.HR_Concept_ID "
				+ "AND c.IsActive = 'Y')", null)
				.setParameters(getPayrollId())
				.setOnlyActiveRecords(true)
				.getIDsAsList();
	}
	
	public MHRProcess getProcess() {
		return process;
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

	public int getPeriodNo() {
		return periodNo;
	}

	public int getPeriodId() {
		return periodId;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public int getConversionTypeId() {
		return conversionTypeId;
	}

	public Timestamp getDateAcct() {
		return dateAcct;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public int getClientId() {
		return clientId;
	}

	public Properties getContext() {
		return context;
	}

	public List<Integer> getConcepts() {
		return concepts;
	}

	@Override
	public String toString() {
		return "PayrollProcess [id=" + id + ", payrollValue=" + payrollValue + ", documentNo=" + documentNo + ", name="
				+ name + "]";
	}
}
