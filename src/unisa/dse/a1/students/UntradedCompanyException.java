package unisa.dse.a1.students;

public class UntradedCompanyException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1168664186445634718L;

	public UntradedCompanyException(String companyCode)
	{
		super(companyCode + "is not a listed company on this exchange");
	}
}