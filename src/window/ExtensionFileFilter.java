package window;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter {

	public void addExtension(String ext) {
		if(!ext.startsWith("."))
			extensions.add("." + ext);
		else 
			extensions.add(ext);
	}
	
	public boolean accept(File f) {
		if(f.isDirectory()) return true;
		
		for(String ext : extensions) {
			if(f.getName().toLowerCase().endsWith(ext))
				return true;
		}
		
		return false;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	private String description;
	private ArrayList<String> extensions = new ArrayList<String>();
}
