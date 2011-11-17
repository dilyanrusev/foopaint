package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.util.Hashtable;
import java.util.Map;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.ui.ToolIDs;

public class ToolFactory {
	private static Map<ToolIDs, Class<?>> tools;
	private static Map<Class<?>, ToolIDs> modelToolMap;
	
	private ToolFactory() {}
	
	static {
		tools = new Hashtable<ToolIDs, Class<?>>();
		modelToolMap = new Hashtable<Class<?>, ToolIDs>();
		
		register(ToolIDs.DRAW_LINE, LineTool.class);
		register(ToolIDs.DRAW_RECTANGLE, RectangleTool.class);
		register(ToolIDs.DRAW_SOLID_RECTANGLE, SolidRectangleTool.class);
		register(ToolIDs.DRAW_ELLIPSE, EllipseTool.class);
		register(ToolIDs.DRAW_SOLID_ELLIPSE, SolidEllipseTool.class);
	}
	
	public static void register(ToolIDs id, Class<?> tool) {
		tools.put(id, tool);
		if (id.getModelType() != null) {
			modelToolMap.put(id.getModelType(), id);
		}
	}
	
	public static Tool getToolByID(ToolIDs id) {
		if (id != null) {
			Class<?> type = tools.get(id);
			if (type != null) {
				return getToolByType(type);
			}
		}
		
		return null;
	}
	
	public static Tool getToolByModelType(Class<?> modelType) {
		if (modelType != null) {
			ToolIDs toolID = modelToolMap.get(modelType);
			if (toolID != null) {
				return getToolByID(toolID);	
			}
		}
		return null;
	}
	
	public static Tool getToolByModel(DrawableItem drawable) {
		if (drawable != null) {
			Tool tool = getToolByModelType(drawable.getClass());
			if (tool != null) {
				tool.update(drawable);
			}
		}
		return null;
	}
	
	private static Tool getToolByType(Class<?> type) {
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
