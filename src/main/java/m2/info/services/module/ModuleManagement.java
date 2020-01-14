package m2.info.services.module;

import m2.info.models.Module;
import m2.info.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class ModuleManagement implements IModuleManagement {

	@Autowired private ModuleRepository repository;

	@Override
	public void saveModule(Module module) { repository.save(module); }

	@Override
	public boolean deleteModule(long id) {
		if (repository.exists(id)) {
			repository.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public Module getModule(long id) {
		return repository.findOne(id);
	}

	@Override
	public Iterable<Module> getAllModules() {
		return repository.findAll();
	}
}
