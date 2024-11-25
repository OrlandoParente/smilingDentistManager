package sdms.service;

import java.util.List;

import sdms.model.DentalMaterial;

public interface DentalMaterialServiceInterface {
	
	// CREATE ----------------------------------------------------
	public void postDentalMaterial( DentalMaterial dentalMaterial );
	
	// READ ------------------------------------------------------
	public DentalMaterial getDentalMaterialById( long id );
	public List<DentalMaterial> getDentalMaterials();
	
	// UPDATE ----------------------------------------------------
	public void putDentalMaterial( DentalMaterial dentalMaterial );
	
	// DELETE ----------------------------------------------------
	public void deleteDentalMaterial( long id );
	
}
