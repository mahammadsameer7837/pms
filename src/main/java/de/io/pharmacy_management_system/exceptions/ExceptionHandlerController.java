package de.io.pharmacy_management_system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

 
@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(value=MedicineAlreadyFoundException.class)
	public ResponseEntity<Object> exceptionOne(MedicineAlreadyFoundException exception){
		
		return new ResponseEntity<>("Medicine already found",HttpStatus.ALREADY_REPORTED);
		
	} 

	@ExceptionHandler(value=MedicineNotFoundException.class)
	public ResponseEntity<Object> exceptionTwo(MedicineNotFoundException exception){
		
		return new ResponseEntity<>("Medicine not found",HttpStatus.NOT_FOUND);
		
	} 
	
	@ExceptionHandler(value=DiseaseAllreadyFoundException.class)
	public ResponseEntity<Object> exceptionThree(DiseaseAllreadyFoundException exception){
		
		return new ResponseEntity<>("Disease already found",HttpStatus.ALREADY_REPORTED);
		
	} 
	
	@ExceptionHandler(value=DiseaseNotFoundException.class)
	public ResponseEntity<Object> exceptionFour(DiseaseNotFoundException exception){
		
		return new ResponseEntity<>("Disease not found",HttpStatus.NOT_FOUND);
		
	} 
	@ExceptionHandler(value=PatientsLimitReachedException.class)
	public ResponseEntity<Object> exceptionFive(PatientsLimitReachedException exception){
		
		return new ResponseEntity<>("Patients Limit Reached",HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
		
	} 
	
	@ExceptionHandler(value=DoctorAlreadyExistWithIdTException.class)
	public ResponseEntity<Object> exceptionSix(DoctorAlreadyExistWithIdTException exception){
		
		return new ResponseEntity<>("Docotor Already Exist With IdT given",HttpStatus.ALREADY_REPORTED);
		
	} 
	
	@ExceptionHandler(value=DoctorNotFoundException.class)
	public ResponseEntity<Object> exceptionSeven(DoctorNotFoundException exception){
		
		return new ResponseEntity<>("Docotor not found",HttpStatus.NOT_FOUND);
		
	} 
	

	@ExceptionHandler(value=PatientNotFoundException.class)
	public ResponseEntity<Object> exceptionEight(PatientNotFoundException exception){
		
		return new ResponseEntity<>("Patient not found",HttpStatus.NOT_FOUND);
		
	} 
	
	@ExceptionHandler(value=DoctorIdMissingException.class)
	public ResponseEntity<Object> exceptionNine(DoctorIdMissingException exception){
		
		return new ResponseEntity<>("Doctor ID OR Doctor IDT is Required",HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(value=EmptyMedicineSetException.class)
	public ResponseEntity<Object> exceptionTen(EmptyMedicineSetException exception){
		
		return new ResponseEntity<>("Medicine Set or Medicine is missing",HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(value=NoMedicinesAssignedException.class)
	public ResponseEntity<Object> exceptionEleven(NoMedicinesAssignedException exception){
		
		return new ResponseEntity<>("No medicines assigned so far",HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value=MedicineNameTakenException.class)
	public ResponseEntity<Object> exceptionEleven(MedicineNameTakenException exception){
		
		return new ResponseEntity<>("Medicine with same name already exists",HttpStatus.ALREADY_REPORTED);
		
	}
	@ExceptionHandler(value=AddressCannotBeNullException.class)
	public ResponseEntity<Object> exceptionTwelve(AddressCannotBeNullException exception){
		
		return new ResponseEntity<>("Address can't be null for registration",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=AdmittedReasonCannotBeNullException.class)
	public ResponseEntity<Object> exceptionThirteen(AdmittedReasonCannotBeNullException exception){
		
		return new ResponseEntity<>("Admitted reason can't be null for registration",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidAddressException.class)
	public ResponseEntity<Object> exceptionFourteen(InvalidAddressException exception){
		
		return new ResponseEntity<>("Address can't be blank",HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=InvalidAdmittedReason.class)
	public ResponseEntity<Object> exceptionFifteen(InvalidAdmittedReason exception){
		
		return new ResponseEntity<>("Admitted reason can't be accepted because:\n"
				+ "1.Admitted reason can't be blank\n"
				+ "2.Starting should be digit or letter\n"
				+ "3.No special symbols allowed other than '.',','\n",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidBillAmount.class)
	public ResponseEntity<Object> exceptionSixteen(InvalidBillAmount exception){
		
		return new ResponseEntity<>("Bill can't be null or negative or zero",HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=InvalidDateOfDischarge.class)
	public ResponseEntity<Object> exceptionSeventeen(InvalidDateOfDischarge exception){
		
		return new ResponseEntity<>("Date of discharge is before addmission please check",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidDiseaseName.class)
	public ResponseEntity<Object> exceptionEighteen(InvalidDiseaseName exception){
		
		return new ResponseEntity<>("Disease name can't be accepted because:\n"
				+ "1.disease name should not be blank\n"
				+ "2.starting should be a letter\n"
				+ "3.No special symbols allowed except '-'",HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=InvalidDoctorId.class)
	public ResponseEntity<Object> exceptionNineteen(InvalidDoctorId exception){
		
		return new ResponseEntity<>("Doctor Id can't be negative",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidNameException.class)
	public ResponseEntity<Object> exceptionNineteen(InvalidNameException exception){
		
		return new ResponseEntity<>("Invalid name\n"
				+ "1. Name can't be blank\n"
				+ "2. no space at start\n"
				+ "3. only letters or whitespaces are allowed",HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value=NameCannotBeNullException.class)
	public ResponseEntity<Object> exceptionTwenty(NameCannotBeNullException exception){
		
		return new ResponseEntity<>("Name can't be null",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidDesignationException.class)
	public ResponseEntity<Object> exceptionTwentyOne(InvalidDesignationException exception){
		
		return new ResponseEntity<>("Designation name can't be accepted because:\n"
				+ "1.designation should not be blank\n"
				+ "2.starting should be a letter\n"
				+ "3.No special symbols allowed except '-'",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidSpecializationException.class)
	public ResponseEntity<Object> exceptionTwentyTwo(InvalidSpecializationException exception){
		
		return new ResponseEntity<>("Specilization can't be accepted because:\n"
				+ "1.Specilization should not be blank\n"
				+ "2.starting should be a letter\n"
				+ "3.No special symbols allowed except '-'",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=DesignationCannotBeNullException.class)
	public ResponseEntity<Object> exceptionTwentyThree(DesignationCannotBeNullException exception){
		
		return new ResponseEntity<>("Designation can't be null registration",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=SpecializationCannotBeNullException.class)
	public ResponseEntity<Object> exceptionTwentyFour(SpecializationCannotBeNullException exception){
		
		return new ResponseEntity<>("Specilization can't be null for registration",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidDoctorIdTException.class)
	public ResponseEntity<Object> exceptionTwentyFive(InvalidDoctorIdTException exception){
		
		return new ResponseEntity<>("DoctorIdT can't be accepted because:\n"
				+ "1.DoctorIdT should not be blank\n"
				+ "2.starting should be a letter or _\n"
				+ "3.No special symbols allowed except '_'",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=DoctorIdTCannotBeNullException.class)
	public ResponseEntity<Object> exceptionTwentyFour(DoctorIdTCannotBeNullException exception){
		
		return new ResponseEntity<>("DoctorIdT can't be null for registration",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidMedicineNameException.class)
	public ResponseEntity<Object> exceptionTwentySix(InvalidMedicineNameException exception){
		
		return new ResponseEntity<>("Medicine name can't be accepted because:\n"
				+ "1.medicine name should not be blank\n"
				+ "2.starting should be a letter\n"
				+ "3.No special symbols allowed except '-'",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=MedicineNameCannotBeNullException.class)
	public ResponseEntity<Object> exceptionTwentySeven(MedicineNameCannotBeNullException exception){
		
		return new ResponseEntity<>("Medicine name can't be null while adding",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=YearOfManufactureCannotBeNullExcpetion.class)
	public ResponseEntity<Object> exceptionTwentyEight(YearOfManufactureCannotBeNullExcpetion exception){
		
		return new ResponseEntity<>("Year of manufacture and expairy can't be null while adding",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=MedicineExpiredException.class)
	public ResponseEntity<Object> exceptionTwentyEight(MedicineExpiredException exception){
		
		return new ResponseEntity<>("Medicine expired",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidMedicineCost.class)
	public ResponseEntity<Object> exceptionTwentyNine(InvalidMedicineCost exception){
		
		return new ResponseEntity<>("Medicine cost can't be negative",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=MedicineCostCannotBeNullException.class)
	public ResponseEntity<Object> exceptionTwentyNine(MedicineCostCannotBeNullException exception){
		
		return new ResponseEntity<>("Medicine cost can't be null",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=ManufacturedCompanyNameCannotBeNullException.class)
	public ResponseEntity<Object> exceptionTwentyNine(ManufacturedCompanyNameCannotBeNullException exception){
		
		return new ResponseEntity<>("Manufactured Company Name Cannot Be Null",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=DiseaseNameNullException.class)
	public ResponseEntity<Object> exceptionTwentyNine(DiseaseNameNullException exception){
		
		return new ResponseEntity<>("Disease Name Cannot Be Null",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidManufacturedCompanyName.class)
	public ResponseEntity<Object> exceptionTwentyNine(InvalidManufacturedCompanyName exception){
		
		return new ResponseEntity<>("Manufactured Company Name Cannot Be Blank",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidDateOfManufacture.class)
	public ResponseEntity<Object> thrity(InvalidDateOfManufacture exception){
		
		return new ResponseEntity<>("Date of manufacture is beofre today's date",HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InvalidDateOfExpairy.class)
	public ResponseEntity<Object> thrityOne(InvalidDateOfExpairy exception){
		
		return new ResponseEntity<>("date of expiry is invalid. "
				+ "The date of expiry is before manufactured date",HttpStatus.BAD_REQUEST);
		
	}
	

	
	
}
