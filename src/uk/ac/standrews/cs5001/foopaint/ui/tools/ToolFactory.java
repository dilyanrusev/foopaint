package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.util.Hashtable;
import java.util.Map;

import uk.ac.standrews.cs5001.foopaint.ui.ToolIDs;

public class ToolFactory {
	private static Map<ToolIDs, Class<?>> tools;
	
	private ToolFactory() {}
	
	static {
		tools = new Hashtable<ToolIDs, Class<?>>();
		
		register(ToolIDs.DRAW_LINE, LineTool.class);
		register(ToolIDs.DRAW_RECTANGLE, RectangleTool.class);
		register(ToolIDs.DRAW_SOLID_RECTANGLE, SolidRectangleTool.class);
		register(ToolIDs.DRAW_ELLIPSE, EllipseTool.class);
		register(ToolIDs.DRAW_SOLID_ELLIPSE, SolidEllipseTool.class);
	}
	
	public static void register(ToolIDs id, Class<?> tool) {
		tools.put(id, tool);		
	}
	
	public static Tool getToolByID(ToolIDs id) {
		Class<?> type = tools.get(id);
		if (type != null) {
			try {
				Tool instance = (Tool) type.newInstance();
				return instance;
			} catch (InstantiationException e) {				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
