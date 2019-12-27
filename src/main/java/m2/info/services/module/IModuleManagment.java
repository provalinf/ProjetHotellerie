package m2.info.services.module;

import m2.info.models.Module;

public interface IModuleManagment {

	Iterable<Module> getAllModules();
	Module addModule(String label, String verboseName, String def);
	Module getModule(String id);

}
