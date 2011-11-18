package uk.ac.standrews.cs5001.foopaint.ui.tools;

import java.util.Hashtable;
import java.util.Map;

import uk.ac.standrews.cs5001.foopaint.data.DrawableItem;
import uk.ac.standrews.cs5001.foopaint.ui.ToolIDs;

/**
 * Factory for obtaining Tool-s
 * @author <110017972>
 *
 */
public class ToolFactory {
	/** Stores Tool implementations identified by ID */
	private static Map<ToolIDs, Class<?>> tools;
	/** Stores IDs identified by Model.Class */
	private static Map<Class<?>, ToolIDs> modelToolMap;
	
	/** Should not be instantiated in the outside world */
	private ToolFactory() {}
	
	/** Fill-up the containers */
	static {
		tools = new Hashtable<ToolIDs, Class<?>>();
		modelToolMap = new Hashtable<Class<?>, ToolIDs>();
		
		register(ToolIDs.DRAW_LINE, LineTool.class);
		register(ToolIDs.DRAW_RECTANGLE, RectangleTool.class);
		register(ToolIDs.DRAW_SOLID_RECTANGLE, SolidRectangleTool.class);
		register(ToolIDs.DRAW_ELLIPSE, EllipseTool.class);
		register(ToolIDs.DRAW_SOLID_ELLIPSE, SolidEllipseTool.class);
	}
	
	/** Register a tool implementation */
	public static void register(ToolIDs id, Class<?> tool) {
		tools.put(id, tool);
		if (id.getModelType() != null) {
			modelToolMap.put(id.getModelType(), id);
		}
	}
	
	/** 
	 * Resolve a specific Tool implementation by ID
	 * @param id Required UI functionality identification
	 * @return Tool implementation or null
	 */
	public static Tool getToolByID(ToolIDs id) {
		if (id != null) {
			Class<?> type = tools.get(id);
			if (type != null) {
				return getToolByType(type);
			}
		}
		
		return null;
	}
	
	/**
	 * Resolve a specific Tool implementation that can draw a particular Model
	 * @param modelType Model.Class
	 * @return Matching Tool implementation or null
	 */
	public static Tool getToolByModelType(Class<?> modelType) {
		if (modelType != null) {
			ToolIDs toolID = modelToolMap.get(modelType);
			if (toolID != null) {
				return getToolByID(toolID);	
			}
		}
		return null;
	}
	
	/**
	 * Resolve and update a Tool implementation that can draw a particular Model
	 * @param drawable Instance of a shape that needs to be drawn
	 * @return Instance of a matching tool implementation that is initialised by the Model, or null
	 */
	public static Tool getToolByModel(DrawableItem drawable) {
		if (drawable != null) {
			Tool tool = getToolByModelType(drawable.getClass());
			if (tool != null) {
				tool.update(drawable);
			}
		}
		return null;
	}
	
	/**
	 * Create a new instance of a tool by its Class
	 * @param type Tool.Class
	 * @return Instance of the class, or null on error
	 */
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
