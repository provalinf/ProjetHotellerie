package m2.info.services.module;

import m2.info.models.Module;

public interface IModuleManagement {

	void saveModule(Module module);
	boolean deleteModule(long id);
	Iterable<Module> getAllModules();
	Module getModule(long id);

}
