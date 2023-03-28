package Harjoitustyo.Yrityspeli.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Data {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Min(value = 1900, message = "min value is 1900")
	@Max(value = 2024, message = "max value is 2024")
	private int submittedYear;
	private double turnover, ebitda, ebit, currentAssets, accountsReceivable, financialAssets, totalAssets, shortTermLiabilities, shortTermPrepayments, longTermLiabilities;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "companyid")
	private Company company;
	
	public Data() {
		super();
	}

	public Data(@Min(value = 1900, message = "min value is 1900") @Max(value = 2024, message = "max value is 2024") int submittedYear,
			double turnover, double ebitda, double ebit, double currentAssets, double accountsReceivable,
			double financialAssets, double totalAssets, double shortTermLiabilities, double shortTermPrepayments,
			double longTermLiabilities, Company company) {
		super();
		this.submittedYear = submittedYear;
		this.turnover = turnover;
		this.ebitda = ebitda;
		this.ebit = ebit;
		this.currentAssets = currentAssets;
		this.accountsReceivable = accountsReceivable;
		this.financialAssets = financialAssets;
		this.totalAssets = totalAssets;
		this.shortTermLiabilities = shortTermLiabilities;
		this.shortTermPrepayments = shortTermPrepayments;
		this.longTermLiabilities = longTermLiabilities;
		this.company = company;
	}

	public Data(@Min(value = 1900, message = "min value is 1900") @Max(value = 2024, message = "max value is 2024") int submittedYear,
			double turnover, double ebitda, double ebit, double currentAssets, double accountsReceivable,
			double financialAssets, double totalAssets, double shortTermLiabilities, double shortTermPrepayments,
			double longTermLiabilities) {
		super();
		this.submittedYear = submittedYear;
		this.turnover = turnover;
		this.ebitda = ebitda;
		this.ebit = ebit;
		this.currentAssets = currentAssets;
		this.accountsReceivable = accountsReceivable;
		this.financialAssets = financialAssets;
		this.totalAssets = totalAssets;
		this.shortTermLiabilities = shortTermLiabilities;
		this.shortTermPrepayments = shortTermPrepayments;
		this.longTermLiabilities = longTermLiabilities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSubmittedYear() {
		return submittedYear;
	}

	public void setSubmittedYear(int submittedYear) {
		this.submittedYear = submittedYear;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public double getEbitda() {
		return ebitda;
	}

	public void setEbitda(double ebitda) {
		this.ebitda = ebitda;
	}

	public double getEbit() {
		return ebit;
	}

	public void setEbit(double ebit) {
		this.ebit = ebit;
	}

	public double getCurrentAssets() {
		return currentAssets;
	}

	public void setCurrentAssets(double currentAssets) {
		this.currentAssets = currentAssets;
	}

	public double getAccountsReceivable() {
		return accountsReceivable;
	}

	public void setAccountsReceivable(double accountsReceivable) {
		this.accountsReceivable = accountsReceivable;
	}

	public double getFinancialAssets() {
		return financialAssets;
	}

	public void setFinancialAssets(double financialAssets) {
		this.financialAssets = financialAssets;
	}

	public double getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public double getShortTermLiabilities() {
		return shortTermLiabilities;
	}

	public void setShortTermLiabilities(double shortTermLiabilities) {
		this.shortTermLiabilities = shortTermLiabilities;
	}

	public double getShortTermPrepayments() {
		return shortTermPrepayments;
	}

	public void setShortTermPrepayments(double shortTermPrepayments) {
		this.shortTermPrepayments = shortTermPrepayments;
	}

	public double getLongTermLiabilities() {
		return longTermLiabilities;
	}

	public void setLongTermLiabilities(double longTermLiabilities) {
		this.longTermLiabilities = longTermLiabilities;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", submittedYear=" + submittedYear + ", turnover=" + turnover + ", ebitda=" + ebitda
				+ ", ebit=" + ebit + ", currentAssets=" + currentAssets + ", accountsReceivable=" + accountsReceivable
				+ ", financialAssets=" + financialAssets + ", totalAssets=" + totalAssets + ", shortTermLiabilities="
				+ shortTermLiabilities + ", shortTermPrepayments=" + shortTermPrepayments + ", longTermLiabilities="
				+ longTermLiabilities + ", company=" + company + "]";
	}
	
	
	
}