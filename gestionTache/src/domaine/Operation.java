package domaine;

import java.util.Random;

public class Operation {
	
	private int idOperation;
	private String operation;
	private String dateoperation;
	private int idMembre;
	
	public int getIdOperation() {
		return idOperation;
	}
	public void setIdOperation(int idOperation) {
		this.idOperation = idOperation;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getDateoperation() {
		return dateoperation;
	}
	public void setDateoperation(String dateoperation) {
		this.dateoperation = dateoperation;
	}
	public int getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	public Operation() {
		super();
	}
	
	public int generateId(){
		Random random = new Random();
		int index = random.nextInt(10);
		return index;
	}
	
	
	
	public Operation(int idOperation, String operation, String dateoperation, int idMembre) {
		super();
		this.idOperation = idOperation;
		this.operation = operation;
		this.dateoperation = dateoperation;
		this.idMembre = idMembre;
	}
	public Operation( String operation, String dateoperation, int idMembre) {
		super();
		this.idOperation=generateId();
		this.operation = operation;
		this.dateoperation = dateoperation;
		this.idMembre = idMembre;
	}
	
	
}
