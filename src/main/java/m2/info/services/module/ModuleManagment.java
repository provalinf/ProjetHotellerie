package m2.info.services.module;

import m2.info.models.Module;
import m2.info.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleManagment implements IModuleManagment {

	@Autowired private ModuleRepository moduleRepository;

	@Override
	public Iterable<Module> getAllModules() {
		return moduleRepository.findAll();
	}

	@Override
	public Module addModule(String verboseName, String label, String description) {
		return moduleRepository.save(new Module(verboseName, label, description));
	}

	@Override
	public Module getModule(String id) {
		return moduleRepository.findOne(id);
	}
}
