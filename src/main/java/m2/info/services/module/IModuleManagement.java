package m2.info.services.module;

import m2.info.models.Module;

public interface IModuleManagement {

	Iterable<Module> getAllModules();
	boolean addModule(Module module);
	Module getModule(long id);
	boolean deleteModule(long id);

}
