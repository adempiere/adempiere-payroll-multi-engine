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
package org.spin.eca59.employee;

import java.sql.Timestamp;
import java.util.Optional;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.util.TimeUtil;
import org.eevolution.hr.model.MHRContract;
import org.eevolution.hr.model.MHREmployee;
import org.eevolution.hr.model.MHRPayroll;

/**
 * 	Employee Definition
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class PayrollEmployee {
	private int id;
	private int businessPartnerId;
	private String value;
	private String taxId;
	private String name;
	private String lastName;
	private String nationalCode;
	private String sSCode;
	private String gender;
	private Timestamp startDate;
	private Timestamp endDate;
	private int payrollId;
	private int organizationId;
	private int organizationTrxId;
	private String payrollValue;
	private int departmentId;
	private int jobId;
	private int contractId;
	private int businessPartnerGroupId;
	private MHREmployee employee;
	private MBPartner businessPartner;
	private MHRContract contract;
	
	public static PayrollEmployee newInstance(MHREmployee sourceEmployee) {
		return new PayrollEmployee(sourceEmployee);
	}
	
	private PayrollEmployee(MHREmployee employee) {
		if(employee == null) {
			throw new AdempiereException("@HR_Concept_ID@ @IsMandatory@");
		}
		MBPartner businessPartner = (MBPartner) employee.getC_BPartner();
		this.employee = employee;
		this.businessPartner = businessPartner;
		id = employee.getHR_Employee_ID();
		businessPartnerId = employee.getC_BPartner_ID();
		businessPartnerGroupId = businessPartner.getC_BP_Group_ID();
		organizationId = employee.getAD_Org_ID();
		organizationTrxId = employee.getAD_OrgTrx_ID();
		value = businessPartner.getValue();
		taxId = businessPartner.getTaxID();
		name = Optional.ofNullable(employee.getName()).orElse(businessPartner.getName());
		lastName = Optional.ofNullable(employee.getName2()).orElse(businessPartner.getName2());
		nationalCode = employee.getNationalCode();
		sSCode = employee.getSSCode();
		gender = businessPartner.getGender();
		startDate = employee.getStartDate();
		endDate = employee.getEndDate() == null ? TimeUtil.getDay(2999, 12, 31) : employee.getEndDate();
		payrollId = employee.getHR_Payroll_ID();
		if(payrollId > 0) {
			MHRPayroll payroll = MHRPayroll.getById(employee.getCtx(), employee.getHR_Payroll_ID(), null);
			if(payroll != null) {
				payrollValue = payroll.getValue();
				contract = MHRContract.getById(employee.getCtx(), payroll.getHR_Contract_ID(), null);
				contractId = contract.getHR_Contract_ID();
			}
		}
		departmentId = employee.getHR_Department_ID();
		jobId = employee.getHR_Job_ID();
	}

	public int getId() {
		return id;
	}

	public int getBusinessPartnerId() {
		return businessPartnerId;
	}

	public String getValue() {
		return value;
	}

	public String getTaxId() {
		return taxId;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public String getsSCode() {
		return sSCode;
	}

	public String getGender() {
		return gender;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
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

	public int getContractId() {
		return contractId;
	}

	public MHREmployee getEmployee() {
		return employee;
	}

	public MBPartner getBusinessPartner() {
		return businessPartner;
	}

	public int getBusinessPartnerGroupId() {
		return businessPartnerGroupId;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public int getOrganizationTrxId() {
		return organizationTrxId;
	}

	public MHRContract getContract() {
		return contract;
	}

	@Override
	public String toString() {
		return "PayrollEmployee [businessPartnerId=" + businessPartnerId + ", value=" + value + ", name=" + name + "]";
	}
}
