package com.revature.models;

import java.sql.Date;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {

	private int id;
	private Status status;
	private double amount;
	private User author;
	private User resolver;
	private Date date_submitted;
	private Date date_resolved;
	private String description;
	private String type;
	private int status_fkey;
	private int user_fkey_resolved;
	
    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    
	public Reimbursement() {
		super();
	};
	
//	public Reimbursement(int id, String type, Status status, double amount) {
//		// TODO Auto-generated constructor stub
//		super();
//		this.id = id;
//		this.type = type;
//		this.status = status;
//		this.amount = amount;
//	
//	}
	
	public Reimbursement(int id, int status_fkey, int user_fkey_resolved, Date date_resolved) {
		super();
		this.id = id;
		this.status_fkey = status_fkey;
		this.user_fkey_resolved = user_fkey_resolved;
		this.date_resolved = date_resolved;
		
	}
 

	public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        super(id, status, author, resolver, amount);
    }
  
    //method overloading changing the parameters; this one is for user when they submit request
    public Reimbursement(int id, Status status, User author, Date date_submitted, double amount, String description) {
    	super();
    	
    	
    	
    	
    }
    public Reimbursement(int id, Status status, User author, Date date_submitted, double amount) {
    	super();
    	this.id = id;
    	this.status = status;
    	this.author = author;
    	this.date_submitted = date_submitted;
    	this.amount = amount;
    	
    }
    //method overloading changing parameters for manager roles
//    public Reimbursement(int id, Status status, User resolver, Date date_resolved, double amount) {
//    	
//    }

    public User getResolver() {
 		return resolver;
 	}

 	public void setResolver(User resolver) {
 		this.resolver = resolver;
 	}

 	public Date getDate_submitted() {
 		return date_submitted;
 	}

 	public void setDate_submitted(Date date_submitted) {
 		this.date_submitted = date_submitted;
 	}

 	public Date getDate_resolved() {
 		return date_resolved;
 	}

 	public void setDate_resolved(Date date_resolved) {
 		this.date_resolved = date_resolved;
 	}

 	public String getDescription() {
 		return description;
 	}

 	public void setDescription(String description) {
 		this.description = description;
 	}

 	public int getStatusFkey() {
 		return status_fkey;
 	}

 	public void setStatusFkey(int status_fkey) {
 		this.status_fkey = status_fkey;
 	}

 	public int getUserFkeyResolved() {
 		return user_fkey_resolved;
 	}
 	
	public void setUserFkeyResolved(int user_fkey_resolved) {
		this.user_fkey_resolved = user_fkey_resolved;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((date_resolved == null) ? 0 : date_resolved.hashCode());
		result = prime * result + ((date_submitted == null) ? 0 : date_submitted.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + status_fkey;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + user_fkey_resolved;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (date_resolved == null) {
			if (other.date_resolved != null)
				return false;
		} else if (!date_resolved.equals(other.date_resolved))
			return false;
		if (date_submitted == null) {
			if (other.date_submitted != null)
				return false;
		} else if (!date_submitted.equals(other.date_submitted))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (status != other.status)
			return false;
		if (status_fkey != other.status_fkey)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (user_fkey_resolved != other.user_fkey_resolved)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", " + (status != null ? "status=" + status + ", " : "") + "amount=" + amount
				+ ", " + (author != null ? "author=" + author + ", " : "")
				+ (resolver != null ? "resolver=" + resolver + ", " : "")
				+ (date_submitted != null ? "date_submitted=" + date_submitted + ", " : "")
				+ (date_resolved != null ? "date_resolved=" + date_resolved + ", " : "")
				+ (description != null ? "description=" + description +", ": "")
				+ (type != null ? "type=" + type + ", ": "") + " ]";
	}



  

}
