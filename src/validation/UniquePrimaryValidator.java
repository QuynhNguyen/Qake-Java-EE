package validation;

import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import service.SignUpServiceRemote;

public class UniquePrimaryValidator implements
ConstraintValidator<UniquePrimary, String> {
	
	@EJB(mappedName="ejb/SignUp") 
	SignUpServiceRemote service;

	@Override
	public void initialize(UniquePrimary constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value.isEmpty())
			return true;
		
		
		if(service.isEmailUnique(value))
			return false;
		
		return true;
		 
	}

}
