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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.eevolution.hr.model.MHRConcept;
import org.eevolution.hr.model.MHREmployee;
import org.eevolution.hr.model.MHRMovement;
import org.eevolution.hr.model.MHRPayroll;
import org.eevolution.hr.model.MHRPayrollConcept;
import org.eevolution.hr.model.MHRPeriod;
import org.eevolution.hr.model.MHRProcess;
import org.spin.eca59.concept.PayrollConcept;
import org.spin.eca59.employee.PayrollEmployee;
import org.spin.eca59.payroll_process.PayrollProcess;

/**
 * Default payroll process implementation
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class OptimizedParallelProcessing extends AbstractImplementation {

	/**	Static Logger	*/
	private static CLogger logger = CLogger.getCLogger (OptimizedParallelProcessing.class);
	/** HR_Concept_ID->MHRMovement */
	public Hashtable<Integer, MHRMovement> movements = new Hashtable<Integer, MHRMovement>();
	/* stack of concepts executing rules - to check loop in recursion */
	private List<MHRConcept> activeConceptRule = new ArrayList<MHRConcept>();
	public OptimizedParallelProcessing(MHRProcess process) {
		super(process);
	}
	
	@Override
	public boolean run() {
		deleteMovements();
		logger.info("createParallelMovements #");
		long startTime = System.currentTimeMillis();
		List<Integer> payrollConcepts = getPayrollConceptIds();
		List<Integer> employees = getEmployeeIds();
		PayrollProcess payrollProcess = PayrollProcess.newInstance(getProcess());
		employees.parallelStream().forEach(businessPartnerId -> {
			MBPartner businessPartner = new MBPartner(getCtx(), businessPartnerId, null);
			logger.info("Employee # " + businessPartner.getValue() + " - " + businessPartner.getName() +  " " + businessPartner.getName2());
			long employeeStartTime = System.currentTimeMillis();
			PayrollEmployee payrollEmployee = PayrollEmployee.newInstance(MHREmployee.getActiveEmployee(getProcess().getCtx(), businessPartnerId, null));
			Trx.run(transactionName -> {
				payrollConcepts.forEach(payrollConceptId -> {
					MHRPayrollConcept payrollConceptReference = new MHRPayrollConcept(getCtx(), payrollConceptId, null);
					MHRConcept concept = MHRConcept.getById(getCtx(), payrollConceptReference.getHR_Concept_ID(), null);
					PayrollConcept payrollConcept = PayrollConcept.newInstance(concept);
					boolean printed = concept.isPrinted() || concept.isPrinted();
					MHRMovement movement = movements.get(concept.get_ID()); // as it's now recursive, it can happen that the concept is already generated
					if (movement == null) {
						createMovementFromConcept(concept, printed);
						movement = movements.get(concept.get_ID());
						movement.setHR_Payroll_ID(payrollProcess.getPayrollId());
						movement.setHR_PayrollConcept_ID(payrollConceptReference.getHR_PayrollConcept_ID());
						movement.setPeriodNo(payrollProcess.getPeriodNo());
					}
				});
			});
			logger.info("Employee # " + businessPartner.getValue() + " - " + businessPartner.getName() +  " " + businessPartner.getName2() + " Time elapsed: " + TimeUtil.formatElapsed(System.currentTimeMillis() - employeeStartTime));
		});
		logger.info("Calculation for createParallelMovements # Time elapsed: " + TimeUtil.formatElapsed(System.currentTimeMillis() - startTime));
		return super.run();
	}
	
	public Properties getCtx() {
		return getProcess().getCtx();
	}
	
	/**
	 * Method use to create a movemeningBuffer whereClause = new StringBuffer();
		StringBuffer orderByClause = new StringBuffer(MHRAttribute.COLUMNNAME_ValidFrom).append(ORDERVALUE);
		//	Add for updated
		orderByClause.append(", " + MHRAttribute.COLUMNNAME_Updated).append(ORDERVALUE);t
	 * @param concept
	 * @param isPrinted
	 * @return
	 */
	private void createMovementFromConcept(MHRConcept concept, boolean isPrinted) {
		logger.info("Calculating -> Concept "+ concept.getValue() + " -> " + concept.getName());
		//	TODO: Implement it
//		MHRAttribute attribute = MHRAttribute.getByConceptAndEmployee(concept , employee, getHR_Payroll_ID(),  dateFrom ,dateTo);
//		if (attribute == null || concept.isManual()) {
//			createDummyMovement(concept);
//			return;
//		}

		logger.info("Concept : " + concept.getName());
//		MHRMovement movement = createMovement(concept, attribute, isPrinted);
//		if (MHRConcept.TYPE_RuleEngine.equals(concept.getType()))
//		{
//			logger.info("Processing -> Rule to Concept " + concept.getValue());
//			if (activeConceptRule.contains(concept)) {
//				throw new AdempiereException("Recursion loop detected in concept " + concept.getValue());
//			}
//			activeConceptRule.add(concept);
//			Object result = executeScript(concept , attribute.getAD_Rule_ID(), attribute.getColumnType());
//			activeConceptRule.remove(concept);
//			movement.setColumnValue(result); // double rounded in MHRMovement.setColumnValue
//			if (description != null)
//				movement.setDescription(description.toString());
//		}
//		movement.setProcessed(true);
//		movements.put(concept.getHR_Concept_ID(), movement);
	}
	
	private void createDummyMovement(MHRConcept concept) {
		logger.info("Skip concept "+concept+" - attribute not found");
		MHRMovement dummyMovement = new MHRMovement (getCtx(), 0, concept.get_TrxName());
		dummyMovement.setSeqNo(concept.getSeqNo());
		dummyMovement.setIsManual(true); // to avoid landing on movement table
		movements.put(concept.getHR_Concept_ID(), dummyMovement);
		return;
	}
	
	private int deleteMovements() {
		AtomicInteger no = new AtomicInteger();
		Trx.run(transactionName -> {
			// RE-Process, delete movement except concept type Incidence
			no.set(DB.executeUpdateEx("DELETE FROM HR_Movement m WHERE HR_Process_ID=? AND IsManual<>?", new Object[]{getProcess().getHR_Process_ID(), true}, transactionName));
			logger.info("Movements Deleted #" + no);
		});
		return no.get();
	}
	
	private List<Integer> getEmployeeIds() {
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		whereClause.append("EXISTS(SELECT 1 FROM HR_Employee e " + 
				"WHERE e.C_BPartner_ID = C_BPartner.C_BPartner_ID " +
				"AND (e.EmployeeStatus = ? OR EmployeeStatus IS NULL) ");
		MHRProcess process = getProcess();
		//	For Active
		params.add(MHREmployee.EMPLOYEESTATUS_Active);
		//	look if it is a not regular payroll
		MHRPayroll payroll = MHRPayroll.getById(process.getCtx(), process.getHR_Payroll_ID(), null);
		// This payroll not content periods, NOT IS a Regular Payroll > ogi-cd 28Nov2007
		if(process.getHR_Payroll_ID() != 0 && process.getHR_Period_ID() != 0 && !payroll.isIgnoreDefaultPayroll())
		{
			whereClause.append(" AND (e.HR_Payroll_ID IS NULL OR e.HR_Payroll_ID=?) " );
			params.add(process.getHR_Payroll_ID());
		}
		//	Organization
		if(process.getAD_OrgTrx_ID() != 0) {
			whereClause.append(" AND e.AD_OrgTrx_ID=? " );
			params.add(process.getAD_OrgTrx_ID());
		}
		//	Project
		if(process.getC_Project_ID() != 0) {
			whereClause.append(" AND e.C_Project_ID=? " );
			params.add(process.getC_Project_ID());
		}
		//	Activity
		if(process.getC_Activity_ID() != 0) {
			whereClause.append(" AND e.C_Activity_ID=? " );
			params.add(process.getC_Activity_ID());
		}
		//	Campaign
		if(process.getC_Campaign_ID() != 0) {
			whereClause.append(" AND e.C_Campaign_ID=? " );
			params.add(process.getC_Campaign_ID());
		}
		//	Sales Region
		if(process.getC_SalesRegion_ID() != 0) {
			whereClause.append(" AND e.C_SalesRegion_ID=? " );
			params.add(process.getC_SalesRegion_ID());
		}
		//	Active Record
		whereClause.append(" AND e.IsActive = 'Y' " );
		// HR Period
		if(process.getHR_Period_ID() == 0) {
			whereClause.append(" AND e.StartDate <=? ");
			params.add(process.getDateAcct());
		} else {
			MHRPeriod period = new MHRPeriod(process.getCtx(), process.getHR_Period_ID(), process.get_TrxName());
			whereClause.append(" AND e.StartDate <=? ");
			params.add(period.getEndDate());
			whereClause.append(" AND (e.EndDate IS NULL OR e.EndDate >=?) ");
			params.add(period.getStartDate());
		}
		
		// Selected Department
		if (process.getHR_Department_ID() != 0) {
			whereClause.append(" AND e.HR_Department_ID =? ");
			params.add(process.getHR_Department_ID());
		}		
		// Selected Job add
		if (process.getHR_Job_ID() != 0) {
			whereClause.append(" AND e.HR_Job_ID =? ");
			params.add(process.getHR_Job_ID());
		}
		
		whereClause.append(" ) "); // end select from HR_Employee
		
		// Selected Employee
		if (process.getC_BPartner_ID() != 0) {
			whereClause.append(" AND C_BPartner_ID =? ");
			params.add(process.getC_BPartner_ID());
		}
		
		//client
		whereClause.append(" AND AD_Client_ID =? ");
		params.add(process.getAD_Client_ID());
		//	
		return new Query(process.getCtx(), MBPartner.Table_Name, whereClause.toString(), process.get_TrxName())
								.setParameters(params)
								.setOnlyActiveRecords(true)
								.setOrderBy(MBPartner.COLUMNNAME_Name)
								.getIDsAsList();
	}
	
	private List<Integer> getPayrollConceptIds() {
		MHRProcess process = getProcess();
		return new Query(process.getCtx(), MHRPayrollConcept.Table_Name, MHRPayroll.COLUMNNAME_HR_Payroll_ID+"=?", null)
												.setOnlyActiveRecords(true)
												.setParameters(process.getHR_Payroll_ID())
												.setOrderBy(MHRPayrollConcept.COLUMNNAME_SeqNo)
												.getIDsAsList();
	}
}
