/**
 * 
 */
package bean;

import java.util.List;
import java.util.Map;

/**
 * @author zuokang.li
 *
 */
public class TranslationItem {

	private String RefName;
	private String stringID;
	private Map<String, String> descriptions;
	private List<TranslateUnit> contents;
	public String getRefName() {
		return RefName;
	}
	public void setRefName(String refName) {
		RefName = refName;
	}
	public String getStringID() {
		return stringID;
	}
	public void setStringID(String stringID) {
		this.stringID = stringID;
	}
	public Map<String, String> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(Map<String, String> descriptions) {
		this.descriptions = descriptions;
	}
	public List<TranslateUnit> getContents() {
		return contents;
	}
	public void setContents(List<TranslateUnit> contents) {
		this.contents = contents;
	}
	
	
	
}
