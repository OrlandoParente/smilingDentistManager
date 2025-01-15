package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.DentalMaterial;
import sdms.repository.DentalMaterialRepository;

@Service
public class DentalMaterialService implements DentalMaterialServiceInterface {

	@Autowired
	DentalMaterialRepository repository;
	
	@Override
	public DentalMaterial getDentalMaterialById(long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<DentalMaterial> getDentalMaterials() {
		
		return repository.findAll();
	}

	@Override
	public void postDentalMaterial(DentalMaterial dentalMaterial) {
		
		repository.save(dentalMaterial);
	}

	
	@Override
	public void putDentalMaterial(DentalMaterial dentalMaterial) {
		
		repository.save(dentalMaterial);
	}

	@Override
	public void deleteDentalMaterial(long id) {
	
		repository.delete( repository.findById(id).get() );
	}

	@Override
	public void increaseDentalMaterialQuantity(long idDentalMaterial, double quantity) {
		
		DentalMaterial dentalMaterial = repository.findById(idDentalMaterial).get();
		
		double newQuantity = dentalMaterial.getQuantity() + quantity;
		dentalMaterial.setQuantity(newQuantity);
		
		this.putDentalMaterial(dentalMaterial);
	}

	@Override
	public void decreaseDentalMaterialQuantity(long idDentalMaterial, double quantity) {
		
		DentalMaterial dentalMaterial = repository.findById(idDentalMaterial).get();
		
		double newQuantity = dentalMaterial.getQuantity() - quantity;
		dentalMaterial.setQuantity(newQuantity);
		
		this.putDentalMaterial(dentalMaterial);
		
	}

	
}
