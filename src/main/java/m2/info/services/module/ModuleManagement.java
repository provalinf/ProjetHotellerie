package m2.info.services.module;

import m2.info.models.Module;
import m2.info.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleManagement implements IModuleManagement {

	@Autowired private ModuleRepository repository;

	@Override
	public Iterable<Module> getAllModules() {
		return repository.findAll();
	}

	@Override
	public Module addModule(Module module) {
		return repository.save(module);
	}

	@Override
	public Module getModule(Long id) {
		return repository.findOne(id);
	}
}
