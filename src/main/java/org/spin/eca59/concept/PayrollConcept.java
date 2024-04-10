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
package org.spin.eca59.concept;

import org.adempiere.exceptions.AdempiereException;
import org.eevolution.hr.model.MHRConcept;

/**
 * 	Concept Definition
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class PayrollConcept {
	private int id;
	private String value;
	private String name;
	private String description;
	private String help;
	private String type;
	private String columnType;
	private String accountSign;
	private boolean isManual;
	private boolean isPaid;
	private boolean isSaveInHistoric;
	private boolean isNotSaveInHistoryIfNull;
	private boolean isEmployee;
	private boolean isInvoiced;
	private boolean isPrinted;
	private int categoryId;
	private int typeId;
	private int sequence;
	private int precision;
	private MHRConcept concept;
	
	public static PayrollConcept newInstance(MHRConcept sourceConcept) {
		return new PayrollConcept(sourceConcept);
	}
	
	private PayrollConcept(MHRConcept concept) {
		if(concept == null) {
			throw new AdempiereException("@HR_Concept_ID@ @IsMandatory@");
		}
		this.concept = concept;
		id = concept.getHR_Concept_ID();
		value = concept.getValue();
		name = concept.getName();
		description = concept.getDescription();
		help = concept.getHelp();
		type = concept.getType();
		columnType = concept.getColumnType();
		accountSign = concept.getAccountSign();
		isManual = concept.isManual();
		isEmployee = concept.isEmployee();
		isInvoiced = concept.isInvoiced();
		isPrinted = concept.isPrinted();
		isPaid = concept.isPaid();
		isSaveInHistoric = concept.isSaveInHistoric();
		isNotSaveInHistoryIfNull = concept.isNotSaveInHistoryIfNull();
		categoryId = concept.getHR_Concept_Category_ID();
		typeId = concept.getHR_Concept_Type_ID();
		sequence = concept.getSeqNo();
		precision = concept.getStdPrecision();
	}

	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getHelp() {
		return help;
	}

	public String getType() {
		return type;
	}

	public String getColumnType() {
		return columnType;
	}

	public String getAccountSign() {
		return accountSign;
	}

	public boolean isManual() {
		return isManual;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public boolean isInvoiced() {
		return isInvoiced;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public int getTypeId() {
		return typeId;
	}

	public boolean isPrinted() {
		return isPrinted;
	}

	public int getSequence() {
		return sequence;
	}

	public MHRConcept getConcept() {
		return concept;
	}

	public int getPrecision() {
		return precision;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public boolean isSaveInHistoric() {
		return isSaveInHistoric;
	}

	public boolean isNotSaveInHistoryIfNull() {
		return isNotSaveInHistoryIfNull;
	}

	@Override
	public String toString() {
		return "PayrollConcept [id=" + id + ", value=" + value + ", name=" + name + "]";
	}
}
