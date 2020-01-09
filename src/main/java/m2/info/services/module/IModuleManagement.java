package m2.info.services.module;

import m2.info.models.Module;

public interface IModuleManagement {

	Iterable<Module> getAllModules();
	Module addModule(Module module);
	Module getModule(Long id);

}
