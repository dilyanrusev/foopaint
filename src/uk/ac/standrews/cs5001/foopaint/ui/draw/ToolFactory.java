package uk.ac.standrews.cs5001.foopaint.ui.draw;

import java.util.Hashtable;
import java.util.Map;

import uk.ac.standrews.cs5001.foopaint.ui.draw.impl.*;

public class ToolFactory {
	private static Map<ToolIDs, Class<?>> tools;
	private static Map<ToolIDs, Renderer> renderers;
	
	private ToolFactory() {}
	
	static {
		tools = new Hashtable<ToolIDs, Class<?>>();
		renderers = new Hashtable<ToolIDs, Renderer>();
		
		register(ToolIDs.DRAW_LINE, LineTool.class, new LineRenderer());
		register(ToolIDs.DRAW_RECTANGLE, RectangleTool.class, new RectangleRenderer());
		register(ToolIDs.DRAW_SOLID_RECTANGLE, SolidRectangleTool.class, new SolidRectangleRenderer());
		register(ToolIDs.DRAW_ELLIPSE, EllipseTool.class, new EllipseRenderer());
		register(ToolIDs.DRAW_SOLID_ELLIPSE, SolidEllipseTool.class, new SolidEllipseRenderer());
	}
	
	public static void register(ToolIDs id, Class<?> tool, Renderer renderer) {
		tools.put(id, tool);
		renderers.put(id, renderer);
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
	
	public static Renderer getRendererByID(ToolIDs id) {
		return renderers.get(id);
	}
}
