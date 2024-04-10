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

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.eevolution.hr.model.MHRAttribute;
import org.eevolution.hr.model.MHRConcept;
import org.eevolution.hr.model.MHRMovement;
import org.spin.eca59.concept.PayrollConcept;

/**
 * 	Contract for rule context, use this to add helper methods
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public interface EngineHelper {
	public String getTransactionName();
	
	public PayrollConcept getCurrentConcept();
	
	/**
	 * Get Monthly Salary
	 * @return
	 */
	public double getMonthlySalary();
	
	/**
	 * Get Daily Salary
	 * @return
	 */
	public double getDailySalary();

	// Helper methods -------------------------------------------------------------------------------

	/**
	 * Helper Method : get the value of the concept
	 * @param conceptValue
	 * @return
	 */
	public double getConcept (String conceptValue); // getConcept

	/**
	 * Helper Method : get the value of the concept string type
	 * @param pconcept
	 * @return String value of concept
	 */
	public String getConceptString (String pconcept); // getConceptString

	/**
	 * Helper Method : get the value of the concept date type
	 * @param conceptValue
	 * @return Timestamp value of concept
	 */
	public Timestamp getConceptDate (String conceptValue); // getConceptDate
	
	/**
	 * Helper Method : get the sum of the concept values, grouped by the concept type
	 * @param typeValue
	 * @return
	 */
	public double getConceptType (String typeValue); // Get Concept Type

	/**
	 * Helper Method : get the sum of the concept values, grouped by the Category
	 * @param categoryValue
	 * @return
	 * @Deprecated
	 */
	public double getConceptGroup (String categoryValue); // getConceptGroup

	/**
	 * Helper Method : get the sum of the concept values, grouped by the Category
	 * @param categoryValue
	 * @return
	 */
	public double getConceptCategory (String categoryValue); // getConceptCategory

	/**
	 * Helper Method : Get Concept [get concept to search key ]
	 * @param listSearchKey Value List
	 * @param amount Amount to search
	 * @param columnParam Number of column to return (1.......8)
	 * @return The amount corresponding to the designated column 'column'
	 */
	public double getList (String listSearchKey, double amount, String columnParam);

	/**
	 * Helper Method : Get Concept [get concept to search key ]
	 * @param listSearchKey
	 * @param from
	 * @param amount
	 * @param columnParam
     * @return The amount corresponding to the designated column 'column'
     */
	public double getList (String listSearchKey, Timestamp from , double amount, String columnParam);

	/**
	 * Helper Method : Get Concept [get concept to search key ]
	 * @param listSearchKey Value List
	 * @from from date to valid list
	 * @param amount Amount to search
	 * @param columnParam Number of column to return (1.......8)
	 * @param columnType can be Amount or null
	 * @return The amount corresponding to the designated column 'column'
	 */
	public double getList (String listSearchKey,Timestamp from, double amount, String columnParam , String columnType); // getList
	
	public MHRAttribute getAttributeInstance(String conceptValue, Timestamp breakDate);
	
	public MHRAttribute getAttributeInstance(String conceptValue);
	
	/**
	 * Get attribute like MHRAttribute
	 * @param concept
	 * @param engineEmployee.getBusinessPartnerId()
	 * @param breakDate
	 * @return
	 */
	public MHRAttribute getAttributeInstance(MHRConcept concept, Timestamp breakDate);
	
	/**
	 * Helper Method : Get Attribute [get Attribute to search key concept ]
	 * @param conceptValue - Value to Concept
	 * @return	Amount of concept, applying to engineEmployee.getEmployee()
	 */ 
	public double getAttribute (String conceptValue); // 
	
	/**
	 * Helper Method : Get Attribute [get Attribute to search key concept and break date]
	 * @param conceptValue
	 * @param breakDate
	 * @return
	 */
	public double getAttribute (String conceptValue, Timestamp breakDate); // 


	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept ]
	 *  @param conceptValue
	 *  @return ServiceDate
	 */ 
	public Timestamp getAttributeDate (String conceptValue); // getAttributeDate
	
	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept and break date]
	 *  @param conceptValue
	 *  @return ServiceDate
	 */ 
	public Timestamp getAttributeDate (String conceptValue, Timestamp breakDate); // getAttributeDate

	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept ]
	 *  @param conceptValue
	 *  @return TextMsg
	 */ 
	public String getAttributeString (String conceptValue); // getAttributeString

	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept and break date]
	 *  @param conceptValue
	 *  @return TextMsg
	 */ 
	public String getAttributeString (String conceptValue, Timestamp breakDate); // getAttributeString
	
	/**
	 * 	Helper Method : Get the number of days between start and end, in Timestamp format
	 *  @param date1 
	 *  @param date2
	 *  @return no. of days
	 */ 
	public int getDays (Timestamp date1, Timestamp date2); // getDays


	/**
	 * 	Helper Method : Get the number of days between start and end, in String format
	 *  @param date1 
	 *  @param date2
	 *  @return no. of days
	 */  
	public  int getDays (String date1, String date2);  // getDays

	/**
	 * 	Helper Method : Get Months, Date in Format Timestamp
	 *  @param startParam
	 *  @param endParam
	 *  @return no. of month between two dates
	 */ 
	public int getMonths(Timestamp startParam,Timestamp endParam); // getMonths

	/**
	 * Helper Method : Concept for a period.
	 * Periods with values of 0 -1 1, etc. actual previous one period, next period
	 * 0 corresponds to actual period.
	 * @param conceptValue concept key(value)
	 * @param period search is done by the period value, it helps to search from previous years
	 */
	public double getConcept (String conceptValue, int period); // getConcept


	/**
	 * Helper Method : Concept for a range from-to in periods.
	 * Periods with values of 0 -1 1, etc. actual previous one period, next period
	 * 0 corresponds to actual period.
	 * @param conceptValue concept key(value)
	 * @param periodFrom the search is done by the period value, it helps to search from previous years
	 * @param periodTo
	 */
	public double getConcept (String conceptValue, int periodFrom, int periodTo); // getConcept

	/**
	 *  Helper Method : Concept by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 * @param payrollValue is the value of the payroll.
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 */
	public double getConcept(String conceptValue, String payrollValue, int periodFrom, int periodTo);

	/**
	 *  Helper Method : Concept by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 *  @param payrollValue is the value of the payroll.
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 * @param includeInProcess
	 */
	public double getConcept(String conceptValue, String payrollValue, int periodFrom, int periodTo, boolean includeInProcess); // getConcept

	/**
	 *  Helper Method : Concept Average by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 * @param payrollValue is the value of the payroll.
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 */
	public double getConceptAvg(String conceptValue, String payrollValue, int periodFrom, int periodTo);

	/**
	 *  Helper Method : Concept Average by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 * @param payrollValue is the value of the payroll.
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 * @param includeInProcess
	 */
	public double getConceptAvg(String conceptValue, String payrollValue, int periodFrom, int periodTo, boolean includeInProcess); // getConcept
	
	/**
	 * Get last movement based on payroll and concept, note that if payrollvalue is null then is used current payroll value
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @param isWithValidFrom
	 * @return
	 */
	public MHRMovement getLastMovement(String conceptValue, String payrollValue, Timestamp breakDate, boolean isWithValidFrom);
	
	/**
	 * Get last concept without break date
	 * @param conceptValue
	 * @param payrollValue
	 * @return
	 */
	public double getLastConcept(String conceptValue, String payrollValue);
	
	/**
	 * Get Last concept amount with break date
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public double getLastConcept(String conceptValue, String payrollValue, Timestamp breakDate);
	
	/**
	 * Get Last concept for a engineEmployee.getEmployee()
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public double getLastConcept(String conceptValue, String payrollValue, Timestamp breakDate, boolean isWithValidFrom); // getConcept
	
	/**
	 * Get last concept date without break date
	 * @param conceptValue
	 * @param payrollValue
	 * @return
	 */
	public Timestamp getLastConceptDate(String conceptValue, String payrollValue);
	
	/**
	 * Get Last concept Date with break date
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public Timestamp getLastConceptDate(String conceptValue, String payrollValue, Timestamp breakDate);
	
	/**
	 * Get Last concept date for a engineEmployee.getEmployee()
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public Timestamp getLastConceptDate(String conceptValue, String payrollValue, Timestamp breakDate, boolean isWithValidFrom); // getConcept
	
	/**
	 * Get last concept String without break date
	 * @param conceptValue
	 * @param payrollValue
	 * @return
	 */
	public String getLastConceptString(String conceptValue, String payrollValue);
	
	/**
	 * Get Last concept String with break date
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public String getLastConceptString(String conceptValue, String payrollValue, Timestamp breakDate);
	
	/**
	 * Get Last concept String for a engineEmployee.getEmployee()
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public String getLastConceptString(String conceptValue, String payrollValue, Timestamp breakDate, boolean isWithValidFrom); // getConcept
	
	/**
	 * Get last concept valid from without break date
	 * @param conceptValue
	 * @param payrollValue
	 * @return
	 */
	public Timestamp getLastConceptValidFrom(String conceptValue, String payrollValue);
	
	/**
	 * Get Last concept valid from with break date
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public Timestamp getLastConceptValidFrom(String conceptValue, String payrollValue, Timestamp breakDate);
	
	/**
	 * Get Last concept Valid From for a engineEmployee.getEmployee()
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public Timestamp getLastConceptValidFrom(String conceptValue, String payrollValue, Timestamp breakDate, boolean isWithValidFrom); // getConcept
	
	/**
	 * Get last concept valid to without break date
	 * @param conceptValue
	 * @param payrollValue
	 * @return
	 */
	public Timestamp getLastConceptValidTo(String conceptValue, String payrollValue);
	
	/**
	 * Get Last concept valid to with break date
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public Timestamp getLastConceptValidTo(String conceptValue, String payrollValue, Timestamp breakDate);
	
	/**
	 * Get Last concept Valid To for a engineEmployee.getEmployee()
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public Timestamp getLastConceptValidTo(String conceptValue, String payrollValue, Timestamp breakDate, boolean isWithValidFrom); // getConcept
	
	/**
	 * Get Concept for this payroll for range dates
	 * @param conceptValue
	 * @param from
	 * @param to
	 * @return
	 */
	public double getConcept (String conceptValue ,Timestamp from,Timestamp to);

	/**
	 * Helper Method: gets Concept value of a payrroll between 2 dates
	 * @param conceptValue
	 * @param payrollValue
	 * @param from
	 * @param to
	 * */
	public double getConcept(String conceptValue, String payrollValue, Timestamp from, Timestamp to);

	/**
	 * Helper Method: gets Concept value of a payrroll between 2 dates
	 * @param conceptValue
	 * @param payrollValue
	 * @param from
	 * @param to
	 * @param includeInProcess
	 * */
	public double getConcept(String conceptValue, String payrollValue, Timestamp from, Timestamp to, boolean includeInProcess); // getConcept

	/**
	 * Helper Method: gets Concept Average value of a payrroll between 2 dates
	 * @param conceptValue
	 * @param payrollValue
	 * @param from
	 * @param to
	 * @return
	 */
	public double getConceptAvg(String conceptValue, String payrollValue, Timestamp from, Timestamp to);

	/**
	 * Helper Method: gets Concept Average value of a payrroll between 2 dates
	 * @param conceptValue
	 * @param payrollValue
	 * @param from
	 * @param to
	 * @param includeInProcess
	 * */
	public double getConceptAvg(String conceptValue, String payrollValue, Timestamp from, Timestamp to, boolean includeInProcess); // getConcept
	
	/**
	 * Helper Method : Get AttributeInvoice 
	 * @param conceptValue - Value to Concept
	 * @return	C_Invoice_ID, 0 if does't
	 */ 
	public int getAttributeInvoice (String conceptValue); // getAttributeInvoice
		
	/**
	 * Helper Method : Get AttributeDocType
	 * @param conceptValue - Value to Concept
	 * @return	C_DocType_ID, 0 if does't
	 */ 
	public int getAttributeDocType (String conceptValue); // getAttributeDocType

	/**
	 * Get attribute by engineEmployee.getEmployee()
	 * @param conceptValue
	 * @param engineEmployee.getBusinessPartnerId()
	 * @return
	 */
	public BigDecimal getAttributeByPartnerId(String conceptValue, Timestamp breakDate);

	/**
	 * Helper Method : get days from specific period
	 * @param period
	 * @return no. of days
	 */
	public double getDays (int period); // getDays
	
	/**********************************************************************************
	 * Helper Method for Get Amount from commission                                   *
	 **********************************************************************************/
	
	/**
	 * Get Commission of Employee sales representative from Commission Run
	 * @param bPartnerId
	 * @param from
	 * @param to
	 * @param docBasisType
	 * @return
	 */
	public double getCommissionAmt(int bPartnerId, Timestamp from, Timestamp to, String docBasisType);
	
	/**
	 * Get commission amount for current engineEmployee.getEmployee()
	 * @param from
	 * @param to
	 * @param docBasisType
	 * @return
	 */
	public double getCommissionAmt(Timestamp from, Timestamp to, String docBasisType);
	
	/**
	 * Get commission amount for current engineEmployee.getEmployee() on current period with a doc basis type
	 * @param docBasisType
	 * @return
	 */
	public double getCommissionAmt(String docBasisType);
	
	/**
	 * Get commission amount for current engineEmployee.getEmployee() and current period and all doc basis type
	 * @return
	 */
	public double getCommissionAmt();
	
	/**********************************************************************************
	 * Helper Method for Get Amount from time and attendance record                   *
	 **********************************************************************************/
	
	/**
	 * Helper Method : Concept by range from-to a sum of incidence
	 * @param conceptValue
	 * @param workShiftValue
	 * @param from
	 * @param to
	 * @return
	 */
	public double getIncidenceSum(String conceptValue, String workShiftValue, Timestamp from, Timestamp to); // getIncidence
	
	/**
	 * Helper Method : Concept by range from-to a sum of incidence
	 * @param conceptValue
	 * @param from
	 * @param to
	 * @return
	 */
	public double getIncidenceSum(String conceptValue, Timestamp from, Timestamp to); // getIncidence
}
