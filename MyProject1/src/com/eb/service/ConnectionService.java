package com.eb.service;
import com.eb.entity.Commercial;
import com.eb.entity.Domestic;

import com.eb.exception.invalidConnectionException;
import com.eb.exception.invalidReadingException;

public class ConnectionService {
        public boolean validate(int currentReading, int previousReading, String type)throws 
invalidReadingException, invalidConnectionException{
	    if(currentReading < previousReading || currentReading < 0 || previousReading < 0) {
		       invalidReadingException e=new invalidReadingException();
		       throw e;
    }
	   else if(!type.equals("Domestic") && !type.equals("Commercial")) {
		       invalidConnectionException e=new invalidConnectionException();
		       throw e;
    }
	 else {
		 return true;
    }
        }
public float calculateBillAmt(int currentReading, int previousReading, String type) {
	try {
	if(validate( currentReading,previousReading,type)) {
		if(type.equals("Domestic")) 
		{
			float slabs[] = {2.30f,4.20f,5.50f};
			Domestic d=new Domestic(currentReading,previousReading,slabs);
			return d.computeBill();
		}
		else {
			float slabs[] = {5.20f,6.80f,8.30f};
			Commercial c=new Commercial(currentReading,previousReading,slabs);
			return c.computeBill();
		}
	}
	}
	catch(invalidReadingException e) {
		return 1;
	}
	catch(invalidConnectionException e) {
		return 2;
	}
	return 0;
}
	public String generateBill(int currentReading, int previousReading,String type) {
		if(calculateBillAmt(currentReading,previousReading,type)==1) {
			return "Incorrect Reading";
			}
		else if(calculateBillAmt(currentReading,previousReading,type)==2) {
			return "Invalid Connection";
		}
		else {
			String s=String.valueOf(calculateBillAmt(currentReading,previousReading,type));
			return s;
		}
	}

}